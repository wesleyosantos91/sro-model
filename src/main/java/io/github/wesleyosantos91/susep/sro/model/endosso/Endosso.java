package io.github.wesleyosantos91.susep.sro.model.endosso;

import io.github.wesleyosantos91.susep.sro.model.documento.*;
import io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Record principal representando um Endosso do SRO
 * <p>Sistema de Registro de Operações (SRO) - Versão 2.0.0</p>
 * <p>Este record representa alterações em documentos de seguro (apólices, bilhetes, certificados).</p>
 * <p>Gerado automaticamente a partir da especificação SUSEP.</p>
 * 
 * @see EndossoAssociado
 * @see br.com.sro.model.documento.Segurado
 * @see br.com.sro.model.documento.Beneficiario
 * @see br.com.sro.model.documento.Tomador
 * @see br.com.sro.model.documento.Intermediario
 * @see br.com.sro.model.documento.ObjetoSegurado
 */
public record Endosso(

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
     * <p><strong>Observação:</strong> Este campo servirá para que as Registradoras utilizem para seu controle interno dos registros e deverá ser retornado no check layout </p>
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
     * <p><strong>Formato:</strong> ISO 8601 "2021-06-25T18:00:00Z"</p>
     */
    LocalDate dataRegistro,

    /**
     * Data da Alteração do Registro
     * <p>Data da alteração do registro</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> ISO 8601 "2021-06-25T18:00:00Z"</p>
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
     * Tipo de Documento Emitido
     * <p>Tipo de Documento Emitido </p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> 1 - Apólice Individual 2 - Apólice Coletiva 3 - Bilhete 4 - Certificado 5 - Apólice Individual Autom...</p>
     * <p><strong>Tamanho:</strong> 1</p>
     * <p><strong>Condição:</strong> Validar se o domínio do tipo do documento emitido informado no endosso é o mesmo utilizado no documento endossado (emissão original).  </p>
     * <p><strong>Observação:</strong> Inclusão de campo  Inclusão de domínio  Inclusão de condição  é idêntico ao campo "Tipo de Documento Emitido" do leiaute '1 - Documento' pois se refere ao documento endossado.</p>
     */
    Integer tipoDocumentoEndossado,

    /**
     * Identificador da Apólice / Bilhete
     * <p>Identificador da apólice ou bilhete</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 60</p>
     */
    String apoliceCodigo,

    /**
     * Número Susep da Apólice
     * <p>Número SUSEP da apólice, conforme Circular Susep 326</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 30</p>
     * <p><strong>Condição:</strong> Campo obrigatório para apólices com coberturas do ramo Garantia (0775 e 0776).</p>
     * <p><strong>Observação:</strong> Regra de formação: SSSSSAAAAFFFFRRRRNNNNNNN SSSSS Código da sociedade seguradora na SUSEP - 5 dígitos AAAA Ano de emissão da apólice - 4 dígitos FFFF Identificador da sucursal da emissão da apólice - </p>
     */
    String numeroSusepApolice,

    /**
     * Identificador do Certificado
     * <p>Identificador do certificado</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 60</p>
     * <p><strong>Condição:</strong> Campo obrigatório somente para o Tipo de Documento Endossado = "4, 7 e 10".</p>
     * <p><strong>Observação:</strong> Alteração de condição</p>
     */
    String certificadoCodigo,

    /**
     * Identificador do Endosso
     * <p>Identificador do endosso (de qualquer documento)</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 60</p>
     */
    String endossoCodigo,

    /**
     * Descrição do Endosso
     * <p>Descrição adicional do endosso</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 1024</p>
     * <p><strong>Condição:</strong> Obrigatório quando "Tipo Endosso" for "Endosso sem Movimentação de Prêmio"</p>
     * <p><strong>Observação:</strong> [Dúvida] A condição descrita para este campo tem algum fundamento ou sentido? </p>
     */
    String endossoDescricao,

    /**
     * Tipo de Endosso
     * <p>Tipo de emissão do endosso</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> 1 - Endosso de cobrança adicional de prêmio 2 - Endosso de restituição de prêmio 3 - Endosso sem mov...</p>
     * <p><strong>Tamanho:</strong> 2</p>
     */
    Integer endossoTipo,

    /**
     * Endosso Averbável
     * <p>Indica se o endosso inclui averbações </p>
     * <p><strong>Cardinalidade:</strong> [1.1]</p>
     * <p><strong>Formato:</strong> 1-Sim 2-Não</p>
     * <p><strong>Tamanho:</strong> 2</p>
     * <p><strong>Observação:</strong> Deverá ser preenchido com 1-Sim quando houver Endosso de Averbação de Transporte, demais ramos como 2-Não</p>
     */
    Integer endossoAverbavel,

    /**
     * Tipo de Emissão
     * <p>Tipo de emissão da apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> 1 - Emissao Própria 2 - Cosseguro Aceito</p>
     * <p><strong>Tamanho:</strong> 2</p>
     */
    Integer tipoEmissao,

    /**
     * Data de Emissão
     * <p>Data de emissão do endosso</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     */
    LocalDate dataEmissao,

    /**
     * Data de Início de Vigência Endosso
     * <p>Data de início de vigência do endosso</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     */
    LocalDate dataInicio,

    /**
     * Data de Fim de Vigência Endosso
     * <p>Data de fim de vigência do endosso</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     */
    LocalDate dataTermino,

    /**
     * Data de Início de Vigência Documento
     * <p>Data de início de vigência do documento</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     */
    LocalDate dataInicioDocumento,

    /**
     * Data de Fim de Vigência Documento
     * <p>Data de fim de vigência do documento</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     */
    LocalDate dataTerminoDocumento,

    /**
     * Código da Filial
     * <p>Código da filial de emissão da apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 4</p>
     */
    String codigoFilial,

    /**
     * Código da Seguradora Líder
     * <p>Código da seguradora líder para apólices com arranjo de cosseguro</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 5</p>
     * <p><strong>Condição:</strong> Obrigatório quando o campo "Tipo Emisão" for "Cosseguro Aceito"</p>
     */
    String codigoSeguradoraLider,

    /**
     * Identificador da Apólice Seguradora Líder
     * <p>Identificador da apólice seguradora líder para apólice de cosseguro aceito</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 60</p>
     * <p><strong>Condição:</strong> Obrigatório quando o campo "Tipo Emisão" for "Cosseguro Aceito"</p>
     */
    String apoliceCodigoLider,

    /**
     * Moeda da Apólice
     * <p>Moeda de emissão da apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong>  ISO 4217 Exemplo ( "BRL", "USD", "EUR", "GBP", "JPY")</p>
     * <p><strong>Tamanho:</strong> 3</p>
     */
    String moedaApolice,

    /**
     * Limite Máximo de Garantia (LMG)
     * <p>Limite máximo de garantia (LMG)</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     * <p><strong>Condição:</strong> Campo não aceita valor negativo</p>
     * <p><strong>Observação:</strong> Quando não for definível o LMG preencher com 0,00</p>
     */
    Double limiteMaximoGarantia,

    /**
     * Limite Máximo de Garantia (LMG) em Reais
     * <p>Limite máximo de garantia (LMG) em reais</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 16.2</p>
     * <p><strong>Condição:</strong> Campo obrigatórios se moeda diferente de Real Campo não aceita valor negativo</p>
     * <p><strong>Observação:</strong> Quando não for definível o LMG preencher com 0,00</p>
     */
    Double limiteMaximoGarantiaReal,

    /**
     * Indicador de Cobertura Básica
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> 1 - Simples 2 - Ampla</p>
     * <p><strong>Tamanho:</strong> 1</p>
     * <p><strong>Condição:</strong> Campo obrigatório para apólices com coberturas do ramo Compreensivo Condomínio</p>
     */
    Integer coberturaBasica,

    /**
     * Lista de Endossos Associados
     * <p><strong>Cardinalidade:</strong> [0..N]</p>
     * <p>Obrigatório para endossos de cancelamento tipo 5 ou 7</p>
     */
    List<EndossoAssociado> endossosAssociados,

    /**
     * Lista de CCG (Contratos de Contragarantia)
     * <p><strong>Cardinalidade:</strong> [0..N]</p>
     */
    List<Ccg> ccgs,

    /**
     * Lista de Segurados
     * <p><strong>Cardinalidade:</strong> [0..N]</p>
     * <p>Enviar todas as pessoas quando houver alteração</p>
     */
    List<Segurado> segurados,

    /**
     * Lista de Beneficiários
     * <p><strong>Cardinalidade:</strong> [0..N]</p>
     * <p>Enviar todas as pessoas quando houver alteração</p>
     */
    List<Beneficiario> beneficiarios,

    /**
     * Lista de Tomadores/Garantidos
     * <p><strong>Cardinalidade:</strong> [0..N]</p>
     * <p>Enviar todas as pessoas quando houver alteração</p>
     */
    List<Tomador> tomadores,

    /**
     * Lista de Intermediários
     * <p><strong>Cardinalidade:</strong> [0..N]</p>
     * <p>Enviar todas as pessoas quando houver alteração</p>
     */
    List<Intermediario> intermediarios,

    /**
     * Lista de Objetos Segurados
     * <p><strong>Cardinalidade:</strong> [0..N]</p>
     * <p>Não preencher para tipos 5, 6, 7 ou quando endosso for averbável</p>
     */
    List<ObjetoSegurado> objetosSegurados,

    /**
     * Prêmio da Apólice
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     */
    PremioApolice premioApolice,

    /**
     * Informações de Cosseguro
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     */
    Cosseguro cosseguro
) {

    private static final Pattern UUID_PATTERN = Pattern.compile(
        "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"
    );

    public Endosso {
        Objects.requireNonNull(uuid, "UUID é obrigatório");
        Objects.requireNonNull(codigoSeguradora, "Código da seguradora é obrigatório");
        Objects.requireNonNull(dataRegistro, "Data de registro é obrigatória");
        Objects.requireNonNull(dataAlteracao, "Data de alteração é obrigatória");
        Objects.requireNonNull(indicadorExclusao, "Indicador de exclusão é obrigatório");
        Objects.requireNonNull(tipoEndosso, "Tipo de endosso é obrigatório");
        Objects.requireNonNull(tipoDocumentoReferenciado, "Tipo de documento é obrigatório");
        Objects.requireNonNull(apoliceCodigo, "Código da apólice é obrigatório");
        Objects.requireNonNull(tipoEmissao, "Tipo de emissão é obrigatório");
        Objects.requireNonNull(dataEmissao, "Data de emissão é obrigatória");
        Objects.requireNonNull(dataInicio, "Data de início é obrigatória");
        Objects.requireNonNull(dataTermino, "Data de término é obrigatória");
        Objects.requireNonNull(codigoFilial, "Código da filial é obrigatório");
        Objects.requireNonNull(moedaApolice, "Moeda é obrigatória");
        Objects.requireNonNull(limiteMaximoGarantia, "Limite máximo de garantia é obrigatório");
        Objects.requireNonNull(limiteMaximoGarantiaReal, "Limite máximo de garantia em reais é obrigatório");

        if (!UUID_PATTERN.matcher(uuid.toLowerCase()).matches()) {
            throw new IllegalArgumentException("UUID deve estar no formato padrão");
        }

        ValidationUtils.requireExactLength(codigoSeguradora, 5, "Código da seguradora");
        ValidationUtils.requireExactLength(codigoFilial, 4, "Código da filial");
        ValidationUtils.requireMaxLength(anotacao, 500, "Anotação");
        ValidationUtils.requireMaxLength(apoliceCodigo, 60, "Código da apólice");
        ValidationUtils.requireMaxLength(numeroSusepApolice, 30, "Número SUSEP");
        ValidationUtils.requireMaxLength(certificadoCodigo, 60, "Certificado");
        ValidationUtils.requireExactLength(moedaApolice, 3, "Moeda");

        ValidationUtils.requireRange(indicadorExclusao, 1, 2, "Indicador de exclusão");
        ValidationUtils.requireRange(tipoDocumentoReferenciado, 1, 11, "Tipo de documento");
        ValidationUtils.requireRange(tipoEmissao, 1, 2, "Tipo de emissão");
        ValidationUtils.requirePositive(limiteMaximoGarantia, "Limite máximo de garantia");
        ValidationUtils.requirePositive(limiteMaximoGarantiaReal, "Limite máximo de garantia em reais");

        ValidationUtils.requirePastOrPresent(dataRegistro, "Data de registro");
        ValidationUtils.requirePastOrPresent(dataAlteracao, "Data de alteração");
        ValidationUtils.requirePastOrPresent(dataEmissao, "Data de emissão");
        ValidationUtils.requireAfterOrEqual(
            dataTermino,
            dataInicio,
            "Data de término deve ser maior ou igual à data de início"
        );

        ccgs = ccgs != null ? List.copyOf(ccgs) : List.of();
        segurados = segurados != null ? List.copyOf(segurados) : List.of();
        beneficiarios = beneficiarios != null ? List.copyOf(beneficiarios) : List.of();
        tomadores = tomadores != null ? List.copyOf(tomadores) : List.of();
        intermediarios = intermediarios != null ? List.copyOf(intermediarios) : List.of();
        objetosSegurados = objetosSegurados != null ? List.copyOf(objetosSegurados) : List.of();
        endossosAssociados = endossosAssociados != null ? List.copyOf(endossosAssociados) : List.of();
    }
}