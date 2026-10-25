package com.leslie.demo.concurrency.pratices;

import java.util.concurrent.locks.ReentrantReadWriteLock;

// easy
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

/*
For reference, the judge creates and runs threads against your class
along these lines, with more threads that all start at the same time
to force contention. In an interview, you can write a driver like this
yourself to demonstrate the full picture:

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ReadWriteCoordinator coordinator = new ReadWriteCoordinator();
        int[] value = new int[1];

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
*/