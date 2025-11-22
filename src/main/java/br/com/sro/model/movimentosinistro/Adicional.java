package br.com.sro.model.movimentosinistro;

import java.math.BigDecimal;

/**
 * Representa Adicionais (Despesas financeiras adicionais ao valor do movimento).
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba MOVIMENTO_SINISTRO</p>
 * <p><b>Grupo:</b> Adicionais</p>
 */
public record Adicional(
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
     * <p><b>Semântica:</b> Corresponde ao valor do delta</p>
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
     * <p><b>Semântica:</b> Corresponde ao valor do delta</p>
     */
    BigDecimal valorMovimentoAdicionalReais
) {}