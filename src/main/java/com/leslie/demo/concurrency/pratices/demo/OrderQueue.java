package com.leslie.demo.concurrency.pratices.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class OrderQueue {

    private final BlockingQueue<String> queue =
            new ArrayBlockingQueue<>(5);

    public void submitOrder(String orderId)
            throws InterruptedException {

        queue.put(orderId);

        System.out.println("Submitted: " + orderId);
    }

    public void processOrders()
            throws InterruptedException {

        while (true) {
            String orderId = queue.take();

            System.out.println("Processing: " + orderId);
        }
    }
}
