# 🎬 Quick Start - Release para Maven Central

## TL;DR - Para quem tem pressa

```bash
# 1. Configure os secrets no GitHub (uma vez)
# Settings → Secrets → Actions → New repository secret
# Adicione: OSSRH_USERNAME, OSSRH_TOKEN, GPG_PRIVATE_KEY, GPG_PASSPHRASE

# 2. Crie uma tag e faça release
git tag v2.0.0
git push origin v2.0.0

# 3. Aguarde! 🎉
# GitHub Actions irá automaticamente:
# - Build + Testes
# - Deploy no Maven Central
# - Criar GitHub Release
```

## ✅ Checklist Rápido

### Primeira vez (apenas uma vez)

- [ ] Criar conta no [Sonatype OSSRH](https://issues.sonatype.org/)
- [ ] Criar ticket JIRA para `br.com.sro`
- [ ] Aguardar aprovação (~2 dias)
- [ ] Gerar chave GPG: `gpg --gen-key`
- [ ] Publicar chave: `gpg --keyserver keyserver.ubuntu.com --send-keys KEY_ID`
- [ ] Configurar 4 secrets no GitHub (ver abaixo)

### A cada release

- [ ] Atualizar versão no `pom.xml`
- [ ] Atualizar `CHANGELOG.md`
- [ ] Commit e push para `main`
- [ ] Criar tag: `git tag v2.0.0`
- [ ] Push da tag: `git push origin v2.0.0`
- [ ] Aguardar workflow do GitHub Actions
- [ ] Verificar em https://search.maven.org/

## 🔑 Secrets do GitHub

Configure em: **Settings → Secrets and variables → Actions**

| Secret | Como obter |
|--------|------------|
| `OSSRH_USERNAME` | Seu username do Sonatype JIRA |
| `OSSRH_TOKEN` | https://s01.oss.sonatype.org/ → Profile → User Token |
| `GPG_PRIVATE_KEY` | `gpg --armor --export-secret-keys KEY_ID` |
| `GPG_PASSPHRASE` | Senha da sua chave GPG |

## 🚀 Criar Release

```bash
# Método 1: Via CLI (recomendado)
git tag v2.0.0
git push origin v2.0.0

# Método 2: Via GitHub UI
# GitHub → Releases → Draft a new release → Create tag: v2.0.0
```

## 📦 Usar a biblioteca

```xml
<dependency>
    <groupId>br.com.sro</groupId>
    <artifactId>sro-model</artifactId>
    <version>2.0.0</version>
</dependency>
```

## 🔍 Verificar Release

- **Maven Central:** https://search.maven.org/artifact/br.com.sro/sro-model
- **GitHub Releases:** https://github.com/wesleysantos91/sro/releases
- **Workflows:** https://github.com/wesleysantos91/sro/actions

## 📚 Documentação Completa

- 📖 [README-RELEASE.md](README-RELEASE.md) - Guia detalhado
- 📖 [docs/GITHUB-SECRETS.md](docs/GITHUB-SECRETS.md) - Como configurar secrets
- 📖 [CHANGELOG.md](CHANGELOG.md) - Histórico de versões

## ❓ Problemas Comuns

**Q: Erro "401 Unauthorized"**
```bash
A: Verifique OSSRH_USERNAME e OSSRH_TOKEN
   Gere novo token em: https://s01.oss.sonatype.org/
```

**Q: Erro "GPG signing failed"**
```bash
A: Verifique se copiou TODA a chave privada
   Deve incluir: -----BEGIN PGP PRIVATE KEY BLOCK-----
```

**Q: Quanto tempo até aparecer no Maven Central?**
```bash
A: ~10 minutos no Nexus, ~2 horas no Maven Central Search
```

## 💡 Dicas

- Use `v` no início da tag (v2.0.0, não 2.0.0)
- Siga [Semantic Versioning](https://semver.org/)
- Mantenha o CHANGELOG.md atualizado
- Teste localmente antes: `mvn clean verify -P release`

---

🆘 **Precisa de ajuda?** Leia [README-RELEASE.md](README-RELEASE.md) ou abra uma issue!
