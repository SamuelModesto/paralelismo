package dead_lock.solution_1;

public class DeadlockSolution {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> acquireLocks());
        Thread t2 = new Thread(() -> acquireLocks());

        t1.start();
        t2.start();
    }

    private static void acquireLocks() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + ": lock1");

            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + ": lock2");
            }
        }
    }
}