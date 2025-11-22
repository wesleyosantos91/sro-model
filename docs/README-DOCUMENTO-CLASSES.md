# DOCUMENTO - Documentação Detalhada das Classes

## Visão Geral

Este documento apresenta a documentação completa das classes Java geradas a partir da aba **DOCUMENTO** 
do arquivo Excel de especificação do SRO (Sistema de Registro de Operações) versão 2.0.0 da SUSEP.

**Documentos** representam contratos de seguro (apólices, bilhetes, certificados) com todos os seus dados cadastrais, 
objetos segurados, coberturas, prêmios e relacionamentos com pessoas envolvidas.

**Tecnologia:** Java 25 Records  
**Package:** `br.com.sro.model.documento`  
**Total de Classes:** 15 records

---

## 📋 Índice

1. [Classe Principal](#classe-principal)
2. [Classes de Grupos/Blocos](#classes-de-gruposblocos)
3. [Detalhamento dos Campos](#detalhamento-dos-campos)

---

## 🎯 Classe Principal

### `Documento.java`

Record principal que representa um documento de apólice no SRO.

#### Campos Principais (21 campos)

| Campo | Tipo | Cardinalidade | Descrição |
|-------|------|---------------|-----------|
| `uuid` | String | [1..1] | Identificador único do registro (UUID formato RFC 4122) |
| `anotacao` | String | [0..1] | Campo livre para controle interno das registradoras |
| `codigoSeguradora` | String | [1..1] | Código SUSEP da seguradora (5 dígitos) |
| `dataRegistro` | LocalDate | [1..1] | Data do registro no sistema |
| `dataAlteracao` | LocalDate | [1..1] | Data da última alteração do registro |
| `indicadorExclusao` | Integer | [1..1] | Indica se é registro de exclusão (1-Sim, 2-Não) |
| `tipoDocumentoEmitido` | Integer | [1..1] | Tipo do documento (1-Apólice Individual, 2-Apólice Coletiva, 3-Bilhete, etc.) |
| `apoliceCodigo` | String | [1..1] | Identificador da apólice/bilhete/certificado (até 60 caracteres) |
| `numeroSusepApolice` | String | [0..1] | Número SUSEP da apólice (formato SSSSSAAAAFFFFRRRRNNNNNNN) |
| `certificadoCodigo` | String | [0..1] | Identificador do certificado (obrigatório para tipos 4, 7 e 10) |
| `tipoEmissao` | Integer | [1..1] | Tipo de emissão (1-Emissão Própria, 2-Cosseguro Aceito) |
| `dataEmissao` | LocalDate | [1..1] | Data de emissão do documento |
| `dataInicio` | LocalDate | [1..1] | Data de início de vigência |
| `dataTermino` | LocalDate | [1..1] | Data de fim de vigência |
| `codigoFilial` | String | [1..1] | Código da filial de emissão (4 caracteres) |
| `codigoSeguradoraLider` | String | [0..1] | Código da seguradora líder (para cosseguro) |
| `apoliceCodigoLider` | String | [0..1] | Identificador da apólice na seguradora líder |
| `moedaApolice` | String | [1..1] | Moeda da apólice (ISO 4217: BRL, USD, EUR, etc.) |
| `limiteMaximoGarantia` | Double | [1..1] | Limite máximo de garantia (LMG) na moeda da apólice |
| `limiteMaximoGarantiaReal` | Double | [1..1] | Limite máximo de garantia (LMG) em reais |
| `coberturaBasica` | Integer | [0..1] | Indicador de cobertura básica (1-Simples, 2-Ampla) |

#### Listas de Grupos (8 coleções)

| Lista | Tipo | Cardinalidade | Descrição |
|-------|------|---------------|-----------|
| `ccgs` | List\<Ccg\> | [0..N] | Contratos de Contragarantia vinculados |
| `segurados` | List\<Segurado\> | [0..N] | Pessoas seguradas pela apólice |
| `beneficiarios` | List\<Beneficiario\> | [0..N] | Beneficiários da apólice |
| `tomadores` | List\<Tomador\> | [0..N] | Tomadores/garantidos da apólice |
| `intermediarios` | List\<Intermediario\> | [0..N] | Intermediários (corretores, estipulantes, etc.) |
| `objetosSegurados` | List\<ObjetoSegurado\> | [0..N] | Objetos cobertos pela apólice |
| `premioApolice` | PremioApolice | [0..1] | Informações de prêmio da apólice |
| `cosseguro` | Cosseguro | [0..1] | Informações de cosseguro |

---

## 🏗️ Classes de Grupos/Blocos

### 1. `Ccg.java` - Contrato de Contragarantia

Representa contratos de contragarantia vinculados à apólice.

**Campos:**
- `ccgIdentificacao` (String) - Identificador do CCG
- `dataVinculacao` (LocalDate) - Data de vinculação do CCG à apólice

**Uso:** Principalmente em seguros garantia e fiança locatícia.

---

### 2. `Segurado.java` - Pessoa Segurada

Representa pessoas físicas ou jurídicas seguradas pela apólice.

**Campos:**
- `documento` (String) - CPF/CNPJ/Passaporte do segurado
- `tipoDocumento` (Integer) - Tipo: 1-CPF, 2-CNPJ, 3-Passaporte, 99-Outros
- `nome` (String) - Nome ou razão social (até 144 caracteres)
- `dataNascimento` (LocalDate) - Data de nascimento (obrigatório para habitacional)
- `sexoSeguradoParticipante` (Integer) - Sexo: 1-Feminino, 2-Masculino, 3-Não informado
- `codigoPostal` (String) - CEP (8 dígitos para Brasil)
- `cidade` (String) - Cidade por extenso (até 100 caracteres)
- `estado` (String) - Estado por extenso (até 50 caracteres)
- `pais` (String) - País (código ISO 3166-1 alfa-3)

**Validações:**
- Unicidade: não permitir segurados duplicados no mesmo documento
- CPF/CNPJ: aceitar apenas números
- Data nascimento: obrigatória para produtos habitacionais e tipos 8, 9 e 10

---

### 3. `Beneficiario.java` - Beneficiário

Representa beneficiários da apólice.

**Campos:**
- `documento` (String) - Documento de identificação
- `tipoDocumento` (Integer) - Tipo do documento
- `nome` (String) - Nome ou razão social
- `codigoPostal` (String) - CEP
- `cidade` (String) - Cidade por extenso
- `estado` (String) - Estado por extenso
- `pais` (String) - País (ISO 3166-1 alfa-3)

**Subgrupo:**
- `beneficiariosPorCobertura` (List\<BeneficiariosPorCobertura\>) - Vincula beneficiário a coberturas específicas

---

### 4. `BeneficiariosPorCobertura.java`

Vincula beneficiários a coberturas e objetos segurados específicos.

**Campos:**
- `identificadorObjetoSeguradoBeneficiarios` (String) - Referência ao objeto segurado
- `grupoRamoCoberturaBeneficiarios` (String) - Grupo e ramo da cobertura (4 dígitos)
- `codigoInternoCoberturaBeneficiarios` (String) - Código interno da cobertura

---

### 5. `Tomador.java` - Tomador/Garantido

Representa tomadores do seguro (quando distintos dos segurados).

**Campos:**
- `documento` (String) - Documento de identificação
- `tipoDocumento` (Integer) - Tipo do documento
- `nome` (String) - Nome ou razão social
- `codigoPostal` (String) - CEP
- `cidade` (String) - Cidade
- `estado` (String) - Estado
- `pais` (String) - País (ISO 3166-1 alfa-3)

**Uso:** Principalmente em seguros garantia.

---

### 6. `Intermediario.java` - Intermediário

Representa intermediários da apólice (corretores, estipulantes, etc.).

**Campos:**
- `tipo` (Integer) - 1-Corretor, 2-Representante, 3-Estipulante, 4-Correspondente, 5-Agente de Microsseguros, 6-Averbador, 7-Instituidor, 99-Outro
- `documento` (String) - CPF/CNPJ do intermediário
- `codigo` (String) - Código SUSEP do corretor (formato YYNSSSSSS)
- `tipoDocumento` (Integer) - Tipo do documento
- `nome` (String) - Nome ou razão social
- `codigoPostal` (String) - CEP
- `cidade` (String) - Cidade
- `estado` (String) - Estado
- `pais` (String) - País (ISO 3166-1 alfa-3)
- `valorComissao` (Double) - Valor total de comissão
- `valorComissaoReal` (Double) - Valor de comissão em reais

**Validações:**
- Campo `codigo` obrigatório quando tipo = 1 (Corretor)
- Formato do código: YYNSSSSSS (YY=ano, N=1 para PF ou 2 para PJ, SSSSSS=sequencial)

---

### 7. `ObjetoSegurado.java` - Objeto Segurado

Representa objetos cobertos pela apólice.

**Campos:**
- `codigo` (String) - Identificador do objeto (até 50 caracteres)
- `tipo` (Integer) - 1-Contrato, 2-Processo Administrativo, 3-Processo Judicial, 4-Automóvel, 5-Condutor, 6-Frota, 7-Pessoa, 99-Outros
- `descricaoTipo` (String) - Descrição quando tipo=99
- `descricaoObjeto` (String) - Descrição do objeto (até 1024 caracteres)
- `valor` (Double) - Valor do objeto
- `valorReal` (Double) - Valor do objeto em reais
- `dataInicio` (LocalDate) - Data de início do objeto
- `dataTermino` (LocalDate) - Data de fim do objeto

**Subgrupos:**
- `coberturas` (List\<Cobertura\>) - Coberturas aplicadas ao objeto
- `objetoRural` (ObjetoRural) - Dados específicos para objetos rurais
- `objetoPatrimonial` (ObjetoPatrimonial) - Dados específicos patrimoniais

**Observações:**
- Não deve ser preenchido para tipos de documento 5, 6 ou 7 (automóveis)
- Campos valor/dataInicio obrigatórios para tipos 1, 2, 3 e ramos garantia/fiança

---

### 8. `Cobertura.java` - Cobertura

Representa coberturas aplicadas aos objetos segurados.

**Campos:**
- `grupoRamo` (String) - Grupo e ramo da cobertura (4 dígitos)
- `codigo` (Integer) - Código da cobertura (até 5 dígitos)
- `outrasDescricao` (String) - Descrição quando codigo=999
- `coberturaInternaSeguradora` (String) - Código interno da seguradora
- `numeroProcesso` (String) - Número do processo SUSEP
- `limiteMaximoIndenizacao` (Double) - LMI na moeda da apólice
- `limiteMaximoIndenizacaoReal` (Double) - LMI em reais
- `limiteMaximoIndenizacaoSublimite` (Integer) - Indica se LMI é sublimite (1-Sim, 2-Não)
- `dataInicioCobertura` (LocalDate) - Data de início da cobertura
- `dataTerminoCobertura` (LocalDate) - Data de fim da cobertura
- `coberturaPrincipal` (Integer) - Indica se é cobertura principal (1-Sim, 2-Não)
- `coberturaCaracteristica` (Integer) - 1-Massificados, 2-Massificados Microsseguros, 3-Grandes Riscos
- `tipoRisco` (Integer) - 1-Pessoas, 2-Danos
- `coberturaTipo` (Integer) - 1-Paramétrico, 2-Intermitente, 3-Regular, 4-Capital Global, 5-Paramétrico e Intermitente
- `valorPremio` (Double) - Valor do prêmio
- `valorPremioReal` (Double) - Valor do prêmio em reais
- `iof` (Double) - Valor de IOF em reais
- `custo` (Double) - Custo de aquisição
- `custoReal` (Double) - Custo de aquisição em reais

**Subgrupo:**
- `franquias` (List\<Franquia\>) - Franquias aplicadas à cobertura

**Validações:**
- Número processo aceita formatos: XX.XXXXXX/XX-XX, XXX-XXXXX/XX, XXXXX.XXXXXX/XX-XX, XXXXX.XXXXXX/XXXX-XX
- Usar "15414.999999/9999-99" quando não houver processo
- Prêmio = 0,00 quando LMI for sublimite

---

### 9. `Franquia.java` - Franquia

Representa franquias aplicadas às coberturas.

**Campos:**
- `franquiaTipo` (Integer) - 1-Reduzida, 2-Normal, 3-Majorada, 4-Dedutível, 99-Outros
- `tipoDescricao` (String) - Descrição quando tipo=99 (até 1000 caracteres)
- `franquiaValor` (Double) - Valor da franquia
- `franquiaDescricao` (String) - Descrição adicional da franquia (até 500 caracteres)

---

### 10. `ObjetoRural.java` - Objeto Rural

Dados específicos para objetos segurados de seguro rural.

**Campos:** 14 campos específicos para operações rurais (lavouras, animais, aquicultura, florestas, benfeitorias, produtos agropecuários, etc.)

---

### 11. `ObjetoPatrimonial.java` - Objeto Patrimonial

Dados específicos para objetos patrimoniais.

**Campos:**
- `cepLocalizacaoRisco` (String) - CEP da localização do risco
- `enderecoLocalizacaoRisco` (String) - Endereço completo
- `numeroImovel` (String) - Número do imóvel
- `complementoEndereco` (String) - Complemento do endereço

---

### 12. `PremioApolice.java` - Prêmio da Apólice

Consolidação dos valores de prêmio da apólice.

**Campos:**
- `premioTotal` (Double) - Prêmio total
- `premioTotalReal` (Double) - Prêmio total em reais
- `iofTotal` (Double) - IOF total
- `custoAquisicaoTotal` (Double) - Custo total de aquisição
- `custoAquisicaoTotalReal` (Double) - Custo de aquisição em reais

---

### 13. `Cosseguro.java` - Cosseguro

Informações sobre operações de cosseguro.

**Campos:**
- `percentualParticipacaoCosseguro` (Float) - Percentual de participação da seguradora

**Subgrupo:**
- `cessionariasCosseguro` (List\<CessionariasCosseguro\>) - Cessionárias do cosseguro

---

### 14. `CessionariasCosseguro.java` - Cessionárias de Cosseguro

Representa cessionárias participantes do cosseguro.

**Campos:**
- `codigoCessionaria` (String) - Código SUSEP da cessionária
- `percentualParticipacaoCessionaria` (Float) - Percentual de participação

---

## 💡 Tipos de Dados Java

### Mapeamento de Tipos

| Tipo Excel | Tipo Java | Observação |
|------------|-----------|------------|
| String | String | Textos e códigos |
| Int | Integer | Números inteiros e domínios |
| Double | Double | Valores monetários e decimais |
| Date | LocalDate | Datas (formato AAAA-MM-DD) |
| Float | Float | Percentuais e valores com precisão |

### Imports Necessários

```java
import java.time.LocalDate;
import java.util.List;
```

---

## 📝 Convenções de Nomenclatura

### Java Records
- **Classes:** PascalCase (ex: `Documento`, `ObjetoSegurado`)
- **Campos:** camelCase (ex: `codigoSeguradora`, `dataRegistro`)
- **Constantes de domínio:** Descritas nos JavaDocs

### Tags XML (referência)
- **snake_case:** Mantidas conforme especificação SUSEP (ex: `codigo_seguradora`, `data_registro`)

---

## ✅ Validações Importantes

### Cardinalidades
- **[1..1]** - Obrigatório, valor único
- **[0..1]** - Opcional, valor único
- **[0..N]** - Opcional, múltiplos valores (List)
- **[1..N]** - Obrigatório, múltiplos valores (List)

### Formatos Especiais
- **UUID:** RFC 4122 (36 caracteres com hífens)
- **CEP:** 8 dígitos numéricos (Brasil)
- **Moeda:** ISO 4217 (3 letras: BRL, USD, EUR)
- **País:** ISO 3166-1 alfa-3 (3 letras: BRA, USA, ARG)
- **Data:** ISO 8601 ou AAAA-MM-DD
- **Número SUSEP:** Formato SSSSSAAAAFFFFRRRRNNNNNNN (24-30 dígitos)

---

## 🎓 Domínios (Enumerações)

### Tipo de Documento Emitido
1. Apólice Individual
2. Apólice Coletiva
3. Bilhete
4. Certificado
5. Apólice Individual Automóvel
6. Apólice Frota Automóvel
7. Certificado Automóvel
8. Contrato Coletivo (PREV)
9. Certificado de Participante Individual (PREV)
10. Certificado de Participante Coletivo (PREV)
11. Apólice Coletiva sem Certificado

### Tipo de Emissão
1. Emissão Própria
2. Cosseguro Aceito

### Tipo de Documento (Pessoa)
1. CPF
2. CNPJ
3. Passaporte
99. Outros

### Tipo de Intermediário
1. Corretor
2. Representante
3. Estipulante
4. Correspondente
5. Agente de Microsseguros
6. Averbador
7. Instituidor
99. Outro

### Tipo de Objeto Segurado
1. Contrato
2. Processo Administrativo
3. Processo Judicial
4. Automóvel
5. Condutor
6. Frota
7. Pessoa
99. Outros

### Característica da Cobertura
1. Massificados
2. Massificados Microsseguros
3. Grandes Riscos

### Tipo de Cobertura
1. Paramétrico
2. Intermitente
3. Regular (comum)
4. Capital Global
5. Paramétrico e Intermitente

### Tipo de Franquia
1. Reduzida
2. Normal
3. Majorada
4. Dedutível
99. Outros

---

## 📦 Estrutura de Arquivos

```
src/main/java/br/com/sro/model/documento/
├── Documento.java                    (Classe principal)
├── Ccg.java
├── Segurado.java
├── Beneficiario.java
├── BeneficiariosPorCobertura.java
├── Tomador.java
├── Intermediario.java
├── ObjetoSegurado.java
├── ObjetoRural.java
├── ObjetoPatrimonial.java
├── Cobertura.java
├── Franquia.java
├── PremioApolice.java
├── Cosseguro.java
└── CessionariasCosseguro.java
```

---

## 🔗 Relacionamentos

```
Documento
├── ccgs [0..N]
├── segurados [0..N]
├── beneficiarios [0..N]
│   └── beneficiariosPorCobertura [0..N]
├── tomadores [0..N]
├── intermediarios [0..N]
├── objetosSegurados [0..N]
│   ├── coberturas [1..N]
│   │   └── franquias [0..N]
│   ├── objetoRural [0..1]
│   └── objetoPatrimonial [0..1]
├── premioApolice [0..1]
└── cosseguro [0..1]
    └── cessionariasCosseguro [0..N]
```

---

## 📄 Exemplo de Uso

```java
// Criar um documento de apólice
var documento = new Documento(
    UUID.randomUUID().toString(),  // uuid
    "Anotação de teste",           // anotacao
    "12345",                        // codigoSeguradora
    LocalDate.now(),                // dataRegistro
    LocalDate.now(),                // dataAlteracao
    2,                              // indicadorExclusao (Não)
    1,                              // tipoDocumentoEmitido (Apólice Individual)
    "APL-2024-001",                 // apoliceCodigo
    null,                           // numeroSusepApolice
    null,                           // certificadoCodigo
    1,                              // tipoEmissao (Emissão Própria)
    LocalDate.of(2024, 1, 15),      // dataEmissao
    LocalDate.of(2024, 1, 15),      // dataInicio
    LocalDate.of(2025, 1, 15),      // dataTermino
    "0001",                         // codigoFilial
    null,                           // codigoSeguradoraLider
    null,                           // apoliceCodigoLider
    "BRL",                          // moedaApolice
    100000.00,                      // limiteMaximoGarantia
    100000.00,                      // limiteMaximoGarantiaReal
    null,                           // coberturaBasica
    List.of(),                      // ccgs
    List.of(segurado),              // segurados
    List.of(),                      // beneficiarios
    List.of(),                      // tomadores
    List.of(corretor),              // intermediarios
    List.of(objetoSegurado),        // objetosSegurados
    premioApolice,                  // premioApolice
    null                            // cosseguro
);
```

---

## 🏷️ Tags e Metadados

**Versão:** 2.0.0  
**Origem:** Especificação SUSEP - SRO  
**Aba:** DOCUMENTO  
**Data de Geração:** Novembro 2025  
**Java Version:** 25  
**Formato:** Records (Immutable Data Classes)

---

## 📚 Referências

- [SUSEP - Superintendência de Seguros Privados](https://www.susep.gov.br)
- [Sistema de Registro de Operações (SRO)](https://www.susep.gov.br/menu/registro-de-operacoes)
- [Java Records Documentation](https://docs.oracle.com/en/java/javase/25/language/records.html)
- [ISO 4217 - Currency Codes](https://www.iso.org/iso-4217-currency-codes.html)
- [ISO 3166-1 - Country Codes](https://www.iso.org/iso-3166-country-codes.html)

---

**Nota:** Todas as classes foram geradas automaticamente com JavaDocs completos para facilitar o entendimento e manutenção do código.
