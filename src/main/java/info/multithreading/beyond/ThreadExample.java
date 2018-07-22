package info.multithreading.beyond;

public class ThreadExample {

    class ExecuteMe implements Runnable {

        public void run() {
            while (true) {
                System.out.println("Say Hello over and over again.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    // swallow interrupted exception

                }
            }
        }
    }

    class MyTask extends Thread {

        @Override
        public void run() {
            System.out.println("Hello World.");
        }

    }

    public void main() throws InterruptedException {
//        ExecuteMe executeMe = new ExecuteMe();
//        Thread innerThread = new Thread(executeMe);
//        //                innerThread.setDaemon(true);
//        innerThread.start();
//        innerThread.join();
//        System.out.println("Main thread exits");

        MyTask myTask = new MyTask();
        myTask.start();

    }
}
