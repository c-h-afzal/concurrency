package info.multithreading.problems.companies.netflix;

public class Run {

    public static void main(String[] args) throws Exception {

        SynchronousExecutor executor = new SynchronousExecutor();
        executor.asynchronousExecution(() -> {
            System.out.println("I am done");
        });

        System.out.println("main thread exiting...");
    }

    void runAsynchronously() throws Exception {
        Executor executor = new Executor();
        executor.asynchronousExecution(() -> {
            System.out.println("I am done");
        });

        System.out.println("main thread exiting...");
    }
}
