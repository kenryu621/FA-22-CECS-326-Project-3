/**
 * Project 3 - All about Deadlock
 * 
 * Organization: California State University Long Beach
 * Course: Fall 2022 CECS 326 Operating System
 * Professor: Hailu Xu
 * 
 * Author: Diego O. Garcia and Kenry Yu
 */

package project3;

import java.util.concurrent.Semaphore;

public class RoadController {
    public static void main(String args[]) {
        // Initialize the number of drivers per village
        final int NUM_VILLAGERS = 5;
        // Initialize the synchronization tool
        Semaphore sem = new Semaphore(1);
        // Initialize the two villages
        west_village[] west_villagers = new west_village[NUM_VILLAGERS];
        east_village[] east_villagers = new east_village[NUM_VILLAGERS];
        // Initialize their drivers and start tasks
        for (int i = 0; i < NUM_VILLAGERS; i++) {
            west_villagers[i] = new west_village(sem, i + 1);
            Thread west_driver = new Thread(west_villagers[i], "West driver " + (i + 1));
            west_driver.start();
            east_villagers[i] = new east_village(sem, i + 1);
            Thread east_driver = new Thread(east_villagers[i], "East driver " + (i + 1));
            east_driver.start();
        }
    }
}
