package br.com.sro.model.complauto;

import br.com.sro.model.util.ValidationUtils;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Representa informações complementares de veículos automotores no sistema SRO.
 *
 * <p>Contém dados específicos sobre veículos segurados, incluindo identificação,
 * características técnicas, coberturas e informações de condutores.</p>
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba COMPL _AUTO</p>
 * <p><b>Bounded Context:</b> COMPL_AUTO (Complemento Automóvel)</p>
 * <p><b>Aggregate Root:</b> Sim</p>
 *
 * @see <a href="https://www2.susep.gov.br/">SUSEP</a>
 */
public record ComplAuto(
    /**
     * Identificador do endosso do documento
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> endosso_codigo</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 60</p>
     * <p><b>Condição:</b> Validar o registro prévio do endosso por meio de um identificador de endosso já registrado.</p>
     * <p><b>Observação:</b> Inclusão de Condição

Campo faz referência ao campo 'identificador do endosso' do bloco endosso do leiaute de endosso.</p>
     */
    String endossoCodigo,

    /**
     * Identificador do objeto segurado
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> codigo</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 50</p>
     * <p><b>Observação:</b> O identificador deve ser único por objeto em cada apólice/bilhete ou em cada certificado e apólice/bilhete.</p>
     */
    String codigo,

    /**
     * Tipo do objeto segurado
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> tipo</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Contrato
2 - Processo administrativo
3 - Processo judicial
4 - Automóvel
5 - Condutor
6 - Frota
99 - Outros</p>
     * <p><b>Tamanho:</b> 2</p>
     * <p><b>Condição:</b> Os tipos de objeto segurado representados pelos domínios 1, 2, 3 e 99 não devem ser aceitos para o registro do leiaute complementar auto.</p>
     * <p><b>Observação:</b> Inclusão de condição</p>
     */
    Integer tipo,

    /**
     * Descrição do tipo do objeto segurado
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> descricao_tipo</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 500</p>
     * <p><b>Condição:</b> Obrigatório quando o campo "Tipo" for preenchido com "Outros".</p>
     * <p><b>Observação:</b> A condição não se aplica por não ser possível a inclusão o tipo do objeto segurado 99 - Outros</p>
     */
    String descricaoTipo,

    /**
     * Descrição do objeto segurado
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> descricao_objeto</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 1024</p>
     */
    String descricaoObjeto,

    /**
     * Identificação exata do veículo (Casco, RCF-A, APP, Assistência e Outras Coberturas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> identificacao_exata_veiculo</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Sim
2 - Não</p>
     * <p><b>Tamanho:</b> 1</p>
     */
    Integer identificacaoExataVeiculo,

    /**
     * Modalidade de cobertura (Casco)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> modalidade_casco</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Valor de mercado referenciado
2 - Valor determinado
3 - Critério diverso
99 - Outros</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    Integer modalidadeCasco,

    /**
     * Percentual em caso de cobertura contratada parcial (Casco)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> percentual_tabela_referencia</p>
     * <p><b>Tipo:</b> Float</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 3.9</p>
     */
    BigDecimal percentualTabelaReferencia,

    /**
     * Tabela de referência adotada no plano (Casco)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> tabela_valor_medio</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1- Molicar 
2- FIPE 
3-  Jornal do Carro
4- VD
99 - Outras</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    Integer tabelaValorMedio,

    /**
     * Código do modelo de acordo com a tabela de referência adotada no plano (Casco, RCF-A, APP, Assistência e Outras Coberturas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> codigo_modelo</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 10</p>
     */
    String codigoModelo,

    /**
     * Ano do modelo (Casco, RCF-A, APP, Assistência e Outras Coberturas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> ano_modelo</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 4</p>
     */
    Integer anoModelo,

    /**
     * Categoria tarifária (Casco, RCF-A, APP, Assistência e Outras Coberturas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> categoria_tarifaria</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> Manual de Orientações para Envio de Dados páginas 130 -132</p>
     * <p><b>Tamanho:</b> 3</p>
     * <p><b>Observação:</b> http://www.susep.gov.br/setores-susep/cgeti/cosis/manual-de-orientacao-para-envio-de-dados-v05-2020.pdf</p>
     */
    String categoriaTarifaria,

    /**
     * CEP de risco (Casco, RCF-A, APP, Assistência e Outras Coberturas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> cep_risco</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 8</p>
     * <p><b>Observação:</b> Alteração de tamanho pós apontamento reunião Ofício - 07/02/23</p>
     */
    String cepRisco,

    /**
     * Código de utilização do veículo (Casco, RCF-A, APP, Assistência e Outras Coberturas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> codigo_utilizacao</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Lazer
2 - Locomoção diária
3 - Exercício do trabalho
99 - Outros</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    Integer codigoUtilizacao,

    /**
     * CEP da localidade de destino frequente do veículo (Casco, RCF-A, APP, Assistência e Outras Coberturas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> cep_localidade_destino</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 8</p>
     * <p><b>Observação:</b> Alteração de tamanho pós apontamento reunião Ofício - 07/02/23</p>
     */
    String cepLocalidadeDestino,

    /**
     * CEP da localidade de pernoite do veículo (Casco, RCF-A, APP, Assistência e Outras Coberturas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> cep_localidade_pernoite</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 8</p>
     * <p><b>Observação:</b> Alteração de tamanho pós apontamento reunião Ofício - 07/02/23</p>
     */
    String cepLocalidadePernoite,

    /**
     * Percentual de desconto por bônus (Casco, RCF-A, APP, Assistência e Outras Coberturas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> percentual_desconto_bonus</p>
     * <p><b>Tipo:</b> Float</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 3.9</p>
     */
    BigDecimal percentualDescontoBonus,

    /**
     * Classe de bônus (Casco, RCF-A, APP, Assistência e Outras Coberturas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> classe_bonus</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    Integer classeBonus,

    /**
     * Lista de Cobertura Automóvel
     *
     * <p><b>Tipo:</b> Lista de CoberturaAutomóvel</p>
     */
    List<CoberturaAutomóvel> coberturaAutomóvels,

    /**
     * Lista de Franquia
     *
     * <p><b>Tipo:</b> Lista de Franquia</p>
     */
    List<Franquia> franquias,

    /**
     * Lista de Pessoas associadas - Condutor
     *
     * <p><b>Tipo:</b> Lista de PessoasAssociadasCondutor</p>
     */
    List<PessoasAssociadasCondutor> pessoasAssociadasCondutors
) {}