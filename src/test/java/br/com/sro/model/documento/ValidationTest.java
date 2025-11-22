package br.com.sro.model.documento;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Testes de validação para os models com Java puro.
 * 
 * <p>Demonstra o comportamento fail-fast das validações implementadas
 * nos compact constructors dos Records.</p>
 * 
 * @author Wesley Santos
 * @version 2.0.0
 */
class ValidationTest {
    
    /**
     * Testa que UUID inválido lança IllegalArgumentException.
     */
    @Test
    void documentoComUuidInvalido_DeveLancarExcecao() {
        assertThatThrownBy(() -> new Documento(
            "uuid-invalido", /* UUID inválido */
            null,
            "12345",
            LocalDate.now(),
            LocalDate.now(),
            2,
            1,
            "APL001",
            null,
            null,
            1,
            LocalDate.now(),
            LocalDate.now(),
            LocalDate.now().plusDays(1),
            "0001",
            null,
            null,
            "BRL",
            100000.0,
            100000.0,
            null,
            List.of(), List.of(), List.of(), List.of(),
            List.of(), List.of(), null, null
        ))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("UUID");
    }
    
    /**
     * Testa que código da seguradora com tamanho inválido lança IllegalArgumentException.
     */
    @Test
    void documentoComCodigoSeguradoraInvalido_DeveLancarExcecao() {
        assertThatThrownBy(() -> new Documento(
            "12345678-1234-1234-1234-123456789abc",
            null,
            "123", /* Deve ter 5 caracteres */
            LocalDate.now(),
            LocalDate.now(),
            2,
            1,
            "APL001",
            null,
            null,
            1,
            LocalDate.now(),
            LocalDate.now(),
            LocalDate.now().plusDays(1),
            "0001",
            null,
            null,
            "BRL",
            100000.0,
            100000.0,
            null,
            List.of(), List.of(), List.of(), List.of(),
            List.of(), List.of(), null, null
        ))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("5 caracteres");
    }
    
    /**
     * Testa que data de término anterior à data de início lança IllegalArgumentException.
     */
    @Test
    void documentoComDataTerminoAnteriorDataInicio_DeveLancarExcecao() {
        assertThatThrownBy(() -> new Documento(
            "12345678-1234-1234-1234-123456789abc",
            null,
            "12345",
            LocalDate.now(),
            LocalDate.now(),
            2,
            1,
            "APL001",
            null,
            null,
            1,
            LocalDate.now(),
            LocalDate.now().plusDays(10), /* Início */
            LocalDate.now().plusDays(5),  /* Término antes do início */
            "0001",
            null,
            null,
            "BRL",
            100000.0,
            100000.0,
            null,
            List.of(), List.of(), List.of(), List.of(),
            List.of(), List.of(), null, null
        ))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Data de término");
    }
    
