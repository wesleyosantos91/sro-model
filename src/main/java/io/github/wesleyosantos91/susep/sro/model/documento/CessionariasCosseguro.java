package io.github.wesleyosantos91.susep.sro.model.documento;

import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireExactLength;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireNonBlank;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requirePositive;

import java.util.Objects;

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
) {
    public CessionariasCosseguro {
        Objects.requireNonNull(codigoCosseguradora, "Código da congênere é obrigatório");
        Objects.requireNonNull(percentualCedido, "Percentual cedido é obrigatório");

        requireNonBlank(codigoCosseguradora, "Código da congênere é obrigatório");
        requireExactLength(codigoCosseguradora, 5, "Código da congênere");
        requirePositive(percentualCedido, "Percentual cedido");
    }
}
