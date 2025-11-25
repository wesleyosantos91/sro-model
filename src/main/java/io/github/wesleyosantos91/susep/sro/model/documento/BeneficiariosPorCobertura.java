package io.github.wesleyosantos91.susep.sro.model.documento;

import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireExactLength;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireNonBlank;

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
) {
    public BeneficiariosPorCobertura {
        requireNonBlank(identificadorObjetoSeguradoBeneficiarios, "Identificador do objeto segurado é obrigatório");
        requireNonBlank(grupoRamoCoberturaBeneficiarios, "Grupo e ramo da cobertura é obrigatório");
        requireNonBlank(codigoInternoCoberturaBeneficiarios, "Código interno da cobertura é obrigatório");

        requireExactLength(grupoRamoCoberturaBeneficiarios, 4, "Grupo e ramo da cobertura");
        requireExactLength(identificadorObjetoSeguradoBeneficiarios, 50, "Identificador do objeto segurado");
        requireExactLength(codigoInternoCoberturaBeneficiarios, 50, "Código interno da cobertura");
    }
}