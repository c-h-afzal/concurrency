package info.multithreading.problems;

import java.util.concurrent.atomic.AtomicInteger;

public class Barrier {

    int count = 0;
    int released = 0;
    int totalThreads;

    public Barrier(int totalThreads) {
        this.totalThreads = totalThreads;
    }

    public synchronized void await() throws InterruptedException {

        while (count == totalThreads)
            wait();

        count++;

        if (count == totalThreads) {
            notifyAll();
            released = totalThreads;
        } else {

            while (count < totalThreads)
                wait();
        }

        released--;
        if (released == 0) {
            count = 0;
            // remember to wakeup any threads
            // waiting on line
            notifyAll();
        }
    }
}
