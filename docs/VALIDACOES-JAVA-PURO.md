# 🎯 Validações com Java Puro - Zero Dependências

Este guia mostra como implementar validações robustas usando **apenas Java puro**, sem frameworks externos. Ideal para bibliotecas que serão usadas em múltiplos contextos.

---

## ✅ Vantagens da Abordagem Java Puro

- **Zero dependências** - Nenhuma biblioteca externa
- **Portabilidade máxima** - Funciona em qualquer ambiente Java
- **Performance** - Validação em tempo de compilação e construção
- **Type-safe** - Erros detectados imediatamente
- **Imutabilidade garantida** - Records imutáveis por design
- **Fail-fast** - Objetos inválidos nunca são criados

---

## 🚀 Estratégias de Validação

### 1. **Compact Constructors** - Principal ✅

Validações dentro do próprio Record sem dependências externas.

### 2. **Utilitários de Validação** - Complementar ✅

Classes helper para validações reutilizáveis.

### 3. **Métodos de Validação** - Opcional ✅

Métodos públicos para validações on-demand.

---

## 📦 Exemplo Completo: Documento

```java
package br.com.sro.model.documento;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Representa um documento de seguro (apólice/bilhete) no sistema SRO.
 * 
 * <p>Todas as validações são executadas no compact constructor, garantindo
 * que apenas objetos válidos possam ser criados.</p>
 * 
 * @see Segurado
 * @see Cobertura
 * @see ObjetoSegurado
 */
public record Documento(
    String uuid,
    String anotacao,
    String codigoSeguradora,
    LocalDate dataRegistro,
    LocalDate dataAlteracao,
    Integer indicadorExclusao,
    Integer tipoDocumentoEmitido,
    String apoliceCodigo,
    String numeroSusepApolice,
    String certificadoCodigo,
    Integer tipoEmissao,
    LocalDate dataEmissao,
    LocalDate dataInicio,
    LocalDate dataTermino,
    String codigoFilial,
    String codigoSeguradoraLider,
    String apoliceCodigoLider,
    String moedaApolice,
    Double limiteMaximoGarantia,
    Double limiteMaximoGarantiaReal,
    Integer coberturaBasica,
    List<Ccg> ccgs,
    List<Segurado> segurados,
    List<Beneficiario> beneficiarios,
    List<Tomador> tomadores,
    List<Intermediario> intermediarios,
    List<ObjetoSegurado> objetosSegurados,
    PremioApolice premioApolice,
    Cosseguro cosseguro
) {
    // Padrões regex compilados (reutilizáveis)
    private static final Pattern UUID_PATTERN = Pattern.compile(
        "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"
    );
    private static final Pattern MOEDA_PATTERN = Pattern.compile("^[A-Z]{3}$");
    
    /**
     * Compact constructor com todas as validações.
     * 
     * <p>Lança {@link IllegalArgumentException} se alguma validação falhar.</p>
     * <p>Lança {@link NullPointerException} se campos obrigatórios forem nulos.</p>
     */
    public Documento {
        // === VALIDAÇÕES DE OBRIGATORIEDADE ===
        Objects.requireNonNull(uuid, "UUID é obrigatório");
        Objects.requireNonNull(codigoSeguradora, "Código da seguradora é obrigatório");
        Objects.requireNonNull(dataRegistro, "Data de registro é obrigatória");
        Objects.requireNonNull(dataAlteracao, "Data de alteração é obrigatória");
        Objects.requireNonNull(indicadorExclusao, "Indicador de exclusão é obrigatório");
        Objects.requireNonNull(tipoDocumentoEmitido, "Tipo de documento é obrigatório");
        Objects.requireNonNull(apoliceCodigo, "Código da apólice é obrigatório");
        Objects.requireNonNull(tipoEmissao, "Tipo de emissão é obrigatório");
        Objects.requireNonNull(dataEmissao, "Data de emissão é obrigatória");
        Objects.requireNonNull(dataInicio, "Data de início é obrigatória");
        Objects.requireNonNull(dataTermino, "Data de término é obrigatória");
        Objects.requireNonNull(codigoFilial, "Código da filial é obrigatório");
        Objects.requireNonNull(moedaApolice, "Moeda é obrigatória");
        Objects.requireNonNull(limiteMaximoGarantia, "Limite máximo de garantia é obrigatório");
        Objects.requireNonNull(limiteMaximoGarantiaReal, "Limite máximo de garantia em reais é obrigatório");
        
        // === VALIDAÇÕES DE FORMATO ===
        
        // UUID: formato padrão UUID v4
        if (!UUID_PATTERN.matcher(uuid).matches()) {
            throw new IllegalArgumentException(
                "UUID deve estar no formato: 12345678-1234-1234-1234-123456789abc"
            );
        }
        
        // Código da seguradora: exatamente 5 caracteres
        if (codigoSeguradora.length() != 5) {
            throw new IllegalArgumentException(
                "Código da seguradora deve ter exatamente 5 caracteres"
            );
        }
        
        // Código da filial: exatamente 4 caracteres
        if (codigoFilial.length() != 4) {
            throw new IllegalArgumentException(
                "Código da filial deve ter exatamente 4 caracteres"
            );
        }
        
        // Moeda: formato ISO 4217 (3 letras maiúsculas)
        if (!MOEDA_PATTERN.matcher(moedaApolice).matches()) {
            throw new IllegalArgumentException(
                "Moeda deve estar no formato ISO 4217 (ex: BRL, USD, EUR)"
            );
        }
        
        // === VALIDAÇÕES DE TAMANHO ===
        
        if (anotacao != null && anotacao.length() > 500) {
            throw new IllegalArgumentException(
                "Anotação deve ter no máximo 500 caracteres"
            );
        }
        
        if (apoliceCodigo.length() > 60) {
            throw new IllegalArgumentException(
                "Código da apólice deve ter no máximo 60 caracteres"
            );
        }
        
        if (numeroSusepApolice != null && numeroSusepApolice.length() > 30) {
            throw new IllegalArgumentException(
                "Número SUSEP deve ter no máximo 30 caracteres"
            );
        }
        
        if (certificadoCodigo != null && certificadoCodigo.length() > 60) {
            throw new IllegalArgumentException(
                "Código do certificado deve ter no máximo 60 caracteres"
            );
        }
        
        if (codigoSeguradoraLider != null && codigoSeguradoraLider.length() != 5) {
            throw new IllegalArgumentException(
                "Código da seguradora líder deve ter exatamente 5 caracteres"
            );
        }
        
        if (apoliceCodigoLider != null && apoliceCodigoLider.length() > 60) {
            throw new IllegalArgumentException(
                "Código da apólice líder deve ter no máximo 60 caracteres"
            );
        }
        
        // === VALIDAÇÕES DE RANGE/DOMÍNIO ===
        
        // Indicador de exclusão: 1 (Sim) ou 2 (Não)
        if (indicadorExclusao < 1 || indicadorExclusao > 2) {
            throw new IllegalArgumentException(
                "Indicador de exclusão deve ser 1 (Sim) ou 2 (Não)"
            );
        }
        
        // Tipo de documento: 1 a 11
        if (tipoDocumentoEmitido < 1 || tipoDocumentoEmitido > 11) {
            throw new IllegalArgumentException(
                "Tipo de documento deve estar entre 1 e 11"
            );
        }
        
        // Tipo de emissão: 1 (Própria) ou 2 (Cosseguro Aceito)
        if (tipoEmissao < 1 || tipoEmissao > 2) {
            throw new IllegalArgumentException(
                "Tipo de emissão deve ser 1 (Própria) ou 2 (Cosseguro Aceito)"
            );
        }
        
        // Cobertura básica: 1 (Simples) ou 2 (Ampla) - se informado
        if (coberturaBasica != null && (coberturaBasica < 1 || coberturaBasica > 2)) {
            throw new IllegalArgumentException(
                "Cobertura básica deve ser 1 (Simples) ou 2 (Ampla)"
            );
        }
        
        // Valores não negativos
        if (limiteMaximoGarantia < 0) {
            throw new IllegalArgumentException(
                "Limite máximo de garantia não pode ser negativo"
            );
        }
        
        if (limiteMaximoGarantiaReal < 0) {
            throw new IllegalArgumentException(
                "Limite máximo de garantia em reais não pode ser negativo"
            );
        }
        
        // === VALIDAÇÕES DE DATAS ===
        
        LocalDate hoje = LocalDate.now();
        
        // Data de registro não pode ser futura
        if (dataRegistro.isAfter(hoje)) {
            throw new IllegalArgumentException(
                "Data de registro não pode ser futura"
            );
        }
        
        // Data de alteração não pode ser futura
        if (dataAlteracao.isAfter(hoje)) {
            throw new IllegalArgumentException(
                "Data de alteração não pode ser futura"
            );
        }
        
        // Data de emissão não pode ser futura
        if (dataEmissao.isAfter(hoje)) {
            throw new IllegalArgumentException(
                "Data de emissão não pode ser futura"
            );
        }
        
        // Data de término deve ser >= data de início
        if (dataTermino.isBefore(dataInicio)) {
            throw new IllegalArgumentException(
                "Data de término deve ser maior ou igual à data de início"
            );
        }
        
        // === VALIDAÇÕES CONDICIONAIS ===
        
        // Certificado obrigatório para tipos 4, 7 e 10
        if ((tipoDocumentoEmitido == 4 || tipoDocumentoEmitido == 7 || tipoDocumentoEmitido == 10)
            && (certificadoCodigo == null || certificadoCodigo.isBlank())) {
            throw new IllegalArgumentException(
                "Certificado é obrigatório para tipos de documento 4, 7 e 10"
            );
        }
        
        // Cosseguro aceito requer seguradora líder
        if (tipoEmissao == 2) { // Cosseguro Aceito
            if (codigoSeguradoraLider == null || codigoSeguradoraLider.isBlank()) {
                throw new IllegalArgumentException(
                    "Código da seguradora líder é obrigatório para cosseguro aceito"
                );
            }
            if (apoliceCodigoLider == null || apoliceCodigoLider.isBlank()) {
                throw new IllegalArgumentException(
                    "Código da apólice líder é obrigatório para cosseguro aceito"
                );
            }
        }
        
        // Cosseguro aceito (domínio) requer dados de cosseguro
        if (cosseguro != null && cosseguro.cosseguroAceito() == 1) {
            if (codigoSeguradoraLider == null || codigoSeguradoraLider.isBlank()) {
                throw new IllegalArgumentException(
                    "Seguradora líder é obrigatória quando há cosseguro aceito"
                );
            }
        }
        
        // === VALIDAÇÕES DE LISTAS ===
        
        // Pelo menos um segurado é recomendado (warning, não erro)
        if (segurados != null && segurados.isEmpty()) {
            System.err.println("⚠️  AVISO: Documento sem segurados");
        }
        
        // === IMUTABILIDADE DAS LISTAS ===
        
        // Garante que as listas são imutáveis (defensive copy)
        ccgs = ccgs != null ? List.copyOf(ccgs) : List.of();
        segurados = segurados != null ? List.copyOf(segurados) : List.of();
        beneficiarios = beneficiarios != null ? List.copyOf(beneficiarios) : List.of();
        tomadores = tomadores != null ? List.copyOf(tomadores) : List.of();
        intermediarios = intermediarios != null ? List.copyOf(intermediarios) : List.of();
        objetosSegurados = objetosSegurados != null ? List.copyOf(objetosSegurados) : List.of();
    }
    
    /**
     * Valida se o documento está em um estado consistente.
     * 
     * <p>Este método pode ser usado para validações adicionais pós-construção.</p>
     * 
     * @return true se válido
     * @throws IllegalStateException se encontrar inconsistências
     */
    public boolean isValid() {
        // Validações complexas de integridade referencial
        if (objetosSegurados != null && !objetosSegurados.isEmpty()) {
            for (var objeto : objetosSegurados) {
                if (objeto.coberturas() == null || objeto.coberturas().isEmpty()) {
                    throw new IllegalStateException(
                        "Objeto segurado deve ter pelo menos uma cobertura"
                    );
                }
            }
        }
        
        return true;
    }
}
```

