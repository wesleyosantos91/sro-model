package io.github.wesleyosantos91.susep.sro.model.documento;

import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireMaxLength;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requirePositive;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireRange;

import io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils;
import java.util.Objects;

/**
 * Record representando Franquia
 * <p>Tag: franquia</p>
 * <p>Gerado automaticamente a partir da especificação SRO v2.0.0</p>
 */
public record Franquia(

    /**
     * Tipo de Franquia
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> 1 - Reduzida 2 - Normal 3 - Majorada 4 - Dedutível 99 - Outros</p>
     * <p><strong>Tamanho:</strong> 2</p>
     */
    Integer franquiaTipo,

    /**
     * Descrição do Tipo de Franquia
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 1000</p>
     * <p><strong>Condição:</strong> Obrigatório quando '99 - Outros'</p>
     */
    String tipoDescricao,

    /**
     * Valor da Franquia
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     */
    Double franquiaValor,

    /**
     * Descrição da Franquia
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 500</p>
     */
    String franquiaDescricao
) {
    public Franquia {
        Objects.requireNonNull(franquiaTipo, "Tipo de franquia é obrigatório");

        requireRange(franquiaTipo, 1, 99, "Tipo de franquia");
        requireMaxLength(tipoDescricao, 1000, "Descrição do tipo de franquia");
        requirePositive(franquiaValor, "Valor da franquia");
        requireMaxLength(franquiaDescricao, 500, "Descrição da franquia");

        if (franquiaTipo == 99) {
            ValidationUtils.requireNonBlank(
                tipoDescricao,
                "Descrição do tipo de franquia é obrigatória quando o tipo é 99"
            );
        }
    }
}