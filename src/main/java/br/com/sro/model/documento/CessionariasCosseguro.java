package br.com.sro.model.documento;

/**
 * Record representando CessionariasCosseguro
 * <p>Tag: cessionarias_cosseguro</p>
 * <p>Gerado automaticamente a partir da especificação SRO v2.0.0</p>
 */
public record CessionariasCosseguro(

    /**
     * Identificação da Congênere
     * <p>Identificação da congênere, cessionário do cosseguro</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 5</p>
     */
    String codigoCosseguradora,

    /**
     * Percentual Cedido em Cosseguro
     * <p>Percentual cedido para a congênere para apólices de cosseguro cedido</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 3.9</p>
     */
    Float percentualCedido
) {}