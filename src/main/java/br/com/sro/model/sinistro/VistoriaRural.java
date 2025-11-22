package br.com.sro.model.sinistro;

/**
 * Representa Dados Vistoria Rural no contexto de Sinistro.
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba SINISTRO</p>
 * <p><b>Grupo:</b> Dados Vistoria Rural</p>
 */
public record VistoriaRural(
    /**
     * UF da vistoria (para Agrícola, Pecuário, Aquícola e Florestas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> uf_vistoria</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> AC, AL, AP, AM, BA, CE, DF, ES, GO, MA, MT, MS, MG, PA, PB, PR, PE, PI, RJ, RN, RS, RO, RR, SC, SP, SE, TO</p>
     * <p><b>Tamanho:</b> 2</p>
     * <p><b>Observação:</b> Anexo VIII - Rural</p>
     */
    String ufVistoria,

    /**
     * Código postal da vistoria, caso haja (para Agrícola, Pecuário, Aquícola e Florestas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> codigo_postal_vistoria</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Tamanho:</b> 30</p>
     * <p><b>Observação:</b> Anexo VIII - Rural</p>
     */
    String codigoPostalVistoria,

    /**
     * País da vistoria (para Agrícola, Pecuário, Aquícola e Florestas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> pais_vistoria</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> Lista de Países: ISO 3166-1 alfa-3</p>
     * <p><b>Tamanho:</b> 3</p>
     * <p><b>Observação:</b> Anexo VIII - Rural</p>
     */
    String paisVistoria
) {}