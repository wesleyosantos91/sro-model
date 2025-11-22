# 📊 Relatórios de Cobertura e Testes de Mutação

Este projeto utiliza **JaCoCo** para análise de cobertura de código e **PIT (Pitest)** para testes de mutação.

---

## 🎯 JaCoCo - Cobertura de Código

### Executar análise de cobertura

```bash
mvn clean test
```

### Visualizar relatórios

**Relatório HTML:**
```bash
open target/site/jacoco/index.html
```

**Relatório XML (CI/CD):**
```
target/site/jacoco/jacoco.xml
```

**Relatório CSV (análise):**
```
target/site/jacoco/jacoco.csv
```

### Métricas configuradas

- **Cobertura de linhas mínima:** 80%
- **Cobertura de branches mínima:** 70%

### Estrutura dos relatórios

```
target/
├── jacoco.exec              # Arquivo binário de execução
└── site/
    └── jacoco/
        ├── index.html       # 📈 Relatório principal
        ├── jacoco.xml       # XML para integração CI/CD
        ├── jacoco.csv       # CSV para análise
        └── br.com.sro.model.*/  # Relatórios por pacote
```

### Exemplo de uso com Maven goals

```bash
# Apenas gerar relatório (sem executar testes)
mvn jacoco:report

# Verificar se atende aos critérios mínimos
mvn jacoco:check

# Limpar + testar + gerar relatório
mvn clean test jacoco:report
```

---

## 🧬 PIT - Testes de Mutação

### Executar análise de mutação

```bash
# Execução completa (pode demorar)
mvn clean test

# Apenas testes de mutação (após compilar)
mvn org.pitest:pitest-maven:mutationCoverage
```

### Visualizar relatórios

**Relatório HTML:**
```bash
open target/pit-reports/index.html
```

**Relatório XML (CI/CD):**
```
target/pit-reports/mutations.xml
```

### Métricas configuradas

- **Threshold de mutação:** 80%
- **Threshold de cobertura:** 80%
- **Mutadores:** DEFAULTS (conjunto padrão do PIT)
- **Threads:** 4 (paralelização)
- **Timeout por teste:** 10 segundos

### Mutadores ativos

Os mutadores DEFAULTS incluem:

- **Condicionais:** Inverter `<`, `>`, `<=`, `>=`, `==`, `!=`
- **Matemática:** Trocar `+`, `-`, `*`, `/`, `%`
- **Retornos:** Modificar valores de retorno
- **Incrementos:** Trocar `++` por `--`
- **Negações:** Inverter valores booleanos
- **Void Method Calls:** Remover chamadas void
- **Constructor Calls:** Modificar chamadas de construtores

### Estrutura dos relatórios

```
target/
└── pit-reports/
    ├── index.html                      # 📊 Relatório principal
    ├── mutations.xml                   # XML para CI/CD
    ├── style.css                       # Estilos do relatório
    └── br.com.sro.model.*/            # Relatórios por pacote
        ├── ValidationUtils.java.html   # Relatório por classe
        └── Documento.java.html
```

### Comandos úteis

```bash
# Executar PIT sem executar os testes novamente
mvn org.pitest:pitest-maven:mutationCoverage -DwithHistory

# Executar PIT apenas para classes específicas
mvn org.pitest:pitest-maven:mutationCoverage \
  -DtargetClasses=br.com.sro.model.util.ValidationUtils

# Executar PIT com mais threads
mvn org.pitest:pitest-maven:mutationCoverage -Dthreads=8

# Gerar relatório incremental (mais rápido)
mvn org.pitest:pitest-maven:mutationCoverage -DwithHistory \
  -DhistoryInputFile=target/pit-reports/history \
  -DhistoryOutputFile=target/pit-reports/history
```

---

## 📋 Integração CI/CD

### GitHub Actions / GitLab CI

```yaml
- name: Run tests with coverage
  run: mvn clean test

- name: Upload JaCoCo coverage
  uses: codecov/codecov-action@v3
  with:
    files: ./target/site/jacoco/jacoco.xml
    
- name: Upload PIT reports
  uses: actions/upload-artifact@v3
  with:
    name: pit-reports
    path: target/pit-reports/
```

### SonarQube

O JaCoCo é automaticamente detectado pelo SonarQube:

```bash
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=sro-model \
  -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
```

---

## 🎨 Badges para README

### JaCoCo Badge

```markdown
![Coverage](https://img.shields.io/badge/coverage-80%25-brightgreen)
```

### PIT Badge

```markdown
![Mutation](https://img.shields.io/badge/mutation-80%25-brightgreen)
```

---

## 🔧 Configuração Avançada

### Excluir classes da análise

Edite o `pom.xml` para excluir classes específicas:

```xml
<configuration>
    <excludes>
        <exclude>**/*Test.class</exclude>
        <exclude>**/model/enums/*.class</exclude>
    </excludes>
</configuration>
```

### Adicionar mutadores customizados

```xml
<mutators>
    <mutator>DEFAULTS</mutator>
    <mutator>STRONGER</mutator>
    <mutator>EXPERIMENTAL_MEMBER_VARIABLE</mutator>
</mutators>
```

### Configurar timeouts

```xml
<timeoutConstant>10000</timeoutConstant>  <!-- 10 segundos -->
<timeoutFactor>1.25</timeoutFactor>       <!-- 25% extra -->
```

---

## 📚 Links Úteis

- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)
- [PIT Mutation Testing](https://pitest.org/)
- [PIT Maven Plugin](https://pitest.org/quickstart/maven/)
- [Mutation Testing Best Practices](https://pitest.org/quickstart/basic_concepts/)

---

## ✅ Checklist de Qualidade

- [x] Cobertura de linhas ≥ 80%
- [x] Cobertura de branches ≥ 70%
- [x] Mutation score ≥ 80%
- [x] Todos os testes passando
- [x] Zero dependências externas em runtime
- [x] JavaDoc completo
