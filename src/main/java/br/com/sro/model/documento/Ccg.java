package br.com.sro.model.documento;

import java.time.LocalDate;

/**
 * Record representando Ccg
 * <p>Tag: ccg</p>
 * <p>Gerado automaticamente a partir da especificação SRO v2.0.0</p>
 */
public record Ccg(

    /**
     * Identificador do CCG
     * <p>Identificador do contrato de contragarantia</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 100</p>
     * <p><strong>Observação:</strong> Este campo faz referência ao identificador do CCG  registrado pelo leiaute '4 - CCG'</p>
     */
    String ccgIdentificacao,

    /**
     * Data de Vinculação do CCG
     * <p>Data de vinculação do contrato de contragarantia</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     * <p><strong>Observação:</strong> Campo aplicável quando o CCG for vinculado em data posterior ao Registro da Apólice/Endosso.</p>
     */
    LocalDate dataVinculacao
) {}