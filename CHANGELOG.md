# Changelog

Todas as mudanças notáveis neste projeto serão documentadas neste arquivo.

O formato é baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/1.0.0/),
e este projeto adere ao [Semantic Versioning](https://semver.org/lang/pt-BR/).

## [Unreleased]

### Planejado
- Exemplos de integração
- Mais casos de teste para cenários edge

## [0.0.2] - 2025-11-24

### 🔒 Segurança
- Validações fail-fast expandidas para todas as classes de domínio e subdomínios (Documento, Endosso, Movimento Prêmio, CCG, ComplAuto, Movimento Sinistro e Sinistro), garantindo presença de campos obrigatórios, limites de tamanho, domínios válidos e consistência temporal.

### ♻️ Refatoração
- Defensive copies para coleções em agregados aninhados, evitando mutabilidade externa após construção.

## [0.0.1] - 2025-11-22

### 🎉 Primeira Release Oficial

**Implementação Completa da Especificação SUSEP SRO v2.0.0**

### ✨ Adicionado

#### Domínio e Modelagem
- 36 classes Java 25 Records implementadas
- 7 bounded contexts modelados seguindo Domain-Driven Design
- Validações com Java puro (zero dependências runtime)
- Compact constructors com fail-fast validation
- 100% imutabilidade e thread-safety

#### Pacotes Implementados
- `br.com.sro.model.documento` (15 classes) - Apólices e bilhetes de seguro
- `br.com.sro.model.endosso` (2 classes) - Alterações contratuais
- `br.com.sro.model.movimentopremio` (1 classe) - Movimentações financeiras de prêmios
- `br.com.sro.model.ccg` (4 classes) - Crédito com Garantia de Seguro
- `br.com.sro.model.complauto` (4 classes) - Complemento de seguros automóveis
- `br.com.sro.model.movimentosinistro` (3 classes) - Movimentos financeiros de sinistros
- `br.com.sro.model.sinistro` (6 classes) - Processos de sinistro
- `br.com.sro.model.util` (1 classe) - Utilitários de validação

#### ValidationUtils
- ✅ Validação de CPF com dígitos verificadores
- ✅ Validação de CNPJ com dígitos verificadores
- ✅ Validação de UUID (RFC 4122)
- ✅ Validação de código de moeda ISO 4217
- ✅ Validação de código de país ISO 3166-1 alpha-3
- ✅ Validações de sequência temporal (dataInicio < dataTermino)
- ✅ Validações de ranges e domínios
- ✅ Defensive copies para listas imutáveis

#### Testes e Qualidade
- 207 testes unitários (100% passing)
- **90% cobertura de linhas** (excedeu meta de 80%)
- **82% cobertura de branches** (excedeu meta de 70%)
- **94% mutation score com PIT** (excedeu meta de 80%)
- Testes organizados por bounded context e funcionalidade
- Suite completa de testes de validação
- Testes de imutabilidade e defensive copying

#### Documentação
- README.md completo com exemplos práticos
- CONTRIBUTING.md - Guia detalhado de contribuição
- LICENSE - Apache License 2.0
- CHANGELOG.md - Histórico de mudanças
- Documentação DDD completa por contexto (7 arquivos)
- JavaDoc 100% completo em todas as classes
- Estruturas JSON de referência extraídas
- Exemplos de uso para cada bounded context

#### Infraestrutura e CI/CD
- Maven POM configurado para Java 25
- JaCoCo 0.8.14 para cobertura de código
- PIT 1.21.0 para mutation testing
- JUnit 5 + AssertJ para testes
- GitHub Actions para CI/CD automático
- Badges automáticos de cobertura (JaCoCo)
- Quality gates configurados:
  - 80% line coverage (atingido: 90%)
  - 70% branch coverage (atingido: 82%)
  - 80% mutation score (atingido: 94%)
- Geração automática de relatórios HTML

### 🔧 Configuração Técnica
- **Java**: 25 (Records, Pattern Matching, Text Blocks)
- **Maven**: 3.9+
- **Encoding**: UTF-8
- **Zero dependências runtime**: Apenas Java Standard Library
- **Dependências de teste**: JUnit 5, AssertJ

### 📊 Estatísticas da Release
| Métrica | Valor | Meta | Status |
|---------|-------|------|--------|
| Classes | 36 | - | ✅ |
| Testes | 207 | - | ✅ |
| Line Coverage | 90% | 80% | ✅ |
| Branch Coverage | 82% | 70% | ✅ |
| Mutation Score | 94% | 80% | ✅ |
| JavaDoc | 100% | 100% | ✅ |
| Runtime Deps | 0 | 0 | ✅ |

### 🎯 Bounded Contexts
1. **Documento** - Gestão de apólices e bilhetes
2. **Endosso** - Alterações contratuais
3. **Movimento Prêmio** - Movimentações financeiras de prêmios
4. **CCG** - Crédito com Garantia de Seguro
5. **Complemento Auto** - Dados específicos de automóveis
6. **Movimento Sinistro** - Movimentações financeiras de sinistros
7. **Sinistro** - Processos de sinistro e indenizações

### 📝 Notas
- Primeira versão estável do projeto
- Pronto para uso em produção
- API estável e retrocompatível
- Seguindo Semantic Versioning

## [2.0.0] - 2025-11-22 [DEPRECATED]

### Adicionado
- ✨ 35 Java Records para modelo de dados completo SUSEP SRO v2.0.0
- ✅ ValidationUtils com 30+ métodos de validação (CPF, CNPJ, UUID, ISO)
- 🧪 Suite de testes com 15 testes unitários (100% passando)
- 📊 Configuração JaCoCo para cobertura de código (threshold 80%)
- 🧬 Configuração PIT para testes de mutação (threshold 80%)
- 📚 JavaDoc completo para todas as classes públicas
- 🔄 GitHub Actions para CI/CD
- 📦 Profile Maven para release no Maven Central
- 🔐 Suporte a assinatura GPG de artifacts

### Mudado
- 🎨 Todos comentários `//` convertidos para JavaDoc `/** */`
- ♻️ Compact constructors com validações fail-fast
- 🏗️ Arquitetura zero-dependências em runtime

### Documentação
- 📖 README-COVERAGE.md - Guia de cobertura e testes
- 📖 README-RELEASE.md - Guia de release Maven Central
- 📖 CHANGELOG.md - Histórico de mudanças

## [1.0.0] - 2024-01-01

### Adicionado
- 🎉 Versão inicial do projeto
- 📋 Modelos básicos do SUSEP SRO

---

## Tipos de mudanças

- `Adicionado` para novas funcionalidades
- `Mudado` para mudanças em funcionalidades existentes
- `Descontinuado` para funcionalidades que serão removidas
- `Removido` para funcionalidades removidas
- `Corrigido` para correções de bugs
- `Segurança` para vulnerabilidades corrigidas

## Emojis

- ✨ Nova funcionalidade
- 🐛 Bug fix
- 📚 Documentação
- 🎨 Formatação/estrutura
- ⚡ Performance
- ✅ Testes
- 🔒 Segurança
- ♻️ Refatoração
- 🔧 Configuração
- 🚀 Deploy/Release
