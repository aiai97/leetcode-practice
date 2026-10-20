package com.leslie.demo.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

// passed -> may need to optimize it in the future -> actually countdownlatch is better
class Foo {

    private AtomicInteger firstPermit;
    private AtomicInteger secondPermit;

    public Foo() {
        firstPermit = new AtomicInteger(0);
        secondPermit = new AtomicInteger(0);;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstPermit.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while(firstPermit.get() != 1) {
            Thread.yield();
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
       secondPermit.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while(secondPermit.get() != 1) {
            Thread.yield();
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();

    }
}