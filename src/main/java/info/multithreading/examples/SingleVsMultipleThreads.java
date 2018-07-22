package info.multithreading.examples;

public class SingleVsMultipleThreads {

    public void example() throws InterruptedException {

        final int sumTo = Integer.MAX_VALUE;
        final long[] halfSums = new long[2];

        Thread single = new Thread(new Runnable() {

            public void run() {
                long singleSum = 0;

                for (int i = 0; i < sumTo; i++) {
                    singleSum += i + 1;
                }
                System.out.println("Sum by single thread = " + singleSum);

            }
        });

        long start = System.currentTimeMillis();
        single.start();
        single.join();
        System.out.println("Execution time for single thread: " + (System.currentTimeMillis() - start));

        Thread multiple1 = new Thread(new Runnable() {

            public void run() {
                long halfSum = 0;

                for (int i = 0; i < sumTo / 2; i++) {
                    halfSum += i + 1;
                }
                System.out.println("Half sum by thread 1 = " + halfSum);
                halfSums[0] = halfSum;
            }
        });

        Thread multiple2 = new Thread(new Runnable() {

            public void run() {
                long halfSum = 0;

                for (int i = sumTo / 2; i < sumTo; i++) {
                    halfSum += i + 1;
                }
                System.out.println("Half sum by thread 2 = " + halfSum);
                halfSums[1] = halfSum;
            }
        });

        start = System.currentTimeMillis();
        multiple1.start();
        multiple2.start();

        multiple1.join();
        multiple2.join();
        System.out.println("Execution time for two thread: " + (System.currentTimeMillis() - start));

        System.out.println("Combined sum of both the threads = " + (halfSums[0] + halfSums[1]));
    }
}
