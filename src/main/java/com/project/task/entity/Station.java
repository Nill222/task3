package com.project.task.entity;

import com.project.task.exception.TrainException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Station {
    private static final Logger log = LogManager.getLogger();
    private final int capacity;
    private  int currentWagons;
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public Station(int capacity, int currentWagons) {
        this.capacity = capacity;
        this.currentWagons = currentWagons;
    }

    public void addWagons(int wagons) throws TrainException {
        lock.lock();
        try {
            if(currentWagons + wagons > capacity) {
                log.info("Station is full, train wait");
                notFull.await();
            }
            currentWagons +=wagons;
            log.info("Station: added {} wagons. Total ={} ", currentWagons, wagons);
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            throw new TrainException(e);
        } finally {
            lock.unlock();
        }
    }

    public void removeWagons(int wagons) throws TrainException {
        lock.lock();
        try {
            if(currentWagons < wagons) {
                log.info("wagons not enough, train wait");
                notEmpty.await();
            }
            currentWagons -= wagons;
            log.info("Station: removed wagons. Total ={} ", currentWagons);
            notFull.signalAll();
        } catch (InterruptedException e) {
            throw new TrainException(e);
        }
        finally {
            lock.unlock();
        }
    }
}
