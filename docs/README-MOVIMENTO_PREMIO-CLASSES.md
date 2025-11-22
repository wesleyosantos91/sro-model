# MOVIMENTO_PREMIO - Documentação Detalhada das Classes

## Visão Geral

Este documento apresenta a documentação completa das classes Java geradas a partir da aba **MOVIMENTO_PREMIO** 
do arquivo Excel de especificação do SRO (Sistema de Registro de Operações) versão 2.0.0 da SUSEP.

**Movimentos de Prêmio** representam todas as movimentações financeiras relacionadas a prêmios de seguros:
- Emissões de prêmios
- Cancelamentos e estornos
- Pagamentos e recebimentos
- Restituições
- Prêmios ganhos e não ganhos

---

## Índice

1. [Classe Principal: MovimentoPremio](#classe-principal-movimentopremio)
2. [Tipos de Movimento](#tipos-de-movimento)
3. [Regras de Negócio](#regras-de-negocio)
4. [Exemplos de Uso](#exemplos-de-uso)

---

## Classe Principal: MovimentoPremio

**Package:** `br.com.sro.model.movimentopremio`

Representa uma movimentação financeira de prêmio com todos os seus atributos.

### Estrutura

Esta é uma classe **simples** (flat) sem grupos aninhados, contendo apenas campos principais.

### Campos

| Campo | Tipo | Cardinalidade | Descrição |
|-------|------|---------------|-----------|
| Uuid | String | [1..1] | Identificador único do registro |
| Anotação Registro | String | [0..1] | Campo livre de anotação |
| Código da Seguradora | String | [1..1] | Código Susep da seguradora |
| Data do Registro | LocalDate | [1..1] | Data do registro |
| Data da Alteração do Registro | LocalDate | [1..1] | Data da alteração do registro |
| Indicador Exclusão | Int | [1..1] | Indica se é um registro de exclusão |
| Identificador da Apólice / Bilhete | String | [1..1] | Identificador da apólice ou bilhete |
| Identificador do Certificado | String | [0..1] | Identificador do certificado |
| Identificador do Endosso do documento | String | [0..1] | Identificador do endosso da apólice |
| Identificador do Movimento | String | [1..1] | Identificador do movimento |
| Moeda | String | [1..1] | Moeda |
| Valor do Movimento | Double | [1..1] | Valor do Movimento |
| Valor do Movimento em Reais | Double | [1..1] | Valor do Movimento em Reais |
| Data do movimento | LocalDate | [1..1] | Data do movimento |
| Número da Parcela do Movimento | Int | [0..1] | Número da Parcela do Movimento |
| Data de Vencimento da Parcela | LocalDate | [0..1] | Data de vencimento da parcela |
| Tipo do Movimento | Int | [1..1] | Tipo do movimento |
| Grupo / Bloco: Prêmio por ramo ou cobertura | None | [0..N] | None |
| Grupo e Ramo da Cobertura | String | [1..1] | Grupo e ramo da cobertura |
| Identificador do Objeto Segurado | String | [1..1] | Referência ao Identificador do Objeto Segurado da cobertura aplicável ao benefic... |
| Código Interno da Cobertura da Seguradora | String | [1..1] | Código Interno da Cobertura da Seguradora |
| Data de Início de Vigência de Prêmio da Cobertura | LocalDate | [1..1] | Data de início de vigência de prêmio da cobertura |
| Data de Fim de Vigência de Prêmio da Cobertura | LocalDate | [1..1] | Data de fim de vigência de prêmio da cobertura |
| Limite Máximo de Indenização (LMI) | Double | [0..1] | Limite máximo de indenização (LMI) |
| Limite Máximo de Indenização (LMI) em Reais | Double | [0..1] | Limite máximo de indenização (LMI) em reais |
| Valor de Prêmio do Ramo ou da Cobertura na Parcela Associada | Double | [1..1] | Valor de prêmio da cobertura |
| Valor de Prêmio do Ramo ou da Cobertura na Parcela Associada | Double | [1..1] | Valor de prêmio da cobertura em reais |
| Adicional de Fracionamento do ramo ou cobertura | Double | [0..1] | Adicional de Fracionamento do ramo ou cobertura |
| Valor de IOF da Cobertura em Reais | Double | [0..1] | Valor de IOF da cobertura em reais |
| Custo de Aquisição do ramo ou da cobertura | Double | [0..1] | Custo de Aquisição do ramo ou da cobertura |

---

## Tipos de Movimento

Os movimentos de prêmio são classificados por tipo de operação:

### Movimentos Típicos

| Tipo | Descrição | Exemplo |
|------|-----------|---------|
| **Emissão** | Registro inicial do prêmio | Emissão de nova apólice |
| **Cancelamento** | Anulação de prêmio | Cancelamento de apólice |
| **Estorno** | Reversão de movimento anterior | Estorno de pagamento |
| **Pagamento** | Recebimento de prêmio | Pagamento pelo segurado |
| **Restituição** | Devolução de prêmio | Cancelamento com devolução |
| **Prêmio Ganho** | Parte do prêmio já apropriada | Cálculo pro-rata |
| **Prêmio Não Ganho** | Parte do prêmio ainda não apropriada | Reserva técnica |

---

## Regras de Negócio

### 1. Relacionamento com Documento

```java
// Todo movimento deve estar vinculado a um documento válido
assert movimento.documentoCodigo() != null && !movimento.documentoCodigo().isEmpty() : 
    "Movimento deve estar vinculado a um documento";
```

### 2. Relacionamento com Endosso

```java
// Se houver endosso, ele deve ser informado
if (movimento.endossoCodigo() != null) {
    // Movimento está vinculado a um endosso específico
    // Validar que o endosso pertence ao documento
}
```

### 3. Valores Financeiros

```java
// Valores devem ser consistentes
public class MovimentoPremioValidator {
    public void validarValores(MovimentoPremio movimento) {
        // Prêmio líquido não pode ser negativo
        if (movimento.premioLiquido() != null && movimento.premioLiquido() < 0) {
            throw new ValidationException("Prêmio líquido não pode ser negativo");
        }
        
        // Soma de componentes deve bater com total
        // premio_total = premio_liquido + iof + custos + ...
    }
}
```

### 4. Datas de Vigência

```java
// Data início deve ser anterior à data término
if (movimento.dataInicio() != null && movimento.dataTermino() != null) {
    assert movimento.dataTermino().isAfter(movimento.dataInicio()) : 
        "Data término deve ser posterior à data início";
}
```

### 5. Data do Movimento

```java
// Data do movimento deve estar dentro da vigência
if (movimento.dataMovimento() != null && 
    movimento.dataInicio() != null && 
    movimento.dataTermino() != null) {
    assert !movimento.dataMovimento().isBefore(movimento.dataInicio()) &&
           !movimento.dataMovimento().isAfter(movimento.dataTermino()) : 
        "Data do movimento deve estar dentro da vigência";
}
```

---

## Exemplos de Uso

### Exemplo 1: Emissão de Prêmio

```java
var movimento = new MovimentoPremio(
    // Identificação
    "550e8400-e29b-41d4-a716-446655440000",  // uuid
    "Emissão inicial",                       // anotacao
    "12345",                                 // codigoSeguradora
    LocalDate.of(2024, 11, 22),               // dataRegistro
    "APO-2024-001",                          // documentoCodigo
    null,                                      // endossoCodigo (sem endosso)
    "1",                                     // tipoMovimento
    
    // Vigência
    LocalDate.of(2024, 11, 22),               // dataInicio
    LocalDate.of(2025, 11, 21),               // dataTermino
    LocalDate.of(2024, 11, 22),               // dataMovimento
    
    // Valores
    1500.00,                                   // premioLiquido
    105.00,                                    // iof
    50.00,                                     // custoApolice
    1655.00,                                   // premioTotal
    
    // ... outros campos financeiros ...
    0.0,                                       // premioGanho
    1655.00,                                   // premioNaoGanho
    
    // ... demais campos ...
);
```

### Exemplo 2: Cancelamento com Restituição

```java
var cancelamento = new MovimentoPremio(
    "660e8400-e29b-41d4-a716-446655440001",  // uuid
    "Cancelamento a pedido do cliente",     // anotacao
    "12345",                                 // codigoSeguradora
    LocalDate.of(2024, 12, 15),               // dataRegistro
    "APO-2024-001",                          // documentoCodigo
    "END-2024-050",                          // endossoCodigo (endosso de cancelamento)
    "4",                                     // tipoMovimento (cancelamento)
    
    LocalDate.of(2024, 11, 22),               // dataInicio
    LocalDate.of(2024, 12, 15),               // dataTermino (antecipado)
    LocalDate.of(2024, 12, 15),               // dataMovimento
    
    -1500.00,                                  // premioLiquido (negativo = estorno)
    -105.00,                                   // iof (estorno)
    0.0,                                       // custoApolice (não reembolsável)
    -1605.00,                                  // premioTotal
    
    150.00,                                    // premioGanho (já apropriado)
    0.0,                                       // premioNaoGanho
    1455.00,                                   // valorRestituicao
    
    // ... demais campos ...
);
```

### Exemplo 3: Pagamento de Prêmio

```java
var pagamento = new MovimentoPremio(
    "770e8400-e29b-41d4-a716-446655440002",  // uuid
    "Pagamento recebido",                    // anotacao
    "12345",                                 // codigoSeguradora
    LocalDate.of(2024, 11, 25),               // dataRegistro
    "APO-2024-001",                          // documentoCodigo
    null,                                      // endossoCodigo
    "5",                                     // tipoMovimento (pagamento)
    
    LocalDate.of(2024, 11, 22),               // dataInicio
    LocalDate.of(2025, 11, 21),               // dataTermino
    LocalDate.of(2024, 11, 25),               // dataMovimento
    
    1655.00,                                   // valorPago
    "PIX",                                   // formaPagamento
    
    // ... outros campos ...
);
```

---

## Relacionamentos

### Com DOCUMENTO

```
DOCUMENTO (1)
    ↓ possui
MOVIMENTO_PREMIO (N)
```

Todo movimento está vinculado a um documento (apólice, bilhete, certificado).

### Com ENDOSSO

```
ENDOSSO (0..1)
    ↓ gera
MOVIMENTO_PREMIO (N)
```

Endossos podem gerar movimentos financeiros (alterações de prêmio, cancelamentos, etc.).

---

## Metadados

- **Fonte:** Especificação SUSEP SRO v2.0.0
- **Aba Excel:** MOVIMENTO_PREMIO
- **Total de Campos:** 30
- **Grupos:** Nenhum (estrutura simples)
- **Classes Geradas:** 1 (MovimentoPremio)
- **Geração:** Automática via Python
