package br.com.sro.model.documento;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Testes unitários para Tomador e Beneficiario.
 * 
 * <p>Como essas classes compartilham a mesma estrutura e validações,
 * os testes são agrupados neste arquivo.</p>
 * 
 * @author Wesley Santos
 * @version 2.0.0
 */
@DisplayName("Tomador e Beneficiario - Testes de Validação")
class PessoasAssociadasTest {
    
    @Nested
    @DisplayName("Tomador - Criação válida")
    class TomadorCriacaoValida {
        
        @Test
        @DisplayName("Deve criar tomador com CPF válido")
        void deveCriarTomadorComCpfValido() {
            assertThatNoException().isThrownBy(() ->
                new Tomador(
                    "11144477735",
                    1,
                    "José Santos",
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            );
        }
        
        @Test
        @DisplayName("Deve criar tomador com CNPJ válido")
        void deveCriarTomadorComCnpjValido() {
            assertThatNoException().isThrownBy(() ->
                new Tomador(
                    "11222333000181",
                    2,
                    "Empresa LTDA",
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            );
        }
        
        @Test
        @DisplayName("Deve criar tomador com passaporte")
        void deveCriarTomadorComPassaporte() {
            assertThatNoException().isThrownBy(() ->
                new Tomador(
                    "AB123456",
                    3,
                    "John Doe",
                    "10001",
                    "New York",
                    "NY",
                    "USA"
                )
            );
        }
    }
    
    @Nested
    @DisplayName("Beneficiario - Criação válida")
    class BeneficiarioCriacaoValida {
        
        @Test
        @DisplayName("Deve criar beneficiário com CPF válido")
        void deveCriarBeneficiarioComCpfValido() {
            assertThatNoException().isThrownBy(() ->
                new Beneficiario(
                    "11144477735",
                    1,
                    "Maria Oliveira",
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            );
        }
        
        @Test
        @DisplayName("Deve criar beneficiário com CNPJ válido")
        void deveCriarBeneficiarioComCnpjValido() {
            assertThatNoException().isThrownBy(() ->
                new Beneficiario(
                    "11222333000181",
                    2,
                    "Instituição Financeira S/A",
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA"
                )
            );
        }
    }
    
    @Nested
    @DisplayName("Tomador - Validações de obrigatoriedade")
    class TomadorValidacoesObrigatoriedade {
        
        @Test
        @DisplayName("Deve rejeitar documento nulo")
        void deveRejeitarDocumentoNulo() {
            assertThatThrownBy(() ->
                new Tomador(null, 1, "José Santos", "01310100", "São Paulo", "São Paulo", "BRA")
            )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Documento é obrigatório");
        }
        
        @Test
        @DisplayName("Deve rejeitar nome nulo")
        void deveRejeitarNomeNulo() {
            assertThatThrownBy(() ->
                new Tomador("11144477735", 1, null, "01310100", "São Paulo", "São Paulo", "BRA")
            )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Nome é obrigatório");
        }
        
        @Test
        @DisplayName("Deve rejeitar país nulo")
        void deveRejeitarPaisNulo() {
            assertThatThrownBy(() ->
                new Tomador("11144477735", 1, "José Santos", "01310100", "São Paulo", "São Paulo", null)
            )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("País é obrigatório");
        }
    }
    
    @Nested
    @DisplayName("Beneficiario - Validações de obrigatoriedade")
    class BeneficiarioValidacoesObrigatoriedade {
        
        @Test
        @DisplayName("Deve rejeitar documento nulo")
        void deveRejeitarDocumentoNulo() {
            assertThatThrownBy(() ->
                new Beneficiario(null, 1, "Maria Oliveira", "01310100", "São Paulo", "São Paulo", "BRA")
            )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Documento é obrigatório");
        }
        
        @Test
        @DisplayName("Deve rejeitar cidade nula")
        void deveRejeitarCidadeNula() {
            assertThatThrownBy(() ->
                new Beneficiario("11144477735", 1, "Maria Oliveira", "01310100", null, "São Paulo", "BRA")
            )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Cidade é obrigatória");
        }
        
        @Test
        @DisplayName("Deve rejeitar estado nulo")
        void deveRejeitarEstadoNulo() {
            assertThatThrownBy(() ->
                new Beneficiario("11144477735", 1, "Maria Oliveira", "01310100", "São Paulo", null, "BRA")
            )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Estado é obrigatório");
        }
    }
    
    @Nested
    @DisplayName("Tomador - Validações de formato")
    class TomadorValidacoesFormato {
        
        @Test
        @DisplayName("Deve rejeitar CPF inválido")
        void deveRejeitarCpfInvalido() {
            assertThatThrownBy(() ->
                new Tomador("11111111111", 1, "José Santos", "01310100", "São Paulo", "São Paulo", "BRA")
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("CPF")
            .hasMessageContaining("inválido");
        }
        
        @Test
        @DisplayName("Deve rejeitar CNPJ inválido")
        void deveRejeitarCnpjInvalido() {
            assertThatThrownBy(() ->
                new Tomador("11111111111111", 2, "Empresa LTDA", "01310100", "São Paulo", "São Paulo", "BRA")
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("CNPJ")
            .hasMessageContaining("inválido");
        }
        
        @Test
        @DisplayName("Deve rejeitar país em formato inválido")
        void deveRejeitarPaisFormatoInvalido() {
            assertThatThrownBy(() ->
                new Tomador("11144477735", 1, "José Santos", "01310100", "São Paulo", "São Paulo", "BR")
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("País deve estar no formato ISO 3166-1 alpha-3");
        }
        
        @Test
        @DisplayName("Deve rejeitar país minúsculo")
        void deveRejeitarPaisMinusculo() {
            assertThatThrownBy(() ->
                new Tomador("11144477735", 1, "José Santos", "01310100", "São Paulo", "São Paulo", "bra")
            )
            .isInstanceOf(IllegalArgumentException.class);
        }
    }
    
    @Nested
    @DisplayName("Beneficiario - Validações de formato")
    class BeneficiarioValidacoesFormato {
        
        @Test
        @DisplayName("Deve rejeitar CPF inválido")
        void deveRejeitarCpfInvalido() {
            assertThatThrownBy(() ->
                new Beneficiario("12345678901", 1, "Maria Oliveira", "01310100", "São Paulo", "São Paulo", "BRA")
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("CPF")
            .hasMessageContaining("inválido");
        }
        
        @Test
        @DisplayName("Deve rejeitar CNPJ inválido")
        void deveRejeitarCnpjInvalido() {
            assertThatThrownBy(() ->
                new Beneficiario("12345678000100", 2, "Instituição S/A", "01310100", "São Paulo", "São Paulo", "BRA")
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("CNPJ")
            .hasMessageContaining("inválido");
        }
        
        @Test
        @DisplayName("Deve aceitar países válidos")
        void deveAceitarPaisesValidos() {
            assertThatNoException().isThrownBy(() -> {
                new Beneficiario("11144477735", 1, "Maria Oliveira", "01310100", "São Paulo", "São Paulo", "BRA");
                new Beneficiario("11144477735", 1, "John Doe", "10001", "New York", "NY", "USA");
                new Beneficiario("11144477735", 1, "Jean Dupont", "75001", "Paris", "IDF", "FRA");
            });
        }
    }
    
    @Nested
    @DisplayName("Tomador - Validações de domínio")
    class TomadorValidacoesDominio {
        
        @Test
        @DisplayName("Deve rejeitar tipo documento inválido")
        void deveRejeitarTipoDocumentoInvalido() {
            assertThatThrownBy(() ->
                new Tomador("DOCUMENTO", 100, "José Santos", "01310100", "São Paulo", "São Paulo", "BRA")
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Tipo de documento deve ser");
        }
        
        @Test
        @DisplayName("Deve aceitar todos os tipos válidos de documento")
        void deveAceitarTiposValidosDeDocumento() {
            assertThatNoException().isThrownBy(() -> {
                new Tomador("11144477735", 1, "José", "01310100", "São Paulo", "SP", "BRA"); /* CPF */
                new Tomador("11222333000181", 2, "Empresa", "01310100", "São Paulo", "SP", "BRA"); /* CNPJ */
                new Tomador("AB123456", 3, "John", "10001", "New York", "NY", "USA"); /* Passaporte */
                new Tomador("OUTRO-DOC", 99, "Pessoa", "01310100", "São Paulo", "SP", "BRA"); /* Outros */
            });
        }
    }
    
    @Nested
    @DisplayName("Beneficiario - Validações de domínio")
    class BeneficiarioValidacoesDominio {
        
        @Test
        @DisplayName("Deve rejeitar tipo documento fora do range permitido")
        void deveRejeitarTipoDocumentoForaRange() {
            assertThatThrownBy(() ->
                new Beneficiario("DOCUMENTO", 0, "Maria", "01310100", "São Paulo", "São Paulo", "BRA")
            )
            .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Deve aceitar tipo documento 99 para outros")
        void deveAceitarTipoDocumento99() {
            assertThatNoException().isThrownBy(() ->
                new Beneficiario("DOC-ESPECIAL", 99, "Maria Oliveira", "01310100", "São Paulo", "SP", "BRA")
            );
        }
    }
    
    @Nested
    @DisplayName("Tomador - Validações de tamanho")
    class TomadorValidacoesTamanho {
        
        @Test
        @DisplayName("Deve rejeitar documento maior que 40 caracteres")
        void deveRejeitarDocumentoTamanhoExcessivo() {
            String documentoLongo = "A".repeat(41);
            
            assertThatThrownBy(() ->
                new Tomador(documentoLongo, 3, "José Santos", "01310100", "São Paulo", "São Paulo", "BRA")
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Documento")
            .hasMessageContaining("máximo");
        }
        
        @Test
        @DisplayName("Deve rejeitar nome menor que 3 caracteres")
        void deveRejeitarNomeMuitoCurto() {
            assertThatThrownBy(() ->
                new Tomador("11144477735", 1, "AB", "01310100", "São Paulo", "São Paulo", "BRA")
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
                new Tomador("11144477735", 1, nomeLongo, "01310100", "São Paulo", "São Paulo", "BRA")
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Nome")
            .hasMessageContaining("máximo");
        }
        
        @Test
        @DisplayName("Deve aceitar nome com tamanho limite")
        void deveAceitarNomeComTamanhoLimite() {
            String nomeLimite = "A".repeat(144);
            
            assertThatNoException().isThrownBy(() ->
                new Tomador("11144477735", 1, nomeLimite, "01310100", "São Paulo", "São Paulo", "BRA")
            );
        }
    }
    
    @Nested
    @DisplayName("Beneficiario - Validações de tamanho")
    class BeneficiarioValidacoesTamanho {
        
        @Test
        @DisplayName("Deve rejeitar código postal maior que 30 caracteres")
        void deveRejeitarCodigoPostalTamanhoExcessivo() {
            String cepLongo = "1".repeat(31);
            
            assertThatThrownBy(() ->
                new Beneficiario("11144477735", 1, "Maria Oliveira", cepLongo, "São Paulo", "São Paulo", "BRA")
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
                new Beneficiario("11144477735", 1, "Maria Oliveira", "01310100", cidadeLonga, "SP", "BRA")
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
                new Beneficiario("11144477735", 1, "Maria Oliveira", "01310100", "São Paulo", estadoLongo, "BRA")
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Estado")
            .hasMessageContaining("máximo");
        }
    }
}
