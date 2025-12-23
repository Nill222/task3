package com.project.task.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public class Station {
    private static final Logger log = LogManager.getLogger();
    private final int capacity;
    private  int currentWagons;
    private final ReentrantLock lock = new ReentrantLock(true);

    public Station(int capacity, int currentWagons) {
        this.capacity = capacity;
        this.currentWagons = currentWagons;
    }

    public void addWagons(int wagons) {
        lock.lock();
        try {
            if(currentWagons + wagons > capacity) {
                throw new IllegalStateException("Station capacity exceeded");
            }
            currentWagons +=wagons;
            log.info("Station: added {} wagons. Total ={} ", currentWagons, wagons);

        } finally {
            lock.unlock();
        }
    }

    public void removeWagons(int wagons) {
        lock.lock();
        try {
            if(currentWagons < wagons) {
                throw new IllegalStateException("Station capacity exceeded");
            }
            currentWagons -= wagons;
            log.info("Station: removed wagons. Total ={} ", currentWagons);
        }
        finally {
            lock.unlock();
        }
    }
}
