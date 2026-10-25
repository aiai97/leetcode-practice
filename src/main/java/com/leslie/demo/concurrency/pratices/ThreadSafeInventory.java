package com.leslie.demo.concurrency.pratices;

// failed
public class ThreadSafeInventory {
    private int stock;
    private final Object lock; // failed, it should be final


    public ThreadSafeInventory(int initialStock) {
        this.stock = initialStock;
        lock = new Object();
    }

    public boolean tryPurchase(int quantity, Runnable onPurchased) {
        synchronized(lock){
            if(this.stock < quantity){
                return false;
            }
            this.stock -= quantity;
        }
        onPurchased.run(); // I used to put this line inside the synchronized block, but it turns out it's not needed and will cause performance issues
        return true;
    }

    public void restock(int quantity) {
        synchronized(lock){
            this.stock += quantity;
        }
    }

    public int available() {
        synchronized(lock){
            return stock;
        }
    }
}

/*
For reference, the judge creates and runs threads against your class
along these lines, with more threads that all start at the same time
to force contention. In an interview, you can write a driver like this
yourself to demonstrate the full picture: */

class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadSafeInventory inventory = new ThreadSafeInventory(1000);
        int[] callbackCalls = new int[2];

        Thread[] buyers = new Thread[2];
        for (int t = 0; t < 2; t++) {
            final int buyerId = t;
            buyers[t] = new Thread(() -> {
                for (int i = 0; i < 500; i++) {
                    inventory.restock(3);
                    inventory.tryPurchase(2, () -> callbackCalls[buyerId]++);
                }
            });
            buyers[t].start();
        }
        for (Thread buyer : buyers) {
            buyer.join();
        }

        // Each thread restocks 3 units before purchasing 2, so no purchase is
        // ever rejected and this always prints 1000.
        System.out.println(callbackCalls[0] + callbackCalls[1]);

        // Each iteration adds 3 units and removes 2, so this always prints 2000.
        System.out.println(inventory.available());
    }
}
