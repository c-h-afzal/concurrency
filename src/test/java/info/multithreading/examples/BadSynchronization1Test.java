package info.multithreading.examples;

import org.junit.Test;

public class BadSynchronization1Test {

    @Test(expected = IllegalMonitorStateException.class)
    public void test() throws InterruptedException {
        (new BadSynchronization1()).example();
    }
}