    /**
     * Testa que documento válido é criado com sucesso e listas são imutáveis.
     */
    @Test
    void documentoValido_DeveCriarComSucesso() {
        assertThatCode(() -> {
            var doc = new Documento(
                "12345678-1234-1234-1234-123456789abc",
                "Teste de documento válido",
                "12345",
                LocalDate.now().minusDays(1),
                LocalDate.now(),
                2,
                1,
                "APL001",
                null,
                null,
                1,
                LocalDate.now().minusDays(1),
                LocalDate.now(),
                LocalDate.now().plusDays(365),
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
            
            // Verifica imutabilidade das listas
            assertThat(doc.segurados()).isEmpty();
            assertThat(doc.beneficiarios()).isEmpty();
        }).doesNotThrowAnyException();
    }
    
    /**
     * Testa que CPF inválido lança IllegalArgumentException.
     */
    @Test
    void seguradoComCpfInvalido_DeveLancarExcecao() {
        assertThatThrownBy(() -> new Segurado(
            "12345678900", /* CPF com dígitos verificadores inválidos */
            1, /* Tipo CPF */
            "João Silva",
            LocalDate.of(1990, 1, 1),
            2,
            "01310100",
            "São Paulo",
            "SP",
            "BRA"
        ))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("CPF inválido");
    }
    
    /**
     * Testa que segurado com CPF válido é criado com sucesso e métodos auxiliares funcionam.
     */
    @Test
    void seguradoComCpfValido_DeveCriarComSucesso() {
        assertThatCode(() -> {
            var segurado = new Segurado(
                "12345678909", /* CPF válido */
                1,
                "João Silva",
                LocalDate.of(1990, 1, 1),
                2,
                "01310100",
                "São Paulo",
                "SP",
                "BRA"
            );
            
            assertThat(segurado.nome()).isEqualTo("João Silva");
            assertThat(segurado.getIdade()).isGreaterThan(30);
            assertThat(segurado.isMaiorDeIdade()).isTrue();
        }).doesNotThrowAnyException();
    }
    
    /**
     * Testa que data de nascimento futura lança IllegalArgumentException.
     */
    @Test
    void seguradoComDataNascimentoFutura_DeveLancarExcecao() {
        assertThatThrownBy(() -> new Segurado(
            "12345678909",
            1,
            "João Silva",
            LocalDate.now().plusDays(1), /* Data futura */
            2,
            "01310100",
            "São Paulo",
            "SP",
            "BRA"
        ))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Data de nascimento não pode ser futura");
    }
    
    /**
     * Testa que código de país inválido lança IllegalArgumentException.
     */
    @Test
    void seguradoComPaisInvalido_DeveLancarExcecao() {
        assertThatThrownBy(() -> new Segurado(
            "12345678909",
            1,
            "João Silva",
            LocalDate.of(1990, 1, 1),
            2,
            "01310100",
            "São Paulo",
            "SP",
            "Brasil" /* Deve ser código ISO 3166-1 alpha-3 (BRA) */
        ))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("ISO 3166-1");
    }
    
    /**
     * Testa que valor negativo de prêmio lança IllegalArgumentException.
     */
    @Test
    void coberturaComValorNegativo_DeveLancarExcecao() {
        assertThatThrownBy(() -> new Cobertura(
            "0123",
            100,
            null,
            "COB001",
            "15414.999999/9999-99",
            100000.0,
            100000.0,
            null,
            LocalDate.now(),
            LocalDate.now().plusDays(365),
            null,
            1,
            null,
            1,
            -1000.0, /* Valor negativo */
            5000.0,
            null,
            null,
            null
        ))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("não pode ser negativo");
    }
    
    /**
     * Testa que data de término de cobertura anterior ao início lança IllegalArgumentException.
     */
    @Test
    void coberturaComDataTerminoAnteriorInicio_DeveLancarExcecao() {
        assertThatThrownBy(() -> new Cobertura(
            "0123",
            100,
            null,
            "COB001",
            "15414.999999/9999-99",
            100000.0,
            100000.0,
            null,
            LocalDate.now().plusDays(10),
            LocalDate.now(), /* Término antes do início */
            null,
            1,
            null,
            1,
            5000.0,
            5000.0,
            null,
            null,
            null
        ))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Data de término");
    }
    
    /**
     * Testa que cobertura válida é criada com sucesso.
     */
    @Test
    void coberturaValida_DeveCriarComSucesso() {
        assertThatCode(() -> {
            var cobertura = new Cobertura(
                "0123",
                100,
                null,
                "COB001",
                "15414.999999/9999-99",
                100000.0,
                100000.0,
                null,
                LocalDate.now(),
                LocalDate.now().plusDays(365),
                null,
                1,
                null,
                1,
                5000.0,
                5000.0,
                250.0,
                null,
                null
            );
            
            assertThat(cobertura.limiteMaximoIndenizacao()).isEqualTo(100000.0);
            assertThat(cobertura.valorPremio()).isEqualTo(5000.0);
        }).doesNotThrowAnyException();
    }
    
    /**
     * Testa que número de parcelas inválido lança IllegalArgumentException.
     */
    @Test
    void premioApoliceComNumeroParcelasInvalido_DeveLancarExcecao() {
        assertThatThrownBy(() -> new PremioApolice(
            5000.0,
            5000.0,
            null,
            null,
            0 /* Deve ser >= 1 */
        ))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("parcelas deve estar entre 1 e 999");
    }
    
    /**
     * Testa que prêmio de apólice válido é criado com sucesso.
     */
    @Test
    void premioApoliceValido_DeveCriarComSucesso() {
        assertThatCode(() -> {
            var premio = new PremioApolice(
                5000.0,
                5000.0,
                100.0,
                250.0,
                12
            );
            
            assertThat(premio.valorTotal()).isEqualTo(5000.0);
            assertThat(premio.numeroParcelas()).isEqualTo(12);
        }).doesNotThrowAnyException();
    }
    
    /**
     * Testa que percentual inválido de cosseguro lança IllegalArgumentException.
     */
    @Test
    void cosseguroComPercentualInvalido_DeveLancarExcecao() {
        assertThatThrownBy(() -> new Cosseguro(
            150.0f /* Deve estar entre 0 e 100 */
        ))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Percentual retido deve estar entre 0 e 100");
    }
    
    /**
     * Testa que cosseguro válido é criado com sucesso.
     */
    @Test
    void cosseguroValido_DeveCriarComSucesso() {
        assertThatCode(() -> {
            var cosseguro = new Cosseguro(45.5f);
            assertThat(cosseguro.percentualRetido()).isEqualTo(45.5f);
        }).doesNotThrowAnyException();
    }
}
