package io.github.wesleyosantos91.susep.sro.model.documento;

import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireExactLength;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireMaxLength;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requirePositive;
import static io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils.requireRange;

/**
 * Record representando ObjetoRural
 * <p>Tag: objeto_rural</p>
 * <p>Gerado automaticamente a partir da especificação SRO v2.0.0</p>
 */
public record ObjetoRural(

    /**
     * Participa do FESR?
     * <p>Indica se participa do Fundo de Estabilidade do Seguro Rural (para Agrícola, Pecuário, Aquícola e Florestas)</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> 1 - Sim 2 - Não</p>
     * <p><strong>Tamanho:</strong> 2</p>
     * <p><strong>Observação:</strong> Anexo VIII - Rural</p>
     */
    Integer participaFesr,

    /**
     * Valor do prêmio subvencionado
     * <p>Informar o valor do prêmio subvencionado (para Agrícola, Pecuário, Aquícola e Florestas)</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     * <p><strong>Observação:</strong> Anexo VIII - Rural</p>
     */
    Double valorPremioSubvencionado,

    /**
     * Origem da subvenção
     * <p>Descrever a origem da subvenção (para Agrícola, Pecuário, Aquícola e Florestas)</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> Lista de UF, BR e "XX" (Outra origem).</p>
     * <p><strong>Tamanho:</strong> 2</p>
     * <p><strong>Observação:</strong> Anexo VIII - Rural</p>
     */
    String origemSubvencao,

    /**
     * Área Segurada Total
     * <p>Área total seguradora.</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 18.2</p>
     * <p><strong>Observação:</strong> Anexo VIII - Rural</p>
     */
    Double areaSeguradaTotal,

    /**
     * Unidade de medida da área segurada
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> 1 - Hectar 2 - Metro ²</p>
     * <p><strong>Tamanho:</strong> 2</p>
     * <p><strong>Observação:</strong> Anexo VIII - Rural</p>
     */
    Integer unidadeMedidaAreaSegurada,

    /**
     * Código Culturas
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 8</p>
     * <p><strong>Observação:</strong> Anexo VIII - Rural</p>
     */
    String codigoCultura,

    /**
     * Código Rebanhos
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> 1 - Bovinos 2 -Equinos 3 - Ovinos 4 - Suinos 5 - Caprinos 6 - Aves 7 - Bubalinos 99 - Outros</p>
     * <p><strong>Tamanho:</strong> 2</p>
     * <p><strong>Observação:</strong> Anexo VIII - Rural</p>
     */
    Integer codigoRebanho,

    /**
     * Código Florestas
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> 1 - Pínus 2 - Eucalipito 3 - Teca 4 - Seringueira 99 - Outros</p>
     * <p><strong>Tamanho:</strong> 2</p>
     * <p><strong>Observação:</strong> Anexo VIII - Rural</p>
     */
    Integer codigoFloresta,

    /**
     * UF da vistoria
     * <p>UF da vistoria (para Agrícola, Pecuário, Aquícola e Florestas)</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> AC, AL, AP, AM, BA, CE, DF, ES, GO, MA, MT, MS, MG, PA, PB, PR, PE, PI, RJ, RN, RS, RO, RR, SC, SP, ...</p>
     * <p><strong>Tamanho:</strong> 2</p>
     * <p><strong>Observação:</strong> Anexo VIII - Rural</p>
     */
    String ufVistoria,

    /**
     * Código postal da vistoria
     * <p>Código postal da vistoria, caso haja (para Agrícola, Pecuário, Aquícola e Florestas)</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 30</p>
     * <p><strong>Observação:</strong> Anexo VIII - Rural</p>
     */
    String codigoPostalVistoria,

    /**
     * País da vistoria
     * <p>País da vistoria (para Agrícola, Pecuário, Aquícola e Florestas)</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong>  Lista dos Paises (ISO 3166-1 alfa-3)</p>
     * <p><strong>Tamanho:</strong> 3</p>
     * <p><strong>Observação:</strong> Anexo VIII - Rural</p>
     */
    String paisVistoria,

    /**
     * Destinação dos animais cobertos para Pecuário
     * <p>Indicar a destinação dos animais cobertos (para Pecuário)</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> 1 - Consumo 2 - Produção 3 - Reprodução</p>
     * <p><strong>Tamanho:</strong> 2</p>
     * <p><strong>Observação:</strong> Anexo VIII - Rural</p>
     */
    Integer destinacaoAnimalCobertoPecuario,

    /**
     * Classificação dos animais cobertos para Animais
     * <p>Indicar a classificação dos animais cobertos (para Animais)</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> 1 - Elite 2 - Doméstico 3 - Segurança</p>
     * <p><strong>Tamanho:</strong> 2</p>
     * <p><strong>Observação:</strong> Anexo VIII - Rural</p>
     */
    Integer classificacaoAnimalCoberto,

    /**
     * Percentual de Despesas Administrativas
     * <p>Indica o percentual da despesa Administrativa prevista</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 3.9</p>
     * <p><strong>Observação:</strong> Fórum Ofício 9</p>
     */
    Float percentualDespesasAdministrativas
) {
    public ObjetoRural {
        requireRange(participaFesr, 1, 2, "Participação no FESR");
        requireRange(unidadeMedidaAreaSegurada, 1, 99, "Unidade de medida da área segurada");
        requireRange(codigoRebanho, 1, 99, "Código do rebanho");
        requireRange(codigoFloresta, 1, 99, "Código da floresta");
        requireRange(destinacaoAnimalCobertoPecuario, 1, 99, "Destinação dos animais");
        requireRange(classificacaoAnimalCoberto, 1, 99, "Classificação dos animais");

        requirePositive(valorPremioSubvencionado, "Valor do prêmio subvencionado");
        requirePositive(areaSeguradaTotal, "Área segurada total");

        requireMaxLength(origemSubvencao, 2, "Origem da subvenção");
        requireMaxLength(codigoCultura, 8, "Código da cultura");
        requireMaxLength(ufVistoria, 2, "UF da vistoria");
        requireMaxLength(codigoPostalVistoria, 30, "Código postal da vistoria");
        requireExactLength(paisVistoria, 3, "País da vistoria");
    }
}