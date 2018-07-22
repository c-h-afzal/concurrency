package info.multithreading.problems;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class DiningPhilosophers2 {

    private static Random random = new Random(System.currentTimeMillis());

    private Semaphore[] forks = new Semaphore[5];

    public DiningPhilosophers2() {
        forks[0] = new Semaphore(1);
        forks[1] = new Semaphore(1);
        forks[2] = new Semaphore(1);
        forks[3] = new Semaphore(1);
        forks[4] = new Semaphore(1);
    }

    void acquireForkForRightHanded(int id) throws InterruptedException {
        forks[id].acquire();
        forks[(id + 1) % 5].acquire();
    }

    void acquireForkLeftHanded(int id) throws InterruptedException {
        forks[(id + 1) % 5].acquire();
        forks[id].acquire();
    }

    void contemplate() throws InterruptedException {
        Thread.sleep(random.nextInt(500));
    }

    void eat(int id) throws InterruptedException {

        if (id == 3) {
            acquireForkLeftHanded(3);

        } else {
            acquireForkForRightHanded(id);
        }

        System.out.println("Philosipher " + id + " is eating");
        forks[id].release();
        forks[(id + 1) % 5].release();
    }

    public void lifecycleOfPhilosopher(int id) throws InterruptedException {

        while (true) {
            contemplate();
            eat(id);
        }
    }
}
