package com.leslie.demo.concurrency.pratices.basic;


import java.util.concurrent.Semaphore;

class ConcurrencyLimiter {
    private Semaphore semaphore;

    public ConcurrencyLimiter(int maxConcurrent) {
        semaphore = new Semaphore(maxConcurrent);
    }

    public void run(Runnable task) throws InterruptedException {
        semaphore.acquire();
        try{
            task.run();
        }finally{
            semaphore.release();
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
                    // every user wanna increment the counter
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
        // it doesn't have original state, so 2000
        System.out.println(counter[0]);
    }
}
