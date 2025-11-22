# CCG - Documentação de Classes

## 📋 Visão Geral

Este documento detalha as classes Java geradas a partir da aba **CCG** (Contrato de Contragarantia) da especificação SUSEP SRO v2.0.0.

**CCG (Contrato de Contragarantia)** é um contrato entre seguradora e ressegurador que estabelece as condições de contragarantia para operações de resseguro, incluindo vigência, valores, comissões e identificações das partes envolvidas.

---

## 🏗️ Estrutura de Classes

### Pacote: `io.github.wesleyosantos91.susep.sro.model.ccg`

| Classe | Tipo | Campos | Descrição |
|--------|------|--------|-----------|
| `Ccg` | Entity/Aggregate Root | 16 | Contrato de contragarantia |

**Estrutura:** Simples (flat) - Sem grupos aninhados

---

## 📊 Classe: Ccg

### Descrição

Representa um **Contrato de Contragarantia (CCG)** no sistema SRO. O CCG documenta o acordo entre seguradora e ressegurador para operações de resseguro, incluindo:

- ✅ Identificação da seguradora e ressegurador
- ✅ Período de vigência do contrato
- ✅ Valores de importância segurada e prêmio
- ✅ Comissões de corretagem e agenciamento
- ✅ Códigos de identificação únicos

### Campos

| Campo | Tipo | Cardinalidade | Descrição |
|-------|------|---------------|-----------|
| `Data de Fim de Vigência` | Date | [0..1] | Data de fim de vigência do contrato de contragarantia |
| `Grupo / Bloco: Tomador` |  | [1..N] |  |
| `Documento do Tomador` | String | [1..1] | Documento do tomador |
| `Tipo de Documento do Tomador` | Int | [1..1] | Tipo de documento do tomador |
| `Indicador de Controlador de Grupo Econômico do Tomador` | Int | [1..1] | Indicador de controlador de frupo econômico do tomador |
| `Nome / Razão Social do Tomador` | String | [1..1] | Nome / Razão Social do tomador |
| `Limite Aprovado do Tomador` | Double | [1..1] | Limite Aprovado do Tomador |
| `Grupo / Bloco: Colateral` |  | [0..N] |  |
| `Tipo de Ativo do Colateral` | Int | [1..1] | Tipo de ativo do colateral |
| `Valor do Ativo do Colateral` | Double | [1..1] | Valor do ativo do colateral |
| `UF do Registro do Ativo Colateral` | String | [0. 1] | UF do registro do ativo colateral |
| `País do Registro do Ativo Colateral` | String | [1..1] | País do Registro do Ativo Colateral |
| `Grupo / Bloco: Fiador` |  | [0..N] |  |
| `Documento do Fiador` | String | [1..1] | Documento do fiador |
| `Tipo de Documento do Fiador` | Int | [1..1] | Tipo de documento do fiador |
| `Nome / Razão Social do Fiador` | String | [1..1] | Nome / Razão Social do fiador |

### Detalhamento dos Campos

#### 1. Data de Fim de Vigência

**Descrição:** Data de fim de vigência do contrato de contragarantia

| Propriedade | Valor |
|-------------|-------|
| Tag | `data_termino` |
| Tipo | Date |
| Formato | AAAA-MM-DD |
| Tamanho | 10 |
| Cardinalidade | [0..1] |

#### 2. Grupo / Bloco: Tomador

**Descrição:** 

| Propriedade | Valor |
|-------------|-------|
| Tag | `tomador` |
| Tipo |  |
| Formato |  |
| Tamanho |  |
| Cardinalidade | [1..N] |
| Semântica | Dados Gerais do CCG \ Tomador |

#### 3. Documento do Tomador

**Descrição:** Documento do tomador

| Propriedade | Valor |
|-------------|-------|
| Tag | `documento` |
| Tipo | String |
| Formato | - |
| Tamanho | 40 |
| Cardinalidade | [1..1] |

#### 4. Tipo de Documento do Tomador

**Descrição:** Tipo de documento do tomador

| Propriedade | Valor |
|-------------|-------|
| Tag | `tipo_documento` |
| Tipo | Int |
| Formato | 1 - CPF
2 - CNPJ
3 - Passaporte
99 - Outros |
| Tamanho | 2 |
| Cardinalidade | [1..1] |
| Observação | Inclusão de domínio 3 - Passaporte para padronização
Reunião 24/01/2023 |

#### 5. Indicador de Controlador de Grupo Econômico do Tomador

**Descrição:** Indicador de controlador de frupo econômico do tomador

| Propriedade | Valor |
|-------------|-------|
| Tag | `controlador_ge` |
| Tipo | Int |
| Formato | 1 - Sim
2 - Não |
| Tamanho | 1 |
| Cardinalidade | [1..1] |

