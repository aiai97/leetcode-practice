package com.leslie.demo.concurrency.pratices.demo;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Department {

    private final String name;
    private final int ownRevenue;
    private final List<Department> children;

    private final ReentrantLock lock;

    public Department(
            String name,
            int ownRevenue,
            List<Department> children,
            ReentrantLock lock) {

        this.name = name;
        this.ownRevenue = ownRevenue;
        this.children = children;
        this.lock = lock;
    }

    public int calculateTotalRevenue() {

        lock.lock();

        try {
            int total = ownRevenue;

            for (Department child : children) {
                total += child.calculateTotalRevenue();
            }

            return total;

        } finally {
            lock.unlock();
        }
    }
}