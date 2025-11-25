package io.github.wesleyosantos91.susep.sro.model.sinistro;

import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireMaxLength;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireNonBlank;

/**
 * Representa Documentos Afetados no contexto de Sinistro.
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba SINISTRO</p>
 * <p><b>Grupo:</b> Documentos Afetados</p>
 */
public record DocumentoAfetado(
    /**
     * Referência ao Identificador da apólice ou bilhete
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> apolice_codigo</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 60</p>
     * <p><b>Observação:</b> Alteração de Cardinalidade
Campo movimentado do bloco 'dados gerais'</p>
     */
    String apoliceCodigo,

    /**
     * Referência ao Identificador do certificado
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> certificado_codigo</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 60</p>
     * <p><b>Observação:</b> Alteração de Cardinalidade
Campo movimentado do bloco 'dados gerais'</p>
     */
    String certificadoCodigo,

    /**
     * Referência ao Identificador do endosso
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> numero_endosso</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 60</p>
     * <p><b>Observação:</b> Alteração de Cardinalidade
Campo movimentado do bloco 'dados gerais'</p>
     */
    String numeroEndosso
) {
    public DocumentoAfetado {
        requireNonBlank(apoliceCodigo, "Código da apólice é obrigatório");
        requireMaxLength(apoliceCodigo, 60, "Código da apólice");
        requireMaxLength(certificadoCodigo, 60, "Código do certificado");
        requireMaxLength(numeroEndosso, 60, "Número do endosso");
    }
}