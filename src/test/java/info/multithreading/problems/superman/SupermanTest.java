package info.multithreading.problems.superman;

import org.junit.Test;

public class SupermanTest {

    @Test
    public void testNaiveButCorrect() {
        SupermanNaiveButCorrect superman = SupermanNaiveButCorrect.getInstance();
        superman.fly();
    }

    @Test
    public void test() {
        Superman superman = Superman.getInstance();
        superman.fly();
    }
}
