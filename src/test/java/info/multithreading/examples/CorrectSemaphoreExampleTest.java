package info.multithreading.examples;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CorrectSemaphoreExampleTest {

    @Test()
    public void test() throws InterruptedException {
        assertTrue((new CorrectSemaphoreExample()).example() == 0);
    }
}
