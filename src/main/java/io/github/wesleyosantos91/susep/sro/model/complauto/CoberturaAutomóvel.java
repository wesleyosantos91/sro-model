package io.github.wesleyosantos91.susep.sro.model.complauto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representa Cobertura Automóvel no contexto de COMPL_AUTO.
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba COMPL _AUTO</p>
 * <p><b>Grupo:</b> Cobertura Automóvel</p>
 */
public record CoberturaAutomóvel(
    /**
     * Grupo e ramo da cobertura
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> grupo_ramo</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 4</p>
     */
    String grupoRamo,

    /**
     * Código da cobertura
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> codigo</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> Tabela de Coberturas</p>
     * <p><b>Tamanho:</b> 5</p>
     */
    Integer codigo,

    /**
     * Descrição / Nome da cobertura
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> outras_descricao</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 500</p>
     * <p><b>Condição:</b> Obrigatório quando o campo "Codigo" for preenchido com "Outras"</p>
     */
    String outrasDescricao,

    /**
     * 
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> cobertura_interna_seguradora</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 50</p>
     * <p><b>Observação:</b> Alteração de cardinalidade</p>
     */
    String coberturaInternaSeguradora,

    /**
     * Número do processo SUSEP relacionado à cobertura
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> numero_processo</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 50</p>
     * <p><b>Observação:</b> 15414.999999/9999-99
Utilizar esse número para os casos em que não houver processo</p>
     */
    String numeroProcesso,

    /**
     * Limite máximo de indenização (LMI)
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> limite_maximo_indenizacao</p>
     * <p><b>Tipo:</b> Double</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 16.2</p>
     */
    BigDecimal limiteMaximoIndenizacao,

    /**
     * Limite máximo de indenização (LMI) em reais
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> limite_maximo_indenizacao_real</p>
     * <p><b>Tipo:</b> Double</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 16.2</p>
     */
    BigDecimal limiteMaximoIndenizacaoReal,

    /**
     * Data de início de vigência da cobertura
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> data_inicio</p>
     * <p><b>Tipo:</b> Date</p>
     * <p><b>Formato:</b> AAAA-MM-DD</p>
     * <p><b>Tamanho:</b> 10</p>
     */
    LocalDate dataInicio,

    /**
     * Data de fim de vigência da cobertura
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> data_termino</p>
     * <p><b>Tipo:</b> Date</p>
     * <p><b>Formato:</b> AAAA-MM-DD</p>
     * <p><b>Tamanho:</b> 10</p>
     */
    LocalDate dataTermino,

    /**
     * 
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> cobertura_principal</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Sim
2 - Não</p>
     * <p><b>Tamanho:</b> 1</p>
     */
    Integer coberturaPrincipal,

    /**
     * 
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> cobertura_caracteristica</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Massificados
2 - Massificados microseguros
3 - Grandes Riscos</p>
     * <p><b>Tamanho:</b> 2</p>
     * <p><b>Observação:</b> Este campo é obrigatório  deve observar a norma de grandes riscos, e o mais adequado é de que estivesse no bloco geral do documento. O seu preenchimento deve observar se a operação é caracterizada como de grandes riscos, caso contrário deve ser definida como massificados ou massificados de microsseguros, conforme o caso.</p>
     */
    Integer coberturaCaracteristica,

    /**
     * 
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> cobertura_tipo</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Paramétrico
2 - Intermitente
3 - Regular (comum)
4 - Capital Global
5 - Paramétrico e Intermitente</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    Integer coberturaTipo,

    /**
     * Valor de prêmio da cobertura
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> valor_premio</p>
     * <p><b>Tipo:</b> Double</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 16.2</p>
     * <p><b>Condição:</b> O campo deverá ser preenchido com 0,00 quando o campo 'limite_maximo_indenizacao_sublimite' for preenchido com o domínio '1' (sim).</p>
     * <p><b>Observação:</b> Inclusão de condição</p>
     */
    BigDecimal valorPremio,

    /**
     * Valor de prêmio da cobertura em reais
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> valor_premio_real</p>
     * <p><b>Tipo:</b> Double</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 16.2</p>
     * <p><b>Condição:</b> O campo deverá ser preenchido com 0,00 quando o campo 'limite_maximo_indenizacao_sublimite' for preenchido com o domínio '1' (sim).</p>
     * <p><b>Observação:</b> Inclusão de condição</p>
     */
    BigDecimal valorPremioReal,

    /**
     * Valor de IOF da cobertura em reais
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> iof</p>
     * <p><b>Tipo:</b> Double</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 16.2</p>
     */
    BigDecimal iof,

    /**
     * Valor do custo de aquisição da cobertura
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> custo</p>
     * <p><b>Tipo:</b> Double</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 16.2</p>
     */
    BigDecimal custo,

    /**
     * Valor do custo de aquisição da cobertura em reais
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> custo_real</p>
     * <p><b>Tipo:</b> Double</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 16.2</p>
     */
    BigDecimal custoReal,

    /**
     * Tipo de indenização por cobertura contratada (Casco)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> tipo_indenizacao</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Integral
2 - Parcial
99 - Outros</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    Integer tipoIndenizacao,

    /**
     * 
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> percentual_indenizacao_parcial</p>
     * <p><b>Tipo:</b> Float</p>
     * <p><b>Tamanho:</b> 3.9</p>
     * <p><b>Condição:</b> Deve ser não negativo</p>
     */
    BigDecimal percentualIndenizacaoParcial,

    /**
     * Percentual aplicado sobre o LMI que irá definir o valor a partir do qual haverá direito à indenização integral em caso de sinistro com indenização integral, necessário para a reparação dos prejuízos causados por eventual sinistro (Casco)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> percentual_lmi</p>
     * <p><b>Tipo:</b> Float</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 3.9</p>
     * <p><b>Condição:</b> Deve ser não negativo</p>
     */
    BigDecimal percentualLmi,

    /**
     * Número de dias de cobertura para direito à indenização pelo valor de novo (Casco)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> dias_cobertura</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 4</p>
     * <p><b>Condição:</b> Deve aceitar apenas números</p>
     */
    String diasCobertura,

    /**
     * Cobertura vinculada (RCF-A, APP, Assistência e Outras Coberturas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> cobertura_vinculada</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Veículo
2 - Condutor
99 - Outros</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    Integer coberturaVinculada
) {}