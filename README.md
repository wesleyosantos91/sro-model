# 📋 SRO Model - Sistema de Registro de Operações

[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://openjdk.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.wesleyosantos91.susep.sro/sro-model)](https://central.sonatype.com/artifact/io.github.wesleyosantos91.susep.sro/sro-model)
[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg)](LICENSE)
[![Maven CI](https://github.com/wesleyosantos91/sro-model/actions/workflows/maven-ci.yml/badge.svg)](https://github.com/wesleyosantos91/sro-model/actions/workflows/maven-ci.yml)

## 📊 Code Coverage

![Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)
[![Mutation Testing](https://img.shields.io/badge/mutation-94%25-brightgreen.svg)](https://github.com/wesleyosantos91/sro-model/actions)

## 🎯 Sobre o Projeto

Modelo de dados Java baseado na **especificação SUSEP SRO v2.0.0** (Sistema de Registro de Operações). Este projeto transforma as especificações do Excel oficial da SUSEP em classes Java 25 Records imutáveis, seguindo os princípios de Domain-Driven Design (DDD).

## 🏗️ Estrutura do Projeto

O projeto está organizado em **7 bounded contexts**, cada um representando uma aba da especificação SUSEP:

```
io.github.wesleyosantos91.susep.sro.model
├── 📦 documento         (15 classes) - Apólices e bilhetes de seguro
├── 📦 endosso           (2 classes)  - Alterações em apólices
├── 📦 movimentopremio   (1 classe)   - Movimentações financeiras de prêmios
├── 📦 ccg               (4 classes)  - Crédito com Garantia de Seguro
├── 📦 complauto         (4 classes)  - Complemento de seguros automóveis
├── 📦 movimentosinistro (3 classes)  - Movimentações financeiras de sinistros
└── 📦 sinistro          (6 classes)  - Processos de sinistro
```

**Total:** 36 classes Java Record com JavaDoc completo e utilitários de validação

## 🚀 Tecnologias

- **Java 25** - Records, Pattern Matching, Text Blocks
- **Maven 3.9+** - Gerenciamento de dependências e build
- **JUnit 5** - Framework de testes
- **AssertJ** - Assertions fluentes
- **Zero Dependências Runtime** - Validações com Java puro

## 📋 Pré-requisitos

- Java 25 (OpenJDK ou Oracle JDK)
- Maven 3.9 ou superior
- Git

## 🔧 Instalação e Build

```bash
# Clonar o repositório
git clone https://github.com/wesleysantos/sro.git
cd sro

# Compilar o projeto
mvn clean compile

# Executar testes
mvn test

# Gerar JAR
mvn package

# Gerar documentação JavaDoc
mvn javadoc:javadoc
```

## 📦 Uso

### Maven Dependency (futuro)

```xml
<dependency>
    <groupId>io.github.wesleyosantos91.susep.sro</groupId>
    <artifactId>sro-model</artifactId>
    <version>0.0.1</version>
</dependency>
```

### Exemplo de Código

```java
import io.github.wesleyosantos91.susep.sro.model.documento.*;
import io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils;
import java.math.BigDecimal;
import java.time.LocalDate;

// Validar CPF/CNPJ antes de criar objetos
ValidationUtils.validateCpf("12345678901");
ValidationUtils.validateCnpj("12345678000195");

// Criar uma apólice
var documento = new Documento(
    "uuid-12345",
    null,
    "12345",
    LocalDate.now(),
    LocalDate.now(),
    1,
    "AP-2024-001",
    // ... demais campos
);

// Criar um sinistro
var sinistro = new Sinistro(
    "uuid-67890",
    null,
    "12345",
    LocalDate.now(),
    LocalDate.now(),
    1,
    "SIN-2024-001",
    // ... demais campos
);
```

## 📚 Documentação

A documentação completa está disponível na pasta `docs/`:

- **Classes:** `docs/README-{CONTEXTO}-CLASSES.md`
- **DDD:** `docs/README-{CONTEXTO}-DDD.md`
- **Sumário:** `docs/SUMARIO-{CONTEXTO}.md`
- **JavaDoc:** `target/site/apidocs/`
- **Estruturas JSON:** `docs/structure-models/*.json`
- **Validações Java Puro:** `docs/VALIDACOES-JAVA-PURO.md` ✨

### Bounded Contexts Documentados

1. [DOCUMENTO](docs/README-DOCUMENTO-CLASSES.md) - Apólices e bilhetes
2. [ENDOSSO](docs/README-ENDOSSO-CLASSES.md) - Alterações contratuais
3. [MOVIMENTO_PREMIO](docs/README-MOVIMENTO_PREMIO-CLASSES.md) - Prêmios
4. [CCG](docs/README-CCG-CLASSES.md) - Crédito com Garantia
5. [COMPL_AUTO](docs/README-COMPL_AUTO-CLASSES.md) - Complemento Automóvel
6. [MOVIMENTO_SINISTRO](docs/README-MOVIMENTO_SINISTRO-DDD.md) - Movimentos de sinistro
7. [SINISTRO](docs/README-SINISTRO-DDD.md) - Processos de sinistro

## 🎯 Características

### ✅ Java 25 Records
- Imutabilidade por design
- Thread-safe
- Sintaxe concisa
- Performance otimizada

### ✅ JavaDoc Completo
Cada campo contém metadados SUSEP:
- Cardinalidade (1..1, 0..1, 1..n)
- Tag (identificador SUSEP)
- Tipo de dado
- Formato
- Tamanho
- Condições
- Observações

### ✅ Domain-Driven Design
- Bounded Contexts bem definidos
- Aggregate Roots identificados
- Value Objects imutáveis
- Domain Events mapeados
- Domain Services especificados
- Invariantes garantidas

### ✅ Validações com Java Puro
- **Zero dependências externas** - Sem frameworks de validação
- **Fail-fast** - Objetos inválidos nunca são criados
- **Compact Constructors** - Validações na construção do Record
- **ValidationUtils** - Classe utilitária reutilizável
- **CPF/CNPJ** - Validação com dígitos verificadores
- **Formatos ISO** - UUID, Moeda (ISO 4217), País (ISO 3166-1)
- **Datas** - Validações de sequência e regras temporais
- **Ranges** - Validações de domínio e limites
- **Imutabilidade** - Listas defensively copied

## 🏛️ Arquitetura

### Padrões Aplicados

- **Aggregate Pattern** - Garantia de consistência transacional
- **Value Object Pattern** - Objetos imutáveis por valor
- **Repository Pattern** - Abstração de persistência
- **Domain Events** - Eventos de negócio
- **Ubiquitous Language** - Linguagem do domínio de seguros

### Princípios SOLID

- ✅ Single Responsibility
- ✅ Open/Closed
- ✅ Liskov Substitution
- ✅ Interface Segregation
- ✅ Dependency Inversion

## 📊 Estatísticas

- **36 classes Java** (100% Records)
- **207 testes unitários** (100% passing)
- **7 bounded contexts** (DDD)
- **90% line coverage** | **82% branch coverage** | **94% mutation score**
- **100% cobertura de JavaDoc**
- **Zero dependências runtime**

## 📂 Estrutura de Diretórios

```
sro/
├── src/main/java/io/github/wesleyosantos91/susep/sro/model/  # Classes Java 25 Records
│   ├── documento/                      # 15 classes
│   ├── endosso/                        # 2 classes
│   ├── movimentopremio/                # 1 classe
│   ├── ccg/                            # 4 classes
│   ├── complauto/                      # 4 classes
│   ├── movimentosinistro/              # 3 classes
│   ├── sinistro/                       # 6 classes
│   └── util/                           # 1 classe (ValidationUtils)
├── docs/                               # Documentação completa
│   ├── README-*-CLASSES.md            # Documentação das classes
│   ├── README-*-DDD.md                # Análise DDD
│   ├── SUMARIO-*.md                   # Sumários executivos
│   └── structure-models/              # Estruturas JSON extraídas
│       ├── documento_estruturado.json
│       ├── endosso_estruturado.json
│       ├── movimento_premio_estruturado.json
│       ├── ccg_estruturado.json
│       ├── compl_auto_estruturado.json
│       ├── movimento_sinistro_estruturado.json
│       └── sinistro_estruturado.json
├── pom.xml                            # Maven POM
├── .mvn/                              # Configuração Maven
├── .gitignore                         # Git ignore
└── README.md                          # Este arquivo
```

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Leia o [Guia de Contribuição](CONTRIBUTING.md)
2. Fork o projeto
3. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
4. Commit suas mudanças seguindo [Conventional Commits](https://www.conventionalcommits.org/)
5. Push para a branch (`git push origin feature/AmazingFeature`)
6. Abra um Pull Request

Veja [CONTRIBUTING.md](CONTRIBUTING.md) para detalhes sobre padrões de código, testes e processo de desenvolvimento.

## 👤 Desenvolvedor

**Wesley Santos**
- GitHub: [@wesleysantos91](https://github.com/wesleysantos91)
- Email: wesleyosantos91@gmail.com

## 📄 Licença

Este projeto está sob a licença Apache 2.0 - veja o arquivo [LICENSE](LICENSE) para detalhes.

**Copyright © 2025 Wesley Oliveira Santos**

## 🔗 Referências

- [SUSEP - Superintendência de Seguros Privados](https://www2.susep.gov.br/)
- [Especificação SRO v2.0.0](https://www2.susep.gov.br/)
- [Domain-Driven Design](https://www.domainlanguage.com/ddd/)
- [Java Records](https://openjdk.org/jeps/395)

## 📝 Changelog

### [v0.0.1](https://github.com/wesleyosantos91/sro-model/releases/tag/v0.0.1) (2025-11-22)

#### 🎉 Primeira Release

**Implementação Completa da Especificação SUSEP SRO v2.0.0**

##### ✨ Features
- ✅ 36 classes Java 25 Records implementadas
- ✅ 7 bounded contexts modelados (DDD)
- ✅ 207 testes unitários (100% passing)
- ✅ Validações com Java puro (zero dependências runtime)
- ✅ ValidationUtils com CPF/CNPJ, UUID, ISO 4217/3166-1
- ✅ Compact constructors com fail-fast
- ✅ 100% JavaDoc coverage
- ✅ Pacote refatorado para Maven Central (`io.github.wesleyosantos91.susep.sro.model`)

##### 📊 Quality Metrics
- ✅ **90% cobertura de linhas** (meta: 80%)
- ✅ **82% cobertura de branches** (meta: 70%)
- ✅ **94% mutation score** (meta: 80%)
- ✅ CI/CD configurado com GitHub Actions
- ✅ Badges automáticos de cobertura

##### 📦 Pacotes Implementados
- `io.github.wesleyosantos91.susep.sro.model.documento` (15 classes) - Apólices e bilhetes
- `io.github.wesleyosantos91.susep.sro.model.endosso` (2 classes) - Alterações contratuais
- `io.github.wesleyosantos91.susep.sro.model.movimentopremio` (1 classe) - Prêmios
- `io.github.wesleyosantos91.susep.sro.model.ccg` (4 classes) - Crédito com Garantia
- `io.github.wesleyosantos91.susep.sro.model.complauto` (4 classes) - Complemento Automóvel
- `io.github.wesleyosantos91.susep.sro.model.movimentosinistro` (3 classes) - Movimentos de sinistro
- `io.github.wesleyosantos91.susep.sro.model.sinistro` (6 classes) - Processos de sinistro
- `io.github.wesleyosantos91.susep.sro.model.util` (1 classe) - Utilitários de validação

##### 🔄 Refatoração para Maven Central
- ✅ Mudança de groupId: `br.com.sro` → `io.github.wesleyosantos91.susep.sro`
- ✅ Estrutura de pacotes alinhada com convenções do Maven Central
- ✅ Configuração OSSRH para publicação
- ✅ Todos os testes e métricas de qualidade mantidos após refatoração

##### 📚 Documentação
- ✅ README completo com exemplos
- ✅ Guia de contribuição (CONTRIBUTING.md)
- ✅ Licença Apache 2.0
- ✅ Documentação DDD por contexto
- ✅ Estruturas JSON de referência

##### 🔧 Configuração
- ✅ Maven POM configurado
- ✅ JaCoCo (cobertura de código)
- ✅ PIT (mutation testing)
- ✅ JUnit 5 + AssertJ
- ✅ GitHub Actions CI/CD

---

⭐ Se este projeto foi útil, considere dar uma estrela no GitHub!
