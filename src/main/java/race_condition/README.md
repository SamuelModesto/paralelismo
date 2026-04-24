# Race Condition

## 🧩 Problema

Múltiplas threads acessam e modificam uma variável compartilhada sem qualquer controle de sincronização.

## 💥 Comportamento esperado

O contador deveria ser:

```
200000
```

## ❌ Comportamento real

O valor varia a cada execução:

```
163482
178921
192003
```

## 🔬 Por que isso acontece?

A operação:

```java
counter++;
```

não é atômica. Ela é composta por três etapas:

1. Ler o valor
2. Incrementar
3. Escrever de volta

Quando duas threads executam isso ao mesmo tempo, ocorre uma **condição de corrida**.
```
Thread 1: lê 5
Thread 2: lê 5

Thread 1: calcula 6
Thread 2: calcula 6

Thread 1: escreve 6
Thread 2: escreve 6
```

👉 resultado final: 6

👉 esperado: 7

💣 você perdeu uma atualização pois elas nao sincronizaram o acesso ao contador.

---

## 🛠️Soluções

### 1. synchronized method
Método inteiro sincronizado

o synchronized garante:
- exclusão mútua.
- só uma thread entra no método por vez.

Visualmente:
```
Thread 1 entra → executa tudo
Thread 2 espera → bloqueada
Thread 1 sai → libera
Thread 2 entra
```
```
Thread 1 pega o lock
→ faz 100k incrementos
→ libera
```
Thread 2 só entra depois

- o counter++ continua não sendo atômico.
- mas agora ele está protegido.
---
### 2. synchronized block
Bloco sincronizado dentro do loop

```
Thread 1 pega lock → incrementa → solta
Thread 2 pega lock → incrementa → solta
Thread 1 pega lock → incrementa → solta
...
```
threads se alternam o tempo todo.

---
### 3. AtomicInteger

Utiliza operações atômicas sem necessidade de lock explícito

Diferente de:
```
counter++;
```
O AtomicInteger usa um mecanismo chamado Compare-And-Swap (CAS), ele faz algo assim:
```
se valor atual == esperado:
    atualiza
senão:
    tenta de novo
```
Visualmente:
```
Thread 1 tenta: valor = 5 → vira 6 ✔
Thread 2 tenta: valor esperado era 5 ❌ (já mudou)
Thread 2 tenta de novo → lê 6 → vira 7 ✔
```
Dessa forma ninguém perde atualização.

### Vantagem vs synchronized

#### **synchronized**
- bloqueia outras threads
- só uma executa por vez

#### **AtomicInteger**
- não bloqueia (lock-free)
- mais performático em muitos casos

### Mas nem tudo são flores
AtomicInteger é ótimo para:
- operações simples (incremento, soma, etc.)

Mas NÃO resolve bem:
- múltiplas variáveis dependentes
- operações complexas (ex: transferir dinheiro entre contas)
---
### 4. Lock
Aqui entra um conceito novo o **ReentrantLock**.

ReentrantLock é uma classe da biblioteca de concorrência do Java (java.util.concurrent.locks) 
que fornece um mecanismo de bloqueio explícito e reentrante.
Ela permite que uma mesma thread adquira o bloqueio múltiplas vezes sem causar deadlock, 
oferecendo controle mais detalhado que a palavra-chave synchronized.

O que ele te dá:
- mais controle que synchronized
- pode tentar lock (tryLock)
- pode ter timeout
- mais usado em sistemas reais

---

## 📊 Resumo das abordagens

| Abordagem                | Facilidade | Controle | Performance | Bloqueio          | Uso ideal               |
| ------------------------ | ---------- | -------- | ----------- | ----------------- | ----------------------- |
| `synchronized` (método)  | ⭐⭐⭐⭐       | ⭐        | 😐 Média    | Sim (lock)        | Casos simples           |
| `synchronized` (bloco)   | ⭐⭐⭐        | ⭐⭐       | 😐 Média    | Sim (lock)        | Escopo mais controlado  |
| `Lock` (`ReentrantLock`) | ⭐⭐         | ⭐⭐⭐⭐     | 🙂 Boa      | Sim (lock)        | Cenários mais complexos |
| `AtomicInteger`          | ⭐⭐⭐⭐       | ⭐        | 🚀 Alta     | ❌ Não (lock-free) | Operações simples       |

---
## ▶️ Como rodar

```bash
javac BrokenCounter.java && java BrokenCounter
javac FixedCounterSynchronized.java && java FixedCounterSynchronized
javac FixedCounterAtomic.java && java FixedCounterAtomic
```

## 💡 Conclusão

Concorrência sem controle de acesso a estado compartilhado pode gerar resultados imprevisíveis e bugs difíceis de reproduzir.
