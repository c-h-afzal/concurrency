package info.multithreading.problems;

import org.junit.Test;

import java.util.Random;

public class BarrierTest {

    static Random random = new Random(System.currentTimeMillis());

    @Test
    public void testBarrier() throws InterruptedException {
        final Barrier barrier = new Barrier(3);

        Thread p1 = new Thread(new Runnable() {

            public void run() {
                try {
                    System.out.println("Thread 1");
                    barrier.await();
                    System.out.println("Thread 1");
                    barrier.await();
                    System.out.println("Thread 1");
                    barrier.await();
                } catch (InterruptedException ie) {
                }
            }
        });

        Thread p2 = new Thread(new Runnable() {

            public void run() {
                try {
                    Thread.sleep(500);
                    System.out.println("Thread 2");
                    barrier.await();
                    Thread.sleep(500);
                    System.out.println("Thread 2");
                    barrier.await();
                    Thread.sleep(500);
                    System.out.println("Thread 2");
                    barrier.await();
                } catch (InterruptedException ie) {
                }
            }
        });

        Thread p3 = new Thread(new Runnable() {

            public void run() {
                try {
                    Thread.sleep(1500);
                    System.out.println("Thread 3");
                    barrier.await();
                    Thread.sleep(1500);
                    System.out.println("Thread 3");
                    barrier.await();
                    Thread.sleep(1500);
                    System.out.println("Thread 3");
                    barrier.await();
                } catch (InterruptedException ie) {
                }
            }
        });

        p1.start();
        p2.start();
        p3.start();

        p1.join();
        p2.join();
        p3.join();
    }

    @Test
    public void testBarrier2() throws InterruptedException {
        final Barrier barrier = new Barrier(4);

        Thread p1 = new Thread(new Runnable() {

            public void run() {
                try {
                    for (int i = 0; i < 500; i++) {
                        System.out.println("Thread 1");
                        barrier.await();
                        Thread.sleep(random.nextInt(101));
                    }
                } catch (InterruptedException ie) {
                }
            }
        });

        Thread p2 = new Thread(new Runnable() {

            public void run() {
                try {
                    for (int i = 0; i < 500; i++) {
                        System.out.println("Thread 2");
                        barrier.await();
                        Thread.sleep(random.nextInt(10));
                    }
                } catch (InterruptedException ie) {
                }
            }
        });

        Thread p3 = new Thread(new Runnable() {

            public void run() {
                try {
                    for (int i = 0; i < 500; i++) {
                        System.out.println("Thread 3");
                        barrier.await();
                        Thread.sleep(random.nextInt(201));
                    }
                } catch (InterruptedException ie) {
                }
            }
        });

        Thread p4 = new Thread(new Runnable() {

            public void run() {
                try {
                    for (int i = 0; i < 500; i++) {
                        System.out.println("Thread 4");
                        barrier.await();
                        Thread.sleep(random.nextInt(51));
                    }
                } catch (InterruptedException ie) {
                }
            }
        });

        p1.start();
        p2.start();
        p3.start();
        p4.start();

        p1.join();
        p2.join();
        p3.join();
        p4.join();
    }
}
