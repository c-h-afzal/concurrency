package info.multithreading.problems;

import org.junit.Test;

public class UnisexBathroomTest {

    @Test
    public void runTest() throws InterruptedException {

        final UnisexBathroom unisexBathroom = new UnisexBathroom();

        Thread female1 = new Thread(new Runnable() {

            public void run() {
                try {
                    unisexBathroom.femaleUseBathroom("Lisa");
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread male1 = new Thread(new Runnable() {

            public void run() {
                try {
                    unisexBathroom.maleUseBathroom("John");
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread male2 = new Thread(new Runnable() {

            public void run() {
                try {
                    unisexBathroom.maleUseBathroom("Bob");
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread male3 = new Thread(new Runnable() {

            public void run() {
                try {
                    unisexBathroom.maleUseBathroom("Anil");
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread male4 = new Thread(new Runnable() {

            public void run() {
                try {
                    unisexBathroom.maleUseBathroom("Wentao");
                } catch (InterruptedException ie) {

                }
            }
        });

        female1.start();
        male1.start();
        male2.start();
        male3.start();
        male4.start();

        female1.join();
        male1.join();
        male2.join();
        male3.join();
        male4.join();
    }
}
