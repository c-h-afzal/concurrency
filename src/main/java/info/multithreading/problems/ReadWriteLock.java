package info.multithreading.problems;

public class ReadWriteLock {

    boolean isWriteLocked = false;
    int readers = 0;

    public synchronized void acquireReadLock() throws InterruptedException {

        while (isWriteLocked) {
            wait();
        }

        readers++;
    }

    public synchronized void acquireWriteLock() throws InterruptedException {

        while (isWriteLocked || readers != 0) {
            wait();
        }

        isWriteLocked = true;
    }

    public synchronized void releaseReadLock() {
        readers--;
        notify();
    }

    public synchronized void releaseWriteLock() {
        isWriteLocked = false;
        notify();
    }
}

