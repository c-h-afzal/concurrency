package info.multithreading.examples;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class InterruptExampleTest {

    @Test()
    public void test() throws InterruptedException {
        assertTrue((new InterruptExample()).example());
    }
}
