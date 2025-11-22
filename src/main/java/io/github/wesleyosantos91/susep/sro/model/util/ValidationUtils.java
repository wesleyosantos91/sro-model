package io.github.wesleyosantos91.susep.sro.model.util;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Utilitários de validação reutilizáveis para os models do SRO.
 * 
 * <p>Esta classe fornece métodos estáticos para validações comuns,
 * evitando duplicação de código e garantindo consistência.</p>
 * 
 * <p><strong>Zero dependências</strong> - Apenas Java stdlib.</p>
 * 
 * @author Wesley Santos
 * @version 2.0.0
 * @since 2024-11-22
 */
public final class ValidationUtils {
    
    private ValidationUtils() {
        throw new AssertionError("Classe utilitária não deve ser instanciada");
    }
    
    /**
     * Padrões regex pré-compilados para melhor performance.
     * Compilar uma única vez evita reprocessamento em cada validação.
     */
    
    private static final Pattern UUID_PATTERN = Pattern.compile(
        "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"
    );
    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{11}$");
    private static final Pattern CNPJ_PATTERN = Pattern.compile("^\\d{14}$");
    private static final Pattern CEP_PATTERN = Pattern.compile("^\\d{8}$");
    private static final Pattern MOEDA_ISO_PATTERN = Pattern.compile("^[A-Z]{3}$");
    private static final Pattern PAIS_ISO_PATTERN = Pattern.compile("^[A-Z]{3}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );
    
    /**
     * Validações de string: obrigatoriedade, tamanho mínimo/máximo/exato.
     */
    
    /**
     * Valida que uma string não é nula nem vazia/branca.
     * 
     * @param value valor a validar
     * @param message mensagem de erro
     * @throws IllegalArgumentException se a validação falhar
     */
    public static void requireNonBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }
    
    /**
     * Valida o tamanho mínimo de uma string.
     * 
     * @param value valor a validar
     * @param min tamanho mínimo
     * @param fieldName nome do campo (para mensagem de erro)
     * @throws IllegalArgumentException se a validação falhar
     */
    public static void requireMinLength(String value, int min, String fieldName) {
        if (value != null && value.length() < min) {
            throw new IllegalArgumentException(
                fieldName + " deve ter no mínimo " + min + " caracteres"
            );
        }
    }
    
    /**
     * Valida o tamanho máximo de uma string.
     * 
     * @param value valor a validar
     * @param max tamanho máximo
     * @param fieldName nome do campo (para mensagem de erro)
     * @throws IllegalArgumentException se a validação falhar
     */
    public static void requireMaxLength(String value, int max, String fieldName) {
        if (value != null && value.length() > max) {
            throw new IllegalArgumentException(
                fieldName + " deve ter no máximo " + max + " caracteres"
            );
        }
    }
    
    /**
     * Valida o tamanho exato de uma string.
     * 
     * @param value valor a validar
     * @param length tamanho exato esperado
     * @param fieldName nome do campo (para mensagem de erro)
     * @throws IllegalArgumentException se a validação falhar
     */
    public static void requireExactLength(String value, int length, String fieldName) {
        if (value != null && value.length() != length) {
            throw new IllegalArgumentException(
                fieldName + " deve ter exatamente " + length + " caracteres"
            );
        }
    }
    
    /**
     * Validações de range e valores numéricos: min/max, positivos, não-nulos.
     */
    
    /**
     * Valida que um inteiro está dentro de um range.
     * 
     * @param value valor a validar
     * @param min valor mínimo (inclusivo)
     * @param max valor máximo (inclusivo)
     * @param fieldName nome do campo (para mensagem de erro)
     * @throws IllegalArgumentException se a validação falhar
     */
    public static void requireRange(Integer value, int min, int max, String fieldName) {
        if (value != null && (value < min || value > max)) {
            throw new IllegalArgumentException(
                fieldName + " deve estar entre " + min + " e " + max
            );
        }
    }
    
