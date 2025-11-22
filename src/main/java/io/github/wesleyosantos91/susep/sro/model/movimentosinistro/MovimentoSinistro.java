package io.github.wesleyosantos91.susep.sro.model.movimentosinistro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Representa movimentos financeiros relacionados a sinistros no sistema SRO.
 *
 * <p>Registra todas as operações financeiras do ciclo de vida de um sinistro,
 * incluindo avisos, reavaliações, pagamentos e cancelamentos.</p>
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba MOVIMENTO_SINISTRO</p>
 * <p><b>Bounded Context:</b> MOVIMENTO_SINISTRO</p>
 * <p><b>Aggregate Root:</b> Sim</p>
 *
 * @see <a href="https://www2.susep.gov.br/">SUSEP</a>
 */
public record MovimentoSinistro(
    /**
     * Código Susep da seguradora
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> codigo_seguradora</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 5</p>
     */
    String codigoSeguradora,

    /**
     * Grupo e ramo do movimento
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> grupo_ramo</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 4</p>
     * <p><b>Observação:</b> Incluir para previdência os dominios:

8800 - Coberturas de Sobrevivência (Previdência)
8900 - Coberturas de Risco (Previdência</p>
     */
    String grupoRamo,

    /**
     * Referência ao Identificador do processo de sinistro
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> codigo_sinistro</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 50</p>
     */
    String codigoSinistro,

    /**
     * Identificador do movimento
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> identificador_movimento</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 80</p>
     * <p><b>Observação:</b> O identificador do movimento não pode se repetir em um mesmo processo de sinistro por apólice/bilhete e certificado (se houver)</p>
     */
    String identificadorMovimento,

    /**
     * Referência ao Identificador da apólice ou bilhete
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> apolice_codigo</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 60</p>
     * <p><b>Observação:</b> Inclusão de campo</p>
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
     * <p><b>Observação:</b> Inclusão de campo</p>
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
     * <p><b>Observação:</b> Inclusão de campo</p>
     */
    String numeroEndosso,

    /**
     * Identificação da contraparte: congênere do cosseguro ou cessionária do resseguro
     *
     * <p><b>Cardinalidade:</b> [0. 1]</p>
     * <p><b>Tag:</b> codigo_contraparte</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 8</p>
     * <p><b>Condição:</b> Obrigatórios somente quando for movimento de cosseguro ou resseguro. Ou seja, campo "Origem do Movimento" igual a:
2 - Aceito
3 - Cedido
4 - Resseguro cedido / recuperado não pago
5 - Resseguro cedido / recuperado pago</p>
     */
    String codigoContraparte,

    /**
     * Descrição / Tipo Pagamento
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> sinistro_tipo_pagamento_outros</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 500</p>
     * <p><b>Condição:</b> Obrigatório apenas quando o domínio do campo Tipo Pagamento for '99'- outros</p>
     */
    String sinistroTipoPagamentoOutros,

    /**
     * Valor do movimento
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> valor_movimento</p>
     * <p><b>Tipo:</b> Double</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 16.2</p>
     */
    BigDecimal valorMovimento,

    /**
     * Valor do movimento em reais
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> valor_movimento_reais</p>
     * <p><b>Tipo:</b> Double</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 16.2</p>
     */
    BigDecimal valorMovimentoReais,

    /**
     * Moeda do movimento
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> moeda</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> ISO 4217
Exemplo ( "BRL", "USD", "EUR", "GBP", "JPY")</p>
     * <p><b>Tamanho:</b> 3</p>
     */
    String moeda,

    /**
     * Tipo do sinistro movimentado
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> tipo_sinistro</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Administrativo
2 - Judicial</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    Integer tipoSinistro,

    /**
     * Tipo de movimento do sinistro
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> tipo_movimento</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Aviso
2 - Reavaliação
3 - Cancelamento
4 - Reabertura
5 - Liquidação Parcial
6 - Liquidação Final
7 - Estorno de Liquidação Parcial
8 - Estorno de Liquidação Total
9 - Transferência de Ativo Redutor de PSL para Crédito com Ressegurador
10 - Estorno de Transferência de Ativo Redutor de PSL para Crédito com Ressegurador</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    Integer tipoMovimento,

    /**
     * Meio de pagamento
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> meio_pagamento</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Boleto
2 - TED
3 - TEF
4 - Cartao
5 - DOC
6 - Cheque
7 - Desconto em folha
8 - PIXinst
9 - Dinheiro em especie
99 - Outros</p>
     * <p><b>Tamanho:</b> 2</p>
     * <p><b>Condição:</b> Obrigatório apenas para Tipo Movimento 5 e 6</p>
     * <p><b>Observação:</b> Alteração de cardinaldiade</p>
     */
    Integer meioPagamento,

    /**
     * Origem do movimento de sinistro
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> origem</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Direto
2 - Aceito
3 - Cedido
4 - Resseguro cedido / recuperado não pago
5 - Resseguro cedido / recuperado pago</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    Integer origem,

    /**
     * Tipo de operação do movimento de sinistro
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> tipo_operacao_sinistro</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Indenização
2 - Despesa diretamente relacionada ao Sinistro
3 - Ressarcidos ou Salvados
4 - Depósito Judicial Redutor
5 - Despesa Ressarcido ou Salvados
6 - Benefício
7 - Despesas com Benefício
8 - Recuperação de Benefícios
9 - Diferença entre atualização mensal da PMBC e a atualização anual da renda.</p>
     * <p><b>Tamanho:</b> 2</p>
     * <p><b>Observação:</b> Ajuste de nomenclatura de domínio</p>
     */
    Integer tipoOperacaoSinistro,

    /**
     * Tipo de Pagamento
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> tipo_pagamento</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Pago a terceiros diretamente
2 - Pago por reembolso ao segurado
3 - Por reembolso ao tomador (empresa)
4 - Diretamente ao segurado (ou executivo)
99 - outros</p>
     * <p><b>Tamanho:</b> 2</p>
     * <p><b>Observação:</b> Exclusão da condição</p>
     */
    Integer tipoPagamento,

    /**
     * Indica se é um registro de exclusão
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> indicador_exclusao</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Sim
2 - Não</p>
     * <p><b>Tamanho:</b> 1</p>
     */
    Integer indicadorExclusao,

    /**
     * Identificador único do registro
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> uuid</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> 7d9b5524-d047-40ee-8f9c-62c0c8e87d8e</p>
     * <p><b>Tamanho:</b> 36</p>
     */
    String uuid,

    /**
     * Data do movimento
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> data_movimento</p>
     * <p><b>Tipo:</b> Date</p>
     * <p><b>Formato:</b> AAAA-MM-DD</p>
     * <p><b>Tamanho:</b> 10</p>
     */
    LocalDate dataMovimento,

    /**
     * Data do registro
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> data_registro</p>
     * <p><b>Tipo:</b> Date</p>
     * <p><b>Formato:</b> ISO 8601
"2021-06-25T18:00:00Z"</p>
     * <p><b>Tamanho:</b> -</p>
     */
    LocalDate dataRegistro,

    /**
     * Data da alteração do registro
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> data_alteracao</p>
     * <p><b>Tipo:</b> Date</p>
     * <p><b>Formato:</b> ISO 8601
"2021-06-25T18:00:00Z"</p>
     * <p><b>Tamanho:</b> -</p>
     */
    LocalDate dataAlteracao,

    /**
     * Campo livre de anotação
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> anotacao</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Tamanho:</b> 500</p>
     * <p><b>Observação:</b> Este campo servirá para que as Registradoras utilizem para seu controle interno dos registros e deverá ser retornado no check layout</p>
     */
    String anotacao,

    /**
     * Lista de despesas financeiras adicionais
     *
     * <p><b>Tipo:</b> Lista de Adicionais</p>
     */
    List<Adicionais> adicionais
) {}