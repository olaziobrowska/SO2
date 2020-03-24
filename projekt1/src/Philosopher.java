import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread {

    public int number;
    public Chopstick leftchopstick;
    public Chopstick rightchopstick;
    public Semaphore table; // rozwiazanie przy uzyciu kelnera

    Philosopher(int num, Chopstick left, Chopstick right, Semaphore table) {
        this.number = num;
        this.leftchopstick = left;
        this.rightchopstick = right;
        this.table = table;
    }

    public void run() {

        while (true) {
            think();
            sit();
            leftchopstick.grab();
            System.out.println("Filozof " + (number+1) + " podnosi lewa paleczke.");
            rightchopstick.grab();
            System.out.println("Filozof " + (number+1) + " podnosi prawa paleczke.");

            eat();

            leftchopstick.release();
            System.out.println("Filozof " + (number+1) + " odklada lewa paleczke.");
            rightchopstick.release();
            System.out.println("Filozof " + (number+1) + " odklada prawa paleczke.");
            table.release();
            System.out.println("Filozof " + (number+1) + " odchodzi od stolu.");
        }
    }

    void think() {
        try {
            int sleepTime = ThreadLocalRandom.current().nextInt(0, 1000);
            System.out.println("Filozof " + (number+1) + " mysli przez " + sleepTime + " milisekund.");
            Thread.sleep(sleepTime);
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    void sit() {
        try {
            System.out.println("Filozof " + (number+1) + " siada.");
            table.acquire();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    void eat() {
        try {
            int sleepTime = ThreadLocalRandom.current().nextInt(0, 1000);
            System.out.println("Filozof " + (number+1) + " je przez " + sleepTime + " milisekund.");
            Thread.sleep(sleepTime);
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
