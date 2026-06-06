# 🧵 Java Concurrency Lab

Repositório com experimentos práticos sobre **concorrência e paralelismo em Java**.

A ideia não é mostrar apenas “como usar threads”, mas sim **entender os problemas reais**, os **trade-offs** e quando concorrência **ajuda ou piora** a performance.

---

## 🎯 Objetivo

Este repositório foi criado para estudar, na prática:

* Por que sistemas concorrentes falham
* Como corrigir problemas clássicos
* Quando paralelismo melhora performance
* Quando concorrência piora tudo

---

## 🧠 Filosofia do repositório

Cada pasta representa um **problema real** que pode acontecer em sistemas concorrentes.

Dentro de cada pasta você vai encontrar:

* Uma implementação **quebrada** (mostrando o problema)
* Uma ou mais **soluções**
* Um `README.md` explicando:

  * o problema
  * por que acontece
  * como resolver
  * impacto na prática

---

## 📚 Ordem recomendada de estudo

Siga essa sequência — ela foi pensada para construir entendimento progressivo:

---

### 🟢 Fundamentos (entender os problemas)

1. **01-race-condition**
   → múltiplas threads alterando o mesmo estado
   → resultado inconsistente

2. **02-synchronization**
   → como controlar acesso ao estado compartilhado
   → uso de `synchronized`, `Lock`, `Atomic`

3. **03-deadlock**
   → threads travadas para sempre
   → problema de ordem de locks

---

### 🟡 Performance e trade-offs

4. **04-thread-overhead**
   → quando concorrência piora performance
   → custo de criação e gerenciamento de threads

5. **05-thread-pool**
   → por que não criar threads manualmente
   → uso de `ExecutorService`

---

### 🔵 Programação moderna

6. **06-futures**
   → como lidar com resultados assíncronos

7. **07-completable-future**
   → composição de tarefas assíncronas
   → estilo moderno de concorrência

---

### 🟣 Paralelismo na prática

8. **08-parallel-streams**
   → paralelismo com streams
   → vantagens e armadilhas

9. **09-cpu-vs-io-bound**
   → quando paralelismo realmente ajuda

---

### 🔴 Avançado (opcional)

10. **10-fork-join**
    → divisão eficiente de tarefas grandes

---

## ⚠️ O que você NÃO vai encontrar aqui

* Explicações superficiais de API
* Exemplos irreais
* Código sem contexto

---

## 💡 O que você VAI aprender

* Identificar uma race condition na prática
* Entender por que um sistema concorrente quebrou
* Saber quando usar (ou evitar) paralelismo
* Pensar como um engenheiro de sistemas, não só como programador

---

## 🚀 Como usar este repositório

1. Escolha um tópico na ordem recomendada
2. Leia o `README.md` da pasta do assunto
3. Execute o código quebrado
4. Observe o problema
5. Execute a solução
6. Compare os resultados

---
