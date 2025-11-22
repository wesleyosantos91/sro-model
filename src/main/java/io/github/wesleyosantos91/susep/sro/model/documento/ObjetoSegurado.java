package io.github.wesleyosantos91.susep.sro.model.documento;

import io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Record representando ObjetoSegurado
 * <p>Tag: objeto_segurado</p>
 * <p>Gerado automaticamente a partir da especificação SRO v2.0.0</p>
 */
public record ObjetoSegurado(

    /**
     * Identificador do Objeto Segurado
     * <p>Identificador do objeto segurado</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 50</p>
     * <p><strong>Observação:</strong> Para Fiança Locatícia, é a identificação do Contrato de Locação.</p>
     */
    String codigo,

    /**
     * Tipo do Objeto Segurado
     * <p>Tipo do objeto segurado</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> 1 - Contrato 2 - Processo administrativo 3 - Processo judicial 4 - Automóvel 5 - Condutor  6 - Frota...</p>
     * <p><strong>Tamanho:</strong> 2</p>
     */
    Integer tipo,

    /**
     * Descrição do Tipo do Objeto Segurado
     * <p>Descrição do tipo do objeto segurado</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 500</p>
     * <p><strong>Condição:</strong> Obrigatório quando o campo "Tipo" for preenchido com "Outros".</p>
     */
    String descricaoTipo,

    /**
     * Descrição do Objeto Segurado
     * <p>Descrição do objeto segurado</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 1024</p>
     * <p><strong>Observação:</strong> Quando "Tipo de Objeto Segurado" for a opção 7- Pessoas, preencher com o texto "Própria pessoa segurada"</p>
     */
    String descricaoObjeto,

    /**
     * Valor do Objeto Segurado
     * <p>Valor do objeto segurado</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     * <p><strong>Condição:</strong> Obrigatório quando o campo "Tipo" for preenchido com "Contrato", "Processo Administrativo" e "Processo Judicial" e  para coberturas dos ramos de seguro garantia e de fiança locatícia</p>
     */
    Double valor,

    /**
     * Valor do Objeto Segurado em Reais
     * <p>Valor do objeto segurado em reais</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     * <p><strong>Condição:</strong> Obrigatório quando o campo "Tipo" for preenchido com "Contrato", "Processo Administrativo" e "Processo Judicial" e  para coberturas dos ramos de seguro garantia e de fiança locatícia</p>
     */
    Double valorReal,

    /**
     * Data de Início do Objeto Segurado
     * <p>Data de início do objeto segurado</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     * <p><strong>Condição:</strong> Obrigatório quando o campo "Tipo" for preenchido com "Contrato", "Processo Administrativo" e "Processo Judicial" e  para coberturas dos ramos de seguro garantia e de fiança locatícia</p>
     */
    LocalDate dataInicio,

    /**
     * Data de Fim do Objeto Segurado
     * <p>Data de fim do objeto segurado</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     * <p><strong>Observação:</strong> Aplicável quando o término do objeto for determinado e conhecido na emissão.</p>
     */
    LocalDate dataTermino
) {
    /**
     * Compact constructor com validações completas.
     */
    public ObjetoSegurado {
        /* === VALIDAÇÕES DE OBRIGATORIEDADE === */
        Objects.requireNonNull(codigo, "Código do objeto segurado é obrigatório");
        Objects.requireNonNull(tipo, "Tipo do objeto segurado é obrigatório");
        Objects.requireNonNull(descricaoObjeto, "Descrição do objeto segurado é obrigatória");
        
        /* === VALIDAÇÕES DE DOMÍNIO === */
        
        /* Tipo: 1 a 99 */
        if (tipo < 1 || tipo > 99) {
            throw new IllegalArgumentException(
                "Tipo do objeto segurado deve estar entre 1 e 99"
            );
        }
        
        /* === VALIDAÇÕES CONDICIONAIS === */
        
        /* Descrição obrigatória para tipo "Outros" */
        boolean isOutros = tipo == 99;
        ValidationUtils.requireNonBlankIf(
            isOutros,
            descricaoTipo,
            "Descrição do tipo é obrigatória para tipo 'Outros'"
        );
        
        /* Valor obrigatório para tipos 1, 2, 3 (Contrato, Processo Admin, Processo Judicial) */
        boolean requerValor = tipo >= 1 && tipo <= 3;
        ValidationUtils.requireNonNullIf(
            requerValor,
            valor,
            "Valor é obrigatório para Contrato, Processo Administrativo e Processo Judicial"
        );
        ValidationUtils.requireNonNullIf(
            requerValor,
            valorReal,
            "Valor em reais é obrigatório para Contrato, Processo Administrativo e Processo Judicial"
        );
        ValidationUtils.requireNonNullIf(
            requerValor,
            dataInicio,
            "Data de início é obrigatória para Contrato, Processo Administrativo e Processo Judicial"
        );
        
        /* === VALIDAÇÕES DE TAMANHO === */
        
        ValidationUtils.requireMaxLength(codigo, 50, "Código");
        ValidationUtils.requireMaxLength(descricaoTipo, 500, "Descrição do tipo");
        ValidationUtils.requireMaxLength(descricaoObjeto, 1024, "Descrição do objeto");
        
        /* === VALIDAÇÕES DE VALORES === */
        
        ValidationUtils.requirePositive(valor, "Valor");
        ValidationUtils.requirePositive(valorReal, "Valor em reais");
        
        /* === VALIDAÇÕES DE DATAS === */
        
        if (dataInicio != null && dataTermino != null) {
            ValidationUtils.requireAfterOrEqual(
                dataTermino,
                dataInicio,
                "Data de término deve ser maior ou igual à data de início"
            );
        }
    }
}