---

## 📦 Exemplo: Segurado com Validação Completa

```java
package br.com.sro.model.documento;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Representa uma pessoa segurada.
 */
public record Segurado(
    String documento,
    Integer tipoDocumento,
    String nome,
    LocalDate dataNascimento,
    Integer sexo,
    String codigoPostal,
    String cidade,
    String estado,
    String pais
) {
    // Padrões pré-compilados (performance)
    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{11}$");
    private static final Pattern CNPJ_PATTERN = Pattern.compile("^\\d{14}$");
    private static final Pattern CEP_PATTERN = Pattern.compile("^\\d{8}$");
    private static final Pattern PAIS_PATTERN = Pattern.compile("^[A-Z]{3}$");
    
    public Segurado {
        // === OBRIGATORIEDADE ===
        Objects.requireNonNull(documento, "Documento é obrigatório");
        Objects.requireNonNull(tipoDocumento, "Tipo de documento é obrigatório");
        Objects.requireNonNull(nome, "Nome é obrigatório");
        Objects.requireNonNull(pais, "País é obrigatório");
        
        // === VALIDAÇÕES DE FORMATO ===
        
        // Nome: mínimo 3 caracteres, máximo 100
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (nome.length() < 3) {
            throw new IllegalArgumentException("Nome deve ter no mínimo 3 caracteres");
        }
        if (nome.length() > 100) {
            throw new IllegalArgumentException("Nome deve ter no máximo 100 caracteres");
        }
        
        // Tipo de documento: 1 (CPF), 2 (CNPJ), 3 (Outro)
        if (tipoDocumento < 1 || tipoDocumento > 3) {
            throw new IllegalArgumentException(
                "Tipo de documento deve ser 1 (CPF), 2 (CNPJ) ou 3 (Outro)"
            );
        }
        
        // Validação específica por tipo de documento
        switch (tipoDocumento) {
            case 1: // CPF
                if (!CPF_PATTERN.matcher(documento).matches()) {
                    throw new IllegalArgumentException(
                        "CPF deve ter exatamente 11 dígitos numéricos"
                    );
                }
                // Validação de dígitos verificadores do CPF
                if (!validarCPF(documento)) {
                    throw new IllegalArgumentException("CPF inválido");
                }
                break;
                
            case 2: // CNPJ
                if (!CNPJ_PATTERN.matcher(documento).matches()) {
                    throw new IllegalArgumentException(
                        "CNPJ deve ter exatamente 14 dígitos numéricos"
                    );
                }
                // Validação de dígitos verificadores do CNPJ
                if (!validarCNPJ(documento)) {
                    throw new IllegalArgumentException("CNPJ inválido");
                }
                break;
                
            case 3: // Outro
                if (documento.length() > 20) {
                    throw new IllegalArgumentException(
                        "Documento deve ter no máximo 20 caracteres"
                    );
                }
                break;
        }
        
        // Sexo: 1 (Feminino), 2 (Masculino), 3 (Não informado) - opcional
        if (sexo != null && (sexo < 1 || sexo > 3)) {
            throw new IllegalArgumentException(
                "Sexo deve ser 1 (Feminino), 2 (Masculino) ou 3 (Não informado)"
            );
        }
        
        // CEP: 8 dígitos - opcional
        if (codigoPostal != null && !CEP_PATTERN.matcher(codigoPostal).matches()) {
            throw new IllegalArgumentException("CEP deve ter exatamente 8 dígitos");
        }
        
        // País: formato ISO 3166-1 alpha-3 (3 letras maiúsculas)
        if (!PAIS_PATTERN.matcher(pais).matches()) {
            throw new IllegalArgumentException(
                "País deve estar no formato ISO 3166-1 alpha-3 (ex: BRA, USA)"
            );
        }
        
        // === VALIDAÇÕES DE TAMANHO ===
        
        if (cidade != null && cidade.length() > 60) {
            throw new IllegalArgumentException("Cidade deve ter no máximo 60 caracteres");
        }
        
        if (estado != null && estado.length() > 60) {
            throw new IllegalArgumentException("Estado deve ter no máximo 60 caracteres");
        }
        
        // === VALIDAÇÕES DE DATA ===
        
        if (dataNascimento != null) {
            LocalDate hoje = LocalDate.now();
            
            // Data de nascimento deve ser no passado
            if (dataNascimento.isAfter(hoje)) {
                throw new IllegalArgumentException(
                    "Data de nascimento não pode ser futura"
                );
            }
            
            // Idade deve ser razoável (0 a 150 anos)
            int idade = Period.between(dataNascimento, hoje).getYears();
            if (idade < 0 || idade > 150) {
                throw new IllegalArgumentException(
                    "Data de nascimento resulta em idade inválida"
                );
            }
            
            // Para CPF, deve ser maior de idade (18 anos) - warning
            if (tipoDocumento == 1 && idade < 18) {
                System.err.println("⚠️  AVISO: Segurado menor de idade com CPF");
            }
        }
    }
    
    /**
     * Valida CPF usando algoritmo de dígitos verificadores.
     */
    private static boolean validarCPF(String cpf) {
        // Remove formatação
        cpf = cpf.replaceAll("[^0-9]", "");
        
        if (cpf.length() != 11) return false;
        
        // CPFs conhecidos como inválidos
        if (cpf.matches("(\\d)\\1{10}")) return false;
        
        // Calcula primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) digito1 = 0;
        
        // Calcula segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) digito2 = 0;
        
        // Verifica se os dígitos calculados conferem
        return (cpf.charAt(9) - '0') == digito1 && (cpf.charAt(10) - '0') == digito2;
    }
    
    /**
     * Valida CNPJ usando algoritmo de dígitos verificadores.
     */
    private static boolean validarCNPJ(String cnpj) {
        // Remove formatação
        cnpj = cnpj.replaceAll("[^0-9]", "");
        
        if (cnpj.length() != 14) return false;
        
        // CNPJs conhecidos como inválidos
        if (cnpj.matches("(\\d)\\1{13}")) return false;
        
        // Calcula primeiro dígito verificador
        int[] peso1 = {5,4,3,2,9,8,7,6,5,4,3,2};
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * peso1[i];
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) digito1 = 0;
        
        // Calcula segundo dígito verificador
        int[] peso2 = {6,5,4,3,2,9,8,7,6,5,4,3,2};
        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * peso2[i];
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) digito2 = 0;
        
        // Verifica se os dígitos calculados conferem
        return (cnpj.charAt(12) - '0') == digito1 && (cnpj.charAt(13) - '0') == digito2;
    }
    
    /**
     * Retorna a idade do segurado em anos.
     * 
     * @return idade em anos, ou null se data de nascimento não informada
     */
    public Integer getIdade() {
        if (dataNascimento == null) return null;
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
    
    /**
     * Verifica se é maior de idade (18 anos).
     */
    public boolean isMaiorDeIdade() {
        Integer idade = getIdade();
        return idade != null && idade >= 18;
    }
}
```

