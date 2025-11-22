# 🤝 Guia de Contribuição

Obrigado por considerar contribuir com o **SRO Model**! Este documento fornece diretrizes para contribuir com o projeto.

## 📋 Índice

- [Código de Conduta](#código-de-conduta)
- [Como Posso Contribuir?](#como-posso-contribuir)
- [Configuração do Ambiente](#configuração-do-ambiente)
- [Processo de Desenvolvimento](#processo-de-desenvolvimento)
- [Padrões de Código](#padrões-de-código)
- [Padrões de Commit](#padrões-de-commit)
- [Pull Requests](#pull-requests)
- [Reportando Bugs](#reportando-bugs)
- [Sugerindo Melhorias](#sugerindo-melhorias)

## 📜 Código de Conduta

Este projeto e todos os participantes estão sujeitos ao seguinte código de conduta:

- **Seja respeitoso**: Trate todos com respeito e consideração
- **Seja colaborativo**: Trabalhe em conjunto para melhorar o projeto
- **Seja inclusivo**: Aceite diferentes perspectivas e experiências
- **Seja profissional**: Mantenha discussões focadas e construtivas

## 🚀 Como Posso Contribuir?

Existem várias formas de contribuir:

### 1. Reportar Bugs 🐛
- Use a aba [Issues](https://github.com/wesleyosantos91/sro-model/issues)
- Descreva o problema detalhadamente
- Inclua passos para reproduzir
- Informe a versão do Java e do projeto

### 2. Sugerir Melhorias 💡
- Abra uma issue com a tag `enhancement`
- Explique o benefício da melhoria
- Forneça exemplos de uso, se possível

### 3. Melhorar Documentação 📚
- Corrija erros de digitação
- Melhore exemplos existentes
- Adicione novos exemplos
- Traduza documentação

### 4. Contribuir com Código 💻
- Implemente novas features
- Corrija bugs existentes
- Melhore testes
- Otimize performance

## 🛠️ Configuração do Ambiente

### Pré-requisitos

```bash
# Java 25
java -version
# java version "25" 2025-09-17

# Maven 3.9+
mvn -version
# Apache Maven 3.9.x

# Git
git --version
```

### Clone e Setup

```bash
# 1. Fork o repositório no GitHub

# 2. Clone seu fork
git clone git@github.com:SEU_USUARIO/sro-model.git
cd sro-model

# 3. Adicione o repositório original como upstream
git remote add upstream git@github.com:wesleyosantos91/sro-model.git

# 4. Compile o projeto
mvn clean compile

# 5. Execute os testes
mvn test

# 6. Verifique a cobertura
mvn verify jacoco:report
```

## 🔄 Processo de Desenvolvimento

### 1. Crie uma Branch

```bash
# Atualize seu fork
git checkout main
git pull upstream main

# Crie uma branch descritiva
git checkout -b feature/nome-da-feature
# ou
git checkout -b fix/nome-do-bug
# ou
git checkout -b docs/melhoria-documentacao
```

### 2. Faça suas Alterações

- Escreva código limpo e idiomático
- Adicione testes para novas funcionalidades
- Mantenha a cobertura de testes acima de 80%
- Atualize a documentação conforme necessário
- Siga os padrões de código do projeto

### 3. Execute os Testes

```bash
# Testes unitários
mvn test

# Cobertura
mvn jacoco:report

# Mutation testing
mvn org.pitest:pitest-maven:mutationCoverage

# Verificação completa
mvn verify
```

### 4. Commit suas Alterações

```bash
git add .
git commit -m "tipo: descrição breve"
```

### 5. Push para seu Fork

```bash
git push origin feature/nome-da-feature
```

### 6. Abra um Pull Request

- Vá para o repositório original no GitHub
- Clique em "New Pull Request"
- Selecione sua branch
- Preencha o template do PR

## 📏 Padrões de Código

### Java

- **Java 25**: Use Records, Pattern Matching, Text Blocks
- **Imutabilidade**: Prefira objetos imutáveis
- **Null Safety**: Evite nulls, use Optional quando apropriado
- **Validações**: Use compact constructors para validação
- **JavaDoc**: Documente classes e métodos públicos

```java
/**
 * Descrição breve da classe.
 * 
 * <p>Descrição detalhada se necessário.
 * 
 * @param campo1 Descrição do campo1
 * @param campo2 Descrição do campo2
 * @author Nome do Autor
 * @since 2.0.0
 */
public record MinhaClasse(
    String campo1,
    Integer campo2
) {
    public MinhaClasse {
        if (campo1 == null || campo1.isBlank()) {
            throw new IllegalArgumentException("campo1 não pode ser vazio");
        }
    }
}
```

### Testes

- **Nomes Descritivos**: Use nomes que descrevam o comportamento
- **Given-When-Then**: Estruture testes claramente
- **AssertJ**: Use assertions fluentes
- **Cobertura**: Mínimo 80% de linhas e branches

```java
@Test
void deveValidarCampoObrigatorio() {
    // Given
    String campoInvalido = null;
    
    // When & Then
    assertThatThrownBy(() -> new MinhaClasse(campoInvalido, 123))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("campo1 não pode ser vazio");
}

@Test
void deveCriarObjetoValido() {
    // Given
    String campo1 = "valor";
    Integer campo2 = 123;
    
    // When
    var objeto = new MinhaClasse(campo1, campo2);
    
    // Then
    assertThat(objeto).isNotNull();
    assertThat(objeto.campo1()).isEqualTo("valor");
    assertThat(objeto.campo2()).isEqualTo(123);
}
```

## 📝 Padrões de Commit

Usamos [Conventional Commits](https://www.conventionalcommits.org/):

### Formato

```
tipo(escopo): descrição breve

[corpo opcional]

[rodapé opcional]
```

### Tipos

- `feat`: Nova funcionalidade
- `fix`: Correção de bug
- `docs`: Alterações na documentação
- `style`: Formatação, espaços em branco (não afeta código)
- `refactor`: Refatoração de código
- `test`: Adição ou correção de testes
- `chore`: Atualizações de build, dependências, etc.
- `perf`: Melhoria de performance

### Exemplos

```bash
# Feature
git commit -m "feat(documento): adicionar validação de CPF/CNPJ"

# Bug fix
git commit -m "fix(validacao): corrigir validação de data nula"

# Documentação
git commit -m "docs(readme): atualizar exemplos de uso"

# Testes
git commit -m "test(sinistro): adicionar testes para SinistroTest"

# Refatoração
git commit -m "refactor(util): extrair lógica de validação para ValidationUtils"
```

## 🔍 Pull Requests

### Checklist antes de Abrir um PR

- [ ] Código compila sem erros
- [ ] Todos os testes passam
- [ ] Cobertura de testes >= 80%
- [ ] Mutation score >= 80%
- [ ] Código segue os padrões do projeto
- [ ] JavaDoc atualizado
- [ ] README atualizado (se necessário)
- [ ] CHANGELOG atualizado (se necessário)
- [ ] Commits seguem Conventional Commits

### Template de PR

```markdown
## Descrição
Descreva claramente o que este PR faz.

## Tipo de Mudança
- [ ] 🐛 Bug fix
- [ ] ✨ Nova feature
- [ ] 📚 Documentação
- [ ] ♻️ Refatoração
- [ ] 🧪 Testes
- [ ] ⚡ Performance

## Motivação e Contexto
Por que esta mudança é necessária?

## Como Foi Testado?
Descreva os testes realizados.

## Screenshots (se aplicável)
Cole screenshots se relevante.

## Checklist
- [ ] Código compila
- [ ] Testes passam
- [ ] Cobertura >= 80%
- [ ] Documentação atualizada
```

### Revisão de Código

- Seja construtivo e respeitoso
- Sugira melhorias, não imponha
- Foque no código, não na pessoa
- Responda aos comentários prontamente

## 🐛 Reportando Bugs

Use o template de issue para bugs:

```markdown
## Descrição do Bug
Descrição clara e concisa do bug.

## Para Reproduzir
Passos para reproduzir:
1. Faça '...'
2. Execute '...'
3. Veja o erro

## Comportamento Esperado
O que deveria acontecer.

## Comportamento Atual
O que está acontecendo.

## Screenshots
Se aplicável, adicione screenshots.

## Ambiente
- OS: [e.g. macOS 14]
- Java: [e.g. 25]
- Maven: [e.g. 3.9.5]
- Versão do Projeto: [e.g. 2.0.0]

## Informações Adicionais
Qualquer outra informação relevante.
```

## 💡 Sugerindo Melhorias

Use o template de issue para features:

```markdown
## Descrição da Feature
Descrição clara e concisa da feature.

## Problema que Resolve
Qual problema esta feature resolve?

## Solução Proposta
Como você imagina que a feature funcione?

## Alternativas Consideradas
Outras abordagens que você considerou?

## Benefícios
Quais os benefícios desta feature?

## Possíveis Desvantagens
Há alguma desvantagem?

## Informações Adicionais
Qualquer outra informação relevante.
```

## 🏆 Reconhecimento

Todos os contribuidores serão reconhecidos:

- No arquivo [CONTRIBUTORS.md](CONTRIBUTORS.md)
- Nas release notes
- No README do projeto

## 📞 Contato

- **GitHub Issues**: Para bugs e features
- **GitHub Discussions**: Para dúvidas e discussões
- **Email**: wesleyosantos91@gmail.com (para questões sensíveis)

## 📚 Recursos Adicionais

- [Java Records](https://openjdk.org/jeps/395)
- [Conventional Commits](https://www.conventionalcommits.org/)
- [JUnit 5](https://junit.org/junit5/)
- [AssertJ](https://assertj.github.io/doc/)
- [JaCoCo](https://www.jacoco.org/)
- [PIT Mutation Testing](https://pitest.org/)

---

**Obrigado por contribuir!** 🙏

Sua contribuição ajuda a tornar este projeto melhor para toda a comunidade de seguros no Brasil.
