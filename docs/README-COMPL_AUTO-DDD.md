# COMPL_AUTO - Análise Domain-Driven Design (DDD)

## 🎯 Visão Geral do Domínio

O contexto **COMPL_AUTO** (Complemento Automóvel) gerencia informações detalhadas sobre veículos segurados, incluindo identificação, características técnicas, coberturas contratadas e condutores autorizados no sistema SUSEP SRO.

---

## 🏛️ Bounded Context

### **COMPL_AUTO (Complemento Automóvel)**

**Responsabilidade:**
Gerenciar dados complementares específicos de seguros de veículos automotores, incluindo características do veículo, coberturas contratadas, franquias aplicáveis e informações de condutores.

**Limites do Contexto:**
- Dados técnicos e identificação de veículos
- Coberturas específicas para automóveis
- Franquias e participações obrigatórias
- Relação de condutores autorizados
- Valores financeiros (prêmios, comissões, importâncias seguradas)

**Não Inclui:**
- Dados cadastrais do segurado (Bounded Context PESSOA)
- Sinistros e movimentações (Bounded Context SINISTRO)
- Apólice principal (Bounded Context DOCUMENTO)
- Prêmios e pagamentos (Bounded Context MOVIMENTO_PREMIO)

---

## 📦 Agregados (Aggregates)

### **Aggregate: ComplAuto**

**Aggregate Root:** `ComplAuto`

**Entidades:**
- `ComplAuto` (Root)

**Value Objects:**
- `CoberturaAutomóvel` - Cobertura de seguro
- `Franquia` - Condições de franquia
- `PessoasAssociadasCondutor` - Dados do condutor

**Invariantes:**
1. Um ComplAuto deve estar associado a um endosso válido
2. A placa do veículo deve seguir padrão Mercosul ou antigo
3. Chassi deve ter 17 caracteres (padrão VIN)
4. RENAVAM deve ter 11 dígitos
5. Ano de fabricação não pode ser maior que ano do modelo
6. Prêmio total deve ser igual a prêmio líquido + IOF + demais encargos
7. Cada cobertura deve ter código único dentro do aggregate
8. Franquias devem estar associadas a coberturas existentes
9. Condutor principal deve ser maior de 18 anos
10. Importância segurada deve ser maior que zero

**Regras de Negócio:**
- Veículos zero km têm tratamento especial (desconto, cobertura)
- Coberturas podem ter franquia obrigatória ou facultativa
- Prêmio varia conforme perfil do condutor (idade, sexo)
- CEP de pernoite influencia cálculo do prêmio
- Tipo de utilização do veículo (particular/comercial) afeta risco

---

## 🎭 Entidades vs Value Objects

### **Entidade: ComplAuto**
**Por quê é Entidade?**
- Possui identidade única (numeroEndosso + placaVeiculo + chassi)
- Ciclo de vida próprio (criação → alteração → cancelamento)
- Histórico de mudanças ao longo do tempo
- Mutabilidade controlada via endossos

**Identificadores:**
- `numeroEndosso` - Identifica a versão do complemento
- `placaVeiculo` - Identificação oficial do veículo
- `chassis` - Número único mundial (VIN)
- `renavam` - Registro nacional do veículo

---

### **Value Object: CoberturaAutomóvel**
**Por quê é Value Object?**
- Identificado pelos seus atributos, não por ID
- Imutável
- Substituível (nova cobertura substitui antiga)
- Sem histórico independente

**Características:**
- Representa uma cobertura contratada
- Contém valores financeiros (prêmio, IS, franquia)
- Associada a fornecedores e equipamentos
- 22 atributos descritivos

---

### **Value Object: Franquia**
**Por quê é Value Object?**
- Valor monetário ou percentual (sem identidade)
- Imutável
- Substituível
- Dependente da cobertura

**Características:**
- Pode ser valor fixo ou percentual
- Associada a uma cobertura específica
- Sem ciclo de vida independente

---

### **Value Object: PessoasAssociadasCondutor**
**Por quê é Value Object?**
- Representa snapshot de dados do condutor
- Imutável (mudanças geram novo objeto)
- Identificado por atributos, não por ID próprio
- Dependente do ComplAuto

**Características:**
- Código de pessoa (referência ao cadastro)
- Data de nascimento e sexo
- Dados para cálculo de risco

---

## 🔄 Domain Events

### **Eventos Gerados:**

1. **`ComplAutoRegistrado`**
   - Quando: Novo complemento criado
   - Dados: numeroEndosso, placaVeiculo, chassis, dataCriacao
   - Consumers: Módulo de Análise de Risco, Integração SUSEP

2. **`CoberturaAdicionada`**
   - Quando: Nova cobertura adicionada ao veículo
   - Dados: codigoCobertura, importanciaSegurada, premioLiquido
   - Consumers: Módulo de Precificação, Faturamento

