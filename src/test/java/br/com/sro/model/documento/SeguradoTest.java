package br.com.sro.model.documento;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

/**
 * Testes unitários para a classe Segurado.
 * 
 * @author Wesley Santos
 * @version 2.0.0
 */
@DisplayName("Segurado - Testes de Validação")
class SeguradoTest {
    
    @Nested
    @DisplayName("Criação com dados válidos")
    class CriacaoValida {
        
        @Test
        @DisplayName("Deve criar segurado com CPF válido")
        void deveCriarSeguradoComCpfValido() {
            assertThatNoException().isThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1, /* CPF */
                    "João da Silva",
                    null,
                    null,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            );
        }
        
        @Test
        @DisplayName("Deve criar segurado com CNPJ válido")
        void deveCriarSeguradoComCnpjValido() {
            assertThatNoException().isThrownBy(() ->
                new Segurado(
                    "11222333000181",
                    2, /* CNPJ */
                    "Empresa LTDA",
                    null,
                    null,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            );
        }
        
        @Test
        @DisplayName("Deve criar segurado com passaporte")
        void deveCriarSeguradoComPassaporte() {
            assertThatNoException().isThrownBy(() ->
                new Segurado(
                    "AB123456",
                    3, /* Passaporte */
                    "John Doe",
                    LocalDate.of(1990, 5, 15),
                    2,
                    "10001",
                    "New York",
                    "NY",
                    "USA"
                )
            );
        }
        
        @Test
        @DisplayName("Deve criar segurado completo com data nascimento")
        void deveCriarSeguradoCompletoComDataNascimento() {
            assertThatNoException().isThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    "Maria Santos",
                    LocalDate.of(1985, 3, 20),
                    1, /* Feminino */
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            );
        }
    }
    
    @Nested
    @DisplayName("Validações de obrigatoriedade")
    class ValidacoesObrigatoriedade {
        
        @Test
        @DisplayName("Deve rejeitar documento nulo")
        void deveRejeitarDocumentoNulo() {
            assertThatThrownBy(() ->
                new Segurado(
                    null,
                    1,
                    "João da Silva",
                    null,
                    null,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Documento é obrigatório");
        }
        
        @Test
        @DisplayName("Deve rejeitar tipo documento nulo")
        void deveRejeitarTipoDocumentoNulo() {
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    null,
                    "João da Silva",
                    null,
                    null,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Tipo de documento é obrigatório");
        }
        
        @Test
        @DisplayName("Deve rejeitar nome nulo")
        void deveRejeitarNomeNulo() {
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    null,
                    null,
                    null,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Nome é obrigatório");
        }
        
        @Test
        @DisplayName("Deve rejeitar código postal nulo")
        void deveRejeitarCodigoPostalNulo() {
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    "João da Silva",
                    null,
                    null,
                    null,
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Código postal é obrigatório");
        }
        
        @Test
        @DisplayName("Deve rejeitar cidade nula")
        void deveRejeitarCidadeNula() {
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    "João da Silva",
                    null,
                    null,
                    "01310100",
                    null,
                    "São Paulo",
                    "BRA"
                )
            )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Cidade é obrigatória");
        }
        
        @Test
        @DisplayName("Deve rejeitar estado nulo")
        void deveRejeitarEstadoNulo() {
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    "João da Silva",
                    null,
                    null,
                    "01310100",
                    "São Paulo",
                    null,
                    "BRA"
                )
            )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Estado é obrigatório");
        }
        
        @Test
        @DisplayName("Deve rejeitar país nulo")
        void deveRejeitarPaisNulo() {
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    "João da Silva",
                    null,
                    null,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    null
                )
            )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("País é obrigatório");
        }
    }
    
    @Nested
    @DisplayName("Validações de formato")
    class ValidacoesFormato {
        
        @Test
        @DisplayName("Deve rejeitar CPF inválido")
        void deveRejeitarCpfInvalido() {
            assertThatThrownBy(() ->
                new Segurado(
                    "11111111111", /* CPF inválido */
                    1,
                    "João da Silva",
                    null,
                    null,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("CPF inválido");
        }
        
        @Test
        @DisplayName("Deve rejeitar CNPJ inválido")
        void deveRejeitarCnpjInvalido() {
            assertThatThrownBy(() ->
                new Segurado(
                    "11111111111111", /* CNPJ inválido */
                    2,
                    "Empresa LTDA",
                    null,
                    null,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("CNPJ inválido");
        }
        
        @Test
        @DisplayName("Deve rejeitar país em formato inválido")
        void deveRejeitarPaisFormatoInvalido() {
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    "João da Silva",
                    null,
                    null,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BR" /* Formato inválido */
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("País deve estar no formato ISO 3166-1 alpha-3");
        }
        
        @Test
        @DisplayName("Deve rejeitar país minúsculo")
        void deveRejeitarPaisMinusculo() {
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    "João da Silva",
                    null,
                    null,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "bra"
                )
            )
            .isInstanceOf(IllegalArgumentException.class);
        }
    }
    
    @Nested
    @DisplayName("Validações de domínio")
    class ValidacoesDominio {
        
        @Test
        @DisplayName("Deve rejeitar tipo documento inválido")
        void deveRejeitarTipoDocumentoInvalido() {
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    100, /* Inválido */
                    "João da Silva",
                    null,
                    null,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Tipo de documento deve ser");
        }
        
        @Test
        @DisplayName("Deve rejeitar sexo fora do range 1-3")
        void deveRejeitarSexoInvalido() {
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    "João da Silva",
                    LocalDate.of(1990, 1, 1),
                    4, /* Inválido */
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Sexo");
        }
        
        @Test
        @DisplayName("Deve aceitar sexos válidos")
        void deveAceitarSexosValidos() {
            assertThatNoException().isThrownBy(() -> {
                new Segurado(
                    "11144477735",
                    1,
                    "Maria Santos",
                    LocalDate.of(1990, 1, 1),
                    1, /* Feminino */
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                );
                new Segurado(
                    "11144477735",
                    1,
                    "João Silva",
                    LocalDate.of(1990, 1, 1),
                    2, /* Masculino */
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                );
                new Segurado(
                    "11144477735",
                    1,
                    "Pessoa",
                    LocalDate.of(1990, 1, 1),
                    3, /* Não informado */
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                );
            });
        }
    }
    
    @Nested
    @DisplayName("Validações de tamanho")
    class ValidacoesTamanho {
        
        @Test
        @DisplayName("Deve rejeitar documento maior que 40 caracteres")
        void deveRejeitarDocumentoTamanhoExcessivo() {
            String documentoLongo = "A".repeat(41);
            
            assertThatThrownBy(() ->
                new Segurado(
                    documentoLongo,
                    3, /* Passaporte */
                    "João da Silva",
                    null,
                    null,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Documento")
            .hasMessageContaining("máximo");
        }
        
        @Test
        @DisplayName("Deve rejeitar nome menor que 3 caracteres")
        void deveRejeitarNomeMuitoCurto() {
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    "AB", /* Muito curto */
                    null,
                    null,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Nome")
            .hasMessageContaining("mínimo");
        }
        
        @Test
        @DisplayName("Deve rejeitar nome maior que 144 caracteres")
        void deveRejeitarNomeTamanhoExcessivo() {
            String nomeLongo = "A".repeat(145);
            
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    nomeLongo,
                    null,
                    null,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Nome")
            .hasMessageContaining("máximo");
        }
        
        @Test
        @DisplayName("Deve rejeitar código postal maior que 30 caracteres")
        void deveRejeitarCodigoPostalTamanhoExcessivo() {
            String cepLongo = "1".repeat(31);
            
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    "João da Silva",
                    null,
                    null,
                    cepLongo,
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Código postal")
            .hasMessageContaining("máximo");
        }
        
        @Test
        @DisplayName("Deve rejeitar cidade maior que 100 caracteres")
        void deveRejeitarCidadeTamanhoExcessivo() {
            String cidadeLonga = "A".repeat(101);
            
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    "João da Silva",
                    null,
                    null,
                    "01310100",
                    cidadeLonga,
                    "São Paulo",
                    "BRA"
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Cidade")
            .hasMessageContaining("máximo");
        }
        
        @Test
        @DisplayName("Deve rejeitar estado maior que 50 caracteres")
        void deveRejeitarEstadoTamanhoExcessivo() {
            String estadoLongo = "A".repeat(51);
            
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    "João da Silva",
                    null,
                    null,
                    "01310100",
                    "São Paulo",
                    estadoLongo,
                    "BRA"
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Estado")
            .hasMessageContaining("máximo");
        }
    }
    
    @Nested
    @DisplayName("Validações de data de nascimento")
    class ValidacoesDataNascimento {
        
        @Test
        @DisplayName("Deve rejeitar data nascimento futura")
        void deveRejeitarDataNascimentoFutura() {
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    "João da Silva",
                    LocalDate.now().plusDays(1), /* Futura */
                    2,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Data de nascimento não pode ser futura");
        }
        
        @Test
        @DisplayName("Deve rejeitar data nascimento resultando idade inválida")
        void deveRejeitarDataNascimentoIdadeInvalida() {
            assertThatThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    "João da Silva",
                    LocalDate.now().minusYears(200), /* Muito antiga */
                    2,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Data de nascimento resulta em idade inválida");
        }
        
        @Test
        @DisplayName("Deve aceitar data nascimento válida")
        void deveAceitarDataNascimentoValida() {
            assertThatNoException().isThrownBy(() ->
                new Segurado(
                    "11144477735",
                    1,
                    "João da Silva",
                    LocalDate.of(1990, 5, 15),
                    2,
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            );
        }
    }
    
    @Nested
    @DisplayName("Métodos auxiliares")
    class MetodosAuxiliares {
        
        @Test
        @DisplayName("getIdade deve calcular idade corretamente")
        void getIdadeDeveCalcularIdadeCorretamente() {
            var segurado = new Segurado(
                "11144477735",
                1,
                "João da Silva",
                LocalDate.now().minusYears(30),
                2,
                "01310100",
                "São Paulo",
                "São Paulo",
                "BRA"
            );
            
            assertThat(segurado.getIdade()).isEqualTo(30);
        }
        
        @Test
        @DisplayName("getIdade deve retornar null quando data nascimento ausente")
        void getIdadeDeveRetornarNullQuandoDataAusente() {
            var segurado = new Segurado(
                "11222333000181",
                2, /* CNPJ - sem data nascimento */
                "Empresa LTDA",
                null,
                null,
                "01310100",
                "São Paulo",
                "São Paulo",
                "BRA"
            );
            
            assertThat(segurado.getIdade()).isNull();
        }
        
        @Test
        @DisplayName("isMaiorDeIdade deve identificar maior de idade")
        void isMaiorDeIdadeDeveIdentificarMaiorDeIdade() {
            var segurado = new Segurado(
                "11144477735",
                1,
                "João da Silva",
                LocalDate.now().minusYears(25),
                2,
                "01310100",
                "São Paulo",
                "São Paulo",
                "BRA"
            );
            
            assertThat(segurado.isMaiorDeIdade()).isTrue();
        }
        
        @Test
        @DisplayName("isMaiorDeIdade deve identificar menor de idade")
        void isMaiorDeIdadeDeveIdentificarMenorDeIdade() {
            var segurado = new Segurado(
                "11144477735",
                1,
                "João da Silva",
                LocalDate.now().minusYears(15),
                2,
                "01310100",
                "São Paulo",
                "São Paulo",
                "BRA"
            );
            
            assertThat(segurado.isMaiorDeIdade()).isFalse();
        }
        
        @Test
        @DisplayName("isMaiorDeIdade deve retornar false quando data nascimento ausente")
        void isMaiorDeIdadeDeveRetornarFalseQuandoDataAusente() {
            var segurado = new Segurado(
                "11222333000181",
                2,
                "Empresa LTDA",
                null,
                null,
                "01310100",
                "São Paulo",
                "São Paulo",
                "BRA"
            );
            
            assertThat(segurado.isMaiorDeIdade()).isFalse();
        }
    }
}
