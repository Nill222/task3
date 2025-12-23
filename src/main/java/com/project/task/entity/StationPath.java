package com.project.task.entity;

import java.util.concurrent.locks.ReentrantLock;

public class StationPath {
    private final int id;
    private final ReentrantLock lock = new ReentrantLock(true);

    public StationPath(int id) {
        this.id = id;
    }

    public void get() {
        lock.lock();
    }

    public void give() {
        lock.unlock();
    }

    @Override
    public String toString() {
        return "Path number" + id;
    }
}
