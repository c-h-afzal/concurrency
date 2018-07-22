package info.multithreading.jmm;

public class ReorderingExample {

    private int ping = 0;
    private int pong = 0;
    private int foo = 0;
    private int bar = 0;

    public void reorderTest() throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {

            public void run() {
                foo = 1;
                ping = bar;
            }
        });

        Thread t2 = new Thread(new Runnable() {

            public void run() {
                bar = 1;
                pong = foo;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(pong + " " + ping);

    }
}
