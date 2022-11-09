package project3;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class west_village implements Runnable {
    // Private member
    // Synchronization tool
    Semaphore sem;
    // Driver ID
    int driver_num;

    // Overloaded constructor
    public west_village(Semaphore sem, int driver_num) {
        this.sem = sem;
        this.driver_num = driver_num;
    }

    // run function
    public void run() {
        // Logging the task has started
        System.out.println("Starting west driver " + driver_num);
        Random rand = new Random();
        // Infinite loop
        while (true) {
            try {
                // Wait for signal from the semaphore
                Thread.sleep((long) rand.nextInt(2000));
                System.out.println("West driver " + driver_num + " is waiting for a permit");
                sem.acquire();
                // Obtain the semaphore once it is freed and start driving
                System.out.println("West driver " + driver_num + " gets a permit, start driving");
                Thread.sleep((long) rand.nextInt(2000));
                // Driver's random actions on the road
                int number_of_actions = rand.nextInt(3) + 1;
                int action;
                for (int i = 0; i < number_of_actions; i++) {
                    action = rand.nextInt(5);
                    System.out.print("West driver " + driver_num + " is ");
                    switch (action) {
                        case 0:
                            System.out.print("drinking water");
                            break;
                        case 1:
                            System.out.print("eating donut");
                            break;
                        case 2:
                            System.out.print("taking a nap");
                            break;
                        case 3:
                            System.out.print("refueling gas");
                            break;
                        case 4:
                            System.out.print("buying lottery");
                            break;
                    }
                    System.out.print('\n');
                    Thread.sleep((long) rand.nextInt(2000));
                }
                // The driving will be finished after all random actions
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            // Finished driving, release the semaphore and start the task again
            System.out.println("West driver " + driver_num + " finished driving and released the permit");
            sem.release();
        }
    }
}