---

## 🛠️ Classe Utilitária de Validação

```java
package br.com.sro.model.util;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Utilitários de validação reutilizáveis.
 */
public final class ValidationUtils {
    
    private ValidationUtils() {
        throw new AssertionError("Classe utilitária não deve ser instanciada");
    }
    
    // === PADRÕES PRÉ-COMPILADOS ===
    
    private static final Pattern UUID_PATTERN = Pattern.compile(
        "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"
    );
    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{11}$");
    private static final Pattern CNPJ_PATTERN = Pattern.compile("^\\d{14}$");
    private static final Pattern CEP_PATTERN = Pattern.compile("^\\d{8}$");
    private static final Pattern MOEDA_ISO_PATTERN = Pattern.compile("^[A-Z]{3}$");
    private static final Pattern PAIS_ISO_PATTERN = Pattern.compile("^[A-Z]{3}$");
    
    // === VALIDAÇÕES DE STRING ===
    
    public static void requireNonBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void requireMinLength(String value, int min, String fieldName) {
        if (value != null && value.length() < min) {
            throw new IllegalArgumentException(
                fieldName + " deve ter no mínimo " + min + " caracteres"
            );
        }
    }
    
    public static void requireMaxLength(String value, int max, String fieldName) {
        if (value != null && value.length() > max) {
            throw new IllegalArgumentException(
                fieldName + " deve ter no máximo " + max + " caracteres"
            );
        }
    }
    
    public static void requireExactLength(String value, int length, String fieldName) {
        if (value != null && value.length() != length) {
            throw new IllegalArgumentException(
                fieldName + " deve ter exatamente " + length + " caracteres"
            );
        }
    }
    
    // === VALIDAÇÕES DE RANGE ===
    
    public static void requireRange(Integer value, int min, int max, String fieldName) {
        if (value != null && (value < min || value > max)) {
            throw new IllegalArgumentException(
                fieldName + " deve estar entre " + min + " e " + max
            );
        }
    }
    
    public static void requirePositive(Number value, String fieldName) {
        if (value != null && value.doubleValue() < 0) {
            throw new IllegalArgumentException(
                fieldName + " não pode ser negativo"
            );
        }
    }
    
    // === VALIDAÇÕES DE DATA ===
    
    public static void requirePastOrPresent(LocalDate date, String fieldName) {
        if (date != null && date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                fieldName + " não pode ser futura"
            );
        }
    }
    
    public static void requirePast(LocalDate date, String fieldName) {
        if (date != null && !date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                fieldName + " deve ser no passado"
            );
        }
    }
    
    public static void requireAfterOrEqual(LocalDate date, LocalDate reference, String message) {
        if (date != null && reference != null && date.isBefore(reference)) {
            throw new IllegalArgumentException(message);
        }
    }
    
    // === VALIDAÇÕES DE FORMATO ===
    
    public static boolean isValidUUID(String uuid) {
        return uuid != null && UUID_PATTERN.matcher(uuid).matches();
    }
    
    public static boolean isValidCPF(String cpf) {
        if (cpf == null || !CPF_PATTERN.matcher(cpf).matches()) {
            return false;
        }
        
        // Validação de dígitos verificadores
        if (cpf.matches("(\\d)\\1{10}")) return false;
        
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) digito1 = 0;
        
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) digito2 = 0;
        
        return (cpf.charAt(9) - '0') == digito1 && (cpf.charAt(10) - '0') == digito2;
    }
    
    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null || !CNPJ_PATTERN.matcher(cnpj).matches()) {
            return false;
        }
        
        if (cnpj.matches("(\\d)\\1{13}")) return false;
        
        int[] peso1 = {5,4,3,2,9,8,7,6,5,4,3,2};
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * peso1[i];
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) digito1 = 0;
        
        int[] peso2 = {6,5,4,3,2,9,8,7,6,5,4,3,2};
        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * peso2[i];
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) digito2 = 0;
        
        return (cnpj.charAt(12) - '0') == digito1 && (cnpj.charAt(13) - '0') == digito2;
    }
    
    public static boolean isValidCEP(String cep) {
        return cep != null && CEP_PATTERN.matcher(cep).matches();
    }
    
    public static boolean isValidMoedaISO(String moeda) {
        return moeda != null && MOEDA_ISO_PATTERN.matcher(moeda).matches();
    }
    
    public static boolean isValidPaisISO(String pais) {
        return pais != null && PAIS_ISO_PATTERN.matcher(pais).matches();
    }
    
    // === VALIDAÇÕES COMPLEXAS ===
    
    public static void requireFormatIf(boolean condition, boolean isValid, String message) {
        if (condition && !isValid) {
            throw new IllegalArgumentException(message);
        }
    }
}
```

