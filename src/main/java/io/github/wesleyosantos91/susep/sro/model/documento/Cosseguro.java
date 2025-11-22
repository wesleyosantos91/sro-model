package io.github.wesleyosantos91.susep.sro.model.documento;

import java.util.Objects;

/**
 * Record representando Cosseguro
 * <p>Tag: cosseguro</p>
 * <p>Gerado automaticamente a partir da especificação SRO v2.0.0</p>
 */
public record Cosseguro(

    /**
     * Percentual Retido em Cosseguro
     * <p>Percentual retido em cosseguro para apólices de cosseguro cedido</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 3.9</p>
     */
    Float percentualRetido
) {
    /**
     * Compact constructor com validações completas.
     */
    public Cosseguro {
        /* === VALIDAÇÕES DE OBRIGATORIEDADE === */
        Objects.requireNonNull(percentualRetido, "Percentual retido é obrigatório");
        
        /* === VALIDAÇÕES DE RANGE === */
        
        /* Percentual: 0 a 100 */
        if (percentualRetido < 0.0f || percentualRetido > 100.0f) {
            throw new IllegalArgumentException(
                "Percentual retido deve estar entre 0 e 100"
            );
        }
    }
}