3. **`FranquiaDefinida`**
   - Quando: Franquia configurada para cobertura
   - Dados: codigoCobertura, valorFranquia, percentualFranquia
   - Consumers: Módulo de Sinistros

4. **`CondutorAutorizado`**
   - Quando: Novo condutor adicionado
   - Dados: codigoPessoa, dataNascimento, sexo
   - Consumers: Módulo de Análise de Risco

5. **`VeiculoAlterado`**
   - Quando: Dados do veículo são modificados
   - Dados: numeroEndosso, camposAlterados
   - Consumers: Integração SUSEP, Auditoria

---

## 🔌 Integrações (Anti-Corruption Layer)

### **Integrações Necessárias:**

1. **Tabela FIPE**
   - Validação de código FIPE
   - Consulta de valor de mercado
   - Atualização periódica de valores

2. **DENATRAN/RENAVAM**
   - Validação de chassi e RENAVAM
   - Consulta de histórico do veículo
   - Verificação de débitos

3. **Cadastro de Pessoas**
   - Validação de condutores
   - Consulta de habilitação
   - Histórico de sinistros do condutor

4. **SUSEP**
   - Envio de complemento automóvel
   - Validação de códigos de ramo
   - Conformidade regulatória

5. **CEP/Endereço**
   - Validação de CEP de pernoite
   - Cálculo de risco geográfico

---

## 📊 Repositórios

### **`ComplAutoRepository`**

**Operações:**
```java
public interface ComplAutoRepository {
    // Consultas
    Optional<ComplAuto> findByEndosso(String numeroEndosso);
    Optional<ComplAuto> findByPlaca(String placaVeiculo);
    Optional<ComplAuto> findByChassis(String chassis);
    List<ComplAuto> findByPeriodo(LocalDate inicio, LocalDate fim);
    
    // Comandos
    ComplAuto save(ComplAuto complAuto);
    void delete(String numeroEndosso);
    
    // Consultas Especializadas
    List<ComplAuto> findVeiculosZeroKm();
    List<ComplAuto> findByMarcaModelo(String marca, String modelo);
    BigDecimal calcularPremioTotal(String numeroEndosso);
}
```

---

## 🎯 Domain Services

### **1. PrecificacaoAutomovelService**

**Responsabilidade:** Cálculo de prêmio baseado em características do veículo e condutor.

**Métodos:**
- `calcularPremio(ComplAuto complAuto): BigDecimal`
- `aplicarDescontoZeroKm(BigDecimal premio, boolean isZeroKm): BigDecimal`
- `calcularFatorPernoite(String cepPernoite): BigDecimal`
- `calcularFatorCondutor(PessoasAssociadasCondutor condutor): BigDecimal`

**Regras:**
- Veículo zero km: desconto de 5-15%
- Idade do condutor: maior risco < 25 anos e > 65 anos
- CEP de pernoite: áreas urbanas têm maior risco
- Tipo de utilização: comercial tem acréscimo de 20-30%

---

### **2. ValidacaoVeiculoService**

**Responsabilidade:** Validações específicas de dados do veículo.

**Métodos:**
- `validarChassis(String chassis): ValidationResult`
- `validarRenavam(String renavam): ValidationResult`
- `validarPlaca(String placa): ValidationResult`
- `verificarCoerenciaAnoFabricacaoModelo(int anoFab, int anoMod): boolean`

**Regras:**
- Chassi: 17 caracteres alfanuméricos (padrão VIN)
- RENAVAM: 11 dígitos numéricos com dígito verificador
- Placa: Mercosul (XXX9X99) ou antiga (XXX-9999)
- Ano fabricação ≤ ano modelo ≤ ano fabricação + 1

---

### **3. GerenciamentoCoberturaService**

**Responsabilidade:** Gestão de coberturas e franquias.

**Métodos:**
- `adicionarCobertura(ComplAuto complAuto, CoberturaAutomóvel cobertura): ComplAuto`
- `removerCobertura(ComplAuto complAuto, String codigoCobertura): ComplAuto`
- `definirFranquia(String codigoCobertura, Franquia franquia): void`
- `validarCompatibilidadeCoberturas(List<CoberturaAutomóvel> coberturas): boolean`

**Regras:**
- Coberturas conflitantes não podem coexistir
- Franquia obrigatória para coberturas de casco
- Prêmio mínimo técnico deve ser respeitado

---

## 🛡️ Validações e Invariantes