---

## 🧪 Exemplo de Uso

```java
import br.com.sro.model.documento.*;
import java.time.LocalDate;
import java.util.List;

public class ExemploValidacao {
    
    public static void main(String[] args) {
        
        try {
            // Tentativa 1: UUID inválido
            var doc1 = new Documento(
                "uuid-invalido",  // ❌ Formato inválido
                null,
                "12345",
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
                LocalDate.now(),
                "0001",
                null,
                null,
                "BRL",
                100000.0,
                100000.0,
                null,
                List.of(), List.of(), List.of(), List.of(),
                List.of(), List.of(), null, null
            );
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro esperado: " + e.getMessage());
            // Saída: UUID deve estar no formato: 12345678-1234-1234-1234-123456789abc
        }
        
        try {
            // Tentativa 2: CPF inválido
            var segurado = new Segurado(
                "12345678900",  // ❌ CPF com dígitos inválidos
                1,              // Tipo: CPF
                "João Silva",
                LocalDate.of(1990, 1, 1),
                2,              // Masculino
                "01310100",
                "São Paulo",
                "SP",
                "BRA"
            );
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro esperado: " + e.getMessage());
            // Saída: CPF inválido
        }
        
        // Tentativa 3: Objeto válido ✅
        var seguradoValido = new Segurado(
            "12345678909",  // ✅ CPF válido
            1,
            "João Silva",
            LocalDate.of(1990, 1, 1),
            2,
            "01310100",
            "São Paulo",
            "SP",
            "BRA"
        );
        
        System.out.println("✅ Segurado válido criado!");
        System.out.println("   Nome: " + seguradoValido.nome());
        System.out.println("   Idade: " + seguradoValido.getIdade() + " anos");
        System.out.println("   Maior de idade: " + seguradoValido.isMaiorDeIdade());
    }
}
```

