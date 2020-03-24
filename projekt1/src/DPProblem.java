import java.util.Random;
import java.util.concurrent.Semaphore;

    public class DPProblem {

        static int philosopherNumber = 5;
        static Philosopher philosophers[] = new Philosopher[philosopherNumber];
        static Chopstick chopsticks[] = new Chopstick[philosopherNumber];
        static Semaphore table = new Semaphore(philosopherNumber - 1, true); // rozwiazanie przy uzyciu kelnera, tylko X - 1 filozofow z X moze siedziec rownoczesnie przy stole - zapobiega to zakleszczeniu

        public static void main(String argv[]) {

            Random r = new Random();

            for (int i = 0; i < philosopherNumber; i++) {
                chopsticks[i] = new Chopstick();
            }

            for (int i = 0; i < philosopherNumber; i++) {
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % philosopherNumber], table);
                philosophers[i].start();
            }
        }
    }


