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

## 🛠️ Soluções

### 1. synchronized
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

### 2. AtomicInteger

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


## 📊 Resultado

| Versão | Resultado       |
| ----- | --------------- |
| Sem controle | Inconsistente ❌ |
| synchronized | Correto ✅       |
| Atomic| Correto ✅       |

## ▶️ Como rodar

```bash
javac BrokenCounter.java && java BrokenCounter
javac FixedCounterSynchronized.java && java FixedCounterSynchronized
javac FixedCounterAtomic.java && java FixedCounterAtomic
```

## 💡 Conclusão

Concorrência sem controle de acesso a estado compartilhado pode gerar resultados imprevisíveis e bugs difíceis de reproduzir.
