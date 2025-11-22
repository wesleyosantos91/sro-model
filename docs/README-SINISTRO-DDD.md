# SINISTRO - Arquitetura DDD

## 📋 Visão Geral

Registro e gestão de sinistros incluindo justificativas, documentos, coberturas e dados específicos.

## 🏗️ Aggregate Root: Sinistro

```
Sinistro
├── 6 campos principais
├── justificativasNegativas: List<JustificativaNegativa>
├── documentoAfetados: List<DocumentoAfetado>
├── coberturaAfetadas: List<CoberturaAfetada>
├── vistoriaRurals: List<VistoriaRural>
└── automovels: List<Automovel>
```

## 📐 Invariantes
1. Documento obrigatório
2. Data aviso válida
3. Cobertura consistente

## 🔄 Domain Events
1. SinistroAvisado
2. SinistroRegulado
3. SinistroNegado

---
**Gerado em:** 2025-11-22