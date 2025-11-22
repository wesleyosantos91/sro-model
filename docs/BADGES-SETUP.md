# 🎨 Badges de Cobertura - Configuração

Este guia explica como configurar badges automáticos de cobertura JaCoCo e PIT.

---

## 🎯 Badges Configurados

### 1. **JaCoCo Coverage Badge** 
- Gerado automaticamente pelo GitHub Actions
- Salvo em `.github/badges/jacoco.svg`
- Atualizado a cada push na branch `main`

### 2. **PIT Mutation Badge**
- Gerado via Gist (atualização dinâmica)
- Não precisa commit
- Cores automáticas: Verde (≥80%), Amarelo (≥60%), Vermelho (<60%)

---

## 🔧 Configuração Inicial

### Passo 1: Criar Gist para badge dinâmico

1. Acesse https://gist.github.com/
2. Clique em "Create gist"
3. Crie um arquivo: `sro-pit-mutation.json`
4. Conteúdo inicial:
```json
{
  "schemaVersion": 1,
  "label": "mutation",
  "message": "0%",
  "color": "red"
}
```
5. Crie como **Public Gist**
6. Copie o **Gist ID** da URL (ex: `abc123def456`)

### Passo 2: Criar Personal Access Token

1. Acesse https://github.com/settings/tokens
2. Clique em **Generate new token (classic)**
3. Nome: `Coverage Badges Token`
4. Selecione o scope: `gist` (apenas este)
5. Clique em **Generate token**
6. **Copie o token** (você não verá novamente!)

### Passo 3: Configurar Secrets no GitHub

Vá em: **Settings → Secrets and variables → Actions → New repository secret**

Adicione dois secrets:

| Secret Name | Value | Descrição |
|-------------|-------|-----------|
| `GIST_SECRET` | `ghp_xxxxxxxxxxxx` | Token do GitHub (passo 2) |
| `GIST_ID` | `abc123def456` | ID do Gist (passo 1) |

---

## 📖 Como usar os badges no README

### Opção 1: Badge do Gist (PIT - Dinâmico)

```markdown
![Mutation Coverage](https://img.shields.io/endpoint?url=https://gist.githubusercontent.com/SEU_USERNAME/SEU_GIST_ID/raw/sro-pit-mutation.json)
```

**Substitua:**
- `SEU_USERNAME` → seu username do GitHub
- `SEU_GIST_ID` → ID do gist criado

**Exemplo:**
```markdown
![Mutation Coverage](https://img.shields.io/endpoint?url=https://gist.githubusercontent.com/wesleysantos91/abc123def456/raw/sro-pit-mutation.json)
```

### Opção 2: Badge do JaCoCo (via arquivo)

```markdown
![Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)
```

### Opção 3: Badges via Codecov

```markdown
[![Coverage](https://codecov.io/gh/wesleysantos91/sro/branch/main/graph/badge.svg)](https://codecov.io/gh/wesleysantos91/sro)
```

### Opção 4: Todas juntas

```markdown
[![Build](https://github.com/wesleysantos91/sro/workflows/Maven%20CI/badge.svg)](https://github.com/wesleysantos91/sro/actions)
[![Coverage](.github/badges/jacoco.svg)](https://github.com/wesleysantos91/sro/actions)
[![Branches](.github/badges/branches.svg)](https://github.com/wesleysantos91/sro/actions)
[![Mutation](https://img.shields.io/endpoint?url=https://gist.githubusercontent.com/wesleysantos91/abc123/raw/sro-pit-mutation.json)](https://github.com/wesleysantos91/sro/actions)
[![Codecov](https://codecov.io/gh/wesleysantos91/sro/branch/main/graph/badge.svg)](https://codecov.io/gh/wesleysantos91/sro)
```

---

## 🎨 Exemplo de README com Badges

