package race_condition.solution_4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSolution {
    static int counter = 0;
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> increment());
        Thread t2 = new Thread(() -> increment());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter: " + counter);
    }

    static void increment() {
        for (int i = 0; i < 100000; i++) {
            lock.lock();
            try {
                counter++;
            } finally {
                lock.unlock();
            }
        }
    }
}
