package io.github.wesleyosantos91.susep.sro.model.documento;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Testes unitários para a classe Documento.
 * 
 * @author Wesley Santos
 * @version 2.0.0
 */
@DisplayName("Documento - Testes de Validação")
class DocumentoTest {
    
    @Nested
    @DisplayName("Criação com dados válidos")
    class CriacaoValida {
        
        @Test
        @DisplayName("Deve criar documento com dados mínimos obrigatórios")
        void deveCriarDocumentoComDadosMinimos() {
            assertThatNoException().isThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            );
        }
        
        @Test
        @DisplayName("Deve criar documento completo")
        void deveCriarDocumentoCompleto() {
            assertThatNoException().isThrownBy(() ->
                new Documento(
                    "550e8400-e29b-41d4-a716-446655440000",
                    "Anotação de teste",
                    "12345",
                    LocalDate.of(2025, 1, 1),
                    LocalDate.of(2025, 1, 2),
                    2,
                    1,
                    "APOLICE-123",
                    "SUSEP-123456",
                    null,
                    1,
                    LocalDate.of(2025, 1, 1),
                    LocalDate.of(2025, 1, 1),
                    LocalDate.of(2025, 12, 31),
                    "0001",
                    null,
                    null,
                    "USD",
                    100000.00,
                    550000.00,
                    1,
                    List.of(),
                    List.of(),
                    List.of(),
                    List.of(),
                    List.of(),
                    List.of(),
                    null,
                    null
                )
            );
        }
        
