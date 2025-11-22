package br.com.sro.model.documento;

/**
 * Record representando BeneficiariosPorCobertura
 * <p>Tag: beneficiarios_por_cobertura</p>
 * <p>Gerado automaticamente a partir da especificação SRO v2.0.0</p>
 */
public record BeneficiariosPorCobertura(

    /**
     * Identificador do Objeto Segurado
     * <p>Referência ao Identificador do Objeto Segurado da cobertura aplicável ao beneficiário</p>
     * <p><strong>Cardinalidade:</strong> [1.1]</p>
     * <p><strong>Tamanho:</strong> 50</p>
     */
    String identificadorObjetoSeguradoBeneficiarios,

    /**
     * Grupo e Ramo da Cobertura
     * <p>Referência ao grupo ramo da cobertura aplicável ao beneficiário</p>
     * <p><strong>Cardinalidade:</strong> [1.1]</p>
     * <p><strong>Tamanho:</strong> 4</p>
     */
    String grupoRamoCoberturaBeneficiarios,

    /**
     * Código Interno da Cobertura da Seguradora
     * <p>Referência ao Código Interno da cobertura aplicável ao beneficiário</p>
     * <p><strong>Cardinalidade:</strong> [1.1]</p>
     * <p><strong>Tamanho:</strong> 50</p>
     */
    String codigoInternoCoberturaBeneficiarios
) {}