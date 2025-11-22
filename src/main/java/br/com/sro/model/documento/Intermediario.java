package br.com.sro.model.documento;

import br.com.sro.model.util.ValidationUtils;
import java.util.Objects;

/**
 * Record representando Intermediario
 * <p>Tag: intermediario</p>
 * <p>Gerado automaticamente a partir da especificação SRO v2.0.0</p>
 */
public record Intermediario(

    /**
     * Tipo do Intermediador
     * <p>Tipo do intermediador da apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> 1 - Corretor 2 - Representante 3 - Estipulante 4 - Correspondente 5 - Agente de Microsseguros 6 - Av...</p>
     * <p><strong>Tamanho:</strong> 2</p>
     */
    Integer tipo ,

    /**
     * Documento do Intermediador
     * <p>Documento do intermediador da apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 40</p>
     */
    String documento ,

    /**
     * Identificador do Intermediador
     * <p>Identificador do intermediador da apólice - código Susep do corretor(a)</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 40</p>
     * <p><strong>Condição:</strong> Campo obrigatório quando o campo “Tipo” for preenchido com “1 - Corretor”.</p>
     */
    String codigo ,

    /**
     * Tipo de Documento do Intermediador
     * <p>Tipo de documento do intermediador da apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> 1 - CPF 2 - CNPJ 3 - PASSAPORTE 99 - Outros</p>
     * <p><strong>Tamanho:</strong> 2</p>
     */
    Integer tipoDocumento,

    /**
     * Nome ou Razão Social do Intermediador
     * <p>Nome ou razão social do intermediador da apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 144</p>
     */
    String nome,

    /**
     * Código Postal do Intermediador
     * <p>Código postal do intermediador da apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 30</p>
     */
    String codigoPostal,

    /**
     * Cidade da Pessoa Associada
     * <p>Cidade do intermediador da apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 100</p>
     * <p><strong>Observação:</strong> Por extenso</p>
     */
    String cidade,

    /**
     * Estado da Pessoa Associada
     * <p>Estado do intermediador da apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 50</p>
     * <p><strong>Observação:</strong> Por extenso</p>
     */
    String estado,

    /**
     * País da Pessoa Associada
     * <p>País do intermediador da apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> Lista de Países: ISO 3166-1 alfa-3 </p>
     * <p><strong>Tamanho:</strong> 3</p>
     * <p><strong>Observação:</strong> Código de 3 letras conforme ISO 3166-1 alfa-3</p>
     */
    String pais,

    /**
     * Valor de Comissão do Intermediador
     * <p>Valor total de comissão do intermediador</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     */
    Double valorComissao,

    /**
     * Valor de Comissão do Intermediador em Reais
     * <p>Valor total de comissão do intermediador em reais</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     */
    Double valorComissaoReal
) {
    /**
     * Compact constructor com validações completas.
     */
    public Intermediario {
        /* === VALIDAÇÕES DE OBRIGATORIEDADE === */
        Objects.requireNonNull(tipo, "Tipo é obrigatório");
        Objects.requireNonNull(documento, "Documento é obrigatório");
        Objects.requireNonNull(tipoDocumento, "Tipo de documento é obrigatório");
        Objects.requireNonNull(nome, "Nome é obrigatório");
        Objects.requireNonNull(codigoPostal, "Código postal é obrigatório");
        Objects.requireNonNull(cidade, "Cidade é obrigatória");
        Objects.requireNonNull(estado, "Estado é obrigatório");
        Objects.requireNonNull(pais, "País é obrigatório");
        Objects.requireNonNull(valorComissao, "Valor de comissão é obrigatório");
        Objects.requireNonNull(valorComissaoReal, "Valor de comissão em reais é obrigatório");
        
        /* === VALIDAÇÕES DE DOMÍNIO === */
        
        /* Tipo: 1 a 6 */
        if (tipo < 1 || tipo > 6) {
            throw new IllegalArgumentException(
                "Tipo deve estar entre 1 e 6"
            );
        }
        
        /* Tipo de documento: 1 (CPF), 2 (CNPJ), 3 (Passaporte), 99 (Outros) */
        if (tipoDocumento < 1 || tipoDocumento > 99) {
            throw new IllegalArgumentException(
                "Tipo de documento deve ser 1 (CPF), 2 (CNPJ), 3 (Passaporte) ou 99 (Outros)"
            );
        }
        
        /* === VALIDAÇÕES CONDICIONAIS === */
        
        /* Código obrigatório para Corretor (tipo 1) */
        ValidationUtils.requireNonBlankIf(
            tipo == 1,
            codigo,
            "Código Susep é obrigatório para tipo Corretor"
        );
        
        /* === VALIDAÇÕES DE FORMATO === */
        
        /* Validação específica por tipo de documento */
        ValidationUtils.requireValidDocumento(documento, tipoDocumento, "Documento");
        
        /* País: formato ISO 3166-1 alpha-3 */
        if (!ValidationUtils.isValidPaisISO(pais)) {
            throw new IllegalArgumentException(
                "País deve estar no formato ISO 3166-1 alpha-3 (ex: BRA, USA)"
            );
        }
        
        /* === VALIDAÇÕES DE TAMANHO === */
        
        ValidationUtils.requireMaxLength(documento, 40, "Documento");
        ValidationUtils.requireMaxLength(codigo, 40, "Código");
        ValidationUtils.requireMinLength(nome, 3, "Nome");
        ValidationUtils.requireMaxLength(nome, 144, "Nome");
        ValidationUtils.requireMaxLength(codigoPostal, 30, "Código postal");
        ValidationUtils.requireMaxLength(cidade, 100, "Cidade");
        ValidationUtils.requireMaxLength(estado, 50, "Estado");
        
        /* === VALIDAÇÕES DE VALORES === */
        
        ValidationUtils.requirePositive(valorComissao, "Valor de comissão");
        ValidationUtils.requirePositive(valorComissaoReal, "Valor de comissão em reais");
    }
}