---

## 📊 Comparação: Java Puro vs Jakarta Validation

| Aspecto | Java Puro | Jakarta Validation |
|---------|-----------|-------------------|
| **Dependências** | ✅ Zero | ⚠️ API + Implementação |
| **Performance** | ✅ Máxima (compile-time) | ⚠️ Runtime |
| **Portabilidade** | ✅ Total | ⚠️ Requer framework |
| **Fail-fast** | ✅ Sim (construção) | ⚠️ Não (pós-construção) |
| **Type-safe** | ✅ 100% | ⚠️ Reflection-based |
| **Mensagens de erro** | ✅ Customizadas | ✅ Customizadas |
| **Validações complexas** | ✅ Métodos Java | ⚠️ Validators customizados |
| **Imutabilidade** | ✅ Garantida | ⚠️ Depende do design |
| **Tamanho do JAR** | ✅ Mínimo | ⚠️ +2MB (dependências) |

---

## 🎯 Boas Práticas

### ✅ FAÇA

1. **Use `Objects.requireNonNull()`** para campos obrigatórios
2. **Compile patterns regex** como constantes estáticas
3. **Valide no compact constructor** para fail-fast
4. **Retorne listas imutáveis** com `List.copyOf()`
5. **Crie métodos auxiliares** para validações complexas
6. **Documente as regras** no JavaDoc
7. **Use constantes** para números mágicos
8. **Crie classes utilitárias** para validações reutilizáveis

