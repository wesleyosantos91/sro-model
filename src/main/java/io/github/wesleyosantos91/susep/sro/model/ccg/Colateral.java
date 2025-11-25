package io.github.wesleyosantos91.susep.sro.model.ccg;

import java.math.BigDecimal;
import java.util.Objects;

import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireMaxLength;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requirePositive;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireRange;

/**
 * Representa Colateral no contexto de CCG.
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba CCG</p>
 * <p><b>Grupo:</b> Colateral</p>
 */
public record Colateral(
    /**
     * Tipo de ativo do colateral
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> tipo_ativo_colateral</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Ativo Financeiros e/ou Valores Mobiliários
2 - Imóveis
3 - Fiança
99 - Outros</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    Integer tipoAtivoColateral,

    /**
     * Valor do ativo do colateral
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> valor_ativo_colateral</p>
     * <p><b>Tipo:</b> Double</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 16.2</p>
     */
    BigDecimal valorAtivoColateral,

    /**
     * UF do registro do ativo colateral
     *
     * <p><b>Cardinalidade:</b> [0. 1]</p>
     * <p><b>Tag:</b> uf_ativo_colateral</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    String ufAtivoColateral,

    /**
     * País do Registro do Ativo Colateral
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> pais_ativo_colateral</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 100</p>
     */
    String paisAtivoColateral
) {
    public Colateral {
        Objects.requireNonNull(tipoAtivoColateral, "Tipo de ativo do colateral é obrigatório");
        Objects.requireNonNull(valorAtivoColateral, "Valor do ativo do colateral é obrigatório");
        Objects.requireNonNull(paisAtivoColateral, "País do ativo colateral é obrigatório");

        requireRange(tipoAtivoColateral, 1, 99, "Tipo de ativo do colateral");
        requirePositive(valorAtivoColateral, "Valor do ativo do colateral");
        requireMaxLength(paisAtivoColateral, 100, "País do ativo colateral");

        if (ufAtivoColateral != null && ufAtivoColateral.length() != 2) {
            throw new IllegalArgumentException("UF do ativo colateral deve ter 2 caracteres");
        }
    }
}