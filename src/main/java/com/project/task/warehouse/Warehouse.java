package com.project.task.warehouse;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Warehouse {
    private final Map<String, Integer> stock;
    private final ReentrantLock lock = new ReentrantLock(true);

    public Warehouse(Map<String, Integer> stock) { this.stock = stock; }

    public void takeParts(Map<String, Integer> req) {
        lock.lock();
        try {
            for (var e : req.entrySet()) {
                int have = stock.getOrDefault(e.getKey(), 0);
                if (have < e.getValue()) throw new IllegalStateException("Not enough " + e.getKey());
                stock.put(e.getKey(), have - e.getValue());
            }
        } finally { lock.unlock(); }
    }

    public void returnParts(Map<String, Integer> req) {
        lock.lock();
        try {
            for (var e : req.entrySet()) {
                stock.put(e.getKey(), stock.getOrDefault(e.getKey(), 0) + e.getValue());
            }
        } finally { lock.unlock(); }
    }
}

