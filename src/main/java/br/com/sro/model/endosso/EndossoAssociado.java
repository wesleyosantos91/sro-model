package br.com.sro.model.endosso;

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
) {}