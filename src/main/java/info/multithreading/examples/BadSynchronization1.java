package info.multithreading.examples;

public class BadSynchronization1 {

    public void example() throws InterruptedException {
        Object dummyObject = new Object();

        // Attempting to call wait() on the object
        // outside of a synchronized block.
        dummyObject.wait();
    }
}
