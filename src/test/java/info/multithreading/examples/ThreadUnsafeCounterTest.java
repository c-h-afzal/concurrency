package info.multithreading.examples;

import org.junit.Test;

public class ThreadUnsafeCounterTest {

    @Test
    public void test() throws InterruptedException {
        (new ThreadUnsafeCounter()).example();
    }
}
