package com.leslie.demo.concurrency;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

// failed, because I used to solve it by using one semaphore and one cyclicbarrier
class H2O {

    private CyclicBarrier barrier;
    private final Semaphore hydrogen = new Semaphore(2);
    private final Semaphore oxygen = new Semaphore(1);

    public H2O() {
        barrier = new CyclicBarrier(3,()->System.out.println("H2O"));
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogen.acquire();
        try {
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        hydrogen.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygen.acquire();
        try {
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        oxygen.release();

    }
}