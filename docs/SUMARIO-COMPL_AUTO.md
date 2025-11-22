# 📋 SUMÁRIO - COMPL_AUTO (Complemento Automóvel)

## 🎯 Visão Executiva

**Bounded Context:** COMPL_AUTO  
**Especificação:** SUSEP SRO v2.0.0 - Aba COMPL _AUTO  
**Pacote Java:** `io.github.wesleyosantos91.susep.sro.model.complauto`  
**Total de Classes:** 4  
**Total de Campos:** 49

---

## 🏗️ Estrutura do Aggregate

```
ComplAuto (Aggregate Root)
│
├── 18 campos principais
│   ├── numeroEndosso
│   ├── placaVeiculo
│   ├── chassis
│   ├── renavam
│   ├── anoFabricacao
│   ├── anoModelo
│   ├── marcaVeiculo
│   ├── modeloVeiculo
│   ├── combustivel
│   ├── categoria
│   ├── especieVeiculo
│   ├── tipoVeiculo
│   ├── importanciaSegurada
│   ├── premioLiquido
│   ├── comissao
│   ├── premioTotal
│   ├── indicadorVeiculoZeroKm
│   └── codigoFipe
│
├── List<CoberturaAutomóvel> (22 campos cada)
│   ├── numeroEndossoCo
│   ├── codigoCoberturaCiaAutomovel
│   ├── descricaoCoberturaCiaAutomovel
│   ├── especieCoberturaCob
│   ├── importanciaSeguradaCob
│   ├── franquiaValorCob
│   ├── premioLiquidoCob
│   └── ... (15 campos adicionais)
│
├── List<Franquia> (5 campos cada)
│   ├── numeroEndossoFra
│   ├── codigoCoberturaCiaAutomovelFra
│   ├── codigoTipoFranquia
│   ├── valorFranquia
│   └── percentualFranquia
│
└── List<PessoasAssociadasCondutor> (4 campos cada)
    ├── numeroEndossoCondPrincipal
    ├── codigoPessoaCondPrincipal
    ├── dataNascimentoCondutorPrincipal
    └── sexoCondutorPrincipal
```

---

## 📦 Classes Geradas

### 1. **ComplAuto** (Aggregate Root)
- **Tipo:** Java Record (imutável)
- **Campos:** 18 principais + 3 listas agregadas
- **Responsabilidade:** Raiz do aggregate, garante consistência transacional
- **Identificadores:** numeroEndosso, placaVeiculo, chassis

### 2. **CoberturaAutomóvel** (Value Object)
- **Tipo:** Java Record (imutável)
- **Campos:** 22
- **Responsabilidade:** Representa cobertura contratada com valores e condições
- **Relacionamento:** N coberturas por ComplAuto

### 3. **Franquia** (Value Object)
- **Tipo:** Java Record (imutável)
- **Campos:** 5
- **Responsabilidade:** Define valores de participação obrigatória em sinistros
- **Relacionamento:** Associada a coberturas específicas

### 4. **PessoasAssociadasCondutor** (Value Object)
- **Tipo:** Java Record (imutável)
- **Campos:** 4
- **Responsabilidade:** Dados de condutores autorizados
- **Relacionamento:** N condutores por ComplAuto

---

## 🎯 Bounded Context: COMPL_AUTO

### **Responsabilidades:**
✅ Gerenciar dados complementares de veículos segurados  
✅ Controlar coberturas específicas para automóveis  
✅ Definir franquias e participações  
✅ Registrar condutores autorizados  
✅ Calcular valores financeiros (prêmios, comissões)  

### **Não Inclui:**
❌ Cadastro de pessoas (Context: PESSOA)  
❌ Sinistros (Context: SINISTRO)  
❌ Apólice principal (Context: DOCUMENTO)  
❌ Pagamentos (Context: MOVIMENTO_PREMIO)  

---

## 🔄 Domain Events

| Evento | Quando Ocorre | Consumers |
|--------|---------------|-----------|
| `ComplAutoRegistrado` | Novo complemento criado | Análise de Risco, SUSEP |
| `CoberturaAdicionada` | Nova cobertura contratada | Precificação, Faturamento |
| `FranquiaDefinida` | Franquia configurada | Sinistros |
| `CondutorAutorizado` | Condutor adicionado | Análise de Risco |
| `VeiculoAlterado` | Dados alterados | SUSEP, Auditoria |

---

## 🛡️ Invariantes Críticas

1. ✅ Placa, chassi e RENAVAM obrigatórios
2. ✅ Chassi deve ter exatamente 17 caracteres (VIN)
3. ✅ RENAVAM deve ter exatamente 11 dígitos
4. ✅ Ano fabricação ≤ ano modelo ≤ ano fabricação + 1
5. ✅ Importância segurada > 0
6. ✅ Prêmio total = prêmio líquido + encargos
7. ✅ Cobertura deve ter código único
8. ✅ Franquia deve estar associada a cobertura existente
9. ✅ Condutor deve ser maior de 18 anos
10. ✅ Código FIPE deve ser válido

