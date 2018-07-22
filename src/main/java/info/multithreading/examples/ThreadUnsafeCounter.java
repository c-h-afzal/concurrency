package info.multithreading.examples;

import java.util.Random;

class ThreadUnsafeCounter {

    // We'll use this to randomly sleep our threads
    static Random random = new Random(System.currentTimeMillis());

    int count = 0;

    public void decrement() {
        count--;
    }

    public void increment() {
        count++;
    }

    void printFinalCounterValue() {
        System.out.println("counter is: " + count);
    }

    public void example() throws InterruptedException {

        // create object of unsafe counter
        final ThreadUnsafeCounter badCounter = new ThreadUnsafeCounter();

        // setup thread1 to increment the badCounter 200 times
        Thread thread1 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 100; i++) {
                    badCounter.increment();
                    sleepRandomlyForLessThan10Secs();
                }
            }
        });

        // setup thread2 to decrement the badCounter 200 times
        Thread thread2 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 100; i++) {
                    badCounter.decrement();
                    sleepRandomlyForLessThan10Secs();
                }
            }
        });

        // run both threads
        thread1.start();
        thread2.start();

        // wait for t1 and t2 to complete.
        thread1.join();
        thread2.join();

        // print final value of counter
        badCounter.printFinalCounterValue();
    }

    private void sleepRandomlyForLessThan10Secs() {
        try {
            Thread.sleep(random.nextInt(10));
        } catch (InterruptedException ie) {
        }
    }

}

