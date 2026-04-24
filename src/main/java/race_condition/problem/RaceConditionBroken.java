package race_condition.problem;

public class RaceConditionBroken {

    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> increment());
        Thread t2 = new Thread(() -> increment());

        t1.start();
        t2.start();

        //A thread principal main, espera as threads t1 e t2 terminarem suas execuções antes de continuar.
        // isso nao resolve race condition, pois as duas podem acontecer ao mesmo tempo.
        t1.join();
        t2.join();

        System.out.println("Expected: 200000");
        System.out.println("Actual: " + counter);
    }

    static void increment() {
        for (int i = 0; i < 100000; i++) {
            counter++; // operação não atômica 👉 sao três passos separados. E entre eles, outra thread pode interferir.
        }
    }
}