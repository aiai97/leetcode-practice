package com.leslie.demo.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;


class FizzBuzzDemo  {
    private final int n;
    private int current = 1;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition turnChanged = lock.newCondition();

    public FizzBuzzDemo(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        runWhen(
                value -> value % 3 == 0 && value % 5 != 0,
                value -> printFizz.run()
        );
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        runWhen(
                value -> value % 5 == 0 && value % 3 != 0,
                value -> printBuzz.run()
        );
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        runWhen(
                value -> value % 15 == 0,
                value -> printFizzBuzz.run()
        );
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        runWhen(
                value -> value % 3 != 0 && value % 5 != 0,
                printNumber
        );
    }

    // failed
    private void runWhen(IntPredicate isMyTurn, IntConsumer print)
            throws InterruptedException {
        while (true) {
            lock.lock();
            try {
                // as long as the task should be done and the current number is not my turn, I will wait
                while (current <= n && !isMyTurn.test(current)) {
                    turnChanged.await();
                }

                // tell the waiting threads to wake up to avoid deadlock
                if (current > n) {
                    turnChanged.signalAll();
                    return;
                }

                print.accept(current);
                current++;
                turnChanged.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}

class FizzBuzzDemo1 {
    public static void main(String[] args) throws InterruptedException {
        FizzBuzzDemo fizzBuzz = new FizzBuzzDemo(15);

        Thread fizzThread = new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.println("fizz"));
            } catch (InterruptedException error) {
                Thread.currentThread().interrupt();
            }
        });
        Thread buzzThread = new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.println("buzz"));
            } catch (InterruptedException error) {
                Thread.currentThread().interrupt();
            }
        });
        Thread fizzbuzzThread = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.println("fizzbuzz"));
            } catch (InterruptedException error) {
                Thread.currentThread().interrupt();
            }
        });
        Thread numberThread = new Thread(() -> {
            try {
                fizzBuzz.number(value -> System.out.println(value));
            } catch (InterruptedException error) {
                Thread.currentThread().interrupt();
            }
        });

        fizzThread.start();
        buzzThread.start();
        fizzbuzzThread.start();
        numberThread.start();

        fizzThread.join();
        buzzThread.join();
        fizzbuzzThread.join();
        numberThread.join();

        // The turn protocol fixes the order, so this always prints
        // 1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz,
        // one token per line.
    }
}