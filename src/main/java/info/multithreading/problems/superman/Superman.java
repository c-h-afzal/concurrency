package info.multithreading.problems.superman;

public class Superman {

    private static volatile Superman superman;

    private Superman() {

    }

    public static Superman getInstance() {

        if (superman == null) {
            synchronized (Superman.class) {

                if (superman == null) {
                    superman = new Superman();
                }
            }
        }

        return superman;
    }

    public void fly() {
        System.out.println("I am Superman & I can fly !");
    }
}
