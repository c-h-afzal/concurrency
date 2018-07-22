package info.multithreading.problems;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UnisexBathroom {

    static String WOMEN = "women";
    static String MEN = "men";
    static String NONE = "none";

    String inUseBy = NONE;
    int empsInBathroom = 0;
    ReentrantLock lock = new ReentrantLock();
    Condition cond = lock.newCondition();
    Semaphore maxEmps = new Semaphore(3);

    void femaleUseBathroom(String name) throws InterruptedException {

        lock.lock();
        while (inUseBy.equals(MEN)) {
            cond.await();
        }
        maxEmps.acquire();
        empsInBathroom++;
        inUseBy = WOMEN;
        lock.unlock();

        useBathroom(name);
        maxEmps.release();

        lock.lock();
        empsInBathroom--;

        if (empsInBathroom == 0)
            inUseBy = NONE;
        cond.signalAll();
        lock.unlock();
    }

    void maleUseBathroom(String name) throws InterruptedException {

        lock.lock();
        while (inUseBy.equals(WOMEN)) {
            cond.await();
        }
        maxEmps.acquire();
        empsInBathroom++;
        inUseBy = MEN;
        lock.unlock();

        useBathroom(name);
        maxEmps.release();

        lock.lock();
        empsInBathroom--;

        if (empsInBathroom == 0)
            inUseBy = NONE;
        cond.signalAll();
        lock.unlock();
    }

    void useBathroom(String name) throws InterruptedException {
        System.out.println(name + " using bathroom. Current employees in bathroom = " + empsInBathroom);
        Thread.sleep(10000);
        System.out.println(name + " done using bathroom");
    }
}
