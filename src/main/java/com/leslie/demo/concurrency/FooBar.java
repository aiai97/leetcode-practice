package com.leslie.demo.concurrency;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

// passed -> will optimize it in the future -> actually semaphore is better
class FooBar {
    private int n;
    private CyclicBarrier barrier;
    private AtomicInteger state = new AtomicInteger(0);

    public FooBar(int n) {
        this.n = n;
        barrier = new CyclicBarrier(2);
        state = new AtomicInteger(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException{
        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            state.set(1);
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException{

        for (int i = 0; i < n; i++) {
            while (state.get() != 1) {
                Thread.yield();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            state.set(0);
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class FooBar2 {
    private int n;
    private Semaphore semaphore1;
    private Semaphore semaphore2;
    public FooBar2(int n) {
        this.n = n;
        semaphore1 = new Semaphore(1);
        semaphore2 = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semaphore1.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            semaphore2.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semaphore2.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            semaphore1.release();
        }
    }
}