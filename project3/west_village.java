package project3;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class west_village implements Runnable {
    Semaphore sem;
    int driver_num;

    public west_village(Semaphore sem, int driver_num) {
        this.sem = sem;
        this.driver_num = driver_num;
    }

    public void run() {
        System.out.println("Starting west driver " + driver_num);
        Random rand = new Random();
        try {
            Thread.sleep((long) rand.nextInt(2000));
            System.out.println("West driver " + driver_num + " is waiting for a permit");
            sem.acquire();
            System.out.println("West driver " + driver_num + " gets a permit, start driving");
            Thread.sleep((long) rand.nextInt(2000));
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
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("West driver " + driver_num + " finished driving and released the permit");
        sem.release();
    }
}
