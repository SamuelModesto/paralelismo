package race_condition.solution_2;

public class SynchronizedBlockSolution {
    static int counter = 0;

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
            synchronized (SynchronizedBlockSolution.class) {
                counter++;
            }
        }
    }
}
