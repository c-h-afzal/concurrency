package info.multithreading.examples;

public class InterruptExample {

    public boolean example() throws InterruptedException {

        final Thread sleepyThread = new Thread(new Runnable() {

            public void run() {
                try {
                    System.out.println("I am too sleepy... Let me sleep for an hour.");
                    Thread.sleep(1000 * 60 * 60);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();

                    System.out.println(Thread.interrupted());
                    System.out.println(Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().isInterrupted());
                    System.out.println(Thread.interrupted());

                    System.out.println("Oh someone woke me up ! ");
                }
            }
        });

        sleepyThread.start();

        System.out.println("About to wake up the sleepy thread ...");
        sleepyThread.interrupt();
        System.out.println("Woke up sleepy thread ...");

        sleepyThread.join();
        return true;
    }
}
