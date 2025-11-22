# 🎯 Enriquecendo os Models com Validações

Este guia mostra como adicionar validações aos models do SRO mantendo compatibilidade com todos os frameworks Java.

---

## 📋 Estratégias de Validação

### 1. **Jakarta Validation API (JSR 380)** - ✅ RECOMENDADO

Usa apenas a **API padrão** (scope `provided`), deixando a implementação para o framework:
- ✅ **Spring Boot** → traz Hibernate Validator automaticamente
- ✅ **Quarkus** → traz Hibernate Validator automaticamente  
- ✅ **Java EE/Jakarta EE** → servidor de aplicação fornece
- ✅ **Micronaut** → traz Micronaut Validation

**Vantagens:**
- Padrão da indústria
- Funciona em qualquer framework
- Zero acoplamento com implementações específicas
- Validação declarativa (annotations)

### 2. **Compact Constructors (Java Records)** - ✅ COMPLEMENTAR

Validações dentro do próprio Record sem dependências externas.

**Vantagens:**
- Zero dependências
- Validação em tempo de construção
- Type-safe
- Performance máxima

### 3. **Método de Validação Customizado** - ✅ OPCIONAL

Métodos `validate()` ou `isValid()` para regras complexas.

---

## 🚀 Exemplos Práticos

### Exemplo 1: Jakarta Validation API

```java
package br.com.sro.model.documento;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Representa um documento de seguro (apólice/bilhete) no sistema SRO.
 */
public record Documento(
    
    @NotNull(message = "UUID é obrigatório")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", 
             message = "UUID deve estar no formato válido")
    String uuid,
    
    @Size(max = 500, message = "Anotação deve ter no máximo 500 caracteres")
    String anotacao,
    
    @NotNull(message = "Código da seguradora é obrigatório")
    @Size(min = 5, max = 5, message = "Código da seguradora deve ter exatamente 5 caracteres")
    String codigoSeguradora,
    
    @NotNull(message = "Data de registro é obrigatória")
    @PastOrPresent(message = "Data de registro não pode ser futura")
    LocalDate dataRegistro,
    
    @NotNull(message = "Data de alteração é obrigatória")
    @PastOrPresent(message = "Data de alteração não pode ser futura")
    LocalDate dataAlteracao,
    
    @NotNull(message = "Indicador de exclusão é obrigatório")
    @Min(value = 1, message = "Indicador de exclusão deve ser 1 (Sim) ou 2 (Não)")
    @Max(value = 2, message = "Indicador de exclusão deve ser 1 (Sim) ou 2 (Não)")
    Integer indicadorExclusao,
    
    @NotNull(message = "Tipo de documento é obrigatório")
    @Min(value = 1, message = "Tipo de documento inválido")
    @Max(value = 10, message = "Tipo de documento inválido")
    Integer tipoDocumentoEmitido,
    
    @NotNull(message = "Código da apólice é obrigatório")
    @Size(max = 60, message = "Código da apólice deve ter no máximo 60 caracteres")
    String apoliceCodigo,
    
    @Size(max = 60, message = "Número SUSEP deve ter no máximo 60 caracteres")
    String numeroSusepApolice,
    
    @Size(max = 60, message = "Código do certificado deve ter no máximo 60 caracteres")
    String certificadoCodigo,
    
    @NotNull(message = "Tipo de emissão é obrigatório")
    Integer tipoEmissao,
    
    @NotNull(message = "Data de emissão é obrigatória")
    @PastOrPresent(message = "Data de emissão não pode ser futura")
    LocalDate dataEmissao,
    
    @NotNull(message = "Data de início é obrigatória")
    LocalDate dataInicio,
    
    @NotNull(message = "Data de término é obrigatória")
    LocalDate dataTermino,
    
    @NotNull(message = "Código da filial é obrigatório")
    @Size(min = 4, max = 4, message = "Código da filial deve ter exatamente 4 caracteres")
    String codigoFilial,
    
    @Size(min = 5, max = 5, message = "Código da seguradora líder deve ter 5 caracteres")
    String codigoSeguradoraLider,
    
    @Size(max = 60, message = "Código da apólice líder deve ter no máximo 60 caracteres")
    String apoliceCodigoLider,
    
    @NotNull(message = "Moeda é obrigatória")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Moeda deve estar no formato ISO 4217 (ex: BRL, USD)")
    String moedaApolice,
    
    @PositiveOrZero(message = "Limite máximo de garantia deve ser maior ou igual a zero")
    Double limiteMaximoGarantia,
    
    @PositiveOrZero(message = "Limite máximo de garantia em reais deve ser maior ou igual a zero")
    Double limiteMaximoGarantiaReal,
    
    String coberturaBasica,
    
    @NotNull(message = "Lista de CCGs não pode ser nula")
    @Valid // Valida recursivamente os objetos da lista
    List<Ccg> ccgs,
    
    @NotNull(message = "Lista de segurados não pode ser nula")
    @NotEmpty(message = "Deve haver pelo menos um segurado")
    @Valid
    List<Segurado> segurados,
    
    @NotNull(message = "Lista de beneficiários não pode ser nula")
    @Valid
    List<Beneficiario> beneficiarios,
    
    @NotNull(message = "Lista de tomadores não pode ser nula")
    @Valid
    List<Tomador> tomadores,
    
    @NotNull(message = "Lista de intermediários não pode ser nula")
    @Valid
    List<Intermediario> intermediarios,
    
    @NotNull(message = "Lista de objetos segurados não pode ser nula")
    @Valid
    List<ObjetoSegurado> objetosSegurados,
    
    @NotNull(message = "Prêmio da apólice é obrigatório")
    @Valid
    PremioApolice premioApolice,
    
    @Valid
    Cosseguro cosseguro
) {
    // Compact constructor com validações customizadas adicionais
    public Documento {
        // Validação: Data término >= Data início
        if (dataTermino != null && dataInicio != null && dataTermino.isBefore(dataInicio)) {
            throw new IllegalArgumentException(
                "Data de término deve ser maior ou igual à data de início"
            );
        }
        
        // Validação: Certificado obrigatório para tipos 4, 7 e 10
        if ((tipoDocumentoEmitido == 4 || tipoDocumentoEmitido == 7 || tipoDocumentoEmitido == 10) 
            && (certificadoCodigo == null || certificadoCodigo.isBlank())) {
            throw new IllegalArgumentException(
                "Certificado é obrigatório para tipos de documento 4, 7 e 10"
            );
        }
        
        // Validação: Cosseguro aceito deve ter seguradora líder
        if (cosseguro != null && cosseguro.cosseguroAceito() == 1) {
            if (codigoSeguradoraLider == null || codigoSeguradoraLider.isBlank()) {
                throw new IllegalArgumentException(
                    "Seguradora líder é obrigatória quando há cosseguro aceito"
                );
            }
        }
        
        // Torna listas imutáveis
        ccgs = ccgs != null ? List.copyOf(ccgs) : List.of();
        segurados = segurados != null ? List.copyOf(segurados) : List.of();
        beneficiarios = beneficiarios != null ? List.copyOf(beneficiarios) : List.of();
        tomadores = tomadores != null ? List.copyOf(tomadores) : List.of();
        intermediarios = intermediarios != null ? List.copyOf(intermediarios) : List.of();
        objetosSegurados = objetosSegurados != null ? List.copyOf(objetosSegurados) : List.of();
    }
}
```

