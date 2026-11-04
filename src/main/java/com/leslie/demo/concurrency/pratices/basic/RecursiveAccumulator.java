package com.leslie.demo.concurrency.pratices.basic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class RecursiveAccumulator {
    private long total = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public RecursiveAccumulator() {

    }

    public void addRange(int n, Runnable onAdd) {
        lock.lock();
        try {
            if (n <= 0) return;

            total += n;
            onAdd.run();
            addRange(n - 1, onAdd);
        } finally {
            lock.unlock();
        }
    }

    public long getTotal() {
        lock.lock();
        try {
            return total;
        } finally {
            lock.unlock();
        }
    }
}


class Main8 {
    public static void main(String[] args) throws InterruptedException {
        RecursiveAccumulator accumulator = new RecursiveAccumulator();
        AtomicInteger calls = new AtomicInteger();

        Runnable worker = () -> {
            for (int i = 0; i < 100; i++) {
                // 2 threads compete for the lock
                accumulator.addRange(10, calls::incrementAndGet);
            }
        };

        Thread first = new Thread(worker);
        Thread second = new Thread(worker);

        first.start();
        second.start();

        first.join();
        second.join();

        // Each addRange(10) adds 55 and invokes onAdd 10 times, so this
        // always prints 11000 and then 2000.
        System.out.println(accumulator.getTotal());
        System.out.println(calls.get());
    }
}
