# Deadlock

## 🧩 Problema

Duas ou mais threads ficam bloqueadas permanentemente esperando por recursos que nunca serão liberados.

## 💥 Comportamento

O programa trava e nunca termina.

## 🔬 Exemplo

* Thread 1 segura `lock1` e espera `lock2`
* Thread 2 segura `lock2` e espera `lock1`

Isso cria um ciclo de espera.

## ⚠️ Condições para deadlock

Para que um deadlock ocorra, todas as 4 condições abaixo precisam existir ao mesmo tempo:

### 1️⃣ Exclusão Mútua

Um recurso só pode ser acessado por uma thread por vez.

synchronized (lock1) {
// apenas uma thread pode executar aqui
}

👉 Isso é necessário para proteger dados, mas também cria contenção.

### 2️⃣ Hold and Wait (Segurar e Esperar)

Uma thread já possui um recurso e está esperando outro.

Thread-1 segura lock1
Thread-1 espera lock2

👉 A thread não libera o recurso que já possui.

### 3️⃣ Não Preempção

Um recurso não pode ser retirado à força de uma thread.

👉 Apenas a própria thread pode liberar o lock.

Thread-1 pegou lock1 → ninguém pode tirar dela

### 4️⃣ Espera Circular

Existe um ciclo de dependência entre threads.

Thread-1 → espera lock2
Thread-2 → espera lock1

👉 Isso cria um ciclo infinito.

---

## 🛠️ Soluções para evitar deadlocks

#### ✅ 1. Ordem consistente de locks (mais importante)

Sempre adquira locks na mesma ordem.

Correto:
lock1 → lock2

Errado:
Thread-1: lock1 → lock2
Thread-2: lock2 → lock1

👉 Isso elimina a espera circular.

---

#### ✅ 2. Evitar “segurar e esperar”

Não segure um recurso enquanto tenta obter outro.

Ruim:
segura lock1 → espera lock2

Melhor:
libera lock1 → tenta pegar ambos novamente

👉 Isso quebra a condição de hold and wait.

---

#### ✅ 3. Usar timeout com locks

Com locks mais avançados (como ReentrantLock), você pode tentar adquirir um lock com timeout.

👉 Isso evita bloqueio infinito.

---

#### ✅ 4. Reduzir número de locks

Menos recursos compartilhados → menos chance de deadlock.

👉 Simplificar o design reduz o risco.

---

#### ✅ 5. Usar estruturas de alto nível

Sempre que possível, prefira abstrações prontas:

ConcurrentHashMap
AtomicInteger
ExecutorService

👉 Elas já tratam concorrência internamente.

---

## 💡 Conclusão

Deadlocks são difíceis de detectar e podem travar sistemas inteiros.
A melhor estratégia é preveni-los com design cuidadoso.
