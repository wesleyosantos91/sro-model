# COMPL_AUTO - Classes Java

## 📋 Visão Geral

Este documento apresenta as classes Java Record geradas a partir da especificação **SUSEP SRO v2.0.0 - Aba COMPL _AUTO**.

**Pacote:** `br.com.sro.model.complauto`  
**Total de Classes:** 4  
**Versão Java:** 25 (Records)

---

## 🏗️ Estrutura de Classes

### Aggregate Root

#### `ComplAuto`
Representa informações complementares de veículos automotores no sistema SRO.

**Responsabilidades:**
- Identificação do veículo (placa, chassi, RENAVAM)
- Características técnicas (ano, modelo, combustível)
- Valores (importância segurada, prêmio, comissão)
- Agregação de coberturas, franquias e condutores

**Campos Principais:** 18
- `numeroEndosso` - Número do endosso
- `placaVeiculo` - Placa do veículo segurado
- `chassis` - Número do chassi
- `renavam` - Código RENAVAM
- `anoFabricacao` - Ano de fabricação
- `anoModelo` - Ano do modelo
- `marcaVeiculo` - Marca do veículo
- `modeloVeiculo` - Modelo do veículo
- `combustivel` - Tipo de combustível
- `categoria` - Categoria do veículo
- `especieVeiculo` - Espécie do veículo
- `tipoVeiculo` - Tipo de veículo
- `importanciaSegurada` - Valor da importância segurada
- `premioLiquido` - Valor do prêmio líquido
- `comissao` - Valor da comissão
- `premioTotal` - Valor do prêmio total
- `indicadorVeiculoZeroKm` - Veículo zero km (S/N)
- `codigoFipe` - Código FIPE

**Listas Agregadas:**
- `List<CoberturaAutomóvel>` - Coberturas do veículo
- `List<Franquia>` - Franquias aplicáveis
- `List<PessoasAssociadasCondutor>` - Condutores autorizados

---

### Value Objects

#### 1. `CoberturaAutomóvel`
Representa uma cobertura de seguro para veículos automotores.

**Campos:** 22
- `numeroEndossoCo` - Número do endosso da cobertura
- `codigoCoberturaCiaAutomovel` - Código da cobertura na companhia
- `descricaoCoberturaCiaAutomovel` - Descrição da cobertura
- `especieCoberturaCob` - Espécie da cobertura
- `codigoRamoSusepCob` - Código do ramo SUSEP
- `importanciaSeguradaCob` - Importância segurada da cobertura
- `franquiaValorCob` - Valor da franquia
- `franquiaPercentualCob` - Percentual da franquia
- `premioTarifaCob` - Prêmio de tarifa
- `premioLiquidoCob` - Prêmio líquido
- `codigoFornecedorCob` - Código do fornecedor
- `nomeFornecedorCob` - Nome do fornecedor
- `taxaCob` - Taxa da cobertura
- `codigoObjetoSegurado` - Código do objeto segurado
- `codigoTipoUtilizacaoVeiculo` - Tipo de utilização do veículo
- `codigoCepPernoite` - CEP de pernoite
- `descricaoTipoResidenciaPernoite` - Tipo de residência de pernoite
- `descricaoRelacaoSeguradoCond` - Relação segurado/condutor
- `codigoModalidadeCobertura` - Modalidade da cobertura
- `codigoProdutoAnvisCob` - Código do produto ANVIS
- `codigoTipoEquipamento` - Tipo de equipamento
- `descricaoEquipamento` - Descrição do equipamento

**Características:**
- Imutável (Record)
- Validação de dados financeiros (BigDecimal)
- Relacionamento com fornecedores e equipamentos

---

#### 2. `Franquia`
Representa valores e condições de franquia aplicável ao seguro.

**Campos:** 5
- `numeroEndossoFra` - Número do endosso da franquia
- `codigoCoberturaCiaAutomovelFra` - Código da cobertura associada
- `codigoTipoFranquia` - Tipo de franquia
- `valorFranquia` - Valor monetário da franquia
- `percentualFranquia` - Percentual da franquia