### Exemplo 2: Segurado com Validações

```java
package br.com.sro.model.documento;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record Segurado(
    
    @NotNull(message = "Documento é obrigatório")
    @Size(max = 20, message = "Documento deve ter no máximo 20 caracteres")
    String documento,
    
    @NotNull(message = "Tipo de documento é obrigatório")
    @Min(value = 1, message = "Tipo de documento deve ser 1 (CPF), 2 (CNPJ) ou 3 (Outro)")
    @Max(value = 3, message = "Tipo de documento deve ser 1 (CPF), 2 (CNPJ) ou 3 (Outro)")
    Integer tipoDocumento,
    
    @NotNull(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    String nome,
    
    @Past(message = "Data de nascimento deve ser no passado")
    LocalDate dataNascimento,
    
    @Min(value = 1, message = "Sexo deve ser 1 (Feminino), 2 (Masculino) ou 3 (Não informado)")
    @Max(value = 3, message = "Sexo deve ser 1 (Feminino), 2 (Masculino) ou 3 (Não informado)")
    Integer sexo,
    
    @Pattern(regexp = "^\\d{8}$", message = "CEP deve ter 8 dígitos")
    String codigoPostal,
    
    @Size(max = 60, message = "Cidade deve ter no máximo 60 caracteres")
    String cidade,
    
    @Size(max = 60, message = "Estado deve ter no máximo 60 caracteres")
    String estado,
    
    @NotNull(message = "País é obrigatório")
    @Pattern(regexp = "^[A-Z]{3}$", message = "País deve estar no formato ISO 3166-1 alpha-3 (ex: BRA)")
    String pais
) {
    public Segurado {
        // Validação customizada: CPF deve ter 11 dígitos
        if (tipoDocumento != null && tipoDocumento == 1) {
            if (documento == null || !documento.matches("^\\d{11}$")) {
                throw new IllegalArgumentException(
                    "CPF deve ter exatamente 11 dígitos numéricos"
                );
            }
        }
        
        // Validação customizada: CNPJ deve ter 14 dígitos
        if (tipoDocumento != null && tipoDocumento == 2) {
            if (documento == null || !documento.matches("^\\d{14}$")) {
                throw new IllegalArgumentException(
                    "CNPJ deve ter exatamente 14 dígitos numéricos"
                );
            }
        }
        
        // Validação customizada: Maior de idade
        if (dataNascimento != null) {
            LocalDate hoje = LocalDate.now();
            int idade = hoje.getYear() - dataNascimento.getYear();
            if (dataNascimento.plusYears(idade).isAfter(hoje)) {
                idade--;
            }
            if (idade < 0 || idade > 150) {
                throw new IllegalArgumentException(
                    "Data de nascimento inválida (idade fora do intervalo válido)"
                );
            }
        }
    }
}
```

