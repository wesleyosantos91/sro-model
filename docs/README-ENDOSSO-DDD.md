# ENDOSSO - Domain-Driven Design (DDD)

## Visão Geral

Este documento descreve a arquitetura DDD (Domain-Driven Design) aplicada ao domínio de **ENDOSSO** 
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

### Bounded Context: Endosso

**Responsabilidade:** Gerenciar alterações em documentos de seguro (apólices, bilhetes, certificados).

**Escopo:**
- Registro de alterações cadastrais
- Inclusão/exclusão de objetos segurados
- Cancelamentos (total, parcial, com/sem devolução)
- Reativações de documentos cancelados
- Averbações de valores

**Fronteiras:**
- **Upstream:** Recebe dados do contexto DOCUMENTO (apólices existentes)
- **Downstream:** Gera eventos para MOVIMENTO_PREMIO e CCG
- **Colaboração:** Compartilha conceitos com DOCUMENTO através de Anti-Corruption Layer

---

## Agregados

### Aggregate Root: Endosso

**Classe:** `br.com.sro.model.endosso.Endosso`

**Responsabilidade:** Garantir consistência de todas as alterações em um documento de seguro.

#### Estrutura do Agregado

```
Endosso (Aggregate Root)
├── EndossoAssociado (Value Object Collection) [0..N]
├── Ccg (Entity Collection) [0..N]
├── Segurado (Entity Collection) [0..N]
├── Beneficiario (Entity Collection) [0..N]
├── Tomador (Entity Collection) [0..N]
├── Intermediario (Entity Collection) [0..N]
├── ObjetoSegurado (Entity Collection) [0..N]
│   ├── Cobertura (Entity Collection) [0..N]
│   │   └── Franquia (Value Object Collection) [0..N]
│   ├── ObjetoRural (Value Object) [0..1]
│   └── ObjetoPatrimonial (Value Object) [0..1]
├── PremioApolice (Value Object) [0..1]
└── Cosseguro (Value Object) [0..1]
    └── CessionariasCosseguro (Value Object Collection) [0..N]
```

#### Identificador

- **Campo:** `endossoCodigo` (String)
- **Características:** Único, imutável, obrigatório
- **Formato:** Definido pela seguradora

#### Limites do Agregado

O agregado Endosso protege as seguintes invariantes:

1. **Consistência transacional:** Todas as alterações devem ser atômicas
2. **Rastreabilidade:** Endossos associados devem existir no sistema
3. **Vigência válida:** Período deve estar dentro da vigência da apólice
4. **Integridade referencial:** Pessoas e objetos devem estar completos

---

## Entidades e Value Objects

### Entidades (Entities)

Objetos com identidade única que podem mudar ao longo do tempo:

| Entidade | Identificador | Mutabilidade | Ciclo de Vida |
|----------|---------------|--------------|---------------|
| `Endosso` | endossoCodigo | Imutável (Java Record) | Criado ao alterar documento |
| `Segurado` | seguradoCodigo | Imutável (Java Record) | Vinculado ao endosso |
| `Beneficiario` | beneficiarioCodigo | Imutável (Java Record) | Vinculado ao endosso |
| `Tomador` | tomadorCodigo | Imutável (Java Record) | Vinculado ao endosso |
| `Intermediario` | intermediarioCodigo | Imutável (Java Record) | Vinculado ao endosso |
| `ObjetoSegurado` | objetoSeguradoCodigo | Imutável (Java Record) | Vinculado ao endosso |
| `Cobertura` | coberturaCodigo | Imutável (Java Record) | Vinculado ao objeto |
| `Ccg` | ccgNumero | Imutável (Java Record) | Vinculado ao endosso |

### Value Objects

Objetos sem identidade própria, definidos apenas por seus atributos:

| Value Object | Características | Contexto |
|--------------|-----------------|----------|
| `EndossoAssociado` | Código de endosso cancelado | Obrigatório para cancelamentos parciais |
| `Franquia` | Valores e tipos de franquia | Associado a coberturas |
| `ObjetoRural` | Detalhes de objetos rurais | Opcional para objetos segurados |
| `ObjetoPatrimonial` | Detalhes patrimoniais | Opcional para objetos segurados |
| `PremioApolice` | Valores financeiros | Calculado com base nas alterações |
| `Cosseguro` | Operação de cosseguro | Presente quando há compartilhamento |
| `CessionariasCosseguro` | Participantes do cosseguro | Lista de cessionárias |
| `BeneficiariosPorCobertura` | Vínculo beneficiário-cobertura | Associação específica |

---

## Invariantes de Negócio

### Invariante 1: Tipos de Endosso

**Regra:** O tipo de endosso determina quais campos são obrigatórios.

