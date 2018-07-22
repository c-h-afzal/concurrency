package info.multithreading.beyond;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StockOrder {

    class Order {
        void execute() {}
    }

    Order waitForNextOrder() {return null;}

    void receiveAndExecuteClientOrders() {

        while (true) {
            Order order = waitForNextOrder();
            order.execute();
        }
    }

    void receiveAndExecuteClientOrdersBetter() {

        while (true) {
            final Order order = waitForNextOrder();

            Thread thread = new Thread(new Runnable() {

                public void run() {
                    order.execute();
                }
            });

            thread.start();
        }
    }

    void receiveAndExecuteClientOrdersBest() {

        int expectedConcurrentOrders = 100;
        Executor executor = Executors.newFixedThreadPool(expectedConcurrentOrders);
        ((ExecutorService) executor).shutdown();
        ExecutorService s = Executors.newFixedThreadPool(5);

        while (true) {
            final Order order = waitForNextOrder();

            executor.execute(new Runnable() {

                public void run() {
                    order.execute();
                }
            });
        }
    }
}
