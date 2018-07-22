package info.multithreading.examples;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FixedMissedSignalExampleTest {
    @Test()
    public void test() throws InterruptedException {
        assertTrue((new FixedMissedSignalExample()).example() == 0);
    }
}
