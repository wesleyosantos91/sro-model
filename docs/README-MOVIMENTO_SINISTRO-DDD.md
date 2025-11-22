# MOVIMENTO_SINISTRO - Arquitetura DDD

## 📋 Visão Geral

Movimentos financeiros de sinistros: pagamentos, recuperações, salvados e despesas.

## 🏗️ Aggregate Root: MovimentoSinistro

```
MovimentoSinistro
├── 16 campos principais
└── adicionais: List<Adicional> [0..N]
```

## 📐 Invariantes
1. Sinistro obrigatório
2. Valores positivos
3. Data dentro da vigência

## 🔄 Domain Events
1. MovimentoSinistroRegistrado
2. PagamentoEfetuado
3. RecuperacaoRegistrada

---
**Gerado em:** 2025-11-22