package info.multithreading.examples;

import java.util.Random;

public class RaceConditionExample {

    int randInt;
    Random random = new Random(System.currentTimeMillis());

    public static void runTest() throws InterruptedException {

        final RaceConditionExample rc = new RaceConditionExample();
        Thread thread1 = new Thread(new Runnable() {

            public void run() {
                rc.printer();
            }
        });
        Thread thread2 = new Thread(new Runnable() {

            public void run() {
                rc.modifier();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    void modifier() {
        while (true) {
            randInt = random.nextInt(1000);
        }
    }

    void printer() {

        while (true) {
            if (randInt % 5 == 0) {
                System.out.println(randInt);
            }
        }
    }
}
