package com.leslie.demo.concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;


class ZeroEvenOddDemo {
    private final int n;
    private final Semaphore zeroTurn = new Semaphore(1);
    private final Semaphore oddTurn = new Semaphore(0);
    private final Semaphore evenTurn = new Semaphore(0);

    public ZeroEvenOddDemo(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int value = 1; value <= n; value++) {
            zeroTurn.acquire();

            // printNumber.accept(x) outputs "x", where x is an integer.
            printNumber.accept(0);

            if (value % 2 == 1) {
                oddTurn.release();
            } else {
                evenTurn.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int value = 2; value <= n; value += 2) {
            evenTurn.acquire();

            // printNumber.accept(x) outputs "x", where x is an integer.
            printNumber.accept(value);

            zeroTurn.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int value = 1; value <= n; value += 2) {
            oddTurn.acquire();

            // printNumber.accept(x) outputs "x", where x is an integer.
            printNumber.accept(value);

            zeroTurn.release();
        }
    }
}



class Main {
    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOddDemo zeroEvenOdd = new ZeroEvenOddDemo(5);
        StringBuilder output = new StringBuilder();
        IntConsumer printNumber = value -> output.append(value);

        Thread zero = new Thread(() -> {
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException error) {
                Thread.currentThread().interrupt();
            }
        });
        Thread even = new Thread(() -> {
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException error) {
                Thread.currentThread().interrupt();
            }
        });
        Thread odd = new Thread(() -> {
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException error) {
                Thread.currentThread().interrupt();
            }
        });

        zero.start();
        even.start();
        odd.start();

        zero.join();
        even.join();
        odd.join();

        // The handoff protocol fixes the callback order,
        // so this always prints 0102030405.
        System.out.println(output);
    }
}
