package com.leslie.demo.concurrency.pratices.basic;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCoordinator {

    final ReentrantReadWriteLock rwl;
    public ReadWriteCoordinator() {
        rwl = new ReentrantReadWriteLock();
    }

    public void read(Runnable readTask) {
        rwl.readLock().lock();
        // readTask.run() performs one read. Do not change or remove this line.
        try {
            readTask.run();
        } finally {
            rwl.readLock().unlock();
        }
    }

    public void write(Runnable writeTask) {
        rwl.writeLock().lock();
        // writeTask.run() performs one write. Do not change or remove this line.
        try {
            writeTask.run();
        } finally {
            rwl.writeLock().unlock();
        }
    }
}
class Main5 {
    public static void main(String[] args) throws InterruptedException {
        ReadWriteCoordinator coordinator = new ReadWriteCoordinator();
        int[] value = new int[1];

        // 1000 writers and 1000 readers, all performed by 2 read threads + 2 write threads
        // mock 1000 and 1000 users
        Runnable writer = () -> {
            for (int i = 0; i < 500; i++) {
                coordinator.write(() -> value[0]++);
            }
        };
        Runnable reader = () -> {
            for (int i = 0; i < 500; i++) {
                coordinator.read(() -> {
                    int snapshot = value[0];
                });
            }
        };

        Thread firstWriter = new Thread(writer);
        Thread secondWriter = new Thread(writer);
        Thread firstReader = new Thread(reader);
        Thread secondReader = new Thread(reader);

        firstWriter.start();
        secondWriter.start();
        firstReader.start();
        secondReader.start();

        firstWriter.join();
        secondWriter.join();
        firstReader.join();
        secondReader.join();

        // Each of the 1000 writes adds 1, so this always prints 1000.
        System.out.println(value[0]);
    }
}
