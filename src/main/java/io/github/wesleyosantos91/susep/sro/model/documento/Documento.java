package io.github.wesleyosantos91.susep.sro.model.documento;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Record principal representando um Documento (Apólice) do SRO
 * <p>Sistema de Registro de Operações (SRO) - Versão 2.0.0</p>
 * <p>Este record contém todos os campos e blocos de informação de um documento de seguro.</p>
 * <p>Gerado automaticamente a partir da especificação SUSEP.</p>
 * 
 * @see Ccg
 * @see Segurado
 * @see Beneficiario
 * @see Tomador
 * @see Intermediario
 * @see ObjetoSegurado
 */
public record Documento(

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
     * Código Susep da seguradora
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
     * <p><strong>Condição:</strong> Campo obrigatório quando o registro for alterado.</p>
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
     * <p>Tipo de Documento Emitido</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> 1 - Apólice Individual 2 - Apólice Coletiva 3 - Bilhete 4 - Certificado 5 - Apólice Individual Autom...</p>
     * <p><strong>Tamanho:</strong> 2</p>
     * <p><strong>Condição:</strong> Para os domínios 4 e 7, validar o registro prévio da apólice coletiva por meio de um identificador da apólice já registrado</p>
     * <p><strong>Observação:</strong> Apólice do tipo 11 será utilizada para as operações de seguros de pessoas coletivas cujos segurados nãosejam identificados no momento da emissão, mas identificáveis pelas condições contratadas pelo Es</p>
     */
    Integer tipoDocumentoEmitido,

    /**
     * Identificador do Apólice, Bilhete, Contrato Coletivo ou Certificado de participante Individual
     * <p>Apólice, Bilhete, Contrato Coletivo ou Certificado de Participante Individual</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 60</p>
     * <p><strong>Observação:</strong> Identificador utilizado para as "entidades de 1º nível", a saber, apólices individuais , bilhetes ei certificados de participante individual de previdência; por sua vez, nas operações coletivas equiva</p>
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
     * <p>Identificador do certificado de seguro ou do certificado de participante coletivo de previdência</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Tamanho:</strong> 60</p>
     * <p><strong>Condição:</strong> Campo obrigatório somente para o Tipo de Documento = "4, 7 e 10".</p>
     * <p><strong>Observação:</strong> Identificador utilizado para as "entidades de 2º nível" exclusivas das operaçõs coletivas, a saber, certificados de seguro e certificados de participante coletivos de previdência.</p>
     */
    String certificadoCodigo,

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
     * <p>Data de emissão do documento</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     */
    LocalDate dataEmissao,

    /**
     * Data de Início de Vigência
     * <p>Data de início de vigência do documento</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     */
    LocalDate dataInicio,

    /**
     * Data de Fim de Vigência
     * <p>Data de fim de vigência do documento</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     */
    LocalDate dataTermino,

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
     * <p><strong>Condição:</strong> Campo obrigatório se moeda diferente de Real Campo não aceita valor negativo</p>
     * <p><strong>Observação:</strong> Quando não for definível o LMG preencher com 0,00</p>
     */
    Double limiteMaximoGarantiaReal,

    /**
     * Indicador de Cobertura Básica
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> 1 - Simples 2 - Ampla</p>
     * <p><strong>Tamanho:</strong> 1</p>
     * <p><strong>Condição:</strong> Campo obrigatório para apólices com coberturas do ramo Compreensivo Condomínio (0116)</p>
     */
    Integer coberturaBasica,

    /**
     * Lista de CCG (Contratos de Contragarantia)
     * <p><strong>Cardinalidade:</strong> [0..N]</p>
     */
    List<Ccg> ccgs,

    /**
     * Lista de Segurados
     * <p><strong>Cardinalidade:</strong> [0..N]</p>
     */
    List<Segurado> segurados,

    /**
     * Lista de Beneficiários
     * <p><strong>Cardinalidade:</strong> [0..N]</p>
     */
    List<Beneficiario> beneficiarios,

    /**
     * Lista de Tomadores/Garantidos
     * <p><strong>Cardinalidade:</strong> [0..N]</p>
     */
    List<Tomador> tomadores,

    /**
     * Lista de Intermediários
     * <p><strong>Cardinalidade:</strong> [0..N]</p>
     */
    List<Intermediario> intermediarios,

    /**
     * Lista de Objetos Segurados
     * <p><strong>Cardinalidade:</strong> [0..N]</p>
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
    /** Padrões regex pré-compilados (reutilizáveis, melhor performance). */
    private static final Pattern UUID_PATTERN = Pattern.compile(
        "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"
    );
    private static final Pattern MOEDA_PATTERN = Pattern.compile("^[A-Z]{3}$");
    
    /**
     * Compact constructor com todas as validações de negócio.
     * 
     * <p>Garante que apenas objetos válidos sejam criados (fail-fast).</p>
     * 
     * @throws NullPointerException se campos obrigatórios forem nulos
     * @throws IllegalArgumentException se validações de negócio falharem
     */
    public Documento {
        /* === VALIDAÇÕES DE OBRIGATORIEDADE === */
        Objects.requireNonNull(uuid, "UUID é obrigatório");
        Objects.requireNonNull(codigoSeguradora, "Código da seguradora é obrigatório");
        Objects.requireNonNull(dataRegistro, "Data de registro é obrigatória");
        Objects.requireNonNull(dataAlteracao, "Data de alteração é obrigatória");
        Objects.requireNonNull(indicadorExclusao, "Indicador de exclusão é obrigatório");
        Objects.requireNonNull(tipoDocumentoEmitido, "Tipo de documento é obrigatório");
        Objects.requireNonNull(apoliceCodigo, "Código da apólice é obrigatório");
        Objects.requireNonNull(tipoEmissao, "Tipo de emissão é obrigatório");
        Objects.requireNonNull(dataEmissao, "Data de emissão é obrigatória");
        Objects.requireNonNull(dataInicio, "Data de início é obrigatória");
        Objects.requireNonNull(dataTermino, "Data de término é obrigatória");
        Objects.requireNonNull(codigoFilial, "Código da filial é obrigatório");
        Objects.requireNonNull(moedaApolice, "Moeda é obrigatória");
        Objects.requireNonNull(limiteMaximoGarantia, "Limite máximo de garantia é obrigatório");
        Objects.requireNonNull(limiteMaximoGarantiaReal, "Limite máximo de garantia em reais é obrigatório");
        
        /* === VALIDAÇÕES DE FORMATO === */
        
        /* UUID: formato padrão UUID v4 */
        if (!UUID_PATTERN.matcher(uuid.toLowerCase()).matches()) {
            throw new IllegalArgumentException(
                "UUID deve estar no formato: 12345678-1234-1234-1234-123456789abc"
            );
        }
        
        /* Código da seguradora: exatamente 5 caracteres */
        if (codigoSeguradora.length() != 5) {
            throw new IllegalArgumentException(
                "Código da seguradora deve ter exatamente 5 caracteres"
            );
        }
        
        /* Código da filial: exatamente 4 caracteres */
        if (codigoFilial.length() != 4) {
            throw new IllegalArgumentException(
                "Código da filial deve ter exatamente 4 caracteres"
            );
        }
        
        /* Moeda: formato ISO 4217 (3 letras maiúsculas) */
        if (!MOEDA_PATTERN.matcher(moedaApolice).matches()) {
            throw new IllegalArgumentException(
                "Moeda deve estar no formato ISO 4217 (ex: BRL, USD, EUR)"
            );
        }
        
        /* === VALIDAÇÕES DE TAMANHO === */
        
        if (anotacao != null && anotacao.length() > 500) {
            throw new IllegalArgumentException(
                "Anotação deve ter no máximo 500 caracteres"
            );
        }
        
        if (apoliceCodigo.length() > 60) {
            throw new IllegalArgumentException(
                "Código da apólice deve ter no máximo 60 caracteres"
            );
        }
        
        if (numeroSusepApolice != null && numeroSusepApolice.length() > 30) {
            throw new IllegalArgumentException(
                "Número SUSEP deve ter no máximo 30 caracteres"
            );
        }
        
        if (certificadoCodigo != null && certificadoCodigo.length() > 60) {
            throw new IllegalArgumentException(
                "Código do certificado deve ter no máximo 60 caracteres"
            );
        }
        
        if (codigoSeguradoraLider != null && codigoSeguradoraLider.length() != 5) {
            throw new IllegalArgumentException(
                "Código da seguradora líder deve ter exatamente 5 caracteres"
            );
        }
        
        if (apoliceCodigoLider != null && apoliceCodigoLider.length() > 60) {
            throw new IllegalArgumentException(
                "Código da apólice líder deve ter no máximo 60 caracteres"
            );
        }
        
        /* === VALIDAÇÕES DE RANGE/DOMÍNIO === */
        
        /* Indicador de exclusão: 1 (Sim) ou 2 (Não) */
        if (indicadorExclusao < 1 || indicadorExclusao > 2) {
            throw new IllegalArgumentException(
                "Indicador de exclusão deve ser 1 (Sim) ou 2 (Não)"
            );
        }
        
        /* Tipo de documento: 1 a 11 */
        if (tipoDocumentoEmitido < 1 || tipoDocumentoEmitido > 11) {
            throw new IllegalArgumentException(
                "Tipo de documento deve estar entre 1 e 11"
            );
        }
        
        /* Tipo de emissão: 1 (Própria) ou 2 (Cosseguro Aceito) */
        if (tipoEmissao < 1 || tipoEmissao > 2) {
            throw new IllegalArgumentException(
                "Tipo de emissão deve ser 1 (Própria) ou 2 (Cosseguro Aceito)"
            );
        }
        
        /* Cobertura básica: 1 (Simples) ou 2 (Ampla) - se informado */
        if (coberturaBasica != null && (coberturaBasica < 1 || coberturaBasica > 2)) {
            throw new IllegalArgumentException(
                "Cobertura básica deve ser 1 (Simples) ou 2 (Ampla)"
            );
        }
        
        /* Valores não negativos */
        if (limiteMaximoGarantia < 0) {
            throw new IllegalArgumentException(
                "Limite máximo de garantia não pode ser negativo"
            );
        }
        
        if (limiteMaximoGarantiaReal < 0) {
            throw new IllegalArgumentException(
                "Limite máximo de garantia em reais não pode ser negativo"
            );
        }
        
        /* === VALIDAÇÕES DE DATAS === */
        
        LocalDate hoje = LocalDate.now();
        
        /* Data de registro não pode ser futura */
        if (dataRegistro.isAfter(hoje)) {
            throw new IllegalArgumentException(
                "Data de registro não pode ser futura"
            );
        }
        
        /* Data de alteração não pode ser futura */
        if (dataAlteracao.isAfter(hoje)) {
            throw new IllegalArgumentException(
                "Data de alteração não pode ser futura"
            );
        }
        
        /* Data de emissão não pode ser futura */
        if (dataEmissao.isAfter(hoje)) {
            throw new IllegalArgumentException(
                "Data de emissão não pode ser futura"
            );
        }
        
        /* Data de término deve ser >= data de início */
        if (dataTermino.isBefore(dataInicio)) {
            throw new IllegalArgumentException(
                "Data de término deve ser maior ou igual à data de início"
            );
        }
        
        /* === VALIDAÇÕES CONDICIONAIS === */
        
        /* Certificado obrigatório para tipos 4, 7 e 10 */
        if ((tipoDocumentoEmitido == 4 || tipoDocumentoEmitido == 7 || tipoDocumentoEmitido == 10)
            && (certificadoCodigo == null || certificadoCodigo.isBlank())) {
            throw new IllegalArgumentException(
                "Certificado é obrigatório para tipos de documento 4, 7 e 10"
            );
        }
        
        /* Cosseguro aceito requer seguradora líder */
        if (tipoEmissao == 2) { /* Cosseguro Aceito */
            if (codigoSeguradoraLider == null || codigoSeguradoraLider.isBlank()) {
                throw new IllegalArgumentException(
                    "Código da seguradora líder é obrigatório para cosseguro aceito"
                );
            }
            if (apoliceCodigoLider == null || apoliceCodigoLider.isBlank()) {
                throw new IllegalArgumentException(
                    "Código da apólice líder é obrigatório para cosseguro aceito"
                );
            }
        }
        
        /* === IMUTABILIDADE DAS LISTAS (defensive copy) === */
        
        ccgs = ccgs != null ? List.copyOf(ccgs) : List.of();
        segurados = segurados != null ? List.copyOf(segurados) : List.of();
        beneficiarios = beneficiarios != null ? List.copyOf(beneficiarios) : List.of();
        tomadores = tomadores != null ? List.copyOf(tomadores) : List.of();
        intermediarios = intermediarios != null ? List.copyOf(intermediarios) : List.of();
        objetosSegurados = objetosSegurados != null ? List.copyOf(objetosSegurados) : List.of();
    }
}