package com.leslie.demo.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

// failed, because I used to solve it by using one semaphore and one cyclicbarrier
class H2ODemo {

    private final CyclicBarrier barrier;
    private final Semaphore hydrogen = new Semaphore(2);
    private final Semaphore oxygen = new Semaphore(1);
    public H2ODemo() {
        barrier = new CyclicBarrier(3,()->System.out.println("H2O"));
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogen.acquire();
        releaseHydrogen.run();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        hydrogen.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygen.acquire();
        releaseOxygen.run();
        try{
            barrier.await();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        oxygen.release();
    }
}