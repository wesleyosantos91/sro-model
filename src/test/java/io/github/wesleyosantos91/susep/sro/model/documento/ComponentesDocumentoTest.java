package io.github.wesleyosantos91.susep.sro.model.documento;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

/**
 * Testes unitários para Cobertura, PremioApolice, Cosseguro e Intermediario.
 * 
 * @author Wesley Santos
 * @version 2.0.0
 */
@DisplayName("Componentes do Documento - Testes de Validação")
class ComponentesDocumentoTest {
    
    @Nested
    @DisplayName("Cobertura - Criação válida")
    class CoberturaCriacaoValida {
        
        @Test
        @DisplayName("Deve criar cobertura completa")
        void deveCriarCoberturaCompleta() {
            assertThatNoException().isThrownBy(() ->
                new Cobertura(
                    "0101",
                    100,
                    null,
                    "COD-INTERNO-001",
                    "15414.999999/9999-99",
                    100000.00,
                    550000.00,
                    2, /* Não é sublimite */
                    LocalDate.now(),
                    LocalDate.now().plusMonths(12),
                    1, /* Principal */
                    1, /* Massificados */
                    1, /* Pessoas */
                    3, /* Regular */
                    5000.00,
                    27500.00,
                    350.00,
                    250.00,
                    1375.00
                )
            );
        }
        
        @Test
        @DisplayName("Deve criar cobertura com código 999 e descrição")
        void deveCriarCoberturaOutrasComDescricao() {
            assertThatNoException().isThrownBy(() ->
                new Cobertura(
                    "0101",
                    999, /* Outras */
                    "Cobertura especial personalizada",
                    "COD-INTERNO-002",
                    "15414.999999/9999-99",
                    50000.00,
                    275000.00,
                    2,
                    LocalDate.now(),
                    LocalDate.now().plusMonths(12),
                    2,
                    1,
                    1,
                    3,
                    2500.00,
                    13750.00,
                    null,
                    null,
                    null
                )
            );
        }
        
        @Test
        @DisplayName("Deve criar cobertura sublimite com prêmio zerado")
        void deveCriarCoberturaSublimiteComPremioZerado() {
            assertThatNoException().isThrownBy(() ->
                new Cobertura(
                    "0101",
                    100,
                    null,
                    "COD-INTERNO-003",
                    "15414.999999/9999-99",
                    10000.00,
                    55000.00,
                    1, /* É sublimite */
                    LocalDate.now(),
                    LocalDate.now().plusMonths(12),
                    2,
                    1,
                    1,
                    3,
                    0.00, /* Prêmio zerado */
                    0.00, /* Prêmio zerado */
                    null,
                    null,
                    null
                )
            );
        }
    }
    
    @Nested
    @DisplayName("Cobertura - Validações")
    class CoberturaValidacoes {
        