#### 6. Nome / Razão Social do Tomador

**Descrição:** Nome / Razão Social do tomador

| Propriedade | Valor |
|-------------|-------|
| Tag | `razao_social` |
| Tipo | String |
| Formato | - |
| Tamanho | 144 |
| Cardinalidade | [1..1] |
| Observação | Tamanho alterado para 144 conforme solicitação e autorizado pela Susep em e-mail de 19/12/22 |

#### 7. Limite Aprovado do Tomador

**Descrição:** Limite Aprovado do Tomador

| Propriedade | Valor |
|-------------|-------|
| Tag | `limite_aprovado` |
| Tipo | Double |
| Formato | - |
| Tamanho | 16.2 |
| Cardinalidade | [1..1] |

#### 8. Grupo / Bloco: Colateral

**Descrição:** 

| Propriedade | Valor |
|-------------|-------|
| Tag | `colateral` |
| Tipo |  |
| Formato |  |
| Tamanho |  |
| Cardinalidade | [0..N] |
| Semântica | Dados Gerais do CCG \ Colateral |

#### 9. Tipo de Ativo do Colateral

**Descrição:** Tipo de ativo do colateral

| Propriedade | Valor |
|-------------|-------|
| Tag | `tipo_ativo_colateral` |
| Tipo | Int |
| Formato | 1 - Ativo Financeiros e/ou Valores Mobiliários
2 - Imóveis
3 - Fiança
99 - Outros |
| Tamanho | 2 |
| Cardinalidade | [1..1] |

#### 10. Valor do Ativo do Colateral

**Descrição:** Valor do ativo do colateral

| Propriedade | Valor |
|-------------|-------|
| Tag | `valor_ativo_colateral` |
| Tipo | Double |
| Formato | - |
| Tamanho | 16.2 |
| Cardinalidade | [1..1] |

#### 11. UF do Registro do Ativo Colateral

**Descrição:** UF do registro do ativo colateral

| Propriedade | Valor |
|-------------|-------|
| Tag | `uf_ativo_colateral` |
| Tipo | String |
| Formato | - |
| Tamanho | 2 |
| Cardinalidade | [0. 1] |

#### 12. País do Registro do Ativo Colateral

**Descrição:** País do Registro do Ativo Colateral

| Propriedade | Valor |
|-------------|-------|
| Tag | `pais_ativo_colateral` |
| Tipo | String |
| Formato | - |
| Tamanho | 100 |
| Cardinalidade | [1..1] |

#### 13. Grupo / Bloco: Fiador

**Descrição:** 

| Propriedade | Valor |
|-------------|-------|
| Tag | `fiador` |
| Tipo |  |
| Formato |  |
| Tamanho |  |
| Cardinalidade | [0..N] |
| Condição | por Grupo / Bloco: Dados Gerais do CCG |
| Semântica | Dados Gerais do CCG \ Fiador |

#### 14. Documento do Fiador

**Descrição:** Documento do fiador

| Propriedade | Valor |
|-------------|-------|
| Tag | `documento` |
| Tipo | String |
| Formato | - |
| Tamanho | 40 |
| Cardinalidade | [1..1] |

#### 15. Tipo de Documento do Fiador

**Descrição:** Tipo de documento do fiador

| Propriedade | Valor |
|-------------|-------|
| Tag | `tipo_documento` |
| Tipo | Int |
| Formato | 1 - CPF
2 - CNPJ
3 - Passaporte
99 - Outros |
| Tamanho | 2 |
| Cardinalidade | [1..1] |
| Observação | Inclusão de domínio 3 - Passaporte para padronização
Reunião 24/01/2023 |

#### 16. Nome / Razão Social do Fiador

**Descrição:** Nome / Razão Social do fiador

| Propriedade | Valor |
|-------------|-------|
| Tag | `razao_social` |
| Tipo | String |
| Formato | - |
| Tamanho | 144 |
| Cardinalidade | [1..1] |

---

## 💼 Regras de Negócio

### Invariantes do Domínio

1. **Vigência Válida**
   - Data de término deve ser posterior à data de início
   - Período mínimo de vigência: 1 dia
   ```java
   public boolean vigenciaValida() {
       return dataTermino.isAfter(dataInicio);
   }
   ```

2. **Identificadores Únicos**
   - UUID deve ser único no sistema
   - Código da seguradora obrigatório
   - Código do ressegurador obrigatório

