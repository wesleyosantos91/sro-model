package br.com.sro.model.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

/**
 * Testes unitários para ValidationUtils.
 * 
 * <p>Garante que todos os métodos de validação funcionam corretamente
 * e lançam exceções apropriadas.</p>
 * 
 * @author Wesley Santos
 * @version 2.0.0
 */
@DisplayName("ValidationUtils - Utilitários de Validação")
class ValidationUtilsTest {
    
    /* === TESTES DE VALIDAÇÕES DE STRING === */
    
    @Test
    @DisplayName("requireNonBlank deve aceitar string válida")
    void requireNonBlank_StringValida_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requireNonBlank("texto válido", "erro")
        );
    }
    
    @Test
    @DisplayName("requireNonBlank deve rejeitar string nula")
    void requireNonBlank_StringNula_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requireNonBlank(null, "String é obrigatória")
        )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("String é obrigatória");
    }
    
    @Test
    @DisplayName("requireNonBlank deve rejeitar string vazia")
    void requireNonBlank_StringVazia_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requireNonBlank("", "String é obrigatória")
        )
        .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("requireNonBlank deve rejeitar string apenas com espaços")
    void requireNonBlank_StringApenasEspacos_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requireNonBlank("   ", "String é obrigatória")
        )
        .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("requireMinLength deve aceitar tamanho adequado")
    void requireMinLength_TamanhoAdequado_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requireMinLength("texto", 3, "campo")
        );
    }
    
    @Test
    @DisplayName("requireMinLength deve rejeitar tamanho insuficiente")
    void requireMinLength_TamanhoInsuficiente_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requireMinLength("ab", 3, "campo")
        )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("deve ter no mínimo 3 caracteres");
    }
    
    @Test
    @DisplayName("requireMinLength deve ignorar null")
    void requireMinLength_Null_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requireMinLength(null, 3, "campo")
        );
    }
    
    @Test
    @DisplayName("requireMaxLength deve aceitar tamanho adequado")
    void requireMaxLength_TamanhoAdequado_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requireMaxLength("texto", 10, "campo")
        );
    }
    
    @Test
    @DisplayName("requireMaxLength deve rejeitar tamanho excessivo")
    void requireMaxLength_TamanhoExcessivo_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requireMaxLength("texto muito longo", 5, "campo")
        )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("deve ter no máximo 5 caracteres");
    }
    
    @Test
    @DisplayName("requireExactLength deve aceitar tamanho exato")
    void requireExactLength_TamanhoExato_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requireExactLength("12345", 5, "campo")
        );
    }
    
    @Test
    @DisplayName("requireExactLength deve rejeitar tamanho diferente")
    void requireExactLength_TamanhoDiferente_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requireExactLength("1234", 5, "campo")
        )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("deve ter exatamente 5 caracteres");
    }
    
    /* === TESTES DE VALIDAÇÕES DE RANGE === */
    
    @Test
    @DisplayName("requireRange deve aceitar valor dentro do range")
    void requireRange_ValorDentroDoRange_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requireRange(5, 1, 10, "campo")
        );
    }
    
    @Test
    @DisplayName("requireRange deve aceitar valor no limite inferior")
    void requireRange_ValorLimiteInferior_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requireRange(1, 1, 10, "campo")
        );
    }
    
    @Test
    @DisplayName("requireRange deve aceitar valor no limite superior")
    void requireRange_ValorLimiteSuperior_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requireRange(10, 1, 10, "campo")
        );
    }
    
    @Test
    @DisplayName("requireRange deve rejeitar valor abaixo do mínimo")
    void requireRange_ValorAbaixoMinimo_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requireRange(0, 1, 10, "campo")
        )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("deve estar entre 1 e 10");
    }
    
    @Test
    @DisplayName("requireRange deve rejeitar valor acima do máximo")
    void requireRange_ValorAcimaMaximo_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requireRange(11, 1, 10, "campo")
        )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("deve estar entre 1 e 10");
    }
    
    @Test
    @DisplayName("requireRange deve ignorar null")
    void requireRange_Null_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requireRange(null, 1, 10, "campo")
        );
    }
    
    @Test
    @DisplayName("requirePositive deve aceitar valores positivos")
    void requirePositive_ValorPositivo_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() -> {
            ValidationUtils.requirePositive(10, "campo");
            ValidationUtils.requirePositive(0, "campo");
            ValidationUtils.requirePositive(10.5, "campo");
        });
    }
    
    @Test
    @DisplayName("requirePositive deve rejeitar valores negativos")
    void requirePositive_ValorNegativo_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requirePositive(-1, "campo")
        )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("não pode ser negativo");
    }
    
    @Test
    @DisplayName("requirePositiveNonZero deve aceitar valores maiores que zero")
    void requirePositiveNonZero_ValorMaiorQueZero_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requirePositiveNonZero(1, "campo")
        );
    }
    
    @Test
    @DisplayName("requirePositiveNonZero deve rejeitar zero")
    void requirePositiveNonZero_Zero_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requirePositiveNonZero(0, "campo")
        )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("deve ser maior que zero");
    }
    
    @Test
    @DisplayName("requirePositiveNonZero deve rejeitar valores negativos")
    void requirePositiveNonZero_ValorNegativo_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requirePositiveNonZero(-1, "campo")
        )
        .isInstanceOf(IllegalArgumentException.class);
    }
    
    /* === TESTES DE VALIDAÇÕES DE DATA === */
    
    @Test
    @DisplayName("requirePastOrPresent deve aceitar data passada")
    void requirePastOrPresent_DataPassada_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requirePastOrPresent(LocalDate.now().minusDays(1), "campo")
        );
    }
    
    @Test
    @DisplayName("requirePastOrPresent deve aceitar data atual")
    void requirePastOrPresent_DataAtual_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requirePastOrPresent(LocalDate.now(), "campo")
        );
    }
    
    @Test
    @DisplayName("requirePastOrPresent deve rejeitar data futura")
    void requirePastOrPresent_DataFutura_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requirePastOrPresent(LocalDate.now().plusDays(1), "campo")
        )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("não pode ser futura");
    }
    
    @Test
    @DisplayName("requirePast deve aceitar data passada")
    void requirePast_DataPassada_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requirePast(LocalDate.now().minusDays(1), "campo")
        );
    }
    
    @Test
    @DisplayName("requirePast deve rejeitar data atual")
    void requirePast_DataAtual_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requirePast(LocalDate.now(), "campo")
        )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("deve ser no passado");
    }
    
    @Test
    @DisplayName("requireAfterOrEqual deve aceitar datas válidas")
    void requireAfterOrEqual_DatasValidas_NaoDeveLancarExcecao() {
        LocalDate ref = LocalDate.of(2025, 1, 1);
        assertThatNoException().isThrownBy(() -> {
            ValidationUtils.requireAfterOrEqual(LocalDate.of(2025, 1, 2), ref, "erro");
            ValidationUtils.requireAfterOrEqual(LocalDate.of(2025, 1, 1), ref, "erro");
        });
    }
    
    @Test
    @DisplayName("requireAfterOrEqual deve rejeitar data anterior")
    void requireAfterOrEqual_DataAnterior_DeveLancarExcecao() {
        LocalDate ref = LocalDate.of(2025, 1, 1);
        assertThatThrownBy(() ->
            ValidationUtils.requireAfterOrEqual(LocalDate.of(2024, 12, 31), ref, "Data inválida")
        )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Data inválida");
    }
    
    @Test
    @DisplayName("requireAfter deve aceitar data posterior")
    void requireAfter_DataPosterior_NaoDeveLancarExcecao() {
        LocalDate ref = LocalDate.of(2025, 1, 1);
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requireAfter(LocalDate.of(2025, 1, 2), ref, "erro")
        );
    }
    
    @Test
    @DisplayName("requireAfter deve rejeitar data igual")
    void requireAfter_DataIgual_DeveLancarExcecao() {
        LocalDate ref = LocalDate.of(2025, 1, 1);
        assertThatThrownBy(() ->
            ValidationUtils.requireAfter(LocalDate.of(2025, 1, 1), ref, "Data inválida")
        )
        .isInstanceOf(IllegalArgumentException.class);
    }
    
    /* === TESTES DE VALIDAÇÕES DE FORMATO === */
    
    @Test
    @DisplayName("isValidUUID deve aceitar UUID válido")
    void isValidUUID_UuidValido_DeveRetornarTrue() {
        assertThat(ValidationUtils.isValidUUID("12345678-1234-1234-1234-123456789abc")).isTrue();
        assertThat(ValidationUtils.isValidUUID("550e8400-e29b-41d4-a716-446655440000")).isTrue();
    }
    
    @Test
    @DisplayName("isValidUUID deve rejeitar UUID inválido")
    void isValidUUID_UuidInvalido_DeveRetornarFalse() {
        assertThat(ValidationUtils.isValidUUID("uuid-invalido")).isFalse();
        assertThat(ValidationUtils.isValidUUID("12345678")).isFalse();
        assertThat(ValidationUtils.isValidUUID(null)).isFalse();
        assertThat(ValidationUtils.isValidUUID("")).isFalse();
    }
    
    @Test
    @DisplayName("isValidCPF deve aceitar CPF válido")
    void isValidCPF_CpfValido_DeveRetornarTrue() {
        assertThat(ValidationUtils.isValidCPF("11144477735")).isTrue();
        assertThat(ValidationUtils.isValidCPF("12345678909")).isTrue();
    }
    
    @Test
    @DisplayName("isValidCPF deve rejeitar CPF inválido")
    void isValidCPF_CpfInvalido_DeveRetornarFalse() {
        assertThat(ValidationUtils.isValidCPF("11111111111")).isFalse(); /* Todos dígitos iguais */
        assertThat(ValidationUtils.isValidCPF("12345678901")).isFalse(); /* Dígitos verificadores errados */
        assertThat(ValidationUtils.isValidCPF("123")).isFalse(); /* Formato inválido */
        assertThat(ValidationUtils.isValidCPF(null)).isFalse();
        assertThat(ValidationUtils.isValidCPF("")).isFalse();
    }
    
    @Test
    @DisplayName("isValidCNPJ deve aceitar CNPJ válido")
    void isValidCNPJ_CnpjValido_DeveRetornarTrue() {
        assertThat(ValidationUtils.isValidCNPJ("11222333000181")).isTrue();
        assertThat(ValidationUtils.isValidCNPJ("12345678000195")).isTrue();
    }
    
    @Test
    @DisplayName("isValidCNPJ deve rejeitar CNPJ inválido")
    void isValidCNPJ_CnpjInvalido_DeveRetornarFalse() {
        assertThat(ValidationUtils.isValidCNPJ("11111111111111")).isFalse(); /* Todos dígitos iguais */
        assertThat(ValidationUtils.isValidCNPJ("12345678000100")).isFalse(); /* Dígitos verificadores errados */
        assertThat(ValidationUtils.isValidCNPJ("123")).isFalse(); /* Formato inválido */
        assertThat(ValidationUtils.isValidCNPJ(null)).isFalse();
    }
    
    @Test
    @DisplayName("isValidCEP deve aceitar CEP válido")
    void isValidCEP_CepValido_DeveRetornarTrue() {
        assertThat(ValidationUtils.isValidCEP("01310100")).isTrue();
        assertThat(ValidationUtils.isValidCEP("12345678")).isTrue();
    }
    
    @Test
    @DisplayName("isValidCEP deve rejeitar CEP inválido")
    void isValidCEP_CepInvalido_DeveRetornarFalse() {
        assertThat(ValidationUtils.isValidCEP("123")).isFalse();
        assertThat(ValidationUtils.isValidCEP("0131-010")).isFalse();
        assertThat(ValidationUtils.isValidCEP(null)).isFalse();
    }
    
    @Test
    @DisplayName("isValidMoedaISO deve aceitar código ISO válido")
    void isValidMoedaISO_CodigoValido_DeveRetornarTrue() {
        assertThat(ValidationUtils.isValidMoedaISO("BRL")).isTrue();
        assertThat(ValidationUtils.isValidMoedaISO("USD")).isTrue();
        assertThat(ValidationUtils.isValidMoedaISO("EUR")).isTrue();
    }
    
    @Test
    @DisplayName("isValidMoedaISO deve rejeitar código inválido")
    void isValidMoedaISO_CodigoInvalido_DeveRetornarFalse() {
        assertThat(ValidationUtils.isValidMoedaISO("BR")).isFalse();
        assertThat(ValidationUtils.isValidMoedaISO("brl")).isFalse(); /* Minúscula */
        assertThat(ValidationUtils.isValidMoedaISO("BRLL")).isFalse();
        assertThat(ValidationUtils.isValidMoedaISO(null)).isFalse();
    }
    
    @Test
    @DisplayName("isValidPaisISO deve aceitar código ISO válido")
    void isValidPaisISO_CodigoValido_DeveRetornarTrue() {
        assertThat(ValidationUtils.isValidPaisISO("BRA")).isTrue();
        assertThat(ValidationUtils.isValidPaisISO("USA")).isTrue();
        assertThat(ValidationUtils.isValidPaisISO("FRA")).isTrue();
    }
    
    @Test
    @DisplayName("isValidPaisISO deve rejeitar código inválido")
    void isValidPaisISO_CodigoInvalido_DeveRetornarFalse() {
        assertThat(ValidationUtils.isValidPaisISO("BR")).isFalse();
        assertThat(ValidationUtils.isValidPaisISO("bra")).isFalse();
        assertThat(ValidationUtils.isValidPaisISO("BRAZ")).isFalse();
        assertThat(ValidationUtils.isValidPaisISO(null)).isFalse();
    }
    
    @Test
    @DisplayName("isValidEmail deve aceitar email válido")
    void isValidEmail_EmailValido_DeveRetornarTrue() {
        assertThat(ValidationUtils.isValidEmail("teste@example.com")).isTrue();
        assertThat(ValidationUtils.isValidEmail("user.name@domain.co.uk")).isTrue();
        assertThat(ValidationUtils.isValidEmail("user+tag@example.com")).isTrue();
    }
    
    @Test
    @DisplayName("isValidEmail deve rejeitar email inválido")
    void isValidEmail_EmailInvalido_DeveRetornarFalse() {
        assertThat(ValidationUtils.isValidEmail("invalido")).isFalse();
        assertThat(ValidationUtils.isValidEmail("@example.com")).isFalse();
        assertThat(ValidationUtils.isValidEmail("user@")).isFalse();
        assertThat(ValidationUtils.isValidEmail(null)).isFalse();
    }
    
    /* === TESTES DE VALIDAÇÕES CONDICIONAIS === */
    
    @Test
    @DisplayName("requireFormatIf deve validar quando condição é true")
    void requireFormatIf_CondicaoTrue_DeveValidar() {
        assertThatThrownBy(() ->
            ValidationUtils.requireFormatIf(true, false, "Formato inválido")
        )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Formato inválido");
    }
    
    @Test
    @DisplayName("requireFormatIf não deve validar quando condição é false")
    void requireFormatIf_CondicaoFalse_NaoDeveValidar() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requireFormatIf(false, false, "Formato inválido")
        );
    }
    
    @Test
    @DisplayName("requireNonNullIf deve validar quando condição é true")
    void requireNonNullIf_CondicaoTrueValorNull_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requireNonNullIf(true, null, "Campo obrigatório")
        )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Campo obrigatório");
    }
    
    @Test
    @DisplayName("requireNonNullIf não deve validar quando condição é false")
    void requireNonNullIf_CondicaoFalse_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requireNonNullIf(false, null, "Campo obrigatório")
        );
    }
    
    @Test
    @DisplayName("requireNonBlankIf deve validar quando condição é true")
    void requireNonBlankIf_CondicaoTrueValorVazio_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requireNonBlankIf(true, "", "Campo obrigatório")
        )
        .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("requireNonBlankIf não deve validar quando condição é false")
    void requireNonBlankIf_CondicaoFalse_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requireNonBlankIf(false, "", "Campo obrigatório")
        );
    }
    
    /* === TESTES DE VALIDAÇÕES COMPLEXAS === */
    
    @Test
    @DisplayName("removeFormatacao deve remover máscara de CPF")
    void removeFormatacao_CpfComMascara_DeveRemoverMascara() {
        assertThat(ValidationUtils.removeFormatacao("111.444.777-35")).isEqualTo("11144477735");
        assertThat(ValidationUtils.removeFormatacao("123.456.789-09")).isEqualTo("12345678909");
    }
    
    @Test
    @DisplayName("removeFormatacao deve remover máscara de CNPJ")
    void removeFormatacao_CnpjComMascara_DeveRemoverMascara() {
        assertThat(ValidationUtils.removeFormatacao("11.222.333/0001-81")).isEqualTo("11222333000181");
    }
    
    @Test
    @DisplayName("removeFormatacao deve retornar null para entrada null")
    void removeFormatacao_Null_DeveRetornarNull() {
        assertThat(ValidationUtils.removeFormatacao(null)).isNull();
    }
    
    @Test
    @DisplayName("requireValidDocumento deve validar CPF quando tipo é 1")
    void requireValidDocumento_TipoCpfValido_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requireValidDocumento("11144477735", 1, "documento")
        );
    }
    
    @Test
    @DisplayName("requireValidDocumento deve rejeitar CPF inválido quando tipo é 1")
    void requireValidDocumento_TipoCpfInvalido_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requireValidDocumento("11111111111", 1, "documento")
        )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("CPF")
        .hasMessageContaining("inválido");
    }
    
    @Test
    @DisplayName("requireValidDocumento deve validar CNPJ quando tipo é 2")
    void requireValidDocumento_TipoCnpjValido_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() ->
            ValidationUtils.requireValidDocumento("11222333000181", 2, "documento")
        );
    }
    
    @Test
    @DisplayName("requireValidDocumento deve rejeitar CNPJ inválido quando tipo é 2")
    void requireValidDocumento_TipoCnpjInvalido_DeveLancarExcecao() {
        assertThatThrownBy(() ->
            ValidationUtils.requireValidDocumento("11111111111111", 2, "documento")
        )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("CNPJ")
        .hasMessageContaining("inválido");
    }
    
    @Test
    @DisplayName("requireValidDocumento não deve validar tipos diferentes de 1 e 2")
    void requireValidDocumento_TipoOutro_NaoDeveLancarExcecao() {
        assertThatNoException().isThrownBy(() -> {
            ValidationUtils.requireValidDocumento("QUALQUER", 3, "documento");
            ValidationUtils.requireValidDocumento("QUALQUER", 99, "documento");
        });
    }
}