---

## 🔌 Integrações Externas

1. **Tabela FIPE** → Validação de código e valor de mercado
2. **DENATRAN/RENAVAM** → Validação de chassi e RENAVAM
3. **Cadastro de Pessoas** → Validação de condutores
4. **SUSEP** → Envio de complemento automóvel
5. **CEP/Endereço** → Validação de CEP de pernoite

---

## 🎯 Domain Services

### **1. PrecificacaoAutomovelService**
Cálculo de prêmio baseado em:
- Características do veículo
- Perfil do condutor (idade, sexo)
- CEP de pernoite
- Tipo de utilização

### **2. ValidacaoVeiculoService**
Validações de:
- Chassi (17 caracteres VIN)
- RENAVAM (11 dígitos)
- Placa (Mercosul ou antiga)
- Coerência de anos

### **3. GerenciamentoCoberturaService**
Gestão de:
- Adição/remoção de coberturas
- Definição de franquias
- Compatibilidade de coberturas

---

## 📊 Métricas de Negócio

| Métrica | Cálculo | Segmentação |
|---------|---------|-------------|
| Ticket Médio | média(premioTotal) | Marca, modelo, ano |
| Taxa Sinistralidade | sinistros / apólices | Condutor, região |
| Índice Renovação | renovações / vencidas | Marca, faixa etária |
| Cobertura Popular | count(cobertura) | Tipo |

---

## 🔗 Relacionamentos com Outros Contexts

```
DOCUMENTO → ENDOSSO → COMPL_AUTO
                          ↓
                    ┌─────┼─────┐
                    ↓     ↓     ↓
                 PESSOA  M.PREMIO  SINISTRO
```

---

## 📝 Casos de Uso Principais

### **UC1: Registrar Complemento Automóvel**
1. Validar dados do veículo
2. Adicionar coberturas
3. Definir franquias
4. Cadastrar condutores
5. Calcular prêmio
6. Emitir evento

### **UC2: Adicionar Cobertura**
1. Buscar ComplAuto
2. Validar compatibilidade
3. Adicionar cobertura
4. Definir franquia
5. Recalcular prêmio
6. Emitir evento

### **UC3: Autorizar Condutor**
1. Buscar ComplAuto
2. Validar maioridade
3. Verificar habilitação
4. Adicionar condutor
5. Recalcular risco
6. Ajustar prêmio

---

## 📚 Documentação Adicional

- **[README-COMPL_AUTO-CLASSES.md](README-COMPL_AUTO-CLASSES.md)** → Documentação detalhada das classes
- **[README-COMPL_AUTO-DDD.md](README-COMPL_AUTO-DDD.md)** → Análise completa de DDD

---

## 🎯 Princípios DDD Aplicados

| Princípio | Status | Descrição |
|-----------|--------|-----------|
| Ubiquitous Language | ✅ | Termos do domínio de seguros |
| Bounded Context | ✅ | Limite claro COMPL_AUTO |
| Aggregates | ✅ | ComplAuto como raiz |
| Value Objects | ✅ | Coberturas, franquias imutáveis |
| Domain Events | ✅ | Eventos de negócio |
| Repositories | ✅ | Abstração de persistência |
| Domain Services | ✅ | Lógica complexa isolada |
| Invariants | ✅ | Regras garantidas |

---

## 🔍 Glossário Rápido

| Termo | Significado |
|-------|-------------|
| **IS** | Importância Segurada (valor máximo indenizável) |
| **Chassi/VIN** | Número único mundial do veículo (17 caracteres) |
| **RENAVAM** | Registro Nacional de Veículo Automotor (11 dígitos) |
| **FIPE** | Tabela de preços médios de veículos |
| **Zero Km** | Veículo novo sem uso anterior |
| **Franquia** | Participação obrigatória do segurado em sinistro |
| **Casco** | Cobertura de danos ao próprio veículo |
| **Pernoite** | Local onde veículo permanece à noite |

---

## 📊 Estatísticas

- **Total de Classes:** 4
- **Total de Campos:** 49
- **Campos Principais:** 18
- **Grupos:** 3
- **Value Objects:** 3
- **Aggregate Roots:** 1
- **Domain Events:** 5
- **Domain Services:** 3
- **Invariantes:** 10

---

## ✅ Checklist de Implementação

- [x] Estrutura extraída do Excel
- [x] Classes Java geradas
- [x] JavaDoc completo
- [x] Aggregate Root identificado
- [x] Value Objects definidos
- [x] Invariantes documentadas
- [x] Domain Events mapeados
- [x] Domain Services especificados
- [x] Integrações identificadas
- [x] Casos de uso documentados
- [x] Métricas definidas
- [x] DDD completo aplicado

---

**Gerado em:** 2024  
**Especificação:** SUSEP SRO v2.0.0  
**Versão:** 1.0  
