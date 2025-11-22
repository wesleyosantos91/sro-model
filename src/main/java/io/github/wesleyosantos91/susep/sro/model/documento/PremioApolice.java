package io.github.wesleyosantos91.susep.sro.model.documento;

import io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils;
import java.util.Objects;

/**
 * Record representando PremioApolice
 * <p>Tag: premio_apolice</p>
 * <p>Gerado automaticamente a partir da especificação SRO v2.0.0</p>
 */
public record PremioApolice(

    /**
     * Valor Total do Prêmio
     * <p>Valor total do prêmio da apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     */
    Double valorTotal,

    /**
     * Valor Total do Prêmio em Reais
     * <p>Valor total do prêmio da apólice em reais</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     */
    Double valorTotalReal,

    /**
     * Valor Total do Adicional de Fracionamento
     * <p>Valor total do adicional de fracionamento da apólice</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     */
    Double adicionalFracionamento,

    /**
     * Valor total do IOF
     * <p>Valor total do IOF da apólice</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     */
    Double iof,

    /**
     * Quantidade de Parcelas de Prêmio
     * <p>Quantidade de parcelas de prêmio da apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 3</p>
     */
    Integer numeroParcelas
) {
    /**
     * Compact constructor com validações completas.
     */
    public PremioApolice {
        /* === VALIDAÇÕES DE OBRIGATORIEDADE === */
        Objects.requireNonNull(valorTotal, "Valor total do prêmio é obrigatório");
        Objects.requireNonNull(valorTotalReal, "Valor total do prêmio em reais é obrigatório");
        Objects.requireNonNull(numeroParcelas, "Número de parcelas é obrigatório");
        
        /* === VALIDAÇÕES DE VALORES === */
        
        ValidationUtils.requirePositive(valorTotal, "Valor total do prêmio");
        ValidationUtils.requirePositive(valorTotalReal, "Valor total do prêmio em reais");
        ValidationUtils.requirePositive(adicionalFracionamento, "Adicional de fracionamento");
        ValidationUtils.requirePositive(iof, "IOF");
        
        /* === VALIDAÇÕES DE RANGE === */
        
        /* Número de parcelas: 1 a 999 */
        if (numeroParcelas < 1 || numeroParcelas > 999) {
            throw new IllegalArgumentException(
                "Número de parcelas deve estar entre 1 e 999"
            );
        }
    }
}