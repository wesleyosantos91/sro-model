package io.github.wesleyosantos91.susep.sro.model.sinistro;

import java.time.LocalDate;
import java.util.Objects;

import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireExactLength;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireMaxLength;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireNonBlank;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requirePastOrPresent;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireRange;

/**
 * Representa Coberturas afetadas no contexto de Sinistro.
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba SINISTRO</p>
 * <p><b>Grupo:</b> Coberturas afetadas</p>
 */
public record CoberturaAfetada(
    /**
     * Referência ao Identificador do objeto segurado
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> codigo_objeto</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 50</p>
     */
    String codigoObjeto,

    /**
     * Grupo e ramo
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> grupo_ramo</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 4</p>
     * <p><b>Observação:</b> Incluir para previdência os dominios:

8800 - Coberturas de Sobrevivência (Previdência)
8900 - Coberturas de Risco (Previdência)</p>
     */
    String grupoRamo,

    /**
     * Código da cobertura afetada
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> sinistro_cobertura_codigo</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> Tabela de Coberturas</p>
     * <p><b>Tamanho:</b> 5</p>
     * <p><b>Observação:</b> Alteração da cardinalidade</p>
     */
    Integer sinistroCoberturaCodigo,

    /**
     * Referência ao Código Interno da Cobertura da Seguradora da cobertura afetada
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> cobertura_interna_seguradora</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 50</p>
     * <p><b>Observação:</b> Campo incluído.</p>
     */
    String coberturaInternaSeguradora,

    /**
     * Descrição / Nome da cobertura afetada (movimento)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> sinistro_cobertura_outros</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 500</p>
     * <p><b>Condição:</b> Obrigatório apenas para o domínio do campo Código da Cobertura '999'- Outros</p>
     */
    String sinistroCoberturaOutros,

    /**
     * Data de aviso do sinistro por cobertura
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> data_aviso_cobertura</p>
     * <p><b>Tipo:</b> Date</p>
     * <p><b>Formato:</b> AAAA-MM-DD</p>
     * <p><b>Tamanho:</b> 10</p>
     */
    LocalDate dataAvisoCobertura,

    /**
     * Data de aviso do sinistro à seguradora por cobertura
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> data_registro_seguradora_cobertura</p>
     * <p><b>Tipo:</b> Date</p>
     * <p><b>Formato:</b> AAAA-MM-DD</p>
     * <p><b>Tamanho:</b> 10</p>
     */
    LocalDate dataRegistroSeguradoraCobertura,

    /**
     * Data de Reclamação do Terceiro por cobertura
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> data_reclamacao_terceiro_cobertura</p>
     * <p><b>Tipo:</b> Date</p>
     * <p><b>Formato:</b> AAAA-MM-DD</p>
     * <p><b>Tamanho:</b> 10</p>
     */
    LocalDate dataReclamacaoTerceiroCobertura
) {
    public CoberturaAfetada {
        requireNonBlank(grupoRamo, "Grupo e ramo é obrigatório");
        requireExactLength(grupoRamo, 4, "Grupo e ramo");

        requireRange(sinistroCoberturaCodigo, 0, 99999, "Código da cobertura afetada");
        requireMaxLength(codigoObjeto, 50, "Código do objeto");
        requireMaxLength(coberturaInternaSeguradora, 50, "Cobertura interna da seguradora");
        requireMaxLength(sinistroCoberturaOutros, 500, "Descrição da cobertura");

        requirePastOrPresent(dataAvisoCobertura, "Data de aviso da cobertura");
        requirePastOrPresent(dataRegistroSeguradoraCobertura, "Data de registro na seguradora");
        requirePastOrPresent(dataReclamacaoTerceiroCobertura, "Data de reclamação do terceiro");
    }
}