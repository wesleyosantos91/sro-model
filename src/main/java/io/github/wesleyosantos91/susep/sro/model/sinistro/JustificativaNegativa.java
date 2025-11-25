package io.github.wesleyosantos91.susep.sro.model.sinistro;

import java.util.Objects;

import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireMaxLength;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireRange;

/**
 * Representa Justificativa da Negativa no contexto de Sinistro.
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba SINISTRO</p>
 * <p><b>Grupo:</b> Justificativa da Negativa</p>
 */
public record JustificativaNegativa(
    /**
     * Justificativa da negativa de sinistro
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> justificativa</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Risco Excluído
2 - Risco Agravado
3 - Sem Documentação
4 - Documentação Incompleta
5 - Prescrição
6 - Fora Cobertura
99 - Outros</p>
     * <p><b>Tamanho:</b> 2</p>
     * <p><b>Observação:</b> Campo trazido do leiaute de movimento de sinistro</p>
     */
    Integer justificativa,

    /**
     * Descrição da justificativa da negativa de sinistro
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> descricao_justificativa</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 1024</p>
     * <p><b>Condição:</b> Campo obrigatório apenas para Justificativa cujo domínio seja '99'- Outros</p>
     * <p><b>Observação:</b> Campo trazido do leiaute de movimento de sinistro

Alteração de cardinalidade</p>
     */
    String descricaoJustificativa
) {
    public JustificativaNegativa {
        Objects.requireNonNull(justificativa, "Justificativa é obrigatória");

        requireRange(justificativa, 1, 99, "Justificativa da negativa");
        requireMaxLength(descricaoJustificativa, 1024, "Descrição da justificativa");
    }
}