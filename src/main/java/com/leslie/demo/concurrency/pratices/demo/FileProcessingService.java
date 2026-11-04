package com.leslie.demo.concurrency.pratices.demo;

import java.util.concurrent.Semaphore;

public class FileProcessingService {

    private final Semaphore permits = new Semaphore(3);

    public void process(String fileName)
            throws InterruptedException {

        permits.acquire();

        try {
            System.out.println("Processing " + fileName);

            // Simulate file processing
            Thread.sleep(1000);

        } finally {
            permits.release();
        }
    }
}