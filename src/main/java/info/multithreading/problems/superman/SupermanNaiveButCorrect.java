package info.multithreading.problems.superman;

public class SupermanNaiveButCorrect {

    // We are initializing the object inline
    private static SupermanNaiveButCorrect superman = new SupermanNaiveButCorrect();

    // We have marked the constructor private
    private SupermanNaiveButCorrect() {
    }

    public static SupermanNaiveButCorrect getInstance() {
        return superman;
    }

    // Object method
    public void fly() {
        System.out.println("I am Superman & I can fly !");
    }
}
