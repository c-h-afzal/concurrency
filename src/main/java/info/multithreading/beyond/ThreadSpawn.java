package info.multithreading.beyond;

public class ThreadSpawn {

    public void spawnThread() {

        Thread innerThread = new Thread(new Runnable() {

            public void run() {

                for (int i = 0; i < 100; i++) {
                    System.out.println("I am a new thread !");
                }
            }
        });

        innerThread.start();
        System.out.println("Main thread exiting");
    }
}