### Exemplo 3: Sinistro com Validações Complexas

```java
package br.com.sro.model.sinistro;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public record Sinistro(
    
    @NotNull @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
    String uuid,
    
    @Size(max = 500)
    String anotacao,
    
    @NotNull @Size(min = 5, max = 5)
    String codigoSeguradora,
    
    @NotNull @PastOrPresent
    LocalDate dataRegistro,
    
    @NotNull @PastOrPresent
    LocalDate dataAlteracao,
    
    @NotNull @Min(1) @Max(2)
    Integer indicadorExclusao,
    
    @NotNull @Size(max = 50)
    String codigoSinistro,
    
    @PastOrPresent
    LocalDate dataEntrega,
    
    @NotNull @Min(1) @Max(4)
    Integer status,
    
    @NotNull
    LocalDate dataAlteracaoStatus,
    
    @NotNull @PastOrPresent
    LocalDate dataOcorrencia,
    
    @NotNull @PastOrPresent
    LocalDate dataAviso,
    
    @NotNull @PastOrPresent
    LocalDate dataRegistroSeguradora,
    
    @PastOrPresent
    LocalDate dataReclamacaoTerceiro,
    
    @NotNull @Valid
    List<JustificativaNegativa> justificativasNegativas,
    
    @NotNull @Valid
    List<DocumentosAfetados> documentosAfetados,
    
    @NotNull @Valid
    List<CoberturasAfetadas> coberturasAfetadas,
    
    @NotNull @Valid
    List<DadosVistoriaRural> dadosVistoriaRural,
    
    @NotNull @Valid
    List<DadosAutomovel> dadosAutomoveis
) {
    public Sinistro {
        // Validação: Data de aviso >= Data de ocorrência
        if (dataAviso != null && dataOcorrencia != null && dataAviso.isBefore(dataOcorrencia)) {
            throw new IllegalArgumentException(
                "Data de aviso deve ser maior ou igual à data de ocorrência"
            );
        }
        
        // Validação: Data de entrega >= Data de aviso
        if (dataEntrega != null && dataAviso != null && dataEntrega.isBefore(dataAviso)) {
            throw new IllegalArgumentException(
                "Data de entrega deve ser maior ou igual à data de aviso"
            );
        }
        
        // Validação: Status encerrado requer justificativa se for negativa
        if (status != null && status == 3) { // 3 = Encerrado sem indenização
            if (justificativasNegativas == null || justificativasNegativas.isEmpty()) {
                throw new IllegalArgumentException(
                    "Sinistro encerrado sem indenização requer justificativa"
                );
            }
        }
        
        // Torna listas imutáveis
        justificativasNegativas = justificativasNegativas != null ? 
            List.copyOf(justificativasNegativas) : List.of();
        documentosAfetados = documentosAfetados != null ? 
            List.copyOf(documentosAfetados) : List.of();
        coberturasAfetadas = coberturasAfetadas != null ? 
            List.copyOf(coberturasAfetadas) : List.of();
        dadosVistoriaRural = dadosVistoriaRural != null ? 
            List.copyOf(dadosVistoriaRural) : List.of();
        dadosAutomoveis = dadosAutomoveis != null ? 
            List.copyOf(dadosAutomoveis) : List.of();
    }
}
```

---

## 🧪 Como Validar

### Programaticamente (sem framework)

```java
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

public class ValidacaoExemplo {
    
    public static void main(String[] args) {
        // Criar validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        // Criar objeto
        var documento = new Documento(
            "uuid-invalido",  // UUID inválido
            null,
            "123",            // Código curto demais
            LocalDate.now(),
            LocalDate.now(),
            2,
            1,
            "APL-001",
            null,
            null,
            1,
            LocalDate.now(),
            LocalDate.now(),
            LocalDate.now().minusDays(1), // Data término < Data início!
            "0001",
            null,
            null,
            "BRL",
            100000.0,
            100000.0,
            null,
            List.of(),
            List.of(segurado),
            List.of(),
            List.of(),
            List.of(),
            List.of(),
            premio,
            null
        );
        
        // Validar
        Set<ConstraintViolation<Documento>> violations = validator.validate(documento);
        
        if (!violations.isEmpty()) {
            System.out.println("❌ Erros de validação:");
            violations.forEach(v -> 
                System.out.println("  • " + v.getPropertyPath() + ": " + v.getMessage())
            );
        } else {
            System.out.println("✅ Objeto válido!");
        }
    }
}
```

