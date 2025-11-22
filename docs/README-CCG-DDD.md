# CCG - Arquitetura DDD

## 📋 Visão Geral

Este documento define a arquitetura **Domain-Driven Design (DDD)** para o contexto **CCG (Contrato de Contragarantia)** no sistema SRO.

---

## 🎯 Bounded Context: CCG

### Definição

O **Bounded Context CCG** é responsável por gerenciar contratos de contragarantia entre seguradoras e resseguradores.

### Responsabilidades

1. ✅ **Gestão de Contratos**: Criar e gerenciar contratos de contragarantia
2. ✅ **Controle de Tomadores**: Gerenciar tomadores e seus limites aprovados
3. ✅ **Gestão de Colaterais**: Controlar garantias oferecidas
4. ✅ **Controle de Fiadores**: Gerenciar fiadores dos contratos
5. ✅ **Validação de Vigência**: Garantir períodos válidos

---

## 🏗️ Estrutura de Agregados

### Aggregate Root: Ccg

```
Ccg (Aggregate Root)
├── dataTermino: LocalDate
├── tomadors: List<Tomador> [1..N]
│   ├── documento: String
│   ├── tipoDocumento: Integer
│   ├── controladorGe: Integer
│   ├── razaoSocial: String
│   └── limiteAprovado: BigDecimal
├── colaterals: List<Colateral> [0..N]
│   ├── tipoAtivoColateral: Integer
│   ├── valorAtivoColateral: BigDecimal
│   ├── ufAtivoColateral: String
│   └── paisAtivoColateral: String
└── fiadors: List<Fiador> [0..N]
    ├── documento: String
    ├── tipoDocumento: Integer
    └── razaoSocial: String
```

---

## 📐 Invariantes do Domínio

### 1. Tomador Obrigatório
Todo CCG deve ter pelo menos um tomador.

### 2. Limite Positivo
O limite aprovado do tomador deve ser maior que zero.

### 3. Colateral Consistente
Se há colateral, tipo e valor devem estar preenchidos.

### 4. Documento Válido
Formato do documento deve corresponder ao tipo.

### 5. Vigência Válida
Data de término deve ser posterior à atual se preenchida.

---

## 🔄 Domain Events

### 1. CcgCriado
Disparado quando novo CCG é registrado.

### 2. TomadorAdicionado
Disparado quando tomador é adicionado ao CCG.

### 3. ColateralRegistrado
Disparado quando colateral é registrado.

---

## 🔗 Context Mapping

```
DOCUMENTO
    ↓ referencia
CCG
    ↓ eventos
RESSEGURO + CONTABILIDADE
```

---

**Gerado em:** 2025-11-22