package br.com.sro.model.complauto;

import java.time.LocalDate;

/**
 * Representa Pessoas associadas - Condutor no contexto de COMPL_AUTO.
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba COMPL _AUTO</p>
 * <p><b>Grupo:</b> Pessoas associadas - Condutor</p>
 */
public record PessoasAssociadasCondutor(
    /**
     * Documento de identificação da pessoa associada à apólice
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> documento</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 40</p>
     * <p><b>Observação:</b> Alteração de cardinalidade</p>
     */
    String documento,

    /**
     * Sexo do condutor utilizado para a taxação (Casco, RCF-A, APP, Assistência e Outras Coberturas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> sexo_condutor</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Masculino
2 - Feminino
3 - Não declarado
99 - Outros</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    Integer sexoCondutor,

    /**
     * Data de nascimento do condutor (Casco, RCF-A, APP, Assistência e Outras Coberturas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> data_nascimento</p>
     * <p><b>Tipo:</b> Date</p>
     * <p><b>Formato:</b> AAAA-MM-DD</p>
     * <p><b>Tamanho:</b> 10</p>
     */
    LocalDate dataNascimento,

    /**
     * Tempo de habilitação do condutor utilizado para taxação (Casco, RCF-A, APP, Assistência e Outras Coberturas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> tempo_habilitacao</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 3</p>
     */
    Integer tempoHabilitacao
) {}