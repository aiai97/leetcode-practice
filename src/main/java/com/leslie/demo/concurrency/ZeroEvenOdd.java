package com.leslie.demo.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;
import java.util.concurrent.Semaphore;


//failed -> need to do it again
public class ZeroEvenOdd {
    private final int n;

    private final Semaphore zero = new Semaphore(1);
    private final Semaphore even = new Semaphore(0);
    private final Semaphore odd = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber)
            throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zero.acquire();
            printNumber.accept(0);

            if (i % 2 == 1) {
                odd.release();
            } else {
                even.release();
            }
        }
    }

    public void even(IntConsumer printNumber)
            throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            even.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber)
            throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }
}

// failed -> need to do it again

// condition, await + signalAll
class ZeroEvenOdd2 {
    private final int n;
    private int currentNumber = 1;
    private boolean isZeroTurn = true;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition zero = lock.newCondition();
    private final Condition odd = lock.newCondition();
    private final Condition even = lock.newCondition();

    public ZeroEvenOdd2(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (currentNumber <= n) {
                while (currentNumber <= n && !isZeroTurn) {
                    zero.await();
                }

                if (currentNumber > n) {
                    break;
                }

                printNumber.accept(0);
                isZeroTurn = false;

                if (currentNumber % 2 == 0) {
                    even.signal();
                } else {
                    odd.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (currentNumber <= n) {
                while (currentNumber <= n &&
                        (isZeroTurn || currentNumber % 2 == 0)) {
                    odd.await();
                }

                if (currentNumber > n) {
                    break;
                }

                printNumber.accept(currentNumber);
                currentNumber++;
                isZeroTurn = true;

                if (currentNumber > n) {
                    wakeAll();
                } else {
                    zero.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (currentNumber <= n) {
                while (currentNumber <= n &&
                        (isZeroTurn || currentNumber % 2 != 0)) {
                    even.await();
                }

                if (currentNumber > n) {
                    break;
                }

                printNumber.accept(currentNumber);
                currentNumber++;
                isZeroTurn = true;

                if (currentNumber > n) {
                    wakeAll();
                } else {
                    zero.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private void wakeAll() {
        zero.signalAll();
        odd.signalAll();
        even.signalAll();
    }
}
