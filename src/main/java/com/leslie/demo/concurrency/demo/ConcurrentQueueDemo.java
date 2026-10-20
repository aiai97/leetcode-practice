package com.leslie.demo.concurrency.demo;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentQueueDemo {

    public static void main(String[] args)
            throws InterruptedException {

        ConcurrentLinkedQueue<Integer> queue =
                new ConcurrentLinkedQueue<>();

        Thread producer1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                queue.offer(i);
            }
        });

        Thread producer2 = new Thread(() -> {
            for (int i = 6; i <= 10; i++) {
                queue.offer(i);
            }
        });

        Runnable consume = () -> {
            Integer task;

            while ((task = queue.poll()) != null) {
                System.out.println(
                        Thread.currentThread().getName()
                                + " processed " + task);
            }
        };

        producer1.start();
        producer2.start();

        producer1.join();
        producer2.join();

        Thread consumer1 = new Thread(consume, "Consumer-1");
        Thread consumer2 = new Thread(consume, "Consumer-2");

        consumer1.start();
        consumer2.start();

        consumer1.join();
        consumer2.join();
    }
}