# Changelog

Todas as mudanças notáveis neste projeto serão documentadas neste arquivo.

O formato é baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/1.0.0/),
e este projeto adere ao [Semantic Versioning](https://semver.org/lang/pt-BR/).

## [Unreleased]

### Planejado
- Mais testes de mutação
- Integração com SonarQube

## [2.0.0] - 2025-11-22

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
