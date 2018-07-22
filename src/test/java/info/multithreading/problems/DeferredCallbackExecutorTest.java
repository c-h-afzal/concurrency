package info.multithreading.problems;

import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DeferredCallbackExecutorTest {

    private static Random random = new Random(System.currentTimeMillis());

    @Test
    // This is a perpetual test-case, because the service thread runs forever
    public void runLateThenEarlyCallback() throws InterruptedException {
        final DeferredCallbackExecutor deferredCallbackExecutor = new DeferredCallbackExecutor();

        Thread service = new Thread(new Runnable() {

            public void run() {
                try {
                    deferredCallbackExecutor.start();
                } catch (InterruptedException ie) {
                }
            }
        });

        service.start();

        Thread lateThread = new Thread(new Runnable() {

            public void run() {
                DeferredCallbackExecutor.CallBack cb = new DeferredCallbackExecutor.CallBack(
                        8,
                        "Hello this is late thread");
                deferredCallbackExecutor.registerCallback(cb);
            }
        });
        lateThread.start();

        Thread.sleep(3000);

        Thread earlyThread = new Thread(new Runnable() {

            public void run() {
                DeferredCallbackExecutor.CallBack cb = new DeferredCallbackExecutor.CallBack(
                        1,
                        "Hello this is early thread");
                deferredCallbackExecutor.registerCallback(cb);
            }
        });
        earlyThread.start();

        lateThread.join();
        earlyThread.join();
        service.join();
    }

    @Test
    // This is a perpetual test-case, because the service thread runs forever
    public void runTestFiveCallbacksTogether() throws InterruptedException {
        Set<Thread> allThreads = new HashSet<Thread>();
        final DeferredCallbackExecutor deferredCallbackExecutor = new DeferredCallbackExecutor();

        Thread service = new Thread(new Runnable() {

            public void run() {
                try {
                    deferredCallbackExecutor.start();
                } catch (InterruptedException ie) {

                }
            }
        });

        service.start();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {

                public void run() {
                    DeferredCallbackExecutor.CallBack cb = new DeferredCallbackExecutor.CallBack(
                            1,
                            "Hello this is " + Thread.currentThread()
                                    .getName());
                    deferredCallbackExecutor.registerCallback(cb);
                }
            });
            thread.setName("Thread_" + (i + 1));
            thread.start();
            allThreads.add(thread);
        }

        for (Thread t : allThreads) {
            t.join();
        }
        service.join();
    }

    @Test
    // This is a perpetual test-case, because the service thread runs forever
    public void runTestTenCallbacks() throws InterruptedException {
        Set<Thread> allThreads = new HashSet<Thread>();
        final DeferredCallbackExecutor deferredCallbackExecutor = new DeferredCallbackExecutor();

        Thread service = new Thread(new Runnable() {

            public void run() {
                try {
                    deferredCallbackExecutor.start();
                } catch (InterruptedException ie) {

                }
            }
        });
        service.start();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {

                public void run() {
                    DeferredCallbackExecutor.CallBack cb = new DeferredCallbackExecutor.CallBack(
                            1,
                            "Hello this is " + Thread.currentThread()
                                    .getName());
                    deferredCallbackExecutor.registerCallback(cb);
                }
            });
            thread.setName("Thread_" + (i + 1));
            thread.start();
            allThreads.add(thread);
            Thread.sleep((random.nextInt(3) + 1) * 1000);
        }

        for (Thread t : allThreads) {
            t.join();
        }
        service.join();
    }
}