        @Test
        @DisplayName("Deve rejeitar grupo e ramo com tamanho diferente de 4")
        void deveRejeitarGrupoRamoTamanhoInvalido() {
            assertThatThrownBy(() ->
                new Cobertura(
                    "01", /* Tamanho inválido */
                    100,
                    null,
                    "COD-INTERNO",
                    "15414.999999/9999-99",
                    100000.00,
                    550000.00,
                    2,
                    LocalDate.now(),
                    LocalDate.now().plusMonths(12),
                    1,
                    1,
                    1,
                    3,
                    5000.00,
                    27500.00,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Grupo e ramo")
            .hasMessageContaining("exatamente 4 caracteres");
        }
        
        @Test
        @DisplayName("Deve rejeitar código fora do range 1-999")
        void deveRejeitarCodigoForaDoRange() {
            assertThatThrownBy(() ->
                new Cobertura(
                    "0101",
                    1000, /* Fora do range */
                    null,
                    "COD-INTERNO",
                    "15414.999999/9999-99",
                    100000.00,
                    550000.00,
                    2,
                    LocalDate.now(),
                    LocalDate.now().plusMonths(12),
                    1,
                    1,
                    1,
                    3,
                    5000.00,
                    27500.00,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Código da cobertura deve estar entre 1 e 999");
        }
        
        @Test
        @DisplayName("Deve exigir descrição para código 999")
        void deveExigirDescricaoParaCodigo999() {
            assertThatThrownBy(() ->
                new Cobertura(
                    "0101",
                    999,
                    null, /* Descrição ausente */
                    "COD-INTERNO",
                    "15414.999999/9999-99",
                    100000.00,
                    550000.00,
                    2,
                    LocalDate.now(),
                    LocalDate.now().plusMonths(12),
                    1,
                    1,
                    1,
                    3,
                    5000.00,
                    27500.00,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Descrição é obrigatória para código 999");
        }
        
        @Test
        @DisplayName("Deve rejeitar prêmio diferente de zero quando sublimite")
        void deveRejeitarPremioNaoZeradoQuandoSublimite() {
            assertThatThrownBy(() ->
                new Cobertura(
                    "0101",
                    100,
                    null,
                    "COD-INTERNO",
                    "15414.999999/9999-99",
                    10000.00,
                    55000.00,
                    1, /* É sublimite */
                    LocalDate.now(),
                    LocalDate.now().plusMonths(12),
                    1,
                    1,
                    1,
                    3,
                    5000.00, /* Deveria ser 0 */
                    27500.00,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Prêmio deve ser 0,00 quando LMI for sublimite");
        }
        
        @Test
        @DisplayName("Deve rejeitar data término antes de data início")
        void deveRejeitarDataTerminoAnteriorInicio() {
            LocalDate hoje = LocalDate.now();
            
            assertThatThrownBy(() ->
                new Cobertura(
                    "0101",
                    100,
                    null,
                    "COD-INTERNO",
                    "15414.999999/9999-99",
                    100000.00,
                    550000.00,
                    2,
                    hoje.plusMonths(12), /* Início */
                    hoje, /* Término antes */
                    1,
                    1,
                    1,
                    3,
                    5000.00,
                    27500.00,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Data de término deve ser maior ou igual");
        }
        
        @Test
        @DisplayName("Deve rejeitar valores negativos")
        void deveRejeitarValoresNegativos() {
            assertThatThrownBy(() ->
                new Cobertura(
                    "0101",
                    100,
                    null,
                    "COD-INTERNO",
                    "15414.999999/9999-99",
                    -1000.00, /* Negativo */
                    550000.00,
                    2,
                    LocalDate.now(),
                    LocalDate.now().plusMonths(12),
                    1,
                    1,
                    1,
                    3,
                    5000.00,
                    27500.00,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("não pode ser negativo");
        }
    }
    
    @Nested
    @DisplayName("PremioApolice - Criação válida")
    class PremioApoliceCriacaoValida {
        
        @Test
        @DisplayName("Deve criar prêmio completo")
        void deveCriarPremioCompleto() {
            assertThatNoException().isThrownBy(() ->
                new PremioApolice(
                    5000.00,
                    27500.00,
                    250.00,
                    350.00,
                    12
                )
            );
        }
        
        @Test
        @DisplayName("Deve criar prêmio sem campos opcionais")
        void deveCriarPremioSemOpcionais() {
            assertThatNoException().isThrownBy(() ->
                new PremioApolice(
                    5000.00,
                    27500.00,
                    null,
                    null,
                    1
                )
            );
        }
        
        @Test
        @DisplayName("Deve aceitar 1 parcela")
        void deveAceitar1Parcela() {
            assertThatNoException().isThrownBy(() ->
                new PremioApolice(5000.00, 27500.00, null, null, 1)
            );
        }
        
        @Test
        @DisplayName("Deve aceitar 999 parcelas")
        void deveAceitar999Parcelas() {
            assertThatNoException().isThrownBy(() ->
                new PremioApolice(5000.00, 27500.00, null, null, 999)
            );
        }
    }
    
    @Nested
    @DisplayName("PremioApolice - Validações")
    class PremioApoliceValidacoes {
        
        @Test
        @DisplayName("Deve rejeitar valor total nulo")
        void deveRejeitarValorTotalNulo() {
            assertThatThrownBy(() ->
                new PremioApolice(null, 27500.00, null, null, 12)
            )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Valor total do prêmio é obrigatório");
        }
        
        @Test
        @DisplayName("Deve rejeitar valores negativos")
        void deveRejeitarValoresNegativos() {
            assertThatThrownBy(() ->
                new PremioApolice(-5000.00, 27500.00, null, null, 12)
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("não pode ser negativo");
        }
        
        @Test
        @DisplayName("Deve rejeitar número parcelas fora do range")
        void deveRejeitarNumeroParcelasForaRange() {
            assertThatThrownBy(() ->
                new PremioApolice(5000.00, 27500.00, null, null, 0)
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Número de parcelas deve estar entre 1 e 999");
            
            assertThatThrownBy(() ->
                new PremioApolice(5000.00, 27500.00, null, null, 1000)
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Número de parcelas deve estar entre 1 e 999");
        }
    }
    
    @Nested
    @DisplayName("Cosseguro - Criação válida")
    class CosseguroCriacaoValida {
        
        @Test
        @DisplayName("Deve criar cosseguro com percentual válido")
        void deveCriarCosseguroComPercentualValido() {
            assertThatNoException().isThrownBy(() -> {
                new Cosseguro(0.0f);
                new Cosseguro(50.0f);
                new Cosseguro(100.0f);
                new Cosseguro(25.5f);
            });
        }
    }
    
    @Nested
    @DisplayName("Cosseguro - Validações")
    class CosseguroValidacoes {
        
        @Test
        @DisplayName("Deve rejeitar percentual nulo")
        void deveRejeitarPercentualNulo() {
            assertThatThrownBy(() ->
                new Cosseguro(null)
            )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Percentual retido é obrigatório");
        }
        
        @Test
        @DisplayName("Deve rejeitar percentual negativo")
        void deveRejeitarPercentualNegativo() {
            assertThatThrownBy(() ->
                new Cosseguro(-1.0f)
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Percentual retido deve estar entre 0 e 100");
        }
        
        @Test
        @DisplayName("Deve rejeitar percentual acima de 100")
        void deveRejeitarPercentualAcimaDe100() {
            assertThatThrownBy(() ->
                new Cosseguro(100.1f)
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Percentual retido deve estar entre 0 e 100");
        }
    }
    
    @Nested
    @DisplayName("Intermediario - Criação válida")
    class IntermediarioCriacaoValida {
        
        @Test
        @DisplayName("Deve criar intermediário corretor com código")
        void deveCriarIntermediarioCorretorComCodigo() {
            assertThatNoException().isThrownBy(() ->
                new Intermediario(
                    1, /* Corretor */
                    "11144477735",
                    "COD-SUSEP-12345",
                    1,
                    "Corretor Exemplo",
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA",
                    1000.00,
                    5500.00
                )
            );
        }
        
        @Test
        @DisplayName("Deve criar intermediário não corretor sem código")
        void deveCriarIntermediarioNaoCorretorSemCodigo() {
            assertThatNoException().isThrownBy(() ->
                new Intermediario(
                    2, /* Representante */
                    "11222333000181",
                    null,
                    2,
                    "Representante Empresa LTDA",
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA",
                    500.00,
                    2750.00
                )
            );
        }
    }
    
    @Nested
    @DisplayName("Intermediario - Validações")
    class IntermediarioValidacoes {
        
        @Test
        @DisplayName("Deve exigir código Susep para tipo Corretor")
        void deveExigirCodigoSusepParaCorretor() {
            assertThatThrownBy(() ->
                new Intermediario(
                    1, /* Corretor */
                    "11144477735",
                    null, /* Código ausente */
                    1,
                    "Corretor Exemplo",
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA",
                    1000.00,
                    5500.00
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Código Susep é obrigatório para tipo Corretor");
        }
        
        @Test
        @DisplayName("Deve rejeitar tipo fora do range 1-6")
        void deveRejeitarTipoForaDoRange() {
            assertThatThrownBy(() ->
                new Intermediario(
                    7, /* Inválido */
                    "11144477735",
                    null,
                    1,
                    "Intermediário",
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA",
                    1000.00,
                    5500.00
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Tipo deve estar entre 1 e 6");
        }
        
        @Test
        @DisplayName("Deve rejeitar CPF inválido")
        void deveRejeitarCpfInvalido() {
            assertThatThrownBy(() ->
                new Intermediario(
                    2,
                    "11111111111",
                    null,
                    1,
                    "Intermediário",
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA",
                    1000.00,
                    5500.00
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("CPF")
            .hasMessageContaining("inválido");
        }
        
        @Test
        @DisplayName("Deve rejeitar valores de comissão negativos")
        void deveRejeitarValoresComissaoNegativos() {
            assertThatThrownBy(() ->
                new Intermediario(
                    2,
                    "11144477735",
                    null,
                    1,
                    "Intermediário",
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BRA",
                    -1000.00,
                    5500.00
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("não pode ser negativo");
        }
        
        @Test
        @DisplayName("Deve rejeitar país em formato inválido")
        void deveRejeitarPaisFormatoInvalido() {
            assertThatThrownBy(() ->
                new Intermediario(
                    2,
                    "11144477735",
                    null,
                    1,
                    "Intermediário",
                    "01310100",
                    "São Paulo",
                    "São Paulo",
                    "BR", /* Formato inválido */
                    1000.00,
                    5500.00
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("País deve estar no formato ISO 3166-1 alpha-3");
        }
    }
}
