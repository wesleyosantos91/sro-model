# 🚀 Guia de Release para Maven Central

Este guia explica como fazer release automático para o Maven Central usando GitHub Actions.

---

## 📋 Pré-requisitos

### 1. Conta no Sonatype OSSRH

1. Crie uma conta em https://issues.sonatype.org/
2. Crie um ticket JIRA para reivindicar seu groupId (`br.com.sro`)
3. Aguarde aprovação (geralmente 1-2 dias úteis)

### 2. Chave GPG para assinar artefatos

```bash
# Gerar chave GPG
gpg --gen-key

# Listar chaves
gpg --list-secret-keys --keyid-format=long

# Exportar chave privada (para GitHub Secrets)
gpg --armor --export-secret-keys SEU_KEY_ID

# Publicar chave pública
gpg --keyserver keyserver.ubuntu.com --send-keys SEU_KEY_ID
```

### 3. Configurar GitHub Secrets

Adicione os seguintes secrets no GitHub (Settings → Secrets and variables → Actions):

| Secret | Descrição | Como obter |
|--------|-----------|------------|
| `OSSRH_USERNAME` | Username do Sonatype | Seu username do JIRA Sonatype |
| `OSSRH_TOKEN` | Token do Sonatype | User token gerado em https://s01.oss.sonatype.org/ |
| `GPG_PRIVATE_KEY` | Chave GPG privada | Output do comando `gpg --armor --export-secret-keys` |
| `GPG_PASSPHRASE` | Senha da chave GPG | Senha que você definiu ao criar a chave |

---

## 🎯 Como fazer um Release

### Opção 1: Via linha de comando (recomendado)

```bash
# 1. Certifique-se que está na branch main e atualizado
git checkout main
git pull origin main

# 2. Crie uma tag de versão
git tag v2.0.0
git push origin v2.0.0
```

### Opção 2: Via GitHub UI

1. Vá para **Releases** no GitHub
2. Clique em **Draft a new release**
3. Crie uma nova tag (ex: `v2.0.0`)
4. Publique a release

---

## ⚙️ O que acontece automaticamente

Quando você cria uma tag `v*.*.*`:

1. ✅ **Build**: Compila o projeto
2. ✅ **Tests**: Executa todos os testes
3. ✅ **Coverage**: Gera relatórios JaCoCo
4. ✅ **Quality Gates**: Verifica thresholds de cobertura
5. ✅ **Sign**: Assina os artefatos com GPG
6. ✅ **Deploy**: Publica no Maven Central
7. ✅ **GitHub Release**: Cria release no GitHub com JARs
8. ✅ **Release Notes**: Gera changelog automático

---

## 📦 Artifacts publicados

Após o release, os seguintes artifacts estarão disponíveis:

```xml
<!-- JAR principal -->
<dependency>
    <groupId>br.com.sro</groupId>
    <artifactId>sro-model</artifactId>
    <version>2.0.0</version>
</dependency>

<!-- Sources JAR -->
<dependency>
    <groupId>br.com.sro</groupId>
    <artifactId>sro-model</artifactId>
    <version>2.0.0</version>
    <classifier>sources</classifier>
</dependency>

<!-- Javadoc JAR -->
<dependency>
    <groupId>br.com.sro</groupId>
    <artifactId>sro-model</artifactId>
    <version>2.0.0</version>
    <classifier>javadoc</classifier>
</dependency>
```

---

## 🔍 Verificar publicação

### 1. Maven Central Search

https://search.maven.org/artifact/br.com.sro/sro-model

### 2. Maven Repository

https://mvnrepository.com/artifact/br.com.sro/sro-model

### 3. Sonatype Nexus

https://s01.oss.sonatype.org/#nexus-search;quick~br.com.sro

**Nota:** Pode levar até 2 horas para aparecer no Maven Central Search após a publicação.

---

## 📊 Workflows GitHub Actions

### 1. `maven-ci.yml` - Integração Contínua

**Triggers:**
- Push em `main` ou `develop`
- Pull requests para `main` ou `develop`

**Ações:**
- Build e testes
- Cobertura com JaCoCo
- Testes de mutação com PIT
- Upload de relatórios
- Comentário automático em PRs com cobertura

### 2. `maven-release.yml` - Release

**Triggers:**
- Push de tags `v*.*.*` (ex: `v2.0.0`, `v2.1.0`)

**Ações:**
- Build e testes completos
- Deploy para Maven Central
- Criação de GitHub Release
- Upload de artifacts (JARs)
- Upload de relatórios de cobertura

---

## 🐛 Troubleshooting

### Problema: GPG signing failed

```bash
# Verifique se a chave está correta
echo "$GPG_PRIVATE_KEY" | gpg --import

# Teste localmente
mvn clean verify -P release -Dgpg.passphrase=SUA_SENHA
```

### Problema: OSSRH authentication failed

```bash
# Verifique as credenciais em https://s01.oss.sonatype.org/
# Gere um novo token se necessário

# Teste localmente
mvn deploy -P release -Dossrh.username=SEU_USER -Dossrh.password=SEU_TOKEN
```

### Problema: Repository not found

- Verifique se você tem permissão para o groupId `br.com.sro`
- Confirme se o ticket JIRA foi aprovado
- Espere até 24h após aprovação

---

## 📝 Versionamento

Seguimos [Semantic Versioning 2.0.0](https://semver.org/):

- **MAJOR** (`1.0.0` → `2.0.0`): Breaking changes
- **MINOR** (`2.0.0` → `2.1.0`): Novas funcionalidades (backward compatible)
- **PATCH** (`2.1.0` → `2.1.1`): Bug fixes

### Exemplos

```bash
# Bug fix
git tag v2.0.1
git push origin v2.0.1

# Nova funcionalidade
git tag v2.1.0
git push origin v2.1.0

# Breaking change
git tag v3.0.0
git push origin v3.0.0
```

---

## 🎯 Checklist antes do Release

- [ ] Todos os testes passando
- [ ] Cobertura ≥ 80%
- [ ] JavaDoc atualizado
- [ ] CHANGELOG.md atualizado
- [ ] Version bumped no `pom.xml`
- [ ] README.md atualizado (se necessário)
- [ ] Branch `main` atualizada

---

## 🔗 Links Úteis

- [Sonatype OSSRH Guide](https://central.sonatype.org/publish/publish-guide/)
- [GPG Signing](https://central.sonatype.org/publish/requirements/gpg/)
- [Maven Central Publishing](https://central.sonatype.org/publish/)
- [Semantic Versioning](https://semver.org/)

---

## 📧 Suporte

Em caso de problemas:

1. Verifique os logs do GitHub Actions
2. Consulte a [documentação do Sonatype](https://central.sonatype.org/publish/publish-guide/)
3. Abra uma issue no repositório
4. Entre em contato: wesleyosantos91@gmail.com
