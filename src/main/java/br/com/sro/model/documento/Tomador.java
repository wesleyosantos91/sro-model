package br.com.sro.model.documento;

import br.com.sro.model.util.ValidationUtils;
import java.util.Objects;

/**
 * Record representando Tomador
 * <p>Tag: tomador</p>
 * <p>Gerado automaticamente a partir da especificação SRO v2.0.0</p>
 */
public record Tomador(

    /**
     * Documento de Identificação da Pessoa Associada
     * <p>Documento de identificação da pessoa associada à apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 40</p>
     */
    String documento,

    /**
     * Tipo de Documento da Pessoa Associada
     * <p>Tipo de documento da pessoa associada à apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> 1 - CPF 2 - CNPJ 3 - PASSAPORTE 99 - Outros</p>
     * <p><strong>Tamanho:</strong> 2</p>
     */
    Integer tipoDocumento,

    /**
     * Nome ou Razão Social da Pessoa Associada
     * <p>Nome ou razão social da pessoa associada à apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 144</p>
     */
    String nome,

    /**
     * Código Postal da Pessoa Associada
     * <p>Código postal da pessoa associada à apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 30</p>
     */
    String codigoPostal,

    /**
     * Cidade da Pessoa Associada
     * <p>Cidade da pessoa associada à apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 100</p>
     * <p><strong>Observação:</strong> Por extenso</p>
     */
    String cidade,

    /**
     * Estado da Pessoa Associada
     * <p>Estado da pessoa associada à apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 50</p>
     * <p><strong>Observação:</strong> Por extenso</p>
     */
    String estado,

    /**
     * País da Pessoa Associada
     * <p>País da pessoa associada à apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> Lista de Países: ISO 3166-1 alfa-3 </p>
     * <p><strong>Tamanho:</strong> 3</p>
     * <p><strong>Observação:</strong> Código de 3 letras conforme ISO 3166-1 alfa-3</p>
     */
    String pais
) {
    /**
     * Compact constructor com validações completas.
     */
    public Tomador {
        /* === VALIDAÇÕES DE OBRIGATORIEDADE === */
        Objects.requireNonNull(documento, "Documento é obrigatório");
        Objects.requireNonNull(tipoDocumento, "Tipo de documento é obrigatório");
        Objects.requireNonNull(nome, "Nome é obrigatório");
        Objects.requireNonNull(codigoPostal, "Código postal é obrigatório");
        Objects.requireNonNull(cidade, "Cidade é obrigatória");
        Objects.requireNonNull(estado, "Estado é obrigatório");
        Objects.requireNonNull(pais, "País é obrigatório");
        
        /* === VALIDAÇÕES DE DOMÍNIO === */
        
        /* Tipo de documento: 1 (CPF), 2 (CNPJ), 3 (Passaporte), 99 (Outros) */
        if (tipoDocumento < 1 || tipoDocumento > 99) {
            throw new IllegalArgumentException(
                "Tipo de documento deve ser 1 (CPF), 2 (CNPJ), 3 (Passaporte) ou 99 (Outros)"
            );
        }
        
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
        ValidationUtils.requireMinLength(nome, 3, "Nome");
        ValidationUtils.requireMaxLength(nome, 144, "Nome");
        ValidationUtils.requireMaxLength(codigoPostal, 30, "Código postal");
        ValidationUtils.requireMaxLength(cidade, 100, "Cidade");
        ValidationUtils.requireMaxLength(estado, 50, "Estado");
    }
}