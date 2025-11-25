package io.github.wesleyosantos91.susep.sro.model.ccg;

import java.math.BigDecimal;
import java.util.Objects;

import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireMaxLength;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireNonBlank;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requirePositive;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireRange;

/**
 * Representa Tomador no contexto de CCG.
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba CCG</p>
 * <p><b>Grupo:</b> Tomador</p>
 */
public record Tomador(
    /**
     * Documento do tomador
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> documento</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 40</p>
     */
    String documento,

    /**
     * Tipo de documento do tomador
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> tipo_documento</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - CPF
2 - CNPJ
3 - Passaporte
99 - Outros</p>
     * <p><b>Tamanho:</b> 2</p>
     * <p><b>Observação:</b> Inclusão de domínio 3 - Passaporte para padronização
Reunião 24/01/2023</p>
     */
    Integer tipoDocumento,

    /**
     * Indicador de controlador de frupo econômico do tomador
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> controlador_ge</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Sim
2 - Não</p>
     * <p><b>Tamanho:</b> 1</p>
     */
    Integer controladorGe,

    /**
     * Nome / Razão Social do tomador
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> razao_social</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 144</p>
     * <p><b>Observação:</b> Tamanho alterado para 144 conforme solicitação e autorizado pela Susep em e-mail de 19/12/22</p>
     */
    String razaoSocial,

    /**
     * Limite Aprovado do Tomador
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> limite_aprovado</p>
     * <p><b>Tipo:</b> Double</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 16.2</p>
     */
    BigDecimal limiteAprovado
) {
    public Tomador {
        requireNonBlank(documento, "Documento do tomador é obrigatório");
        Objects.requireNonNull(tipoDocumento, "Tipo de documento é obrigatório");
        Objects.requireNonNull(controladorGe, "Indicador de controlador é obrigatório");
        requireNonBlank(razaoSocial, "Razão social é obrigatória");
        Objects.requireNonNull(limiteAprovado, "Limite aprovado é obrigatório");

        requireMaxLength(documento, 40, "Documento do tomador");
        requireRange(tipoDocumento, 1, 99, "Tipo de documento");
        requireRange(controladorGe, 1, 2, "Indicador de controlador GE");
        requireMaxLength(razaoSocial, 144, "Razão social");
        requirePositive(limiteAprovado, "Limite aprovado");
    }
}