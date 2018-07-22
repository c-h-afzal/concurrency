package info.multithreading.problems;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DeferredCallbackExecutor {

    PriorityQueue<CallBack> q = new PriorityQueue<CallBack>(new Comparator<CallBack>() {

        public int compare(CallBack o1, CallBack o2) {
            return (int) (o1.executeAt - o2.executeAt);
        }
    });
    ReentrantLock lock = new ReentrantLock();
    Condition newCallbackArrived = lock.newCondition();

    public void registerCallback(CallBack callBack) {
        lock.lock();
        q.add(callBack);
        newCallbackArrived.signal();
        lock.unlock();
    }

    public void start() throws InterruptedException {
        long sleepFor = 0;
        int lastSeenQSize = 0;

        while (true) {

            lock.lock();

            while (q.size() == 0) {
                newCallbackArrived.await();
            }

            if (lastSeenQSize == q.size()) {
                newCallbackArrived.await(sleepFor, TimeUnit.MILLISECONDS);

            }

            long currentTime = System.currentTimeMillis();
            while (q.size() != 0 && currentTime >= q.peek().executeAt) {
                CallBack cb = q.poll();
                System.out.println(
                        "Executed at " + System.currentTimeMillis() / 1000 + " required at " + cb.executeAt / 1000
                        + ": message:" + cb.message);
            }
            sleepFor = q.size() == 0 ? 0 : q.peek().executeAt - currentTime;

            lastSeenQSize = q.size();
            lock.unlock();
        }
    }

    static class CallBack {

        long executeAt;
        String message;

        public CallBack(long executeAfter, String message) {
            this.executeAt = System.currentTimeMillis() + executeAfter * 1000;
            this.message = message;
        }
    }
}
