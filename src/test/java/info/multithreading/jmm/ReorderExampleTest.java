package info.multithreading.jmm;

import org.junit.Test;

public class ReorderExampleTest {

    @Test
    public void test() throws InterruptedException {
        (new ReorderingExample()).reorderTest();
    }
}
