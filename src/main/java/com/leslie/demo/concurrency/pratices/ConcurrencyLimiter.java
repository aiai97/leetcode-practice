package com.leslie.demo.concurrency.pratices;

import java.util.concurrent.Semaphore;

// passed
class ConcurrencyLimiter {
    // the namings, I used to call it semaphore directly, but I think it's better to call it permits
    private final Semaphore permits; // but I didn't use final that much, it could be better if I could use final

    public ConcurrencyLimiter(int maxConcurrent) {
        permits = new Semaphore(maxConcurrent);
    }

    public void run(Runnable task) throws InterruptedException {
        permits.acquire();
        try {
            task.run();
        } finally {
            permits.release();
        }
    }
}

 class Main2 {
    public static void main(String[] args) throws InterruptedException {
        ConcurrencyLimiter limiter = new ConcurrencyLimiter(1);
        int[] counter = {0};

        Runnable worker = () -> {
            try {
                for (int i = 0; i < 500; i++) {
                    limiter.run(() -> counter[0]++);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(worker);
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        // The single permit serializes every increment, so this always prints 2000.
        System.out.println(counter[0]);
    }
}
