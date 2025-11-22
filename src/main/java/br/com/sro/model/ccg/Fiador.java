package br.com.sro.model.ccg;

/**
 * Representa Fiador no contexto de CCG.
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba CCG</p>
 * <p><b>Grupo:</b> Fiador</p>
 */
public record Fiador(
    /**
     * Documento do fiador
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> documento</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 40</p>
     */
    String documento,

    /**
     * Tipo de documento do fiador
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
     * Nome / Razão Social do fiador
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> razao_social</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 144</p>
     */
    String razaoSocial
) {}