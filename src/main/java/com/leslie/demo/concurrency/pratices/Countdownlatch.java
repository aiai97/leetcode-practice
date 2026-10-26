package com.leslie.demo.concurrency.pratices;

// failed, I used to implement it with an atomic integer
class CountDownLatch {
    private final Object lock = new Object();
    private int count;

    public CountDownLatch(int initialCount) {
        count = initialCount;
    }

    public void countDown() {
        synchronized(lock){
            if(count <= 0) return; // failed here
            count--;
            if(count == 0){
                lock.notifyAll();
            }
        }
    }

    public void await() throws InterruptedException {
        synchronized(lock){
            while(count > 0){
                lock.wait();
            }
        }
    }

    public int getCount() {
        synchronized(lock){
            return count;
        }
    }
}

/*
For reference, the judge creates and runs threads against your class
along these lines, with more threads that all start at the same time
to force contention. In an interview, you can write a driver like this
yourself to demonstrate the full picture:

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(4);
        int[] results = new int[4];

        for (int i = 0; i < 4; i++) {
            final int id = i;
            new Thread(() -> {
                results[id] = (id + 1) * 10;
                latch.countDown();
            }).start();
        }

        latch.await();

        int sum = 0;
        for (int result : results) {
            sum += result;
        }

        // await returns only after all four countDown calls, so this always prints 100.
        System.out.println(sum);
    }
}
*/