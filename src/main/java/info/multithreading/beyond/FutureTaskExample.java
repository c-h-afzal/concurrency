package info.multithreading.beyond;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTaskExample {

    Random random = new Random(System.currentTimeMillis());

    @SuppressWarnings("")
    void example() throws Exception {

        FutureTask<Integer> futureTask = new FutureTask(new Callable() {

            public Object call() throws Exception {
                try{
                    Thread.sleep(1);
                }
                catch(InterruptedException ie){
                    // swallow exception
                }
                return 5;
            }
        });

        ExecutorService threadPool = (Executors.newSingleThreadExecutor());
        Future duplicateFuture = threadPool.submit(futureTask);

        // Awful idea to busy wait
        while (!futureTask.isDone()) {
            System.out.println("Waiting");
        }

        if(duplicateFuture.isDone() != futureTask.isDone()){
            System.out.println("This should never happen.");
        }

        System.out.println(futureTask.get());
    }

    void completionServiceExample() throws Exception {

        class TrivialTask implements Runnable {

            int n;

            public TrivialTask(int n) {
                this.n = n;
            }

            public void run() {
                try {
                    // sleep for one second
                    Thread.sleep(random.nextInt(101));
                } catch (InterruptedException ie) {
                    // swallow exception
                }
            }
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        ExecutorCompletionService<Integer> service =
                new ExecutorCompletionService<Integer>(threadPool);

        // Submit 10 trivial tasks.
        for (int i = 0; i < 10; i++) {
            service.submit(new TrivialTask(i), new Integer(i));
        }

        // wait for all tasks to get done
        int count = 10;
        while (count != 0) {
            Future<Integer> f = service.poll();
            if (f != null) {
                System.out.println("Thread" + f.get() + " got done.");
                count--;
            }
        }

        threadPool.shutdown();

    }

}
