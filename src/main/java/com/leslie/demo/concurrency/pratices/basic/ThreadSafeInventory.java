package com.leslie.demo.concurrency.pratices.basic;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeInventory {
    private int stock;
    private final ReentrantLock lock;


    public ThreadSafeInventory(int initialStock) {
        this.stock = initialStock;
        this.lock = new ReentrantLock();
    }

    public boolean tryPurchase(int quantity, Runnable onPurchased) {
        this.lock.lock();
        try{
            if(this.stock < quantity){
                return false;
            }
            this.stock -= quantity;
        }finally{
            lock.unlock();
        }
        onPurchased.run();
        return true;
    }

    public void restock(int quantity) {
        this.lock.lock();
        try{
            this.stock += quantity;
        }finally{
            this.lock.unlock();
        }

    }

    public int available() {
        this.lock.lock();
        try {
            return stock;
        } finally {
            this.lock.unlock();
        }
    }
}

 class Main1 {
    public static void main(String[] args) throws InterruptedException {
        ThreadSafeInventory inventory = new ThreadSafeInventory(1000);
        int[] callbackCalls = new int[2]; // each user keep track of how many times the callback is called

        Thread[] buyers = new Thread[2];
        for (int t = 0; t < 2; t++) {
            final int buyerId = t;
            buyers[t] = new Thread(
                    // for every user, do operation for 500 times,
                    () -> {
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
