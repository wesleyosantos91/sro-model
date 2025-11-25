package io.github.wesleyosantos91.susep.sro.model.movimentosinistro;

import java.math.BigDecimal;
import java.util.Objects;

import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requirePositive;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireRange;

/**
 * Representa Adicionais (Despesas financeiras adicionais ao valor do movimento).
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba MOVIMENTO_SINISTRO</p>
 * <p><b>Grupo:</b> Adicionais (Despesas financeiras adicionais ao valor do movimento)</p>
 */
public record Adicionais(
    /**
     * Identificação do tipo da operação de recuperação de sinistro
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> tipo_adicional</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Atualização Monetária
2 - Oscilação Cambial
3 - Juros
4 - Multa
99 - Outros</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    Integer tipoAdicional,

    /**
     * Valor do movimento de sinistro na moeda original
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> valor_movimento_adicional</p>
     * <p><b>Tipo:</b> Double</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 16.2</p>
     */
    BigDecimal valorMovimentoAdicional,

    /**
     * Valor do movimento de sinistro na moeda nacional
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> valor_movimento_adicional_reais</p>
     * <p><b>Tipo:</b> Double</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 16.2</p>
     */
    BigDecimal valorMovimentoAdicionalReais
) {
    public Adicionais {
        Objects.requireNonNull(tipoAdicional, "Tipo adicional é obrigatório");
        Objects.requireNonNull(valorMovimentoAdicional, "Valor adicional é obrigatório");
        Objects.requireNonNull(valorMovimentoAdicionalReais, "Valor adicional em reais é obrigatório");

        requireRange(tipoAdicional, 1, 99, "Tipo adicional");
        requirePositive(valorMovimentoAdicional, "Valor adicional");
        requirePositive(valorMovimentoAdicionalReais, "Valor adicional em reais");
    }
}