3. **Valores Financeiros Positivos**
   - Importância segurada > 0
   - Prêmio de resseguro ≥ 0
   - Comissões ≥ 0
   ```java
   public boolean valoresValidos() {
       return importanciaSegurada.compareTo(BigDecimal.ZERO) > 0
           && premioResseguro.compareTo(BigDecimal.ZERO) >= 0
           && comissaoCorretagem.compareTo(BigDecimal.ZERO) >= 0;
   }
   ```

4. **Consistência de Comissões**
   - Comissão de corretagem + agenciamento ≤ prêmio de resseguro
   ```java
   public boolean comissoesConsistentes() {
       BigDecimal totalComissoes = comissaoCorretagem.add(comissaoAgenciamento);
       return totalComissoes.compareTo(premioResseguro) <= 0;
   }
   ```

---

## 🔄 Relacionamentos

### Upstream (Dependências)

```
DOCUMENTO
    ↓ (referência)
CCG
```

- **DOCUMENTO**: CCG referencia documentos através de código identificador
- O CCG pode estar associado a múltiplos documentos de seguro

### Downstream (Consumidores)

```
CCG
    ↓ (eventos)
CONTABILIDADE + RESSEGURO + AUDITORIA
```

- **CONTABILIDADE**: Processa valores de prêmio e comissões
- **RESSEGURO**: Gerencia operações de resseguro
- **AUDITORIA**: Rastreia contratos e alterações

---

## 📝 Exemplos de Uso

### Exemplo 1: Criação de CCG Básico

```java
Ccg ccg = new Ccg(
    UUID.randomUUID().toString(),
    "Observações sobre o contrato",
    "12345", // codigoSeguradora
    "67890", // codigoRessegurador
    LocalDate.of(2024, 1, 1), // dataInicio
    LocalDate.of(2024, 12, 31), // dataTermino
    new BigDecimal("1000000.00"), // importanciaSegurada
    new BigDecimal("50000.00"), // premioResseguro
    new BigDecimal("5000.00"), // comissaoCorretagem
    new BigDecimal("2000.00"), // comissaoAgenciamento
    "CCG-2024-001", // numeroContrato
    "Proporcional", // tipoContrato
    "BRL", // moeda
    "REG001", // registroSusep
    LocalDate.of(2024, 1, 1), // dataRegistro
    "Ativo" // status
);
```

### Exemplo 2: Validação de Vigência

```java
public class CcgService {
    
    public boolean validarVigencia(Ccg ccg) {
        if (!ccg.dataTermino().isAfter(ccg.dataInicio())) {
            throw new IllegalArgumentException(
                "Data de término deve ser posterior à data de início");
        }
        
        LocalDate hoje = LocalDate.now();
        return !hoje.isBefore(ccg.dataInicio()) 
            && !hoje.isAfter(ccg.dataTermino());
    }
}
```

### Exemplo 3: Cálculo de Comissões

```java
public class CcgFinanceiroService {
    
    public BigDecimal calcularComissaoTotal(Ccg ccg) {
        return ccg.comissaoCorretagem()
               .add(ccg.comissaoAgenciamento());
    }
    
    public BigDecimal calcularPremioLiquido(Ccg ccg) {
        BigDecimal comissaoTotal = calcularComissaoTotal(ccg);
        return ccg.premioResseguro().subtract(comissaoTotal);
    }
    
    public BigDecimal calcularTaxaComissao(Ccg ccg) {
        BigDecimal comissaoTotal = calcularComissaoTotal(ccg);
        return comissaoTotal.divide(ccg.premioResseguro(), 
                                   4, RoundingMode.HALF_UP)
               .multiply(new BigDecimal("100"));
    }
}
```

---

## 🎯 Características Técnicas

### Imutabilidade

- ✅ Java Record (imutável por padrão)
- ✅ Todos os campos são `final`
- ✅ Thread-safe
- ✅ Ideal para Event Sourcing

### Tipos de Dados

- ✅ `LocalDate` para datas (ISO-8601)
- ✅ `BigDecimal` para valores monetários (precisão exata)
- ✅ `String` para identificadores e textos
- ✅ Sem tipos primitivos (evita NPE)

### Validações

- ✅ Validação de vigência
- ✅ Validação de valores positivos
- ✅ Validação de consistência financeira
- ✅ Validação de identificadores obrigatórios

---

## 📚 Referências

- [SUSEP - Superintendência de Seguros Privados](https://www2.susep.gov.br/)
- [SRO v2.0.0 - Sistema de Registro de Operações](https://www2.susep.gov.br/menuestatistica/SES/principal.aspx)
- [Circular SUSEP sobre Resseguro](https://www2.susep.gov.br/bibliotecaweb/)

---

**Gerado automaticamente em:** "2025-11-22"
**Total de campos:** 16
**Versão da especificação:** SUSEP SRO v2.0.0