### ❌ EVITE

1. Validações após a construção do objeto
2. Objetos mutáveis
3. Validações silenciosas (log em vez de exception)
4. Regex complexos repetidos
5. Validações que dependem de estado externo
6. Mensagens de erro genéricas
7. Ignorar validações condicionais

---

## 🚀 Vantagens da Abordagem

1. **Zero Runtime Overhead** - Validações em tempo de construção
2. **Impossível criar objetos inválidos** - Fail-fast garantido
3. **Thread-safe por design** - Records são imutáveis
4. **Código autodocumentado** - Validações visíveis no código
5. **Sem reflection** - Performance máxima
6. **Compatível com GraalVM Native Image** - Sem problemas de reflection
7. **Testável** - Testes unitários simples
8. **Manutenível** - Toda lógica em um lugar

---

## 📝 Próximos Passos

1. ✅ Aplicar validações em todos os 35 models
2. ✅ Criar classe `ValidationUtils` reutilizável
3. ✅ Adicionar métodos auxiliares (getIdade, isValid, etc.)
4. ✅ Documentar todas as regras no JavaDoc
5. ✅ Criar testes unitários abrangentes
6. ✅ Adicionar validações de CPF/CNPJ
7. ✅ Implementar validações de datas

---

**Versão:** 2.0  
**Data:** 2024-11-22  
**Dependências:** Zero (Java 25 puro)
