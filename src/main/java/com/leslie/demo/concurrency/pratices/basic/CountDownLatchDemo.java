package com.leslie.demo.concurrency.pratices.basic;

public class CountDownLatchDemo {
}
class CountDownLatch {
    private final Object lock = new Object();
    private int count;

    public CountDownLatch(int initialCount) {
        count = initialCount;
    }

    public void countDown() {
        synchronized(lock){
            if(count <= 0) return;  // failed
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



class Main6 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(4);
        int[] results = new int[4]; // each user will update their own result

        // start 4 threads and each thread will add something like 10，20，30，40 to the results array
        for (int i = 0; i < 4; i++) {
            final int id = i;
            new Thread(() -> {
                results[id] = (id + 1) * 10;
                latch.countDown(); // each user count down the latch when they finish
            }).start();
        }

        latch.await(); // wait for all threads to finish

        int sum = 0;
        for (int result : results) {
            sum += result;
        }

        // await returns only after all four countDown calls, so this always prints 100.
        System.out.println(sum);
    }
}
