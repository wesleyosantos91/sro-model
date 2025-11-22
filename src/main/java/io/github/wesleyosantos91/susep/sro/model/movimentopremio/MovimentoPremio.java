package io.github.wesleyosantos91.susep.sro.model.movimentopremio;

import io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Record representando um Movimento de Prêmio do SRO
 * <p>Sistema de Registro de Operações (SRO) - Versão 2.0.0</p>
 * <p>Este record representa movimentações financeiras relacionadas a prêmios de seguros.</p>
 * <p>Inclui emissões, cancelamentos, pagamentos, restituições e outras operações financeiras.</p>
 * <p>Gerado automaticamente a partir da especificação SUSEP.</p>
 */
public record MovimentoPremio(

    /**
     * Uuid
     * <p>Identificador único do registro</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> 7d9b5524-d047-40ee-8f9c-62c0c8e87d8e</p>
     * <p><strong>Tamanho:</strong> 36</p>
     */
    String uuid,

    /**
     * Anotação Registro
     * <p>Campo livre de anotação</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 500</p>
     * <p><strong>Condição:</strong> Este campo servirá para que as Registradoras utilizem para seu controle interno dos registros</p>
     */
    String anotacao,

    /**
     * Código da Seguradora
     * <p>Código Susep da seguradora</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 5</p>
     */
    String codigoSeguradora,

    /**
     * Data do Registro
     * <p>Data do registro</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> ISO 8601  "2021-06-25T18:00:00Z"</p>
     */
    LocalDate dataRegistro,

    /**
     * Data da Alteração do Registro
     * <p>Data da alteração do registro</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> ISO 8601  "2021-06-25T18:00:00Z"</p>
     */
    LocalDate dataAlteracao,

    /**
     * Indicador Exclusão
     * <p>Indica se é um registro de exclusão</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> 1 - Sim 2 - Não</p>
     * <p><strong>Tamanho:</strong> 1</p>
     */
    Integer indicadorExclusao,

    /**
     * Identificador da Apólice / Bilhete
     * <p>Identificador da apólice ou bilhete</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 60</p>
     */
    String apoliceCodigo,

    /**
     * Identificador do Certificado
     * <p>Identificador do certificado</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 60</p>
     */
    String certificadoCodigo,

    /**
     * Identificador do Endosso do documento
     * <p>Identificador do endosso da apólice</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 60</p>
     */
    String endossoCodigo,

    /**
     * Identificador do Movimento
     * <p>Identificador do movimento</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 80</p>
     * <p><strong>Observação:</strong> Campo movimentado do bloco 'parcelas' para o bloco 'Movimento de prêmio' hierarquicamente superior  Ampliação do tamanho do campo (de 50 para 80, conforme padrão dos demais leiautes)  Identificador de</p>
     */
    String identificadorMovimento,

    /**
     * Moeda
     * <p>Moeda</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> ISO 4217  Exemplo ( "BRL", "USD", "EUR", "GBP", "JPY")</p>
     * <p><strong>Tamanho:</strong> 3</p>
     * <p><strong>Observação:</strong> Inclusão de campo (reestabelecimento de campo existente na V1)</p>
     */
    String moeda,

    /**
     * Valor do Movimento
     * <p>Valor do Movimento</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     * <p><strong>Observação:</strong> Inclusão de campo (reestabelecimento de campo existente na V1)</p>
     */
    Double valorMovimento,

    /**
     * Valor do Movimento em Reais
     * <p>Valor do Movimento em Reais</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     * <p><strong>Observação:</strong> Inclusão de campo (reestabelecimento de campo existente na V1)</p>
     */
    Double valorMovimentoReal,

    /**
     * Data do movimento
     * <p>Data do movimento</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     * <p><strong>Observação:</strong> Inclusão de campo (reestabelecimento de campo existente na V1)</p>
     */
    LocalDate dataMovimento,

    /**
     * Número da Parcela do Movimento
     * <p>Número da Parcela do Movimento</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 4</p>
     * <p><strong>Observação:</strong> Inclusão de campo (reestabelecimento de campo existente na V1)</p>
     */
    Integer numeroParcelaMovimento,

    /**
     * Data de Vencimento da Parcela
     * <p>Data de vencimento da parcela</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     * <p><strong>Observação:</strong> Inclusão de campo (reestabelecimento de campo existente na V1 no bloco de parcelas dos leiautes de documento e endosso)</p>
     */
    LocalDate dataVencimento,

    /**
     * Tipo do Movimento
     * <p>Tipo do movimento</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> 1 - Liquidação de Prêmio  2 - Liquidação de Restituição de Prêmio  3 - Liquidação de Custo de Aquisi...</p>
     * <p><strong>Tamanho:</strong> 2</p>
     * <p><strong>Observação:</strong> Inclusão de domínio  Os tipos de movimento 8, 10 e 13 são movimentos têm reflexo contábil. Os demais tipos de movimentos são puramente financeiros.</p>
     */
    Integer tipoMovimento,

    /**
     * Grupo / Bloco: Prêmio por ramo ou cobertura
     * <p><strong>Cardinalidade:</strong> [0..N]</p>
     * <p><strong>Condição:</strong> 1) O bloco é obrigatório quando o tipo de movimento for um dos domínios 8, 10 e 13.   2) O bloco não deve ser registrado para os demais  tipos de movimento.</p>
     * <p><strong>Observação:</strong> Inclusão de condição</p>
     */
    String premioCobertura,

    /**
     * Grupo e Ramo da Cobertura
     * <p>Grupo e ramo da cobertura</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 4</p>
     */
    String grupoRamo,

    /**
     * Identificador do Objeto Segurado
     * <p>Referência ao Identificador do Objeto Segurado da cobertura aplicável ao beneficiário</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 50</p>
     * <p><strong>Observação:</strong> Alteração de cardinalidade (avaliar impacto)</p>
     */
    String codigo,

    /**
     * Código Interno da Cobertura da Seguradora
     * <p>Código Interno da Cobertura da Seguradora</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 50</p>
     * <p><strong>Observação:</strong> Alteração de cardinalidade (avaliar impacto)</p>
     */
    String coberturaInternaSeguradora,

    /**
     * Data de Início de Vigência de Prêmio da Cobertura
     * <p>Data de início de vigência de prêmio da cobertura</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     * <p><strong>Observação:</strong> Alteração de cardinalidade (avaliar impacto) Remoção de condição</p>
     */
    LocalDate dataInicio,

    /**
     * Data de Fim de Vigência de Prêmio da Cobertura
     * <p>Data de fim de vigência de prêmio da cobertura</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     * <p><strong>Observação:</strong> Alteração de cardinalidade (avaliar impacto) Remoção de condição</p>
     */
    LocalDate dataTermino,

    /**
     * Limite Máximo de Indenização (LMI)
     * <p>Limite máximo de indenização (LMI)</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     * <p><strong>Observação:</strong> Remoção de condição</p>
     */
    Double limiteMaximoIndenizacao,

    /**
     * Limite Máximo de Indenização (LMI) em Reais
     * <p>Limite máximo de indenização (LMI) em reais</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     * <p><strong>Observação:</strong> Remoção de condição</p>
     */
    Double limiteMaximoIndenizacaoReal,

    /**
     * Valor de Prêmio do Ramo ou da Cobertura na Parcela Associada
     * <p>Valor de prêmio da cobertura</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     * <p><strong>Observação:</strong> Os valores de prêmio deste bloco correspodem ao valor do prêmio comercial tal como no bloco de coberturas dos leiautes 1 - Documento e 2 - Endosso  (vide Manual de Orientação do SRO)</p>
     */
    Double valorPremio,

    /**
     * Valor de Prêmio do Ramo ou da Cobertura na Parcela Associada
     * <p>Valor de prêmio da cobertura em reais</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     * <p><strong>Observação:</strong> Os valores de prêmio deste bloco correspodem ao valor do prêmio comercial tal como no bloco de coberturas dos leiautes 1 - Documento e 2 - Endosso (vide Manual de Orientação do SRO)</p>
     */
    Double valorPremioReal,

    /**
     * Adicional de Fracionamento do ramo ou cobertura
     * <p>Adicional de Fracionamento do ramo ou cobertura</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     * <p><strong>Observação:</strong> Os valores de adicional de francionamento deste bloco correspodem ao valor do adicional de fracionamento tal como no bloco de coberturas dos leiautes 1 - Documento e 2 - Endosso (vide Manual de Orient</p>
     */
    Double adicionalFracionamento,

    /**
     * Valor de IOF da Cobertura em Reais
     * <p>Valor de IOF da cobertura em reais</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     * <p><strong>Observação:</strong> Os valores de IOF deste bloco correspodem ao valor do IOF tal como no bloco de coberturas dos leiautes 1 - Documento e 2 - Endosso (vide Manual de Orientação do SRO)</p>
     */
    Double iof,

    /**
     * Custo de Aquisição do ramo ou da cobertura
     * <p>Custo de Aquisição do ramo ou da cobertura</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     * <p><strong>Observação:</strong> Os valores de custo de aquisição deste bloco correspodem ao valor de custo de aquisição tal como no bloco de coberturas dos leiautes 1 - Documento e 2 - Endosso (vide Manual de Orientação do SRO)</p>
     */
    Double custoAquisicao
) {}