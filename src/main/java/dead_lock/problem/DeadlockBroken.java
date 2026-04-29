package dead_lock.problem;

public class DeadlockBroken {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread-1: segurando lock1");

                sleep(1000);

                System.out.println("Thread-1: esperando lock2");
                synchronized (lock2) {
                    System.out.println("Thread-1: pegou lock2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread-2: segurando lock2");

                sleep(1000);

                System.out.println("Thread-2: esperando lock1");
                synchronized (lock1) {
                    System.out.println("Thread-2: pegou lock1");
                }
            }
        });

        t1.start();
        t2.start();
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}