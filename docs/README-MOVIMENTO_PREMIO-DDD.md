# MOVIMENTO_PREMIO - Domain-Driven Design (DDD)

## Visão Geral

Este documento descreve a arquitetura DDD (Domain-Driven Design) aplicada ao domínio de **MOVIMENTO_PREMIO** 
do Sistema de Registro de Operações (SRO) versão 2.0.0 da SUSEP.

---

## Índice

1. [Contexto Delimitado (Bounded Context)](#contexto-delimitado)
2. [Agregados (Aggregates)](#agregados)
3. [Entidades e Value Objects](#entidades-e-value-objects)
4. [Invariantes de Negócio](#invariantes-de-negocio)
5. [Linguagem Ubíqua](#linguagem-ubiqua)
6. [Relacionamento com outros BCs](#relacionamento-com-outros-bcs)
7. [Eventos de Domínio](#eventos-de-dominio)

---

## Contexto Delimitado

### Bounded Context: MovimentoPremio

**Responsabilidade:** Gerenciar todas as movimentações financeiras relacionadas a prêmios de seguros.

**Escopo:**
- Registro de emissões de prêmios
- Cancelamentos e estornos
- Pagamentos e recebimentos
- Restituições ao segurado
- Cálculo de prêmio ganho e não ganho
- Controle de IOF e custos

**Fronteiras:**
- **Upstream:** Recebe eventos de DOCUMENTO e ENDOSSO
- **Downstream:** Alimenta sistemas de contabilidade e faturamento
- **Colaboração:** Consome dados de documentos e endossos

---

## Agregados

### Aggregate Root: MovimentoPremio

**Classe:** `io.github.wesleyosantos91.susep.sro.model.movimentopremio.MovimentoPremio`

**Responsabilidade:** Garantir consistência de cada movimentação financeira.

#### Estrutura do Agregado

```
MovimentoPremio (Aggregate Root)
├── Identificação
│   ├── uuid
│   ├── documentoCodigo
│   └── endossoCodigo
├── Temporal
│   ├── dataRegistro
│   ├── dataInicio
│   ├── dataTermino
│   └── dataMovimento
└── Financeiro
    ├── premioLiquido
    ├── iof
    ├── custoApolice
    ├── premioTotal
    ├── premioGanho
    ├── premioNaoGanho
    └── valorRestituicao
```

#### Identificador

- **Campo:** `uuid` (String)
- **Características:** Único, imutável, obrigatório
- **Formato:** UUID padrão (36 caracteres)

#### Limites do Agregado

O agregado MovimentoPremio é **simples** e **auto-contido**:

1. **Não possui sub-agregados:** Todos os dados estão no próprio record
2. **Referencia outros agregados:** Por IDs (documentoCodigo, endossoCodigo)
3. **Consistência local:** Valida apenas seus próprios dados financeiros

---

## Entidades e Value Objects

### Entidade (Entity)

| Entidade | Identificador | Mutabilidade | Ciclo de Vida |
|----------|---------------|--------------|---------------|
| `MovimentoPremio` | uuid | Imutável (Java Record) | Criado ao registrar movimentação financeira |

### Value Objects Internos

Embora seja um record simples, podemos identificar conceitos de valor dentro do MovimentoPremio:

| Conceito | Campos | Significado |
|----------|--------|-------------|
| **Período de Vigência** | dataInicio, dataTermino | Período em que o prêmio é válido |
| **Valores Financeiros** | premioLiquido, iof, custoApolice, premioTotal | Composição do prêmio |
| **Apropriação** | premioGanho, premioNaoGanho | Estado contábil do prêmio |

---

## Invariantes de Negócio

### Invariante 1: Referência a Documento Obrigatória

**Regra:** Todo movimento DEVE estar vinculado a um documento válido.

```java
public record MovimentoPremio(...) {
    public MovimentoPremio {
        if (documentoCodigo == null || documentoCodigo.isEmpty()) {
            throw new DomainException(
                "Movimento de prêmio deve estar vinculado a um documento"
            );
        }
    }
}
```

### Invariante 2: Consistência de Vigência

**Regra:** Data de término deve ser posterior à data de início.

```java
public record MovimentoPremio(...) {
    public MovimentoPremio {
        if (dataInicio != null && dataTermino != null) {
            if (!dataTermino.isAfter(dataInicio)) {
                throw new DomainException(
                    "Data término deve ser posterior à data início"
                );
            }
        }
    }
}
```

### Invariante 3: Data do Movimento Dentro da Vigência

**Regra:** A data do movimento deve estar dentro do período de vigência.

```java
public record MovimentoPremio(...) {
    public MovimentoPremio {
        if (dataMovimento != null && dataInicio != null && dataTermino != null) {
            if (dataMovimento.isBefore(dataInicio) || 
                dataMovimento.isAfter(dataTermino)) {
                throw new DomainException(
                    "Data do movimento deve estar dentro da vigência"
                );
            }
        }
    }
}
```

### Invariante 4: Composição Financeira

**Regra:** O prêmio total deve ser a soma dos componentes (quando todos estão presentes).

```java
public class MovimentoPremioService {
    public void validarComposicaoFinanceira(MovimentoPremio movimento) {
        if (movimento.premioLiquido() != null && 
            movimento.iof() != null && 
            movimento.custoApolice() != null &&
            movimento.premioTotal() != null) {
            
            double calculado = movimento.premioLiquido() + 
                               movimento.iof() + 
                               movimento.custoApolice();
            
            double diferenca = Math.abs(calculado - movimento.premioTotal());
            
            if (diferenca > 0.01) { // tolerância de 1 centavo
                throw new DomainException(
                    "Prêmio total inconsistente com componentes"
                );
            }
        }
    }
}
```

### Invariante 5: Apropriação Contábil

**Regra:** A soma de prêmio ganho e não ganho deve ser igual ao prêmio total.

```java
public class MovimentoPremioService {
    public void validarApropriacao(MovimentoPremio movimento) {
        if (movimento.premioGanho() != null && 
            movimento.premioNaoGanho() != null &&
            movimento.premioTotal() != null) {
            
            double total = movimento.premioGanho() + movimento.premioNaoGanho();
            double diferenca = Math.abs(total - movimento.premioTotal());
            
            if (diferenca > 0.01) {
                throw new DomainException(
                    "Apropriação inconsistente: ganho + não ganho != total"
                );
            }
        }
    }
}
```

---

## Linguagem Ubíqua

### Termos do Domínio

| Termo | Definição | Exemplo |
|-------|-----------|---------|
| **Movimento de Prêmio** | Registro de operação financeira relacionada a prêmio | Emissão, cancelamento, pagamento |
| **Prêmio Líquido** | Valor base do seguro sem impostos e custos | R$ 1.500,00 |
| **IOF** | Imposto sobre Operações Financeiras | 7% do prêmio líquido |
| **Custo de Apólice** | Taxa administrativa | R$ 50,00 fixo |
| **Prêmio Total** | Soma de prêmio líquido + IOF + custos | R$ 1.655,00 |
| **Prêmio Ganho** | Parte do prêmio já apropriada contabilmente | Cálculo pro-rata temporal |
| **Prêmio Não Ganho** | Parte do prêmio ainda não apropriada | Reserva técnica |
| **Restituição** | Devolução de prêmio ao segurado | Cancelamento proporcional |
| **Estorno** | Reversão de movimento anterior | Cancelamento de pagamento |

### Regras de Comunicação

- **"Emitir prêmio"** = Criar movimento de emissão
- **"Cancelar prêmio"** = Criar movimento de cancelamento (valores negativos)
- **"Receber pagamento"** = Criar movimento de pagamento
- **"Restituir prêmio"** = Criar movimento com valor de restituição
- **"Apropriar prêmio"** = Calcular e registrar prêmio ganho/não ganho

---

## Relacionamento com outros BCs

### Upstream: DOCUMENTO e ENDOSSO

**Tipo:** Conformist

**Integração:** Subscriber de Eventos

```java
/**
 * Listener que cria movimentos ao receber eventos de documento/endosso
 */
@EventHandler
public class MovimentoPremioEventHandler {
    
    @Subscribe
    public void onDocumentoEmitido(DocumentoEmitidoEvent event) {
        // Criar movimento de emissão de prêmio
        var movimento = new MovimentoPremio(
            UUID.randomUUID().toString(),
            "Emissão inicial",
            event.seguradoraCodigo(),
            LocalDate.now(),
            event.documentoCodigo(),
            null, // sem endosso
            "EMISSAO",
            // ... demais campos ...
        );
        
        movimentoRepository.save(movimento);
    }
    
    @Subscribe
    public void onEndossoAprovado(EndossoAprovadoEvent event) {
        // Criar movimento baseado no tipo de endosso
        if (event.endossoTipo() >= 4 && event.endossoTipo() <= 7) {
            // Cancelamento - criar movimento negativo
            criarMovimentoCancelamento(event);
        } else {
            // Alteração - criar movimento de ajuste
            criarMovimentoAlteracao(event);
        }
    }
}
```

### Downstream: CONTABILIDADE e FATURAMENTO

**Tipo:** Published Language

**Integração:** Domain Events

```java
public class MovimentoPremioService {
    
    public void registrarMovimento(MovimentoPremio movimento) {
        // Salvar movimento
        repository.save(movimento);
        
        // Publicar evento para sistemas downstream
        eventPublisher.publish(new MovimentoPremioRegistradoEvent(
            movimento.uuid(),
            movimento.documentoCodigo(),
            movimento.premioTotal(),
            movimento.dataMovimento()
        ));
    }
}
```

---

## Eventos de Domínio

### MovimentoPremioRegistradoEvent

**Quando:** Um novo movimento de prêmio é registrado no sistema.

```java
public record MovimentoPremioRegistradoEvent(
    String movimentoId,
    String documentoCodigo,
    String endossoCodigo,
    String tipoMovimento,
    Double premioTotal,
    LocalDate dataMovimento,
    LocalDateTime timestamp
) implements DomainEvent {}
```

**Consumidores:**
- CONTABILIDADE: Registra lançamentos contábeis
- FATURAMENTO: Gera cobranças
- ANALYTICS: Estatísticas financeiras

### PremioApropriadoEvent

**Quando:** Prêmio ganho/não ganho é calculado e atualizado.

```java
public record PremioApropriadoEvent(
    String movimentoId,
    String documentoCodigo,
    Double premioGanho,
    Double premioNaoGanho,
    LocalDate dataApropriacao,
    LocalDateTime timestamp
) implements DomainEvent {}
```

**Consumidores:**
- CONTABILIDADE: Atualiza apropriação de receita
- REGULATORIO: Calcula reservas técnicas

### RestituicaoProcessadaEvent

**Quando:** Uma restituição é calculada e registrada.

```java
public record RestituicaoProcessadaEvent(
    String movimentoId,
    String documentoCodigo,
    String seguradoCodigo,
    Double valorRestituicao,
    LocalDate dataRestituicao,
    LocalDateTime timestamp
) implements DomainEvent {}
```

**Consumidores:**
- FATURAMENTO: Processa devolução
- FINANCEIRO: Agenda pagamento

---

## Diagrama de Contexto

```
┌─────────────────┐      ┌─────────────────┐
│   DOCUMENTO     │      │    ENDOSSO      │
│                 │      │                 │
│ - Apólices      │      │ - Alterações    │
│ - Bilhetes      │      │ - Cancelamentos │
└────────┬────────┘      └────────┬────────┘
         │                        │
         │  Events                │  Events
         ↓                        ↓
         └────────┬───────────────┘
                  ↓
         ┌────────────────────┐
         │ MOVIMENTO_PREMIO   │ (Core)
         │                    │
         │ - Emissões         │
         │ - Cancelamentos    │
         │ - Pagamentos       │
         │ - Restituições     │
         └─────────┬──────────┘
                   │ Events
                   ↓
         ┌─────────────────────┐
         │   CONTABILIDADE     │
         │   FATURAMENTO       │
         │   FINANCEIRO        │
         └─────────────────────┘
```

---

## Metadados

- **Bounded Context:** MovimentoPremio
- **Aggregate Roots:** 1 (MovimentoPremio)
- **Entities:** 1 (MovimentoPremio)
- **Value Objects:** 0 (estrutura simples)
- **Domain Events:** 3 (MovimentoPremioRegistrado, PremioApropriado, RestituicaoProcessada)
- **Upstream Dependencies:** DOCUMENTO, ENDOSSO (via eventos)
- **Downstream Consumers:** CONTABILIDADE, FATURAMENTO, FINANCEIRO
