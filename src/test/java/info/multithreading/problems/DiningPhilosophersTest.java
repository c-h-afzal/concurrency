package info.multithreading.problems;

import org.junit.Test;

public class DiningPhilosophersTest {

    void startPhilosoper(DiningPhilosophers dp, int id) {
        try {
            dp.lifecycleOfPhilosopher(id);
        } catch (InterruptedException ie) {

        }
    }

    @Test
    // This is a perpetual test-case, because philosopher threads never die
    public void testDiningPhilosopher() throws InterruptedException {

        final DiningPhilosophers dp = new DiningPhilosophers();

        Thread p1 = new Thread(new Runnable() {

            public void run() {
                startPhilosoper(dp, 0);
            }
        });

        Thread p2 = new Thread(new Runnable() {

            public void run() {
                startPhilosoper(dp, 1);
            }
        });

        Thread p3 = new Thread(new Runnable() {

            public void run() {
                startPhilosoper(dp, 2);
            }
        });

        Thread p4 = new Thread(new Runnable() {

            public void run() {
                startPhilosoper(dp, 3);
            }
        });

        Thread p5 = new Thread(new Runnable() {

            public void run() {
                startPhilosoper(dp, 4);
            }
        });

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();

        p1.join();
        p2.join();
        p3.join();
        p4.join();
        p5.join();
    }
}
