package com.leslie.demo.concurrency.pratices.basic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private final AtomicInteger value;

    public AtomicCounter(int initialValue) {
        value = new AtomicInteger(initialValue);
    }

    public int incrementAndGet() {
        return value.incrementAndGet();
    }

    public int addAndGet(int delta) {
        return value.addAndGet(delta);
    }

    public int get() {
        return value.get();
    }
}



class Main7 {
    public static void main(String[] args) throws InterruptedException {
        AtomicCounter counter = new AtomicCounter(0);
        // mock 2000 users -> delete the task to 2 threads
        Runnable worker = () -> {
            for (int i = 0; i < 1000; i++) {
                // increment and get 5 times
                counter.incrementAndGet();
                counter.addAndGet(4);
            }
        };

        Thread first = new Thread(worker);
        Thread second = new Thread(worker);

        first.start();
        second.start();

        first.join();
        second.join();

        // Each iteration adds 1 and then 4, so this always prints 10000.
        System.out.println(counter.get());
    }
}
