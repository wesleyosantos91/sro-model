# DOCUMENTO - Domain-Driven Design (DDD)

## Visão Geral

Este documento descreve a arquitetura DDD (Domain-Driven Design) aplicada ao domínio de **DOCUMENTO** 
do Sistema de Registro de Operações (SRO) versão 2.0.0 da SUSEP.

---

## 🎯 Índice

1. [Contextos Delimitados (Bounded Contexts)](#contextos-delimitados)
2. [Agregados (Aggregates)](#agregados)
3. [Entidades (Entities)](#entidades)
4. [Objetos de Valor (Value Objects)](#objetos-de-valor)
5. [Linguagem Ubíqua (Ubiquitous Language)](#linguagem-ubíqua)
6. [Padrões Táticos](#padrões-táticos)
7. [Invariantes de Negócio](#invariantes-de-negócio)

---

## 🏛️ Contextos Delimitados

### 1. Contexto de Documento (Document Context)

**Responsabilidade:** Gerenciar o ciclo de vida completo de documentos de seguro (apólices, bilhetes, certificados).

**Linguagem do Domínio:**
- Documento/Apólice
- Emissão
- Vigência
- Endosso
- Cancelamento

**Limites:**
- Início: Criação/Registro do documento
- Fim: Encerramento da vigência ou cancelamento

---

### 2. Contexto de Cobertura (Coverage Context)

**Responsabilidade:** Definir e gerenciar coberturas, riscos segurados e suas características.

**Linguagem do Domínio:**
- Cobertura
- Risco
- Limite Máximo de Indenização (LMI)
- Prêmio
- Franquia

---

### 3. Contexto de Pessoa (Party Context)

**Responsabilidade:** Gerenciar informações de pessoas e entidades relacionadas às operações de seguro.

**Linguagem do Domínio:**
- Segurado
- Beneficiário
- Tomador
- Intermediário
- Corretor
- Estipulante

---

### 4. Contexto de Objeto Segurado (Insured Object Context)

**Responsabilidade:** Gerenciar objetos, bens e riscos cobertos pelas apólices.

**Linguagem do Domínio:**
- Objeto Segurado
- Bem Segurado
- Risco Rural
- Risco Patrimonial
- Veículo (Automóvel)

---

## 🔷 Agregados

### Agregado Raiz: `Documento`

**Raiz do Agregado:** `Documento.java`

**Entidades Filhas:**
- `Segurado`
- `Beneficiario`
- `Tomador`
- `Intermediario`
- `ObjetoSegurado`
  - `Cobertura`
    - `Franquia`
  - `ObjetoRural`
  - `ObjetoPatrimonial`

**Objetos de Valor:**
- `Ccg`
- `BeneficiariosPorCobertura`
- `PremioApolice`
- `Cosseguro`
- `CessionariasCosseguro`

**Invariantes do Agregado:**
1. Um documento deve ter pelo menos um UUID único
2. A data de término deve ser maior ou igual à data de início
3. A data de emissão não pode ser futura
4. O código da seguradora deve existir na tabela SUSEP
5. Documentos tipo 4, 7 e 10 devem ter certificado
6. Cosseguro aceito deve ter seguradora líder

**Limites do Agregado:**
- Nenhuma entidade externa pode modificar diretamente entidades filhas
- Todas as operações devem passar pela raiz `Documento`
- Consistência transacional dentro do agregado

---

## 🔶 Entidades

### Entidade: `Documento`

**Identidade:** `uuid` (String - UUID RFC 4122)

**Características:**
- Raiz do agregado principal
- Possui ciclo de vida independente
- Mutável ao longo do tempo (endossos, alterações)

**Responsabilidades:**
- Manter integridade dos dados do documento
- Coordenar operações de emissão, alteração e cancelamento
- Validar regras de negócio do documento

---

### Entidade: `Segurado`

**Identidade:** `documento` (CPF/CNPJ/Passaporte)

**Características:**
- Pessoa física ou jurídica segurada
- Pode ter múltiplas apólices
- Informações de localização e contato

**Responsabilidades:**
- Representar a pessoa ou entidade segurada
- Manter dados cadastrais atualizados
- Validar unicidade dentro do documento

---

### Entidade: `Beneficiario`

**Identidade:** `documento` (CPF/CNPJ/Passaporte)

**Características:**
- Pessoa designada para receber indenizações
- Pode estar vinculado a coberturas específicas
- Dados cadastrais e localização

**Responsabilidades:**
- Representar destinatário de indenizações
- Vincular-se a coberturas específicas via `BeneficiariosPorCobertura`

---

### Entidade: `Tomador`

**Identidade:** `documento` (CPF/CNPJ/Passaporte)

**Características:**
- Contratante do seguro (quando diferente do segurado)
- Comum em seguros garantia
- Dados cadastrais completos

**Responsabilidades:**
- Representar o contratante do seguro
- Garantir obrigações contratuais

---

### Entidade: `Intermediario`

**Identidade:** Composta (`documento` + `codigo`)

**Características:**
- Corretor, estipulante, representante
- Possui código SUSEP (quando corretor)
- Recebe comissão pela intermediação

**Responsabilidades:**
- Intermediar operação de seguro
- Receber comissão
- Manter registro SUSEP válido

---

### Entidade: `ObjetoSegurado`

**Identidade:** `codigo` (String - até 50 caracteres)

**Características:**
- Bem, contrato, processo ou pessoa segurada
- Pode ter múltiplas coberturas
- Possui valor e período de cobertura

**Responsabilidades:**
- Representar o bem ou risco segurado
- Agregar coberturas aplicáveis
- Especializar-se em Rural ou Patrimonial

---

### Entidade: `Cobertura`

**Identidade:** Composta (`grupoRamo` + `coberturaInternaSeguradora`)

**Características:**
- Define riscos cobertos
- Possui LMI, prêmio e período
- Pode ter franquias

**Responsabilidades:**
- Definir escopo de proteção
- Calcular prêmio e custos
- Gerenciar franquias aplicáveis

---

## 💎 Objetos de Valor

### `Ccg` (Contrato de Contragarantia)

**Imutável:** ✅

**Características:**
- Identificação do CCG
- Data de vinculação

**Uso:** Relacionar documentos a contratos de contragarantia externos.

---

### `BeneficiariosPorCobertura`

**Imutável:** ✅

**Características:**
- Vincula beneficiário a cobertura específica
- Referencia objeto segurado e grupo/ramo

**Uso:** Permitir beneficiários específicos por cobertura.

---

### `Franquia`

**Imutável:** ✅

**Características:**
- Tipo, valor e descrição
- Aplicada a coberturas específicas

**Uso:** Definir participação obrigatória do segurado em sinistros.

---

### `PremioApolice`

**Imutável:** ✅

**Características:**
- Consolidação de valores
- Prêmio total, IOF e custos

**Uso:** Representar valores totais da apólice.

---

### `Cosseguro` e `CessionariasCosseguro`

**Imutável:** ✅

**Características:**
- Percentuais de participação
- Identificação de cessionárias

**Uso:** Representar operações compartilhadas entre seguradoras.

---

### `ObjetoRural` e `ObjetoPatrimonial`

**Imutável:** ✅

**Características:**
- Especialização de objetos segurados
- Dados específicos por tipo de risco

**Uso:** Estender informações do objeto segurado conforme especialização.

---

## 🗣️ Linguagem Ubíqua

### Termos do Domínio de Seguros

| Termo | Significado | Contexto |
|-------|-------------|----------|
| **Apólice** | Contrato de seguro | Documento principal |
| **Bilhete** | Documento simplificado de seguro | Operações massificadas |
| **Certificado** | Documento individual em apólices coletivas | Seguros coletivos |
| **Segurado** | Pessoa ou entidade protegida pelo seguro | Contexto de Pessoa |
| **Tomador** | Contratante do seguro | Contexto de Pessoa |
| **Estipulante** | Intermediário em seguros coletivos | Contexto de Pessoa |
| **LMI** | Limite Máximo de Indenização | Valor máximo de cobertura |
| **LMG** | Limite Máximo de Garantia | Valor máximo da apólice |
| **Prêmio** | Valor pago pelo seguro | Custo da proteção |
| **Franquia** | Participação obrigatória do segurado | Dedução em sinistros |
| **Cosseguro** | Operação compartilhada entre seguradoras | Distribuição de risco |
| **Endosso** | Alteração na apólice | Modificação contratual |
| **Vigência** | Período de validade | Tempo de cobertura |
| **Objeto Segurado** | Bem ou risco coberto | O que está protegido |
| **Cobertura** | Risco específico garantido | Proteção contratada |
| **Grupo/Ramo** | Classificação SUSEP de produtos | Categoria regulatória |
| **Massificados** | Produtos padronizados | Mercado de varejo |
| **Grandes Riscos** | Produtos customizados | Mercado corporativo |

---

## 🎨 Padrões Táticos

### 1. Repository Pattern

**Repositório:** `DocumentoRepository`

```java
public interface DocumentoRepository {
    Documento save(Documento documento);
    Optional<Documento> findByUuid(String uuid);
    Optional<Documento> findByApoliceCodigo(String apoliceCodigo);
    List<Documento> findByCodigoSeguradora(String codigoSeguradora);
    void delete(String uuid);
}
```

**Responsabilidade:** Abstrair persistência do agregado `Documento`.

---

### 2. Factory Pattern

**Factory:** `DocumentoFactory`

```java
public class DocumentoFactory {
    public static Documento criarApoliceIndividual(
        String codigoSeguradora,
        LocalDate dataEmissao,
        LocalDate dataInicio,
        LocalDate dataTermino,
        Segurado segurado,
        List<ObjetoSegurado> objetos
    ) {
        // Lógica de criação com validações
        String uuid = UUID.randomUUID().toString();
        // ... construção do documento
        return new Documento(...);
    }
    
    public static Documento criarBilhete(...) { }
    public static Documento criarCertificado(...) { }
}
```

**Responsabilidade:** Encapsular lógica complexa de criação de documentos.

---

### 3. Specification Pattern

**Especificações de Validação:**

```java
public interface DocumentoSpecification {
    boolean isSatisfiedBy(Documento documento);
    String getErrorMessage();
}

public class DataVigenciaValidaSpecification implements DocumentoSpecification {
    @Override
    public boolean isSatisfiedBy(Documento documento) {
        return !documento.dataTermino().isBefore(documento.dataInicio());
    }
    
    @Override
    public String getErrorMessage() {
        return "Data de término deve ser maior ou igual à data de início";
    }
}

public class CosseguroAceitoSpecification implements DocumentoSpecification {
    @Override
    public boolean isSatisfiedBy(Documento documento) {
        if (documento.tipoEmissao() == 2) { // Cosseguro Aceito
            return documento.codigoSeguradoraLider() != null 
                && documento.apoliceCodigoLider() != null;
        }
        return true;
    }
    
    @Override
    public String getErrorMessage() {
        return "Cosseguro aceito deve informar seguradora líder e apólice líder";
    }
}
```

---

### 4. Domain Service

**Serviço:** `CalculadoraPremioService`

```java
public class CalculadoraPremioService {
    public PremioApolice calcularPremioTotal(List<ObjetoSegurado> objetos) {
        double premioTotal = 0.0;
        double iofTotal = 0.0;
        double custoTotal = 0.0;
        
        for (ObjetoSegurado objeto : objetos) {
            for (Cobertura cobertura : objeto.coberturas()) {
                premioTotal += cobertura.valorPremioReal();
                iofTotal += cobertura.iof() != null ? cobertura.iof() : 0.0;
                custoTotal += cobertura.custoReal() != null ? cobertura.custoReal() : 0.0;
            }
        }
        
        return new PremioApolice(
            premioTotal, premioTotal, iofTotal, custoTotal, custoTotal
        );
    }
}
```

**Responsabilidade:** Lógica de negócio que não pertence naturalmente a uma entidade.

---

### 5. Domain Events

**Eventos de Domínio:**

```java
public record DocumentoEmitidoEvent(
    String uuid,
    String apoliceCodigo,
    String codigoSeguradora,
    LocalDate dataEmissao,
    LocalDate timestamp
) {}

public record DocumentoAlteradoEvent(
    String uuid,
    LocalDate dataAlteracao,
    String motivoAlteracao,
    LocalDate timestamp
) {}

public record DocumentoCanceladoEvent(
    String uuid,
    LocalDate dataCancelamento,
    String motivoCancelamento,
    LocalDate timestamp
) {}
```

**Uso:** Comunicar mudanças importantes no domínio para outros contextos.

---

## ⚖️ Invariantes de Negócio

### Invariantes do Agregado `Documento`

#### 1. Consistência Temporal

```java
// Regra: Data término >= Data início
invariant: dataTermino >= dataInicio
```

#### 2. Emissão Não Futura

```java
// Regra: Data emissão <= Data atual
invariant: dataEmissao <= LocalDate.now()
```

#### 3. Registro Não Futuro

```java
// Regra: Data registro <= Data atual
invariant: dataRegistro <= LocalDate.now()
```

#### 4. Alteração Posterior ao Registro

```java
// Regra: Data alteração >= Data registro
invariant: dataAlteracao >= dataRegistro
```

#### 5. Certificado em Coletivas

```java
// Regra: Tipos 4, 7 e 10 devem ter certificado
invariant: if (tipoDocumentoEmitido in [4, 7, 10]) 
           then certificadoCodigo != null
```

#### 6. Cosseguro Aceito

```java
// Regra: Cosseguro aceito deve ter líder
invariant: if (tipoEmissao == 2) // Cosseguro Aceito
           then (codigoSeguradoraLider != null && apoliceCodigoLider != null)
```

#### 7. Moeda e Conversão

```java
// Regra: Se moeda != BRL, LMG em reais é obrigatório
invariant: if (moedaApolice != "BRL") 
           then limiteMaximoGarantiaReal != null
```

---

### Invariantes da Entidade `ObjetoSegurado`

#### 1. Cobertura Obrigatória

```java
// Regra: Todo objeto deve ter ao menos uma cobertura
invariant: coberturas.size() >= 1
```

#### 2. Período da Cobertura

```java
// Regra: Coberturas devem estar dentro da vigência do documento
invariant: for all cobertura in coberturas:
    cobertura.dataInicioCobertura >= documento.dataInicio &&
    cobertura.dataTerminoCobertura <= documento.dataTermino
```

#### 3. Valores Obrigatórios para Garantia

```java
// Regra: Objetos tipo 1, 2, 3 devem ter valor
invariant: if (tipo in [1, 2, 3]) // Contrato, Proc. Admin, Proc. Judicial
           then (valor != null && dataInicio != null)
```

---

### Invariantes da Entidade `Cobertura`

#### 1. Prêmio em Sublimite

```java
// Regra: Sublimites não têm prêmio próprio
invariant: if (limiteMaximoIndenizacaoSublimite == 1) // Sim
           then (valorPremio == 0.0 && valorPremioReal == 0.0)
```

#### 2. Valores Não Negativos

```java
// Regra: Valores monetários não podem ser negativos
invariant: limiteMaximoIndenizacao >= 0 &&
           valorPremio >= 0 &&
           valorPremioReal >= 0 &&
           (iof == null || iof >= 0) &&
           (custo == null || custo >= 0)
```

#### 3. Número de Processo SUSEP

```java
// Regra: Formato válido ou padrão sem processo
invariant: numeroProcesso matches 
    "[0-9]{2}\.[0-9]{6}/[0-9]{2}-[0-9]{2}" ||
    "[0-9]{3}-[0-9]{5}/[0-9]{2}" ||
    "[0-9]{5}\.[0-9]{6}/[0-9]{2}-[0-9]{2}" ||
    "[0-9]{5}\.[0-9]{6}/[0-9]{4}-[0-9]{2}" ||
    numeroProcesso == "15414.999999/9999-99" // Sem processo
```

---

### Invariantes da Entidade `Intermediario`

#### 1. Código SUSEP para Corretor

```java
// Regra: Corretores devem ter código SUSEP
invariant: if (tipo == 1) // Corretor
           then codigo != null
```

#### 2. Formato do Código SUSEP

```java
// Regra: Código formato YYNSSSSSS
invariant: if (codigo != null) then
    codigo.length() == 9 &&
    codigo matches "[0-9]{2}[12][0-9]{6}"
    // YY = ano, N = 1 (PF) ou 2 (PJ), SSSSSS = sequencial
```

#### 3. Valores de Comissão

```java
// Regra: Comissões não negativas
invariant: valorComissao >= 0 && valorComissaoReal >= 0
```

---

### Invariantes da Entidade `Segurado`

#### 1. Unicidade no Documento

```java
// Regra: Não permitir segurados duplicados
invariant: for all s1, s2 in documento.segurados:
    if s1 != s2 then s1.documento != s2.documento
```

#### 2. Data Nascimento Obrigatória

```java
// Regra: Obrigatória para habitacional e tipos 8, 9, 10
invariant: if (documento.hasRamoHabitacional() || 
               documento.tipoDocumentoEmitido in [8, 9, 10])
           then dataNascimento != null
```

#### 3. CEP Brasileiro

```java
// Regra: CEP com 8 dígitos para Brasil
invariant: if (pais == "BRA")
           then codigoPostal.length() == 8 &&
                codigoPostal matches "[0-9]{8}"
```

---

## 🏗️ Arquitetura em Camadas

### Camada de Domínio (Domain Layer)

```
br.com.sro.model.documento/
├── Documento.java              (Aggregate Root)
├── Segurado.java               (Entity)
├── Beneficiario.java           (Entity)
├── Tomador.java                (Entity)
├── Intermediario.java          (Entity)
├── ObjetoSegurado.java         (Entity)
├── Cobertura.java              (Entity)
├── Ccg.java                    (Value Object)
├── Franquia.java               (Value Object)
├── PremioApolice.java          (Value Object)
└── ...
```

### Camada de Aplicação (Application Layer)

```
br.com.sro.application/
├── DocumentoService.java       (Application Service)
├── EmissaoApoliceUseCase.java  (Use Case)
├── AlteracaoApoliceUseCase.java
└── ConsultaApoliceUseCase.java
```

### Camada de Infraestrutura (Infrastructure Layer)

```
br.com.sro.infrastructure/
├── repository/
│   └── DocumentoRepositoryImpl.java
├── messaging/
│   └── DocumentoEventPublisher.java
└── validation/
    └── SusepValidator.java
```

---

## 🔄 Fluxos de Negócio

### Fluxo: Emissão de Apólice

```
1. [Application] EmissaoApoliceUseCase recebe request
2. [Domain] DocumentoFactory cria novo Documento
3. [Domain] Validação de invariantes (Specifications)
4. [Domain] Evento DocumentoEmitidoEvent é gerado
5. [Infrastructure] DocumentoRepository persiste
6. [Infrastructure] EventPublisher publica evento
7. [Application] Retorna DTO de sucesso
```

### Fluxo: Alteração de Apólice (Endosso)

```
1. [Application] AlteracaoApoliceUseCase recebe request
2. [Infrastructure] DocumentoRepository busca documento
3. [Domain] Documento aplica alterações
4. [Domain] Validação de invariantes
5. [Domain] Atualiza dataAlteracao
6. [Domain] Evento DocumentoAlteradoEvent é gerado
7. [Infrastructure] DocumentoRepository atualiza
8. [Infrastructure] EventPublisher publica evento
9. [Application] Retorna DTO de sucesso
```

---

## 🎯 Bounded Context Map

```
┌─────────────────────────┐
│  Documento Context      │
│  (Core Domain)          │
│  - Documento            │
│  - Emissão              │
│  - Vigência             │
└───────────┬─────────────┘
            │
            │ Published Language (Events)
            │
            ├─────────────┐
            │             │
            ▼             ▼
┌───────────────────┐  ┌──────────────────┐
│  Pessoa Context   │  │  Cobertura       │
│  - Segurado       │  │  Context         │
│  - Beneficiário   │  │  - Cobertura     │
│  - Intermediário  │  │  - Franquia      │
└───────────────────┘  └──────────────────┘
            │                    │
            │                    │
            ▼                    ▼
┌───────────────────────────────────┐
│  Objeto Segurado Context          │
│  - ObjetoSegurado                 │
│  - ObjetoRural                    │
│  - ObjetoPatrimonial              │
└───────────────────────────────────┘
```

**Tipo de Relacionamento:**
- **Core Domain:** Documento Context (coração do negócio)
- **Supporting Subdomain:** Pessoa, Cobertura, Objeto Segurado
- **Integration:** Published Language via Domain Events

---

## 📊 Diagrama de Agregados

```
╔═════════════════════════════════════════════╗
║          AGREGADO: Documento                ║
║  (Aggregate Root)                           ║
╠═════════════════════════════════════════════╣
║  + uuid: String                             ║
║  + codigoSeguradora: String                 ║
║  + dataEmissao: LocalDate                   ║
║  + dataInicio: LocalDate                    ║
║  + dataTermino: LocalDate                   ║
║  ...                                        ║
╠═════════════════════════════════════════════╣
║  Entidades:                                 ║
║  ├─ Segurado [0..N]                        ║
║  ├─ Beneficiario [0..N]                    ║
║  ├─ Tomador [0..N]                         ║
║  ├─ Intermediario [0..N]                   ║
║  └─ ObjetoSegurado [0..N]                  ║
║      ├─ Cobertura [1..N]                   ║
║      │   └─ Franquia [0..N]                ║
║      ├─ ObjetoRural [0..1]                 ║
║      └─ ObjetoPatrimonial [0..1]           ║
╠═════════════════════════════════════════════╣
║  Value Objects:                             ║
║  ├─ Ccg [0..N]                             ║
║  ├─ PremioApolice [0..1]                   ║
║  └─ Cosseguro [0..1]                       ║
║      └─ CessionariasCosseguro [0..N]       ║
╚═════════════════════════════════════════════╝
```

---

## 🛡️ Anti-Corruption Layer

Quando integrar com sistemas legados ou externos, use ACL:

```java
public interface SusepServiceAdapter {
    SeguradoraInfo consultarSeguradora(String codigo);
    boolean validarNumeroProcesso(String numeroProcesso);
}

public class SusepAntiCorruptionLayer {
    private final SusepServiceAdapter adapter;
    
    public boolean isSeguradoraAtiva(String codigo) {
        SeguradoraInfo info = adapter.consultarSeguradora(codigo);
        // Traduz modelo externo para modelo interno
        return info != null && info.getSituacao().equals("ATIVA");
    }
}
```

---

## 📚 Referências DDD

- **Livro:** Domain-Driven Design: Tackling Complexity in the Heart of Software (Eric Evans)
- **Livro:** Implementing Domain-Driven Design (Vaughn Vernon)
- **Pattern:** Aggregate Pattern
- **Pattern:** Repository Pattern
- **Pattern:** Specification Pattern
- **Concept:** Ubiquitous Language
- **Concept:** Bounded Context

---

## ✅ Checklist DDD

- [x] Agregados identificados e definidos
- [x] Raiz do agregado estabelecida (Documento)
- [x] Invariantes de negócio documentadas
- [x] Objetos de valor imutáveis
- [x] Linguagem ubíqua definida
- [x] Bounded contexts mapeados
- [x] Repository pattern planejado
- [x] Domain events identificados
- [x] Factory pattern para criação complexa
- [x] Specifications para validações

---

**Versão:** 2.0.0  
**Domínio:** Sistema de Registro de Operações (SRO) - SUSEP  
**Arquitetura:** Domain-Driven Design (DDD)  
**Data:** Novembro 2025
