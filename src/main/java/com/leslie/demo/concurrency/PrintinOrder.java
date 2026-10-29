package com.leslie.demo.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintinOrder {
}

class Foo {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition stageChanged = lock.newCondition();
    private int stage = 1;

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while(stage != 1){
                stageChanged.await();
            }
            printFirst.run();
            stage = 2;
            stageChanged.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (stage != 2) {
                stageChanged.await();
            }
            printSecond.run();
            stage = 3;
            stageChanged.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (stage != 3) {
                stageChanged.await();
            }
            printThird.run();
        } finally {
            lock.unlock();
        }
    }
}

/*
For reference, the judge creates one thread per method (first, second,
third) along these lines. In an interview, you can write a driver like
this yourself to demonstrate the full picture:

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        StringBuilder output = new StringBuilder();

        Thread third = new Thread(() -> {
            try {
                foo.third(() -> output.append("third"));
            } catch (InterruptedException error) {
                Thread.currentThread().interrupt();
            }
        });
        Thread second = new Thread(() -> {
            try {
                foo.second(() -> output.append("second"));
            } catch (InterruptedException error) {
                Thread.currentThread().interrupt();
            }
        });
        Thread first = new Thread(() -> {
            try {
                foo.first(() -> output.append("first"));
            } catch (InterruptedException error) {
                Thread.currentThread().interrupt();
            }
        });

        third.start();
        second.start();
        first.start();

        third.join();
        second.join();
        first.join();

        // The synchronization enforces the order first, second, third,
        // so this always prints firstsecondthird.
        System.out.println(output);
    }
}
*/