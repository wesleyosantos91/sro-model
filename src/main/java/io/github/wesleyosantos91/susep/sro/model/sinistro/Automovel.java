package io.github.wesleyosantos91.susep.sro.model.sinistro;

import java.time.LocalDate;

/**
 * Representa Dados Automóvel no contexto de Sinistro.
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba SINISTRO</p>
 * <p><b>Grupo:</b> Dados Automóvel</p>
 */
public record Automovel(
    /**
     * Referência ao Identificador do objeto segurado
     *
     * <p><b>Cardinalidade:</b> [1..1]</p>
     * <p><b>Tag:</b> codigo_objeto</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 50</p>
     * <p><b>Observação:</b> Campo incluído.</p>
     */
    String codigoObjeto,

    /**
     * Causa do Sinistro
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> causa_sinistro</p>
     * <p><b>Tipo:</b> Int</p>
     * <p><b>Formato:</b> 1 - Roubo/Furto (este código somente deve ser utilizadoquando a companhia não dispõe das informações deRoubo e Furto separadamente)
2 - Roubo
3 - Furto
4 - Colisão parcial
5 - Colisão Indenização Integral
6 - Incêndio
7 - Assistência 24 horas
99 - Outros</p>
     * <p><b>Tamanho:</b> 2</p>
     */
    Integer causaSinistro,

    /**
     * Sexo do condutor do veículo no momento do sinistro
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
     * Data de nascimento do condutor do veículo no momento do sinistro (Casco, RCF-A, APP e Assistência e Outras Coberturas)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> data_nascimento</p>
     * <p><b>Tipo:</b> Date</p>
     * <p><b>Formato:</b> AAAA-MM-DD</p>
     * <p><b>Tamanho:</b> 10</p>
     */
    LocalDate dataNascimento,

    /**
     * País de ocorrência do sinistro (Carta Verde)
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> pais_ocorrencia_sinistro</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> Lista de Países: ISO 3166-1 alfa-3</p>
     * <p><b>Tamanho:</b> 3</p>
     */
    String paisOcorrenciaSinistro,

    /**
     * CEP da localidade de ocorrência do sinistro
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> cep_localidade_sinistro</p>
     * <p><b>Tipo:</b> String</p>
     * <p><b>Formato:</b> -</p>
     * <p><b>Tamanho:</b> 30</p>
     */
    String cepLocalidadeSinistro
) {}