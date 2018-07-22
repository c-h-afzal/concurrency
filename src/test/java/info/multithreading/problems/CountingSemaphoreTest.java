package info.multithreading.problems;

import org.junit.Test;

public class CountingSemaphoreTest {

    @Test
    public void test() throws InterruptedException {

        final CountingSemaphore cs = new CountingSemaphore(1);

        Thread t1 = new Thread(new Runnable() {

            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        cs.acquire();
                        System.out.println("Ping " + i);
                        System.out.flush();
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        cs.release();
                        System.out.println("Pong " + i);
                        System.out.flush();
                    } catch (InterruptedException ie) {

                    }
                }
            }
        });

        t2.start();
        t1.start();
        t1.join();
        t2.join();
    }
}
