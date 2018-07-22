package info.multithreading.problems.superman;

public class SupermanWithFlaws {

    private static SupermanWithFlaws superman;

    private SupermanWithFlaws() {

    }

    // This will fail with multiple threads
    public static SupermanWithFlaws getInstance() {
        if (superman == null) {
            // A thread can be context switched at this point and
            // superman will evaluate to null for any other threads
            // testing the if condition. Now multiple threads will
            // fall into this if clause till the superman object is
            // assigned a value. All these threads will intialize the
            // superman object when it should have been initialized
            // only one.
            superman = new SupermanWithFlaws();
        }
        return superman;
    }

    // Object method
    public void fly() {
        System.out.println("I am Superman & I can fly !");
    }
}
