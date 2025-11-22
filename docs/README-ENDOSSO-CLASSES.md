# ENDOSSO - Documentação Detalhada das Classes

## Visão Geral

Este documento apresenta a documentação completa das classes Java geradas a partir da aba **ENDOSSO** 
do arquivo Excel de especificação do SRO (Sistema de Registro de Operações) versão 2.0.0 da SUSEP.

**Endossos** representam alterações em documentos de seguro (apólices, bilhetes, certificados) e podem incluir:
- Alterações de dados cadastrais
- Inclusão/exclusão de objetos segurados
- Alterações de prêmio
- Cancelamentos e reativações
- Averbações

---

## Índice

1. [Classe Principal: Endosso](#classe-principal-endosso)
2. [Classe Específica: EndossoAssociado](#classe-endossoassociado)
3. [Classes Reutilizadas do Documento](#classes-reutilizadas)
4. [Tipos de Endosso](#tipos-de-endosso)
5. [Regras de Negócio](#regras-de-negocio)

---

## Classe Principal: Endosso

**Package:** `br.com.sro.model.endosso`

Aggregate root que representa um endosso completo com todos os seus campos e relacionamentos.

### Campos Principais

| Campo | Tipo | Cardinalidade | Descrição |
|-------|------|---------------|-----------|
| Uuid | String | [1..1] | Identificador único do registro |
| Anotação Registro | String | [0..1] | Campo livre de anotação |
| Código da Seguradora | String | [1..1] | Código Susep da seguradora |
| Data do Registro | LocalDate | [1..1] | Data do registro |
| Data da Alteração do Registro | LocalDate | [1..1] | Data da alteração do registro |
| Indicador Exclusão | Int | [1..1] | Indica se é um registro de exclusão |
| Tipo de Documento Emitido | Int | [1..1] | Tipo de Documento Emitido  |
| Identificador da Apólice / Bilhete | String | [1..1] | Identificador da apólice ou bilhete |
| Número Susep da Apólice | String | [0..1] | Número SUSEP da apólice, conforme Circular Susep 326 |
| Identificador do Certificado | String | [0..1] | Identificador do certificado |
| Identificador do Endosso | String | [1..1] | Identificador do endosso (de qualquer documento) |
| Descrição do Endosso | String | [0..1] | Descrição adicional do endosso |
| Tipo de Endosso | Int | [1..1] | Tipo de emissão do endosso |
| Endosso Averbável | Int | [1.1] | Indica se o endosso inclui averbações  |
| Tipo de Emissão | Int | [1..1] | Tipo de emissão da apólice |
| Data de Emissão | LocalDate | [1..1] | Data de emissão do endosso |
| Data de Início de Vigência Endosso | LocalDate | [1..1] | Data de início de vigência do endosso |
| Data de Fim de Vigência Endosso | LocalDate | [1..1] | Data de fim de vigência do endosso |
| Data de Início de Vigência Documento | LocalDate | [0..1] | Data de início de vigência do documento |
| Data de Fim de Vigência Documento | LocalDate | [0..1] | Data de fim de vigência do documento |
| Código da Filial | String | [1..1] | Código da filial de emissão da apólice |
| Código da Seguradora Líder | String | [0..1] | Código da seguradora líder para apólices com arranjo de cosseguro |
| Identificador da Apólice Seguradora Líder | String | [0..1] | Identificador da apólice seguradora líder para apólice de cosseguro aceito |
| Moeda da Apólice | String | [1..1] | Moeda de emissão da apólice |
| Limite Máximo de Garantia (LMG) | Double | [1..1] | Limite máximo de garantia (LMG) |
| Limite Máximo de Garantia (LMG) em Reais | Double | [1..1] | Limite máximo de garantia (LMG) em reais |
| Indicador de Cobertura Básica | Int | [0..1] | None |

### Listas de Grupos

| Grupo | Classe | Cardinalidade | Descrição |
|-------|--------|---------------|-----------|
| Endossos Associados | `EndossoAssociado` | [0..N] | Lista de endossos relacionados (obrigatório para tipos 5 e 7) |
| CCG | `Ccg` | [0..N] | Contratos de Contragarantia |
| Segurados | `Segurado` | [0..N] | Lista de segurados (enviar todos quando houver alteração) |
| Beneficiários | `Beneficiario` | [0..N] | Lista de beneficiários (enviar todos quando houver alteração) |
| Tomadores | `Tomador` | [0..N] | Lista de tomadores/garantidos (enviar todos quando houver alteração) |
| Intermediários | `Intermediario` | [0..N] | Lista de intermediários (enviar todos quando houver alteração) |
| Objetos Segurados | `ObjetoSegurado` | [0..N] | Lista de objetos segurados (não preencher para tipos 5, 6, 7) |
| Prêmio | `PremioApolice` | [0..1] | Informações de prêmio |
| Cosseguro | `Cosseguro` | [0..1] | Informações de cosseguro |

---

## Classe EndossoAssociado

**Package:** `br.com.sro.model.endosso`

Value object que representa endossos associados ao endosso principal.

### Campos

| Campo | Tipo | Cardinalidade | Descrição |
|-------|------|---------------|-----------|
| Identificador do Endosso Associado | String | [1..1] | Identificador do endosso associado |

### Quando Usar

- **Obrigatório** para endossos tipo 5 (cancelamento parcial) ou tipo 7 (cancelamento parcial sem devolução)
- Indica quais endossos anteriores estão sendo cancelados
- Permite rastreabilidade de cancelamentos

---

## Classes Reutilizadas

As seguintes classes são importadas do pacote `br.com.sro.model.documento` e reutilizadas no contexto de endosso:

### Classes de Pessoas

- **`Segurado`** - Pessoa física ou jurídica que tem o risco segurado
- **`Beneficiario`** - Pessoa que receberá a indenização
- **`Tomador`** - Contratante do seguro (pode ser diferente do segurado)
- **`Intermediario`** - Corretor ou outro intermediário

### Classes de Objetos

- **`ObjetoSegurado`** - Item segurado (veículo, imóvel, pessoa, etc.)
- **`Cobertura`** - Coberturas do objeto segurado
- **`Franquia`** - Franquias das coberturas
- **`ObjetoRural`** - Detalhes de objetos rurais
- **`ObjetoPatrimonial`** - Detalhes de objetos patrimoniais

### Classes Financeiras

- **`PremioApolice`** - Informações de prêmio e pagamento
- **`Cosseguro`** - Informações de cosseguro
- **`CessionariasCosseguro`** - Cessionárias em operações de cosseguro

### Outras Classes

- **`Ccg`** - Contrato de Contragarantia
- **`BeneficiariosPorCobertura`** - Associação de beneficiários a coberturas específicas

Para detalhes completos dessas classes, consulte [README-CLASSES.md](README-CLASSES.md)

---

## Tipos de Endosso

Os endossos são classificados por tipo no campo `endosso_tipo`:

| Código | Tipo | Descrição |
|--------|------|-----------|
| 1 | Alteração | Alteração de dados cadastrais ou valores |
| 2 | Inclusão | Inclusão de objetos segurados |
| 3 | Exclusão | Exclusão de objetos segurados |
| 4 | Cancelamento Total | Cancelamento de toda a apólice |
| 5 | Cancelamento Parcial | Cancelamento de endossos específicos |
| 6 | Cancelamento Total sem Devolução | Cancelamento sem restituição de prêmio |
| 7 | Cancelamento Parcial sem Devolução | Cancelamento parcial sem restituição |
| 8 | Reativação | Reativação de apólice cancelada |
| 9 | Averbação | Averbação de valores ou objetos |

---

## Regras de Negócio

### 1. Endossos Associados

```java
// Obrigatório para tipos 5 e 7
if (endosso.endossoTipo() == 5 || endosso.endossoTipo() == 7) {
    assert !endosso.endossosAssociados().isEmpty() : 
        "Endossos associados obrigatórios para cancelamento parcial";
}
```

### 2. Objetos Segurados

```java
// Não preencher para tipos 5, 6, 7 ou quando endosso for averbável
if (endosso.endossoTipo() >= 5 && endosso.endossoTipo() <= 7) {
    assert endosso.objetosSegurados().isEmpty() : 
        "Objetos segurados não devem ser enviados para cancelamentos";
}

if (endosso.endossoAverbavel() != null && endosso.endossoAverbavel()) {
    assert endosso.objetosSegurados().isEmpty() : 
        "Objetos segurados não devem ser enviados para endossos averbáveis";
}
```

### 3. Alteração de Pessoas

```java
// Ao alterar pessoas, enviar TODAS as pessoas (não apenas as alteradas)
// Exemplo: se houver alteração em um segurado, enviar todos os segurados
if (houveAlteracaoSegurado(endosso)) {
    endosso = endosso.withSegurados(
        obterTodosSeguradosAtualizados(endosso.endossoCodigo())
    );
}
```

### 4. Vigência do Endosso

```java
// Data de término deve ser posterior à data de início
assert endosso.dataTermino().isAfter(endosso.dataInicio()) : 
    "Data de término deve ser posterior à data de início";

// Vigência do endosso deve estar dentro da vigência da apólice
assert !endosso.dataInicio().isBefore(apolice.dataInicio()) &&
       !endosso.dataTermino().isAfter(apolice.dataTermino()) : 
    "Vigência do endosso deve estar dentro da vigência da apólice";
```

---

## Exemplo de Uso

```java
// Criar um endosso de inclusão de segurado
var endosso = new Endosso(
    // Identificação
    "END-2024-001",                    // endossoCodigo
    "Inclusão de novo segurado",       // endossoDescricao
    1,                                    // endossoTipo (Alteração)
    false,                                // endossoAverbavel
    
    // Vigência
    LocalDate.of(2024, 3, 15),           // dataInicio
    LocalDate.of(2025, 3, 14),           // dataTermino
    
    // ... outros 21 campos principais ...
    
    // Grupos
    List.of(),                           // endossosAssociados (não se aplica)
    List.of(),                           // ccgs
    List.of(novoSegurado),               // segurados (incluindo o novo)
    List.of(),                           // beneficiarios
    List.of(),                           // tomadores
    List.of(),                           // intermediarios
    List.of(),                           // objetosSegurados
    null,                                // premioApolice
    null                                 // cosseguro
);
```

---

## Metadados

- **Fonte:** Especificação SUSEP SRO v2.0.0
- **Aba Excel:** ENDOSSO
- **Total de Campos Principais:** 27
- **Total de Grupos:** 9
- **Classes Geradas:** 2 (Endosso, EndossoAssociado)
- **Classes Reutilizadas:** 14 (do pacote documento)
- **Geração:** Automática via Python
