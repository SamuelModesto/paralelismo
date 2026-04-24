package race_condition.solution_1;

public class SynchronizedSolution {
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> increment());
        Thread t2 = new Thread(() -> increment());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Expected: 200000");
        System.out.println("Actual: " + counter);
    }

    static synchronized void increment() {
        for (int i = 0; i < 100000; i++) {
            counter++;
        }
    }
}
