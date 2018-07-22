package info.multithreading.examples;

import org.junit.Test;

public class BadSynchronization2Test {

    @Test(expected = IllegalMonitorStateException.class)
    public void test(){
        (new BadSynchronization2()).example();
    }
}