### Com Spring Boot

```java
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/documentos")
@Validated
public class DocumentoController {
    
    @PostMapping
    public ResponseEntity<Documento> criar(@Valid @RequestBody Documento documento) {
        // Spring valida automaticamente antes de entrar no método
        // Se houver erro, retorna 400 Bad Request com detalhes
        return ResponseEntity.ok(documentoService.salvar(documento));
    }
}
```

### Com Quarkus

```java
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/api/documentos")
public class DocumentoResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criar(@Valid Documento documento) {
        // Quarkus valida automaticamente
        // Se houver erro, retorna 400 Bad Request
        return Response.ok(documentoService.salvar(documento)).build();
    }
}
```

---

## 📋 Annotations Disponíveis

### Validações Básicas
- `@NotNull` - Campo não pode ser nulo
- `@NotEmpty` - String/Collection não pode ser vazia
- `@NotBlank` - String não pode ser vazia ou só espaços
- `@Null` - Campo deve ser nulo

### Validações de String
- `@Size(min=, max=)` - Tamanho da string/collection
- `@Pattern(regexp=)` - Regex pattern
- `@Email` - Formato de e-mail válido

### Validações Numéricas
- `@Min(value)` - Valor mínimo
- `@Max(value)` - Valor máximo
- `@Positive` - Número positivo (> 0)
- `@PositiveOrZero` - >= 0
- `@Negative` - Número negativo (< 0)
- `@NegativeOrZero` - <= 0
- `@DecimalMin(value)` - Decimal mínimo
- `@DecimalMax(value)` - Decimal máximo
- `@Digits(integer=, fraction=)` - Número de dígitos

### Validações de Data
- `@Past` - Data no passado
- `@PastOrPresent` - Data no passado ou hoje
- `@Future` - Data no futuro
- `@FutureOrPresent` - Data no futuro ou hoje

### Validações Compostas
- `@Valid` - Valida recursivamente objetos aninhados
- `@AssertTrue` - Booleano deve ser true
- `@AssertFalse` - Booleano deve ser false

---

## 🎯 Recomendações

### ✅ Faça

1. **Use Jakarta Validation API** para validações padrão
2. **Use Compact Constructors** para validações complexas de negócio
3. **Combine as duas abordagens** para melhor resultado
4. **Valide recursivamente** com `@Valid` em objetos aninhados
5. **Crie mensagens claras** de erro
6. **Documente as regras** no JavaDoc

### ❌ Evite

1. Não use implementações específicas (Hibernate Validator direto)
2. Não deixe validações apenas na camada de apresentação
3. Não ignore validações de regras de negócio complexas
4. Não use validações que dependam de estado externo

---

## 🚀 Compatibilidade com Frameworks

| Framework | Validação Automática | Configuração Necessária |
|-----------|---------------------|-------------------------|
| **Spring Boot** | ✅ Sim | `@Valid` nos métodos |
| **Quarkus** | ✅ Sim | `@Valid` nos métodos |
| **Jakarta EE** | ✅ Sim | CDI + Bean Validation |
| **Micronaut** | ✅ Sim | `@Valid` nos métodos |
| **Java Puro** | ⚠️ Manual | Criar `Validator` explicitamente |

---

## 📦 Dependências por Framework

### Spring Boot
```xml
<!-- Já incluído no spring-boot-starter-web -->
<!-- Não precisa adicionar nada extra -->
```

### Quarkus
```xml
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-hibernate-validator</artifactId>
</dependency>
```

### Jakarta EE / Java EE
```xml
<!-- Fornecido pelo servidor de aplicação -->
<!-- Não precisa adicionar -->
```

### Java Puro (standalone)
```xml
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>8.0.1.Final</version>
</dependency>
<dependency>
    <groupId>org.glassfish.expressly</groupId>
    <artifactId>expressly</artifactId>
    <version>5.0.0</version>
</dependency>
```

---

## 📝 Próximos Passos

1. Adicionar validações aos 35 models existentes
2. Criar testes unitários para validações
3. Documentar regras de negócio no JavaDoc
4. Criar classes de teste com cenários válidos e inválidos
5. Considerar criar validadores customizados para regras SUSEP específicas

---

**Versão:** 1.0  
**Data:** 2024-11-22  
**Compatibilidade:** Java 25, Jakarta Validation 3.1
