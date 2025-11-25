package io.github.wesleyosantos91.susep.sro.model.sinistro;

import io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Representa um Sinistro no sistema SRO.
 *
 * <p>Registro de sinistros incluindo aviso, regulação, cobertura,
 * justificativas e dados específicos por modalidade.</p>
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba SINISTRO</p>
 * <p><b>Bounded Context:</b> Sinistro</p>
 * <p><b>Aggregate Root:</b> Sim</p>
 *
 * @see <a href="https://www2.susep.gov.br/">SUSEP</a>
 */
public record Sinistro(
    /**
     * Status do processo de sinistro
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> status</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Aberto 
2 - Encerrado com Indenização
3 - Encerrado sem Indenização
4 - Reaberto
5 - Cancelado (Por erro operacional)
6 - Avaliação Inicial</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    Integer status,

    /**
     * Data da alteração do registro
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> data_alteracao_status</p>
     * <p><b>Tipo:</b> Date</p>
     * <p><b>Formato:</b> AAAA-MM-DD</p>
     * <p><b>Tamanho:</b> 10</p>
     */
    LocalDate dataAlteracaoStatus,

    /**
     * Data de ocorrência do sinistro
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> data_ocorrencia</p>
     * <p><b>Tipo:</b> Date</p>
     * <p><b>Formato:</b> AAAA-MM-DD</p>
     * <p><b>Tamanho:</b> 10</p>
     */
    LocalDate dataOcorrencia,

    /**
     * Data de aviso do sinistro
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> data_aviso</p>
     * <p><b>Tipo:</b> Date</p>
     * <p><b>Formato:</b> AAAA-MM-DD</p>
     * <p><b>Tamanho:</b> 10</p>
     */
    LocalDate dataAviso,

    /**
     * Data de aviso do sinistro à seguradora
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> data_registro_seguradora</p>
     * <p><b>Tipo:</b> Date</p>
     * <p><b>Formato:</b> AAAA-MM-DD</p>
     * <p><b>Tamanho:</b> 10</p>
     */
    LocalDate dataRegistroSeguradora,

    /**
     * Data de Reclamação do Terceiro
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> data_reclamacao_terceiro</p>
     * <p><b>Tipo:</b> Date</p>
     * <p><b>Formato:</b> AAAA-MM-DD</p>
     * <p><b>Tamanho:</b> 10</p>
     */
    LocalDate dataReclamacaoTerceiro,

    /**
     * Lista de Justificativa da Negativa
     *
     * <p><b>Tipo:</b> Lista de JustificativaNegativa</p>
     */
    List<JustificativaNegativa> justificativanegativas,

    /**
     * Lista de Documentos Afetados
     *
     * <p><b>Tipo:</b> Lista de DocumentoAfetado</p>
     */
    List<DocumentoAfetado> documentoafetados,

    /**
     * Lista de Coberturas afetadas
     *
     * <p><b>Tipo:</b> Lista de CoberturaAfetada</p>
     */
    List<CoberturaAfetada> coberturaafetadas,

    /**
     * Lista de Dados Vistoria Rural
     *
     * <p><b>Tipo:</b> Lista de VistoriaRural</p>
     */
    List<VistoriaRural> vistoriarurals,

    /**
     * Lista de Dados Automóvel
     *
     * <p><b>Tipo:</b> Lista de Automovel</p>
     */
    List<Automovel> automovels
) {

    public Sinistro {
        Objects.requireNonNull(status, "Status do sinistro é obrigatório");
        Objects.requireNonNull(dataAlteracaoStatus, "Data de alteração é obrigatória");
        Objects.requireNonNull(dataOcorrencia, "Data de ocorrência é obrigatória");
        Objects.requireNonNull(dataAviso, "Data de aviso é obrigatória");
        Objects.requireNonNull(dataRegistroSeguradora, "Data de registro é obrigatória");

        ValidationUtils.requireRange(status, 1, 6, "Status do sinistro");
        ValidationUtils.requirePastOrPresent(dataOcorrencia, "Data de ocorrência");
        ValidationUtils.requirePastOrPresent(dataAviso, "Data de aviso");
        ValidationUtils.requirePastOrPresent(dataRegistroSeguradora, "Data de registro na seguradora");
        ValidationUtils.requirePastOrPresent(dataAlteracaoStatus, "Data de alteração do status");
        ValidationUtils.requireAfterOrEqual(
            dataAviso,
            dataOcorrencia,
            "Data de aviso deve ser igual ou posterior à data de ocorrência"
        );

        justificativanegativas = justificativanegativas != null ? List.copyOf(justificativanegativas) : List.of();
        documentoafetados = documentoafetados != null ? List.copyOf(documentoafetados) : List.of();
        coberturaafetadas = coberturaafetadas != null ? List.copyOf(coberturaafetadas) : List.of();
        vistoriarurals = vistoriarurals != null ? List.copyOf(vistoriarurals) : List.of();
        automovels = automovels != null ? List.copyOf(automovels) : List.of();
    }
}