### **ComplAuto**
```java
public record ComplAuto(...) {
    public ComplAuto {
        requireNonNull(numeroEndosso, "Número do endosso obrigatório");
        requireNonNull(placaVeiculo, "Placa obrigatória");
        requireNonNull(chassis, "Chassi obrigatório");
        
        if (chassis.length() != 17) {
            throw new IllegalArgumentException("Chassi deve ter 17 caracteres");
        }
        
        if (renavam != null && renavam.length() != 11) {
            throw new IllegalArgumentException("RENAVAM deve ter 11 dígitos");
        }
        
        if (anoFabricacao > anoModelo || anoModelo > anoFabricacao + 1) {
            throw new IllegalArgumentException("Anos inconsistentes");
        }
        
        if (importanciaSegurada != null && importanciaSegurada.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Importância segurada deve ser maior que zero");
        }
    }
}
```

---

## 📈 Métricas e KPIs

### **Métricas de Negócio:**
1. **Ticket Médio por Veículo**
   - Cálculo: média(premioTotal)
   - Segmentação: por marca, modelo, ano

2. **Taxa de Sinistralidade**
   - Cálculo: sinistros / apólices vigentes
   - Segmentação: por perfil de condutor, região

3. **Índice de Renovação**
   - Cálculo: renovações / apólices vencidas
   - Segmentação: por marca, faixa etária

4. **Cobertura Mais Contratada**
   - Cálculo: count(cobertura) group by tipo
   - Análise de preferências

---

## 🔗 Relacionamentos com Outros Contexts

```
┌─────────────────┐
│   DOCUMENTO     │ ──▶ Apólice principal
└─────────────────┘
        │
        ▼
┌─────────────────┐
│   ENDOSSO       │ ──▶ Alterações na apólice
└─────────────────┘
        │
        ▼
┌─────────────────┐
│   COMPL_AUTO    │ ◀── VOCÊ ESTÁ AQUI
└─────────────────┘
        │
        ├──▶ ┌─────────────────┐
        │    │   PESSOA        │ (Condutores)
        │    └─────────────────┘
        │
        ├──▶ ┌─────────────────┐
        │    │ MOVIMENTO_PREMIO│ (Pagamentos)
        │    └─────────────────┘
        │
        └──▶ ┌─────────────────┐
             │   SINISTRO      │ (Reclamações)
             └─────────────────┘
```

---

## 🚀 Casos de Uso

### **UC1: Registrar Complemento Automóvel**
**Ator:** Sistema de Emissão  
**Pré-condições:** Endosso criado, veículo validado  
**Fluxo:**
1. Validar dados do veículo (placa, chassi, RENAVAM)
2. Verificar código FIPE
3. Adicionar coberturas contratadas
4. Definir franquias
5. Cadastrar condutores autorizados
6. Calcular prêmio total
7. Persistir ComplAuto
8. Emitir evento `ComplAutoRegistrado`

---

### **UC2: Adicionar Cobertura**
**Ator:** Corretor  
**Pré-condições:** ComplAuto existente  
**Fluxo:**
1. Buscar ComplAuto por endosso
2. Validar compatibilidade da nova cobertura
3. Adicionar CoberturaAutomóvel
4. Definir franquia (se obrigatória)
5. Recalcular prêmio
6. Persistir alteração
7. Emitir evento `CoberturaAdicionada`

---

### **UC3: Autorizar Condutor**
**Ator:** Segurado  
**Pré-condições:** ComplAuto existente, condutor cadastrado  
**Fluxo:**
1. Buscar ComplAuto
2. Validar maioridade do condutor
3. Verificar habilitação válida
4. Adicionar PessoasAssociadasCondutor
5. Recalcular fator de risco
6. Ajustar prêmio (se necessário)
7. Persistir alteração
8. Emitir evento `CondutorAutorizado`

---

## 📚 Glossário do Domínio

- **Importância Segurada (IS):** Valor máximo indenizável em caso de sinistro
- **Prêmio Líquido:** Valor do seguro sem impostos e taxas
- **Franquia:** Valor/percentual de participação obrigatória em sinistro
- **Chassi/VIN:** Número de identificação único mundial do veículo
- **RENAVAM:** Registro Nacional de Veículo Automotor
- **Código FIPE:** Identificador da tabela FIPE de preços
- **Veículo Zero Km:** Veículo novo, sem uso anterior
- **Pernoite:** Local onde o veículo permanece durante a noite
- **Condutor Principal:** Motorista habitual do veículo
- **Casco:** Cobertura de danos ao próprio veículo segurado

---

## 🎯 Princípios DDD Aplicados

✅ **Ubiquitous Language:** Termos do domínio de seguros  
✅ **Bounded Context:** Limite claro do contexto COMPL_AUTO  
✅ **Aggregates:** ComplAuto como raiz de consistência  
✅ **Value Objects:** Coberturas, franquias, condutores imutáveis  
✅ **Domain Events:** Eventos de negócio publicados  
✅ **Repositories:** Abstração de persistência  
✅ **Domain Services:** Lógica de negócio complexa  
✅ **Invariants:** Regras de consistência garantidas  

---

**Versão:** 1.0  
**Especificação:** SUSEP SRO v2.0.0  
**Bounded Context:** COMPL_AUTO  