```markdown
# SRO Model

[![Build Status](https://github.com/wesleysantos91/sro/workflows/Maven%20CI/badge.svg)](https://github.com/wesleysantos91/sro/actions)
[![Coverage](.github/badges/jacoco.svg)](https://github.com/wesleysantos91/sro/actions)
[![Branches](.github/badges/branches.svg)](https://github.com/wesleysantos91/sro/actions)
[![Mutation Coverage](https://img.shields.io/endpoint?url=https://gist.githubusercontent.com/wesleysantos91/abc123/raw/sro-pit-mutation.json)](https://github.com/wesleysantos91/sro/actions)
[![Maven Central](https://img.shields.io/maven-central/v/br.com.sro/sro-model.svg)](https://search.maven.org/artifact/br.com.sro/sro-model)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Sistema de Registro de Operações - Modelo de Dados SUSEP SRO v2.0.0

## 📊 Quality Metrics

| Metric | Coverage |
|--------|----------|
| Line Coverage (JaCoCo) | ![Coverage](.github/badges/jacoco.svg) |
| Branch Coverage | ![Branches](.github/badges/branches.svg) |
| Mutation Coverage (PIT) | ![Mutation](https://img.shields.io/endpoint?url=https://gist.githubusercontent.com/wesleysantos91/abc123/raw/sro-pit-mutation.json) |
```

---

## 🔄 Como funciona

### A cada push na branch `main`:

1. **JaCoCo** executa análise de cobertura
2. **PIT** executa testes de mutação
3. GitHub Actions:
   - Gera `jacoco.svg` e `branches.svg`
   - Extrai dados do PIT do `mutations.xml`
   - Atualiza o Gist com novo valor
   - Faz commit dos badges SVG (apenas JaCoCo)
4. Badges são atualizados automaticamente!

### Vantagens:

- ✅ **Automático**: Atualiza a cada build
- ✅ **Sem manutenção**: Zero configuração após setup inicial
- ✅ **Cores dinâmicas**: Verde/Amarelo/Vermelho baseado nos valores
- ✅ **Sempre atual**: Reflete o último build

---

## 📊 Summary no GitHub Actions

Após cada build, um summary é gerado com:

```
## 📊 Coverage Summary

| Metric | Coverage |
|--------|----------|
| JaCoCo Lines | 85.3% |
| JaCoCo Branches | 78.2% |
| PIT Mutation | 82.5% |
| PIT Line Coverage | 86.1% |
```

Acesse em: **Actions → Workflow Run → Summary**

---

## 🎨 Cores dos badges

### JaCoCo (automático)
- 🟢 Verde: ≥ 90%
- 🟡 Amarelo: 70-89%
- 🔴 Vermelho: < 70%

### PIT (configurável)
- 🟢 Verde: ≥ 80%
- 🟡 Amarelo: 60-79%
- 🔴 Vermelho: < 60%

**Personalizar:** Edite o workflow `.github/workflows/maven-ci.yml`:

```yaml
color: ${{ steps.pit.outputs.MUTATION_COVERAGE >= 90 && 'brightgreen' || steps.pit.outputs.MUTATION_COVERAGE >= 75 && 'green' || steps.pit.outputs.MUTATION_COVERAGE >= 60 && 'yellow' || 'red' }}
```

---

## 🐛 Troubleshooting

### Problema: Badge não aparece

**Solução:**
1. Verifique se o Gist é **público**
2. Confirme que os secrets estão corretos
3. Execute o workflow manualmente
4. Aguarde 1-2 minutos para cache do badge

### Problema: Badge não atualiza

**Solução:**
1. Limpe cache do browser
2. Adicione `?v=1` ao final da URL: `...raw/badge.json?v=1`
3. Verifique logs do GitHub Actions

### Problema: "Error: No gist found"

**Solução:**
1. Verifique `GIST_ID` está correto
2. Confirme que `GIST_SECRET` tem permissão `gist`
3. Teste o token: `curl -H "Authorization: token TOKEN" https://api.github.com/user`

---

## 🔗 Links Úteis

- [JaCoCo Badge Generator](https://github.com/cicirello/jacoco-badge-generator)
- [Dynamic Badges Action](https://github.com/schneegans/dynamic-badges-action)
- [Shields.io](https://shields.io/)
- [GitHub Gist API](https://docs.github.com/en/rest/gists)

---

## ✅ Checklist

- [ ] Criar Gist público
- [ ] Copiar Gist ID
- [ ] Gerar Personal Access Token (scope: gist)
- [ ] Adicionar `GIST_SECRET` nos secrets
- [ ] Adicionar `GIST_ID` nos secrets
- [ ] Atualizar README.md com badges
- [ ] Fazer push e verificar workflow
- [ ] Confirmar que badges aparecem

---

**Dica:** Use cache busting adicionando `?v=TIMESTAMP` nas URLs dos badges se eles não atualizarem imediatamente!