        @Test
        @DisplayName("Deve criar documento com cosseguro aceito")
        void deveCriarDocumentoComCosseguroAceito() {
            assertThatNoException().isThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    2,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    2, /* Cosseguro Aceito */
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    "67890", /* Seguradora líder */
                    "APOLICE-LIDER",
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            );
        }
        
        @Test
        @DisplayName("Deve criar documento com certificado quando tipo requer")
        void deveCriarDocumentoComCertificado() {
            assertThatNoException().isThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    2,
                    4, /* Tipo que requer certificado */
                    "APOLICE-001",
                    null,
                    "CERT-12345", /* Certificado */
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            );
        }
    }
    
    @Nested
    @DisplayName("Validações de UUID")
    class ValidacoesUuid {
        
        @Test
        @DisplayName("Deve rejeitar UUID nulo")
        void deveRejeitarUuidNulo() {
            assertThatThrownBy(() ->
                new Documento(
                    null,
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("UUID é obrigatório");
        }
        
        @Test
        @DisplayName("Deve rejeitar UUID em formato inválido")
        void deveRejeitarUuidFormatoInvalido() {
            assertThatThrownBy(() ->
                new Documento(
                    "uuid-invalido",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("UUID deve estar no formato");
        }
        
        @Test
        @DisplayName("Deve aceitar UUID maiúsculo")
        void deveAceitarUuidMaiusculo() {
            assertThatNoException().isThrownBy(() ->
                new Documento(
                    "550E8400-E29B-41D4-A716-446655440000",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            );
        }
    }
    
    @Nested
    @DisplayName("Validações de códigos")
    class ValidacoesCodigos {
        
        @Test
        @DisplayName("Deve rejeitar código seguradora com tamanho diferente de 5")
        void deveRejeitarCodigoSeguradoraTamanhoInvalido() {
            assertThatThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "1234", /* Tamanho inválido */
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Código da seguradora deve ter exatamente 5 caracteres");
        }
        
        @Test
        @DisplayName("Deve rejeitar código filial com tamanho diferente de 4")
        void deveRejeitarCodigoFilialTamanhoInvalido() {
            assertThatThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "001", /* Tamanho inválido */
                    null,
                    null,
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Código da filial deve ter exatamente 4 caracteres");
        }
        
        @Test
        @DisplayName("Deve rejeitar código apólice maior que 60 caracteres")
        void deveRejeitarCodigoApoliceTamanhoExcessivo() {
            String codigoLongo = "A".repeat(61);
            
            assertThatThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    1,
                    codigoLongo,
                    null,
                    null,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Código da apólice deve ter no máximo 60 caracteres");
        }
    }
    
    @Nested
    @DisplayName("Validações de moeda")
    class ValidacoesMoeda {
        
        @Test
        @DisplayName("Deve aceitar moedas ISO válidas")
        void deveAceitarMoedasValidadas() {
            assertThatNoException().isThrownBy(() -> {
                LocalDate hoje = LocalDate.now();
                new Documento(
                    "12345678-1234-1234-1234-123456789abc", null, "12345", hoje, hoje, 1, 1,
                    "APOLICE-001", null, null, 1, hoje, hoje, hoje.plusDays(30), "0001",
                    null, null, "BRL", 0.0, 0.0, null, null, null, null, null, null, null, null, null
                );
                new Documento(
                    "12345678-1234-1234-1234-123456789abc", null, "12345", hoje, hoje, 1, 1,
                    "APOLICE-002", null, null, 1, hoje, hoje, hoje.plusDays(30), "0001",
                    null, null, "USD", 0.0, 0.0, null, null, null, null, null, null, null, null, null
                );
                new Documento(
                    "12345678-1234-1234-1234-123456789abc", null, "12345", hoje, hoje, 1, 1,
                    "APOLICE-003", null, null, 1, hoje, hoje, hoje.plusDays(30), "0001",
                    null, null, "EUR", 0.0, 0.0, null, null, null, null, null, null, null, null, null
                );
            });
        }
        
        @Test
        @DisplayName("Deve rejeitar moeda em formato inválido")
        void deveRejeitarMoedaFormatoInvalido() {
            assertThatThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "BR", /* Formato inválido */
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Moeda deve estar no formato ISO 4217");
        }
        
        @Test
        @DisplayName("Deve rejeitar moeda minúscula")
        void deveRejeitarMoedaMinuscula() {
            assertThatThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "brl",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class);
        }
    }
    
    @Nested
    @DisplayName("Validações de domínios")
    class ValidacoesDominios {
        
        @Test
        @DisplayName("Deve rejeitar indicador exclusão inválido")
        void deveRejeitarIndicadorExclusaoInvalido() {
            assertThatThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    3, /* Inválido */
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Indicador de exclusão deve ser 1 (Sim) ou 2 (Não)");
        }
        
        @Test
        @DisplayName("Deve rejeitar tipo documento fora do range 1-11")
        void deveRejeitarTipoDocumentoInvalido() {
            assertThatThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    12, /* Inválido */
                    "APOLICE-001",
                    null,
                    null,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Tipo de documento deve estar entre 1 e 11");
        }
        
        @Test
        @DisplayName("Deve rejeitar tipo emissão inválido")
        void deveRejeitarTipoEmissaoInvalido() {
            assertThatThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    3, /* Inválido */
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Tipo de emissão deve ser 1 (Própria) ou 2 (Cosseguro Aceito)");
        }
        
        @Test
        @DisplayName("Deve rejeitar cobertura básica inválida")
        void deveRejeitarCoberturaBasicaInvalida() {
            assertThatThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "BRL",
                    0.0,
                    0.0,
                    3, /* Inválido */
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Cobertura básica deve ser 1 (Simples) ou 2 (Ampla)");
        }
    }
    
    @Nested
    @DisplayName("Validações de valores")
    class ValidacoesValores {
        
        @Test
        @DisplayName("Deve rejeitar LMG negativo")
        void deveRejeitarLmgNegativo() {
            assertThatThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "BRL",
                    -1000.0, /* Negativo */
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Limite máximo de garantia não pode ser negativo");
        }
        
        @Test
        @DisplayName("Deve rejeitar LMG em reais negativo")
        void deveRejeitarLmgRealNegativo() {
            assertThatThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "BRL",
                    0.0,
                    -1000.0, /* Negativo */
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Limite máximo de garantia em reais não pode ser negativo");
        }
    }
    
    @Nested
    @DisplayName("Validações de datas")
    class ValidacoesDatas {
        
        @Test
        @DisplayName("Deve rejeitar data registro futura")
        void deveRejeitarDataRegistroFutura() {
            LocalDate futuro = LocalDate.now().plusDays(1);
            
            assertThatThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    futuro, /* Futura */
                    LocalDate.now(),
                    1,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Data de registro não pode ser futura");
        }
        
        @Test
        @DisplayName("Deve rejeitar data término antes de data início")
        void deveRejeitarDataTerminoAnteriorInicio() {
            LocalDate hoje = LocalDate.now();
            
            assertThatThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    hoje,
                    hoje,
                    1,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    1,
                    hoje,
                    hoje.plusDays(30), /* Início */
                    hoje, /* Término antes */
                    "0001",
                    null,
                    null,
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Data de término deve ser maior ou igual à data de início");
        }
    }
    
    @Nested
    @DisplayName("Validações condicionais")
    class ValidacoesCondicionais {
        
        @Test
        @DisplayName("Deve exigir certificado para tipo documento 4")
        void deveExigirCertificadoParaTipo4() {
            assertThatThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    4, /* Tipo que requer certificado */
                    "APOLICE-001",
                    null,
                    null, /* Certificado ausente */
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null,
                    null,
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Certificado é obrigatório para tipos de documento 4, 7 e 10");
        }
        
        @Test
        @DisplayName("Deve exigir seguradora líder para cosseguro aceito")
        void deveExigirSeguradoraLiderParaCosseguroAceito() {
            assertThatThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    2, /* Cosseguro Aceito */
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    null, /* Seguradora líder ausente */
                    null,
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Código da seguradora líder é obrigatório para cosseguro aceito");
        }
        
        @Test
        @DisplayName("Deve exigir apólice líder para cosseguro aceito")
        void deveExigirApoliceLiderParaCosseguroAceito() {
            assertThatThrownBy(() ->
                new Documento(
                    "12345678-1234-1234-1234-123456789abc",
                    null,
                    "12345",
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    1,
                    "APOLICE-001",
                    null,
                    null,
                    2, /* Cosseguro Aceito */
                    LocalDate.now(),
                    LocalDate.now(),
                    LocalDate.now().plusDays(30),
                    "0001",
                    "67890",
                    null, /* Apólice líder ausente */
                    "BRL",
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Código da apólice líder é obrigatório para cosseguro aceito");
        }
    }
    
    @Nested
    @DisplayName("Imutabilidade")
    class TestesImutabilidade {
        
        @Test
        @DisplayName("Deve criar cópias defensivas de listas")
        void deveCriarCopiasDefensivasDeListas() {
            var documento = new Documento(
                "12345678-1234-1234-1234-123456789abc",
                null,
                "12345",
                LocalDate.now(),
                LocalDate.now(),
                1,
                1,
                "APOLICE-001",
                null,
                null,
                1,
                LocalDate.now(),
                LocalDate.now(),
                LocalDate.now().plusDays(30),
                "0001",
                null,
                null,
                "BRL",
                0.0,
                0.0,
                null,
                List.of(),
                List.of(),
                List.of(),
                List.of(),
                List.of(),
                List.of(),
                null,
                null
            );
            
            assertThat(documento.segurados()).isNotNull();
            assertThat(documento.segurados()).isEmpty();
            assertThatThrownBy(() -> 
                documento.segurados().add(null)
            ).isInstanceOf(UnsupportedOperationException.class);
        }
    }
}
