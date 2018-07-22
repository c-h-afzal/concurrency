package info.multithreading.beyond;

import java.util.concurrent.Executor;

public class ThreadExecutorExample {

    public void main() throws InterruptedException {

        DumbExecutor dumbExecutor = new DumbExecutor();
        MyTask myTask = new MyTask();
        dumbExecutor.execute(myTask);
    }

    static class MyTask implements Runnable {

        public void run() {
            System.out.println("Mytask is running now ...");
        }
    }

    static class DumbExecutor implements Executor {

        public void execute(Runnable runnable) {
            Thread newThread = new Thread(runnable);
            newThread.start();
        }
    }

}
