package com.leslie.demo.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// failed, one step to success,
class BoundedBlockingQueue {
    private Queue<Integer> queue;
    private int capacity; // not need to increment or decrement, because the queue size tells the current size
    private ReentrantLock lock;
    private Condition isFull;
    private Condition isEmpty;

    public BoundedBlockingQueue(int capacity) {
        lock = new ReentrantLock();
        this.capacity = capacity;
        isEmpty = lock.newCondition();
        isFull = lock.newCondition();
        queue = new LinkedList<Integer>();
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lockInterruptibly(); // failed, I used to write wait and then lock
        try{
            while(queue.size() == capacity){
                isFull.await();
            }
            queue.add(element);
            isEmpty.signalAll();
        }finally {
            lock.unlock();
        }

    }

    public int dequeue() throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while(queue.size() == 0){
                isEmpty.await();
            }
            int result = (int)queue.poll();
            isFull.signalAll();
            return result;
        }finally {
            lock.unlock();
        }
    }

    // the linkedlist is not thread-safe, so we need to use lock to read
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}