package info.multithreading.beyond;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {

    int example1(final int n) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        Callable<Integer> sumTask = new Callable<Integer>() {

            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 1; i <= n; i++)
                    sum += i;
                System.out.println("Running");
                return sum;
            }
        };

        Future<Integer> f = threadPool.submit(sumTask);
        int ans = f.get();


        f = threadPool.submit(sumTask);
        f.get();

        return ans;
    }

    int example2(final int n) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        int result = -1;

        Callable<Integer> sumTask = new Callable<Integer>() {

            public Integer call() throws Exception {
                throw new RuntimeException("something bad happened.");
            }
        };

        Future<Integer> f = threadPool.submit(sumTask);

        try {
            result = f.get();
        } catch (ExecutionException ee) {
            System.out.println("Something went wrong.");
        }

        return result;
    }

    int example3(final int n) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        int result = -1;

        Callable<Integer> sumTask1 = new Callable<Integer>() {

            public Integer call() throws Exception {

                // wait for 2 seconds
                Thread.sleep(1000);

                int sum = 0;
                for (int i = 1; i <= n; i++)
                    sum += i;
                return sum;
            }
        };

        Callable<Void> randomTask = new Callable<Void>() {

            public Void call() throws Exception {

                // go to sleep for an hours
                Thread.sleep(3600 * 1000);
                return null;
            }
        };

        Future<Integer> f1 = threadPool.submit(sumTask1);
        Future<Void> f2 = threadPool.submit(randomTask);

        // Poll for completion of first task
        try {

            // Before we poll for completion of second task,
            // cancel the second one
            f2.cancel(true);

            while (!f1.isDone()) {
                System.out.println("Waiting for first task to complete.");
            }
            result = f1.get();
        } catch (ExecutionException ee) {
            System.out.println("Something went wrong.");
        }

        System.out.println("Is second task cancelled : " + f2.isCancelled());

        return result;
    }

    void test() throws ExecutionException, InterruptedException {
        //        System.out.println(example1(10));

        System.out.println(example1(10));
    }

}
