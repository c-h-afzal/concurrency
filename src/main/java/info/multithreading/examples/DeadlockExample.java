package info.multithreading.examples;

import java.util.concurrent.CountDownLatch;

public class DeadlockExample {

    CountDownLatch latch = new CountDownLatch(2);
    private int counter = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    Runnable incrementer = new Runnable() {

        //        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    incrementCounter();
                    System.out.println("Incrementing " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    };

    Runnable decrementer = new Runnable() {

        //        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                decrementCounter();
                System.out.println("Decrementing " + i);
            }
        }
    };

    void decrementCounter() {
        synchronized (lock2) {
            System.out.println("Acquired lock2");
            latch.countDown();
            synchronized (lock1) {
                counter--;
            }
        }
    }

    void incrementCounter() throws InterruptedException {
        synchronized (lock1) {
            latch.countDown();
            System.out.println("Acquired lock1");
            latch.await();
            synchronized (lock2) {
                counter++;
            }
        }
    }

    public void runTest() throws InterruptedException {

        Thread thread1 = new Thread(incrementer);
        Thread thread2 = new Thread(decrementer);

        thread1.start();
        // sleep to make sure thread 1 gets a chance to acquire lock1
        Thread.sleep(100);
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Done : " + counter);
    }
}
