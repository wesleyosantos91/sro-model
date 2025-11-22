package io.github.wesleyosantos91.susep.sro.model.complauto;

import java.math.BigDecimal;

/**
 * Representa Franquia no contexto de COMPL_AUTO.
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba COMPL _AUTO</p>
 * <p><b>Grupo:</b> Franquia</p>
 */
public record Franquia(
    /**
     * 
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> franquia_tipo</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Reduzida
2 - Normal
3 - Majorada
4 - Isenta
5 - Flexível
99 - Outros</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    Integer franquiaTipo,

    /**
     * 
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> tipo_descricao</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 1000</p>
     * <p><b>Condição:</b> Obrigatório quando '99 - Outros'</p>
     * <p><b>Observação:</b> Inclusão de condição

Ampliação de tamanho do campo</p>
     */
    String tipoDescricao,

    /**
     * 
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> franquia_valor</p>
     * <p><b>Tipo:</b> Double</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 16.2</p>
     * <p><b>Observação:</b> Alteração de cardinalidade</p>
     */
    BigDecimal franquiaValor,

    /**
     * 
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> franquia_descricao</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 500</p>
     */
    String franquiaDescricao,

    /**
     * Franquia sobre indenização integral (Casco)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> franquia_indenizacao_integral</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Sim
2 - Não</p>
     * <p><b>Tamanho:</b> 1</p>
     */
    Integer franquiaIndenizacaoIntegral
) {}