package com.leslie.demo.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

//failed
class FizzBuzz {
    private int n;
    private int current = 1;
    private ReentrantLock lock = new ReentrantLock();
    private Condition fizz = lock.newCondition();
    private Condition buzz = lock.newCondition();
    private Condition fizzbuzz = lock.newCondition();
    private final Condition number = lock.newCondition();


    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (current <= n) {
                // failed in waiting condition , because one shared lock between so many conditions,
                // when releasing a lock, current may be changed by others
                while (current <= n &&
                        !(current % 3 == 0 && current % 5 != 0)) {
                    fizz.await();
                }

                if (current > n) break; // failed, see the current++ ?

                printFizz.run();
                current++;
                signalNext();
            }
        } finally {
            lock.unlock();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (current <= n) {
                while (current <= n &&
                        !(current % 5 == 0 && current % 3 != 0)) {
                    buzz.await();
                }

                if (current > n) break;

                printBuzz.run();
                current++;
                signalNext();
            }
        } finally {
            lock.unlock();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (current <= n) {
                while (current <= n && current % 15 != 0) {
                    fizzbuzz.await();
                }

                if (current > n) break;

                printFizzBuzz.run();
                current++;
                signalNext();
            }
        } finally {
            lock.unlock();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (current <= n) {
                while (current <= n &&
                        (current % 3 == 0 || current % 5 == 0)) {
                    number.await();
                }

                if (current > n) break;

                printNumber.accept(current);
                current++;
                signalNext();
            }
        } finally {
            lock.unlock();
        }
    }

    private void signalNext() {
        if (current > n) {
            fizz.signalAll();
            buzz.signalAll();
            fizzbuzz.signalAll();
            number.signalAll();
        } else if (current % 15 == 0) {
            fizzbuzz.signal();
        } else if (current % 3 == 0) {
            fizz.signal();
        } else if (current % 5 == 0) {
            buzz.signal();
        } else {
            number.signal();
        }
    }

}