    /**
     * Valida que um número não é negativo.
     * 
     * @param value valor a validar
     * @param fieldName nome do campo (para mensagem de erro)
     * @throws IllegalArgumentException se a validação falhar
     */
    public static void requirePositive(Number value, String fieldName) {
        if (value != null && value.doubleValue() < 0) {
            throw new IllegalArgumentException(
                fieldName + " não pode ser negativo"
            );
        }
    }
    
    /**
     * Valida que um número é maior que zero.
     * 
     * @param value valor a validar
     * @param fieldName nome do campo (para mensagem de erro)
     * @throws IllegalArgumentException se a validação falhar
     */
    public static void requirePositiveNonZero(Number value, String fieldName) {
        if (value != null && value.doubleValue() <= 0) {
            throw new IllegalArgumentException(
                fieldName + " deve ser maior que zero"
            );
        }
    }
    
    /**
     * Validações de data: passado, presente, futuro e comparações entre datas.
     */
    
    /**
     * Valida que uma data não é futura (passado ou presente).
     * 
     * @param date data a validar
     * @param fieldName nome do campo (para mensagem de erro)
     * @throws IllegalArgumentException se a validação falhar
     */
    public static void requirePastOrPresent(LocalDate date, String fieldName) {
        if (date != null && date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                fieldName + " não pode ser futura"
            );
        }
    }
    
    /**
     * Valida que uma data é no passado.
     * 
     * @param date data a validar
     * @param fieldName nome do campo (para mensagem de erro)
     * @throws IllegalArgumentException se a validação falhar
     */
    public static void requirePast(LocalDate date, String fieldName) {
        if (date != null && !date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                fieldName + " deve ser no passado"
            );
        }
    }
    
    /**
     * Valida que uma data não é anterior a outra (date >= reference).
     * 
     * @param date data a validar
     * @param reference data de referência
     * @param message mensagem de erro
     * @throws IllegalArgumentException se a validação falhar
     */
    public static void requireAfterOrEqual(LocalDate date, LocalDate reference, String message) {
        if (date != null && reference != null && date.isBefore(reference)) {
            throw new IllegalArgumentException(message);
        }
    }
    
    /**
     * Valida que uma data é posterior a outra (date > reference).
     * 
     * @param date data a validar
     * @param reference data de referência
     * @param message mensagem de erro
     * @throws IllegalArgumentException se a validação falhar
     */
    public static void requireAfter(LocalDate date, LocalDate reference, String message) {
        if (date != null && reference != null && !date.isAfter(reference)) {
            throw new IllegalArgumentException(message);
        }
    }
    
    /**
     * Validações de formato: UUID, CPF, CNPJ, CEP, ISO codes, e-mail.
     */
    
    /**
     * Valida formato UUID.
     * 
     * @param uuid UUID a validar
     * @return true se válido, false caso contrário
     */
    public static boolean isValidUUID(String uuid) {
        return uuid != null && UUID_PATTERN.matcher(uuid.toLowerCase()).matches();
    }
    
    /**
     * Valida formato e dígitos verificadores de CPF.
     * 
     * @param cpf CPF a validar (11 dígitos numéricos)
     * @return true se válido, false caso contrário
     */
    public static boolean isValidCPF(String cpf) {
        if (cpf == null || !CPF_PATTERN.matcher(cpf).matches()) {
            return false;
        }
        
        /* CPFs conhecidos como inválidos (todos os dígitos iguais) */
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        
        /* Calcula primeiro dígito verificador */
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) digito1 = 0;
        
        /* Calcula segundo dígito verificador */
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) digito2 = 0;
        
        /* Verifica se os dígitos calculados conferem */
        return (cpf.charAt(9) - '0') == digito1 && (cpf.charAt(10) - '0') == digito2;
    }
    
    /**
     * Valida formato e dígitos verificadores de CNPJ.
     * 
     * @param cnpj CNPJ a validar (14 dígitos numéricos)
     * @return true se válido, false caso contrário
     */
    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null || !CNPJ_PATTERN.matcher(cnpj).matches()) {
            return false;
        }
        
        /* CNPJs conhecidos como inválidos (todos os dígitos iguais) */
        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }
        
        /* Calcula primeiro dígito verificador */
        int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * peso1[i];
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) digito1 = 0;
        
        /* Calcula segundo dígito verificador */
        int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * peso2[i];
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) digito2 = 0;
        
        /* Verifica se os dígitos calculados conferem */
        return (cnpj.charAt(12) - '0') == digito1 && (cnpj.charAt(13) - '0') == digito2;
    }
    
    /**
     * Valida formato de CEP (8 dígitos).
     * 
     * @param cep CEP a validar
     * @return true se válido, false caso contrário
     */
    public static boolean isValidCEP(String cep) {
        return cep != null && CEP_PATTERN.matcher(cep).matches();
    }
    
    /**
     * Valida formato ISO 4217 para moeda (3 letras maiúsculas).
     * 
     * @param moeda código da moeda a validar
     * @return true se válido, false caso contrário
     */
    public static boolean isValidMoedaISO(String moeda) {
        return moeda != null && MOEDA_ISO_PATTERN.matcher(moeda).matches();
    }
    
    /**
     * Valida formato ISO 3166-1 alpha-3 para país (3 letras maiúsculas).
     * 
     * @param pais código do país a validar
     * @return true se válido, false caso contrário
     */
    public static boolean isValidPaisISO(String pais) {
        return pais != null && PAIS_ISO_PATTERN.matcher(pais).matches();
    }
    
    /**
     * Valida formato de e-mail.
     * 
     * @param email e-mail a validar
     * @return true se válido, false caso contrário
     */
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * Validações condicionais: aplicadas apenas quando uma condição é verdadeira.
     */
    
    /**
     * Valida formato apenas se uma condição for verdadeira.
     * 
     * @param condition condição para aplicar a validação
     * @param isValid resultado da validação de formato
     * @param message mensagem de erro
     * @throws IllegalArgumentException se a validação falhar
     */
    public static void requireFormatIf(boolean condition, boolean isValid, String message) {
        if (condition && !isValid) {
            throw new IllegalArgumentException(message);
        }
    }
    
    /**
     * Valida que um campo é obrigatório apenas se uma condição for verdadeira.
     * 
     * @param condition condição para tornar o campo obrigatório
     * @param value valor a validar
     * @param message mensagem de erro
     * @throws IllegalArgumentException se a validação falhar
     */
    public static void requireNonNullIf(boolean condition, Object value, String message) {
        if (condition && value == null) {
            throw new IllegalArgumentException(message);
        }
    }
    
    /**
     * Valida que um campo string não está vazio apenas se uma condição for verdadeira.
     * 
     * @param condition condição para tornar o campo obrigatório
     * @param value valor a validar
     * @param message mensagem de erro
     * @throws IllegalArgumentException se a validação falhar
     */
    public static void requireNonBlankIf(boolean condition, String value, String message) {
        if (condition && (value == null || value.isBlank())) {
            throw new IllegalArgumentException(message);
        }
    }
    
    /**
     * Validações complexas: combinações de múltiplas regras e formatações.
     */
    
    /**
     * Remove formatação de CPF/CNPJ (mantém apenas dígitos).
     * 
     * @param documento CPF ou CNPJ formatado
     * @return documento sem formatação
     */
    public static String removeFormatacao(String documento) {
        return documento == null ? null : documento.replaceAll("[^0-9]", "");
    }
    
    /**
     * Valida documento (CPF ou CNPJ) com base no tipo.
     * 
     * @param documento documento a validar
     * @param tipo 1 para CPF, 2 para CNPJ
     * @param fieldName nome do campo (para mensagem de erro)
     * @throws IllegalArgumentException se a validação falhar
     */
    public static void requireValidDocumento(String documento, int tipo, String fieldName) {
        if (tipo == 1) {
            if (!isValidCPF(documento)) {
                throw new IllegalArgumentException(fieldName + " (CPF) inválido");
            }
        } else if (tipo == 2) {
            if (!isValidCNPJ(documento)) {
                throw new IllegalArgumentException(fieldName + " (CNPJ) inválido");
            }
        }
    }
}