```java
public enum TipoEndosso {
    ALTERACAO(1),
    INCLUSAO(2),
    EXCLUSAO(3),
    CANCELAMENTO_TOTAL(4),
    CANCELAMENTO_PARCIAL(5),
    CANCELAMENTO_TOTAL_SEM_DEVOLUCAO(6),
    CANCELAMENTO_PARCIAL_SEM_DEVOLUCAO(7),
    REATIVACAO(8),
    AVERBACAO(9);
    
    private final int codigo;
    
    TipoEndosso(int codigo) {
        this.codigo = codigo;
    }
}
```

### Invariante 2: Endossos Associados Obrigatórios

**Regra:** Cancelamentos parciais (tipos 5 e 7) DEVEM informar quais endossos estão sendo cancelados.

```java
public record Endosso(...) {
    public Endosso {
        if (endossoTipo == 5 || endossoTipo == 7) {
            if (endossosAssociados == null || endossosAssociados.isEmpty()) {
                throw new DomainException(
                    "Cancelamento parcial exige endossos associados"
                );
            }
        }
    }
}
```

### Invariante 3: Objetos Segurados Proibidos em Cancelamentos

**Regra:** Cancelamentos (tipos 4-7) e endossos averbáveis NÃO devem incluir objetos segurados.

```java
public record Endosso(...) {
    public Endosso {
        // Cancelamentos não podem ter objetos
        if (endossoTipo >= 4 && endossoTipo <= 7) {
            if (objetosSegurados != null && !objetosSegurados.isEmpty()) {
                throw new DomainException(
                    "Cancelamentos não devem incluir objetos segurados"
                );
            }
        }
        
        // Averbáveis não podem ter objetos
        if (Boolean.TRUE.equals(endossoAverbavel)) {
            if (objetosSegurados != null && !objetosSegurados.isEmpty()) {
                throw new DomainException(
                    "Endossos averbáveis não devem incluir objetos segurados"
                );
            }
        }
    }
}
```

### Invariante 4: Vigência Dentro da Apólice

**Regra:** O período de vigência do endosso deve estar contido no período da apólice.

```java
public class EndossoService {
    public void validarVigencia(Endosso endosso, Documento documento) {
        if (endosso.dataInicio().isBefore(documento.dataInicio()) ||
            endosso.dataTermino().isAfter(documento.dataTermino())) {
            throw new DomainException(
                "Vigência do endosso deve estar dentro da vigência da apólice"
            );
        }
        
        if (!endosso.dataTermino().isAfter(endosso.dataInicio())) {
            throw new DomainException(
                "Data término deve ser posterior à data início"
            );
        }
    }
}
```

### Invariante 5: Completude de Pessoas

**Regra:** Ao alterar qualquer pessoa, TODAS as pessoas daquele tipo devem ser enviadas.

```java
/**
 * Ao alterar um segurado, enviar TODOS os segurados
 * Não enviar apenas os modificados!
 */
public class EndossoService {
    public Endosso alterarSegurado(
        Endosso endosso, 
        Segurado seguradoAlterado,
        DocumentoRepository documentoRepo
    ) {
        // Buscar TODOS os segurados atualizados
        var todosSegurados = documentoRepo
            .findByCodigo(endosso.endossoCodigo())
            .segurados();
        
        // Atualizar o modificado
        todosSegurados = todosSegurados.stream()
            .map(s -> s.seguradoCodigo().equals(seguradoAlterado.seguradoCodigo()) 
                ? seguradoAlterado : s)
            .toList();
        
        // Retornar endosso com TODOS os segurados
        return new Endosso(
            endosso.endossoCodigo(),
            // ... outros campos ...
            todosSegurados, // TODOS, não apenas o alterado
            endosso.beneficiarios(),
            // ... demais listas ...
        );
    }
}
```

---

## Linguagem Ubíqua

### Termos do Domínio

| Termo | Definição | Exemplo |
|-------|-----------|---------|
| **Endosso** | Alteração em documento de seguro vigente | Inclusão de novo segurado |
| **Averbação** | Endosso que apenas registra informação sem alterar prêmio | Troca de placa de veículo |
| **Cancelamento Total** | Extinção completa do documento | Cliente solicitou cancelamento |
| **Cancelamento Parcial** | Anulação de endossos específicos | Desfazer endosso anterior |
| **Reativação** | Retorno de documento cancelado à vigência | Cliente desistiu do cancelamento |
| **Endosso Associado** | Endosso anterior afetado por cancelamento parcial | END-001 cancela END-003 |
| **Vigência do Endosso** | Período em que as alterações são válidas | De 15/03/2024 a 14/03/2025 |
| **Apólice Mãe** | Documento original sendo alterado | Apólice 12345 |

### Regras de Comunicação

- **"Endossar"** = Criar um endosso para alterar documento
- **"Averbar"** = Criar endosso averbável (sem alteração de prêmio)
- **"Cancelar totalmente"** = Criar endosso tipo 4 ou 6
- **"Cancelar parcialmente"** = Criar endosso tipo 5 ou 7 com endossos associados
- **"Reativar"** = Criar endosso tipo 8 para documento cancelado

