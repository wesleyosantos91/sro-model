# 🛡️ Configuração de Segurança - Secrets do GitHub

Este documento explica como configurar os secrets necessários para publicação no Maven Central.

---

## 🔑 Secrets Necessários

Configure os seguintes secrets em: **Settings → Secrets and variables → Actions → New repository secret**

### 1. OSSRH_USERNAME

**Descrição:** Username da sua conta Sonatype OSSRH

**Como obter:**
1. Acesse https://issues.sonatype.org/
2. Seu username é o mesmo que você usa para login no JIRA

**Exemplo:**
```
wesleysantos91
```

---

### 2. OSSRH_TOKEN

**Descrição:** Token de autenticação do Sonatype

**Como obter:**
1. Acesse https://s01.oss.sonatype.org/
2. Faça login com suas credenciais
3. Clique no seu username → **Profile**
4. Na aba **User Token**, clique em **Access User Token**
5. Copie o **Password Code** (não o username)

**Formato:**
```
AbCdEfGh1234567890
```

**⚠️ Importante:** Use o Password Code, não o username do token!

---

### 3. GPG_PRIVATE_KEY

**Descrição:** Chave privada GPG para assinar os artifacts

**Como criar e exportar:**

```bash
# 1. Gerar nova chave GPG (se ainda não tiver)
gpg --gen-key
# Escolha:
# - RSA and RSA (default)
# - 4096 bits
# - Não expira (0)
# - Seu nome: Wesley Santos
# - Seu email: wesleyosantos91@gmail.com
# - Defina uma senha forte

# 2. Listar suas chaves
gpg --list-secret-keys --keyid-format=long
# Saída:
# sec   rsa4096/ABCD1234EFGH5678 2025-11-22 [SC]
#       1234567890ABCDEF1234567890ABCDEF12345678
# uid                 [ultimate] Wesley Santos <wesleyosantos91@gmail.com>

# 3. Exportar chave privada (use o ID após rsa4096/)
gpg --armor --export-secret-keys ABCD1234EFGH5678

# 4. Copie TODA a saída, incluindo as linhas:
# -----BEGIN PGP PRIVATE KEY BLOCK-----
# ... (muitas linhas) ...
# -----END PGP PRIVATE KEY BLOCK-----
```

**Formato do secret:**
```
-----BEGIN PGP PRIVATE KEY BLOCK-----

lQdGBGb...muitas linhas...xyz==
=abcd
-----END PGP PRIVATE KEY BLOCK-----
```

**⚠️ Atenção:**
- Cole TODO o conteúdo, incluindo as linhas BEGIN e END
- Mantenha as quebras de linha
- Não adicione espaços extras

---

### 4. GPG_PASSPHRASE

**Descrição:** Senha que você definiu ao criar a chave GPG

**Como obter:**
- É a senha que você digitou durante `gpg --gen-key`
- Use uma senha forte e segura

**Exemplo:**
```
MinhaS3nh@Forte123!
```

---

## 🔓 Publicar chave GPG pública

Após criar sua chave GPG, publique-a para validação:

```bash
# Publicar em múltiplos servidores (recomendado)
gpg --keyserver keyserver.ubuntu.com --send-keys ABCD1234EFGH5678
gpg --keyserver keys.openpgp.org --send-keys ABCD1234EFGH5678
gpg --keyserver pgp.mit.edu --send-keys ABCD1234EFGH5678
```

**Verificar publicação:**
- https://keyserver.ubuntu.com/
- https://keys.openpgp.org/
- https://pgp.mit.edu/

---

## ✅ Validar configuração

### Teste local antes de criar release:

```bash
# 1. Configurar variáveis locais
export GPG_PASSPHRASE="sua_senha_gpg"
export MAVEN_USERNAME="seu_username_ossrh"
export MAVEN_PASSWORD="seu_token_ossrh"

# 2. Testar build e signing
mvn clean verify -P release

# 3. Testar deploy (dry-run)
mvn clean deploy -P release -DskipTests -DaltDeploymentRepository=local::file:./target/staging-deploy
```

Se tudo funcionar localmente, funcionará no GitHub Actions!

---

## 🔒 Segurança

### Boas práticas:

✅ **FAZER:**
- Use secrets do GitHub (nunca commite credenciais)
- Use senhas fortes para GPG
- Rotacione tokens periodicamente
- Mantenha backup da chave GPG
- Use 2FA no Sonatype OSSRH

❌ **NÃO FAZER:**
- Não commite chaves ou senhas no código
- Não compartilhe sua chave privada GPG
- Não use senhas fracas
- Não publique tokens em logs
- Não desabilite GPG signing

---

## 🆘 Problemas comuns

### Erro: "gpg: signing failed: No secret key"

**Solução:**
```bash
# Verifique se importou a chave corretamente
echo "$GPG_PRIVATE_KEY" | gpg --import --batch

# Liste as chaves importadas
gpg --list-secret-keys
```

### Erro: "gpg: signing failed: Inappropriate ioctl for device"

**Solução:**
Adicione ao GitHub Actions (já configurado):
```yaml
gpgArguments:
  - --pinentry-mode
  - --loopback
```

### Erro: "401 Unauthorized" no deploy

**Soluções:**
1. Verifique se o token está correto
2. Gere um novo User Token no Sonatype
3. Confirme que seu ticket JIRA foi aprovado
4. Espere até 24h após aprovação

---

## 📚 Referências

- [Sonatype OSSRH Guide](https://central.sonatype.org/publish/publish-guide/)
- [GPG Documentation](https://gnupg.org/documentation/)
- [GitHub Encrypted Secrets](https://docs.github.com/en/actions/security-guides/encrypted-secrets)
- [Maven GPG Plugin](https://maven.apache.org/plugins/maven-gpg-plugin/)

---

## 📧 Ajuda

Se precisar de ajuda com a configuração:
- 📖 Consulte a [documentação do Sonatype](https://central.sonatype.org/publish/)
- 🐛 Abra uma issue no repositório
- 📧 Email: wesleyosantos91@gmail.com
