package info.multithreading.examples;

public class NonReentrantLockExample {

    boolean isLocked;

    public NonReentrantLockExample() {
        isLocked = false;
    }

    public synchronized void lock() throws InterruptedException {

        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}
