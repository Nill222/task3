package com.project.task.entity;

import java.util.concurrent.locks.ReentrantLock;

public class Station {
    private final int id;
    private final ReentrantLock lock = new ReentrantLock(true);
    public Station(int id) {
        this.id = id;
    }

    public void loading() {
        lock.lock();
    }

    public void unloading() {
        lock.unlock();
    }

    @Override
    public String toString() {
        return "Station number" + id;
    }
}