---

## Relacionamento com outros BCs

### Upstream: DOCUMENTO

**Tipo:** Customer-Supplier

**Integração:** Anti-Corruption Layer (ACL)

```java
/**
 * ACL para proteger o contexto ENDOSSO de mudanças no DOCUMENTO
 */
public class DocumentoAdapter {
    private final DocumentoClient documentoClient;
    
    public DocumentoInfo buscarDocumento(String documentoCodigo) {
        var doc = documentoClient.getDocumento(documentoCodigo);
        
        // Traduzir para modelo do contexto ENDOSSO
        return new DocumentoInfo(
            doc.getCodigo(),
            doc.getDataInicio(),
            doc.getDataTermino(),
            doc.getSituacao()
        );
    }
}

/**
 * Modelo interno do contexto ENDOSSO
 */
record DocumentoInfo(
    String codigo,
    LocalDate dataInicio,
    LocalDate dataTermino,
    SituacaoDocumento situacao
) {}
```

### Downstream: MOVIMENTO_PREMIO

**Tipo:** Conformist

**Integração:** Eventos de Domínio

```java
public class EndossoService {
    public void aprovarEndosso(Endosso endosso) {
        // ... validações ...
        
        // Publicar evento para MOVIMENTO_PREMIO processar
        eventPublisher.publish(new EndossoAprovadoEvent(
            endosso.endossoCodigo(),
            endosso.endossoTipo(),
            endosso.premioApolice()
        ));
    }
}
```

### Shared Kernel: Pessoas e Objetos

**Compartilhamento:** Classes de `Segurado`, `Beneficiario`, `Tomador`, etc.

```
br.com.sro.model.documento.*
  ↓ (importado por)
br.com.sro.model.endosso.Endosso
```

**Decisão:** Reutilizar classes do DOCUMENTO ao invés de duplicar.

**Vantagem:** Consistência entre documentos e endossos.

**Risco:** Mudanças em DOCUMENTO afetam ENDOSSO diretamente.

---

## Eventos de Domínio

### EndossoCriadoEvent

**Quando:** Um novo endosso é registrado no sistema.

```java
public record EndossoCriadoEvent(
    String endossoCodigo,
    String documentoCodigo,
    Integer endossoTipo,
    LocalDate dataInicio,
    LocalDate dataTermino,
    LocalDateTime timestamp
) implements DomainEvent {}
```

### EndossoAprovadoEvent

**Quando:** Endosso é validado e aprovado para produção de efeitos.

```java
public record EndossoAprovadoEvent(
    String endossoCodigo,
    Integer endossoTipo,
    PremioApolice premio,
    LocalDateTime timestamp
) implements DomainEvent {}
```

**Consumidores:**
- MOVIMENTO_PREMIO: Gera movimentações financeiras
- ANALYTICS: Estatísticas de alterações

### EndossoCanceladoEvent

**Quando:** Endosso de cancelamento (tipos 4-7) é processado.

```java
public record EndossoCanceladoEvent(
    String endossoCodigo,
    String documentoCodigo,
    Integer tipoCancelamento,
    List<String> endossosAssociados,
    boolean comDevolucao,
    LocalDateTime timestamp
) implements DomainEvent {}
```

**Consumidores:**
- DOCUMENTO: Atualiza status da apólice
- MOVIMENTO_PREMIO: Calcula devoluções

---

## Diagrama de Contexto

```
┌─────────────────┐
│   DOCUMENTO     │ (Upstream)
│                 │
│ - Apólices      │
│ - Bilhetes      │
│ - Certificados  │
└────────┬────────┘
         │ ACL
         ↓
┌─────────────────┐
│    ENDOSSO      │ (Core)
│                 │
│ - Alterações    │
│ - Cancelamentos │
│ - Averbações    │
└────────┬────────┘
         │ Events
         ↓
┌─────────────────┐
│ MOVIMENTO_PREMIO│ (Downstream)
│                 │
│ - Cálculos      │
│ - Pagamentos    │
│ - Devoluções    │
└─────────────────┘
```

---

## Metadados

- **Bounded Context:** Endosso
- **Aggregate Roots:** 1 (Endosso)
- **Entities:** 8 (Endosso, Segurado, Beneficiario, Tomador, Intermediario, ObjetoSegurado, Cobertura, Ccg)
- **Value Objects:** 7 (EndossoAssociado, Franquia, ObjetoRural, ObjetoPatrimonial, PremioApolice, Cosseguro, CessionariasCosseguro)
- **Domain Events:** 3 (EndossoCriadoEvent, EndossoAprovadoEvent, EndossoCanceladoEvent)
- **Upstream Dependencies:** DOCUMENTO (ACL)
- **Downstream Consumers:** MOVIMENTO_PREMIO, CCG, ANALYTICS
