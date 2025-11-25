package io.github.wesleyosantos91.susep.sro.model.endosso;

import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireMaxLength;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireNonBlank;

/**
 * Record representando EndossoAssociado
 * <p>Tag: endosso_associado</p>
 * <p>Gerado automaticamente a partir da especificação SRO v2.0.0</p>
 */
public record EndossoAssociado(

    /**
     * Identificador do Endosso Associado
     * <p>Identificador do endosso associado</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 60</p>
     * <p><strong>Observação:</strong> Alteração de cardinalidade</p>
     */
    String endossoAssociadoCodigo
) {
    public EndossoAssociado {
        requireNonBlank(endossoAssociadoCodigo, "Código do endosso associado é obrigatório");
        requireMaxLength(endossoAssociadoCodigo, 60, "Código do endosso associado");
    }
}