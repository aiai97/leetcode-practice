package com.leslie.demo.concurrency.pratices.demo;

import java.util.concurrent.CountDownLatch;


public class OrderBatchProcessor {

    public void processBatch(int orderCount)
            throws InterruptedException {

        CountDownLatch latch =
                new CountDownLatch(orderCount);

        for (int i = 1; i <= orderCount; i++) {
            final int orderId = i;

            new Thread(() -> {
                try {
                    processOrder(orderId);
                } finally {
                    latch.countDown();
                }
            }).start();
        }

        latch.await();

        generateBatchReport();
    }

    private void processOrder(int orderId) {
        System.out.println("Processed order " + orderId);
    }

    private void generateBatchReport() {
        System.out.println("Batch report generated");
    }
}