package com.leslie.demo.concurrency.pratices;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// too hard, will do it again
class ThreadPool {
    private final Queue<Runnable> tasks = new ArrayDeque<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition workAvailable = lock.newCondition();
    private final Condition terminated = lock.newCondition();
    private final Thread[] workers;
    private boolean accepting = true; // will the pool accept new tasks?
    private int liveWorkers; // Number of workers that have not exited

    public ThreadPool(int workerCount) {
        workers = new Thread[workerCount];
        liveWorkers = workerCount;

        for (int i = 0; i < workerCount; i++) {
            workers[i] = new Thread(this::runWorker);
            workers[i].setDaemon(true);
            workers[i].start();
        }
    }

    private void runWorker() {
        while (true) {
            Runnable task;

            lock.lock();
            try {
                while (tasks.isEmpty() && accepting) {
                    workAvailable.awaitUninterruptibly(); // when there are no tasks, let the thread sleep
                }

                if (tasks.isEmpty()) {
                    liveWorkers--;
                    if (liveWorkers == 0) {
                        terminated.signalAll();
                    }
                    return;
                }

                task = tasks.remove();
            } finally {
                lock.unlock();
            }

            task.run();
        }
    }

    public boolean submit(Runnable task) {
        lock.lock();
        try {
            if (!accepting) {
                return false;
            }
            tasks.add(task);
            workAvailable.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void shutdown() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            if (accepting) {
                accepting = false;
                workAvailable.signalAll();
            }
            while (liveWorkers > 0) {
                terminated.await();
            }
        } finally {
            lock.unlock();
        }
    }
}