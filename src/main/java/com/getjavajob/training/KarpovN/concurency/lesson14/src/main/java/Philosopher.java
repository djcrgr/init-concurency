import java.util.Random;

import static java.lang.Thread.sleep;

public class Philosopher implements Runnable {
    private Fork left;
    private Fork right;
    private int id;
    private Random rand = new Random(47);

    public Philosopher(Fork left, Fork right, int id) {
        this.left = left;
        this.right = right;
        this.id = id;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " " + "thinking");
                sleep(1000);
                // Философ проголодался
                System.out.println(this + " " + "pick up right fork");
                right.take();
                System.out.println(this + " " + "pick up left fork");
                left.take();
                System.out.println(this + " " + "eating");
                sleep(2500);
                right.drop();
                left.drop();
            }
        } catch (InterruptedException е) {
            System.out.println(this);
        }
    }
}