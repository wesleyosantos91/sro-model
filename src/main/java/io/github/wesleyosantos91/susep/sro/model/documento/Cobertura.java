package io.github.wesleyosantos91.susep.sro.model.documento;

import io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Record representando Cobertura
 * <p>Tag: cobertura</p>
 * <p>Gerado automaticamente a partir da especificação SRO v2.0.0</p>
 */
public record Cobertura(

    /**
     * Grupo e Ramo da Cobertura
     * <p>Grupo e ramo da cobertura</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 4</p>
     */
    String grupoRamo,

    /**
     * Código da Cobertura
     * <p>Código da cobertura</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> Tabela de Coberturas</p>
     * <p><strong>Tamanho:</strong> 5</p>
     */
    Integer codigo,

    /**
     * Descrição / Nome da Cobertura
     * <p>Descrição / Nome da cobertura</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 500</p>
     * <p><strong>Condição:</strong> Obrigatório quando o campo "Codigo" for preenchido com "999 - Outras"</p>
     */
    String outrasDescricao ,

    /**
     * Código Interno da Cobertura da Seguradora
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 50</p>
     */
    String coberturaInternaSeguradora,

    /**
     * Número do Processo Susep da Cobertura
     * <p>Número do processo SUSEP relacionado à cobertura</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 50</p>
     * <p><strong>Observação:</strong> 15414.999999/9999-99 Utilizar esse número para os casos em que não houver processo</p>
     */
    String numeroProcesso,

    /**
     * Limite Máximo de Indenização (LMI)
     * <p>Limite máximo de indenização (LMI)</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     */
    Double limiteMaximoIndenizacao,

    /**
     * Limite Máximo de Indenização (LMI) em Reais
     * <p>Limite máximo de indenização (LMI) em reais</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     */
    Double limiteMaximoIndenizacaoReal,

    /**
     * Limite Máximo de Indenização (LMI) é sublimite
     * <p>Indica se o LMI é sublimite ou não</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> 1 - Sim 2 - Não</p>
     * <p><strong>Tamanho:</strong> 1</p>
     */
    Integer limiteMaximoIndenizacaoSublimite,

    /**
     * Data de Início de Vigência da Cobertura
     * <p>Data de início de vigência da cobertura</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     */
    LocalDate dataInicioCobertura,

    /**
     * Data de Fim de Vigência da Cobertura
     * <p>Data de fim de vigência da cobertura</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     */
    LocalDate dataTerminoCobertura,

    /**
     * Cobertura Principal
     * <p>Indica se a cobertura é principal ou acessoria</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> 1 - Sim 2 - Não</p>
     * <p><strong>Tamanho:</strong> 1</p>
     */
    Integer coberturaPrincipal,

    /**
     * Característica da Cobertura
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> 1 - Massificados 2 - Massificados microseguros 3 - Grandes Riscos</p>
     * <p><strong>Tamanho:</strong> 2</p>
     * <p><strong>Observação:</strong> Este campo é obrigatório  deve observar a norma de grandes riscos, e o mais adequado é de que estivesse no bloco geral do documento. O seu preenchimento deve observar se a operação é caracterizada com</p>
     */
    Integer coberturaCaracteristica,

    /**
     * Tipo do Risco
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> 1 - Pessoas 2 - Danos</p>
     * <p><strong>Tamanho:</strong> 2</p>
     */
    Integer tipoRisco,

    /**
     * Tipo de Cobertura
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> 1 - Paramétrico 2 - Intermitente 3 - Regular (comum) 4 - Capital Global 5 - Paramétrico e Intermiten...</p>
     * <p><strong>Tamanho:</strong> 2</p>
     */
    Integer coberturaTipo,

    /**
     * Valor de Prêmio da Cobertura
     * <p>Valor de prêmio da cobertura</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     * <p><strong>Condição:</strong> O campo deverá ser preenchido com 0,00 quando o campo 'limite_maximo_indenizacao_sublimite' for preenchido com o domínio '1' (sim).</p>
     */
    Double valorPremio,

    /**
     * Valor de Prêmio da Cobertura em Reais
     * <p>Valor de prêmio da cobertura em reais</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     * <p><strong>Condição:</strong> O campo deverá ser preenchido com 0,00 quando o campo 'limite_maximo_indenizacao_sublimite' for preenchido com o domínio '1' (sim).</p>
     */
    Double valorPremioReal,

    /**
     * Valor de IOF da Cobertura em Reais
     * <p>Valor de IOF da cobertura em reais</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     */
    Double iof,

    /**
     * Valor do Custo de Aquisição da Cobertura
     * <p>Valor do custo de aquisição da cobertura</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     */
    Double custo,

    /**
     * Valor do Custo de Aquisição da Cobertura em Reais
     * <p>Valor do custo de aquisição da cobertura em reais</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     */
    Double custoReal
) {
    /**
     * Compact constructor com validações completas.
     */
    public Cobertura {
        /* === VALIDAÇÕES DE OBRIGATORIEDADE === */
        Objects.requireNonNull(grupoRamo, "Grupo e ramo é obrigatório");
        Objects.requireNonNull(codigo, "Código da cobertura é obrigatório");
        Objects.requireNonNull(coberturaInternaSeguradora, "Código interno da seguradora é obrigatório");
        Objects.requireNonNull(numeroProcesso, "Número do processo Susep é obrigatório");
        Objects.requireNonNull(limiteMaximoIndenizacao, "Limite máximo de indenização é obrigatório");
        Objects.requireNonNull(limiteMaximoIndenizacaoReal, "Limite máximo de indenização em reais é obrigatório");
        Objects.requireNonNull(dataInicioCobertura, "Data de início da cobertura é obrigatória");
        Objects.requireNonNull(dataTerminoCobertura, "Data de término da cobertura é obrigatória");
        Objects.requireNonNull(coberturaCaracteristica, "Característica da cobertura é obrigatória");
        Objects.requireNonNull(coberturaTipo, "Tipo de cobertura é obrigatório");
        Objects.requireNonNull(valorPremio, "Valor de prêmio é obrigatório");
        Objects.requireNonNull(valorPremioReal, "Valor de prêmio em reais é obrigatório");
        
        /* === VALIDAÇÕES DE TAMANHO === */
        
        ValidationUtils.requireExactLength(grupoRamo, 4, "Grupo e ramo");
        ValidationUtils.requireMaxLength(outrasDescricao, 500, "Descrição de outras coberturas");
        ValidationUtils.requireMaxLength(coberturaInternaSeguradora, 50, "Código interno da seguradora");
        ValidationUtils.requireMaxLength(numeroProcesso, 50, "Número do processo");
        
        /* === VALIDAÇÕES DE DOMÍNIO === */
        
        /* Código da cobertura: 1 a 999 */
        if (codigo < 1 || codigo > 999) {
            throw new IllegalArgumentException(
                "Código da cobertura deve estar entre 1 e 999"
            );
        }
        
        /* Sublimite: 1 (Sim) ou 2 (Não) */
        ValidationUtils.requireRange(limiteMaximoIndenizacaoSublimite, 1, 2, "LMI é sublimite");
        
        /* Cobertura principal: 1 (Sim) ou 2 (Não) */
        ValidationUtils.requireRange(coberturaPrincipal, 1, 2, "Cobertura principal");
        
        /* Característica: 1 a 3 */
        ValidationUtils.requireRange(coberturaCaracteristica, 1, 3, "Característica da cobertura");
        
        /* Tipo de risco: 1 ou 2 */
        ValidationUtils.requireRange(tipoRisco, 1, 2, "Tipo de risco");
        
        /* Tipo de cobertura: 1 a 6 */
        ValidationUtils.requireRange(coberturaTipo, 1, 6, "Tipo de cobertura");
        
        /* === VALIDAÇÕES CONDICIONAIS === */
        
        /* Descrição obrigatória para código 999 (Outras) */
        ValidationUtils.requireNonBlankIf(
            codigo == 999,
            outrasDescricao,
            "Descrição é obrigatória para código 999 (Outras)"
        );
        
        /* Prêmio deve ser 0,00 quando for sublimite */
        if (limiteMaximoIndenizacaoSublimite != null && limiteMaximoIndenizacaoSublimite == 1) {
            if (valorPremio != 0.0 || valorPremioReal != 0.0) {
                throw new IllegalArgumentException(
                    "Prêmio deve ser 0,00 quando LMI for sublimite"
                );
            }
        }
        
        /* === VALIDAÇÕES DE VALORES === */
        
        ValidationUtils.requirePositive(limiteMaximoIndenizacao, "Limite máximo de indenização");
        ValidationUtils.requirePositive(limiteMaximoIndenizacaoReal, "Limite máximo de indenização em reais");
        ValidationUtils.requirePositive(valorPremio, "Valor de prêmio");
        ValidationUtils.requirePositive(valorPremioReal, "Valor de prêmio em reais");
        ValidationUtils.requirePositive(iof, "IOF");
        ValidationUtils.requirePositive(custo, "Custo de aquisição");
        ValidationUtils.requirePositive(custoReal, "Custo de aquisição em reais");
        
        /* === VALIDAÇÕES DE DATAS === */
        
        ValidationUtils.requireAfterOrEqual(
            dataTerminoCobertura,
            dataInicioCobertura,
            "Data de término deve ser maior ou igual à data de início da cobertura"
        );
    }
}