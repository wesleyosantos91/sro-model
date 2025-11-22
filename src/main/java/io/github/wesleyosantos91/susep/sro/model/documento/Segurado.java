package io.github.wesleyosantos91.susep.sro.model.documento;

import io.github.wesleyosantos91.susep.sro.model.util.ValidationUtils;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * Record representando Segurado
 * <p>Tag: segurado</p>
 * <p>Gerado automaticamente a partir da especificação SRO v2.0.0</p>
 */
public record Segurado(

    /**
     * Documento de Identificação da Pessoa Associada
     * <p>Documento de identificação da pessoa associada à apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 40</p>
     */
    String documento,

    /**
     * Tipo de Documento da Pessoa Associada
     * <p>Tipo de documento da pessoa associada à apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> 1 - CPF 2 - CNPJ 3 - PASSAPORTE 99 - Outros</p>
     * <p><strong>Tamanho:</strong> 2</p>
     * <p><strong>Observação:</strong> Inclusão do domínio "3 - Passaporte" para equivalência com o dispositivo na Circular Susep 642. A utilização do domínio "99 - Outros" para legado anterior à vigência da Circular Susep 642, em 1° de ou</p>
     */
    Integer tipoDocumento,

    /**
     * Nome ou Razão Social da Pessoa Associada
     * <p>Nome ou razão social da pessoa associada à apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 144</p>
     */
    String nome,

    /**
     * Data Nascimento
     * <p>Data de Nascimento do segurado</p>
     * <p><strong>Cardinalidade:</strong> [0.1]</p>
     * <p><strong>Formato:</strong> AAAA-MM-DD</p>
     * <p><strong>Tamanho:</strong> 10</p>
     * <p><strong>Condição:</strong> 1) obrigatório quando cobertura do ramo habitacional; 2) obrigatório quando o tipo de documento pertencer aos domínios 8, 9 e 10</p>
     */
    LocalDate dataNascimento,

    /**
     * Sexo da Pessoa Associada
     * <p>Sexo do segurado ou participante</p>
     * <p><strong>Cardinalidade:</strong> [0..1]</p>
     * <p><strong>Formato:</strong> 1 - Feminino 2 - Masculino 3 - Não informado</p>
     * <p><strong>Tamanho:</strong> 2</p>
     * <p><strong>Condição:</strong> 1) obrigatório quando o tipo de documento pertencer aos domínios 8, 9 e 10</p>
     */
    Integer sexoSeguradoParticipante,

    /**
     * Código Postal da Pessoa Associada
     * <p>Código postal da pessoa associada à apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 30</p>
     */
    String codigoPostal,

    /**
     * Cidade da Pessoa Associada
     * <p>Cidade da pessoa associada à apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 100</p>
     * <p><strong>Observação:</strong> Por extenso</p>
     */
    String cidade,

    /**
     * Estado da Pessoa Associada
     * <p>Estado da pessoa associada à apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Tamanho:</strong> 50</p>
     * <p><strong>Observação:</strong> Por extenso</p>
     */
    String estado,

    /**
     * País da Pessoa Associada
     * <p>País da pessoa associada à apólice</p>
     * <p><strong>Cardinalidade:</strong> [1..1]</p>
     * <p><strong>Formato:</strong> Lista de Países: ISO 3166-1 alfa-3 </p>
     * <p><strong>Tamanho:</strong> 3</p>
     * <p><strong>Observação:</strong> Código de 3 letras conforme ISO 3166-1 alfa-3</p>
     */
    String pais
) {
    /**
     * Compact constructor com validações completas.
     */
    public Segurado {
        /* === VALIDAÇÕES DE OBRIGATORIEDADE === */
        Objects.requireNonNull(documento, "Documento é obrigatório");
        Objects.requireNonNull(tipoDocumento, "Tipo de documento é obrigatório");
        Objects.requireNonNull(nome, "Nome é obrigatório");
        Objects.requireNonNull(codigoPostal, "Código postal é obrigatório");
        Objects.requireNonNull(cidade, "Cidade é obrigatória");
        Objects.requireNonNull(estado, "Estado é obrigatório");
        Objects.requireNonNull(pais, "País é obrigatório");
        
        /* === VALIDAÇÕES DE DOMÍNIO === */
        
        /* Tipo de documento: 1 (CPF), 2 (CNPJ), 3 (Passaporte), 99 (Outros) */
        if (tipoDocumento < 1 || tipoDocumento > 99) {
            throw new IllegalArgumentException(
                "Tipo de documento deve ser 1 (CPF), 2 (CNPJ), 3 (Passaporte) ou 99 (Outros)"
            );
        }
        
        /* Sexo: 1 (Feminino), 2 (Masculino), 3 (Não informado) - opcional */
        ValidationUtils.requireRange(sexoSeguradoParticipante, 1, 3, "Sexo");
        
        /* === VALIDAÇÕES DE FORMATO === */
        
        /* Validação específica por tipo de documento */
        if (tipoDocumento == 1) { /* CPF */
            if (!ValidationUtils.isValidCPF(documento)) {
                throw new IllegalArgumentException("CPF inválido");
            }
        } else if (tipoDocumento == 2) { /* CNPJ */
            if (!ValidationUtils.isValidCNPJ(documento)) {
                throw new IllegalArgumentException("CNPJ inválido");
            }
        }
        
        /* País: formato ISO 3166-1 alpha-3 */
        if (!ValidationUtils.isValidPaisISO(pais)) {
            throw new IllegalArgumentException(
                "País deve estar no formato ISO 3166-1 alpha-3 (ex: BRA, USA)"
            );
        }
        
        /* === VALIDAÇÕES DE TAMANHO === */
        
        ValidationUtils.requireMaxLength(documento, 40, "Documento");
        ValidationUtils.requireMinLength(nome, 3, "Nome");
        ValidationUtils.requireMaxLength(nome, 144, "Nome");
        ValidationUtils.requireMaxLength(codigoPostal, 30, "Código postal");
        ValidationUtils.requireMaxLength(cidade, 100, "Cidade");
        ValidationUtils.requireMaxLength(estado, 50, "Estado");
        
        /* === VALIDAÇÕES DE DATA === */
        
        if (dataNascimento != null) {
            LocalDate hoje = LocalDate.now();
            
            /* Data de nascimento deve ser no passado */
            if (dataNascimento.isAfter(hoje)) {
                throw new IllegalArgumentException(
                    "Data de nascimento não pode ser futura"
                );
            }
            
            /* Idade deve ser razoável (0 a 150 anos) */
            int idade = Period.between(dataNascimento, hoje).getYears();
            if (idade < 0 || idade > 150) {
                throw new IllegalArgumentException(
                    "Data de nascimento resulta em idade inválida"
                );
            }
        }
    }
    
    /**
     * Retorna a idade do segurado em anos.
     * 
     * @return idade em anos, ou null se data de nascimento não informada
     */
    public Integer getIdade() {
        if (dataNascimento == null) return null;
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
    
    /**
     * Verifica se é maior de idade (18 anos).
     */
    public boolean isMaiorDeIdade() {
        Integer idade = getIdade();
        return idade != null && idade >= 18;
    }
}