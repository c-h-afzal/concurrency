package info.multithreading.jmm;

public class BadExample {

    int myVariable = 0;
    boolean neverQuit = true;

    public void runMethodForMainThread() {

        // Change the variable value to lucky 7
        myVariable = 7;
    }

    public void runMethodForOtherThreads() {

        while (neverQuit) {
            System.out.println("myVariable : " + myVariable);
        }
    }
}