**Características:**
- Imutável (Record)
- Associada a uma cobertura específica
- Pode ser valor fixo ou percentual

---

#### 3. `PessoasAssociadasCondutor`
Representa informações sobre condutores autorizados do veículo.

**Campos:** 4
- `numeroEndossoCondPrincipal` - Número do endosso do condutor
- `codigoPessoaCondPrincipal` - Código da pessoa condutora
- `dataNascimentoCondutorPrincipal` - Data de nascimento
- `sexoCondutorPrincipal` - Sexo do condutor (M/F)

**Características:**
- Imutável (Record)
- Dados pessoais do condutor
- Relacionamento com pessoa jurídica/física

---

## 📊 Relacionamentos

```
ComplAuto (Aggregate Root)
├── List<CoberturaAutomóvel>
│   └── Relaciona com Franquia via codigoCoberturaCiaAutomovel
├── List<Franquia>
└── List<PessoasAssociadasCondutor>
```

---

## 🎯 Padrões de Design Aplicados

### 1. **Aggregate Pattern**
`ComplAuto` é o aggregate root que garante consistência transacional.

### 2. **Value Object Pattern**
`CoberturaAutomóvel`, `Franquia`, e `PessoasAssociadasCondutor` são value objects imutáveis.

### 3. **Immutability**
Todos os records são imutáveis, garantindo thread-safety.

### 4. **Composition over Inheritance**
Uso de composição via `List<>` em vez de herança.

---

## 📝 Observações Técnicas

1. **Imutabilidade**: Todas as classes são Java Records (imutáveis por design)
2. **JavaDoc Completo**: Cada campo possui documentação com metadados SUSEP
3. **Tipos Adequados**: 
   - `BigDecimal` para valores monetários
   - `LocalDate` para datas
   - `String` para textos
   - `Integer` para códigos numéricos
4. **Listas Agregadas**: ComplAuto mantém listas de coberturas, franquias e condutores

---

## 🔍 Metadados SUSEP

Cada campo contém no JavaDoc:
- **Cardinalidade**: Obrigatoriedade (1..1, 0..1, 1..n)
- **Tag**: Identificador SUSEP
- **Tipo**: Tipo de dado conforme especificação
- **Formato**: Padrão de formatação (quando aplicável)
- **Tamanho**: Limite de caracteres
- **Condição**: Regras de negócio
- **Observação**: Notas adicionais

---

## 📦 Uso Exemplo

```java
// Criar cobertura
var cobertura = new CoberturaAutomóvel(
    "001",
    "COB001",
    "Colisão Total",
    "CASCO",
    "0531",
    new BigDecimal("50000.00"),
    new BigDecimal("2000.00"),
    null,
    new BigDecimal("850.00"),
    new BigDecimal("800.00"),
    null, null, null, null, null, null, null, null, null, null, null, null
);

// Criar franquia
var franquia = new Franquia(
    "001",
    "COB001",
    "REDUZIDA",
    new BigDecimal("1500.00"),
    null
);

// Criar condutor
var condutor = new PessoasAssociadasCondutor(
    "001",
    "PES123",
    LocalDate.of(1985, 3, 15),
    "M"
);

// Criar ComplAuto
var complAuto = new ComplAuto(
    "001",
    "ABC1234",
    "9BWZZZ377VT004251",
    "12345678901",
    2023,
    2024,
    "VOLKSWAGEN",
    "GOL",
    "FLEX",
    "PASSEIO",
    "AUTOMOVEL",
    "PARTICULAR",
    new BigDecimal("65000.00"),
    new BigDecimal("2500.00"),
    new BigDecimal("250.00"),
    new BigDecimal("2750.00"),
    "S",
    "123456-7",
    List.of(cobertura),
    List.of(franquia),
    List.of(condutor)
);
```

---

## 🔗 Referências

- **Especificação:** SUSEP SRO v2.0.0
- **Aba Excel:** COMPL _AUTO
- **Versão Java:** 25
- **Padrão:** Domain-Driven Design (DDD)

---

**Gerado em:** 2024  
**Total de Classes:** 4  
**Total de Campos:** 49
