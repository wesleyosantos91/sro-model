package io.github.wesleyosantos91.susep.sro.model.documento;

import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireExactLength;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireMaxLength;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireRange;

/**
 * Record representando ObjetoPatrimonial
 * <p>Tag: objeto_patrimonial</p>
 * <p>Gerado automaticamente a partir da especificação SRO v2.0.0</p>
 */
public record ObjetoPatrimonial(

    /**
     * Tipo do imóvel ou condomínio segurado
     * <p>Indica o tipo do imóvel ou condomínio segurado (Compreensivo Residencial e Compreensivo Condomínio)</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> 1 - Casa 2 - Apartamento 3 - Condomínio Residencial 4 - Condomínio Comercial 5 - Condomínios Mistos</p>
     * <p><strong>Tamanho:</strong> 2</p>
     * <p><strong>Observação:</strong> Anexo X - Patrimonial</p>
     */
    Integer tipoImovelSegurado,

    /**
     * Tipo de estruturação para Compreensivo Condomínio
     * <p>Indica o tipo de estruturação (para Compreensivo Condomínio)</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> 1 - Condomínio Vertical 2 - Condomínio Horizontal 3 - Misto</p>
     * <p><strong>Tamanho:</strong> 2</p>
     * <p><strong>Observação:</strong> Anexo X - Patrimonial</p>
     */
    Integer tipoEstruturacaoCondominio,

    /**
     * Código postal do imóvel, condomínio ou unidade
     * <p>Código postal do imóvel, condomínio ou unidade empresarial/planta</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 30</p>
     * <p><strong>Observação:</strong> Anexo X - Patrimonial</p>
     */
    String codigoPostal,

    /**
     * Código CNAE
     * <p>Informar o código nacional de atividade econômica do IBGE.</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> 9999999 - Outros</p>
     * <p><strong>Tamanho:</strong> 7</p>
     * <p><strong>Observação:</strong> Anexo X - Patrimonial</p>
     */
    String codigoCnae
) {
    public ObjetoPatrimonial {
        requireRange(tipoImovelSegurado, 1, 99, "Tipo do imóvel segurado");
        requireRange(tipoEstruturacaoCondominio, 1, 99, "Tipo de estruturação do condomínio");

        requireMaxLength(codigoPostal, 30, "Código postal");
        if (codigoCnae != null) {
            requireExactLength(codigoCnae, 7, "Código CNAE");
        }
    }
}