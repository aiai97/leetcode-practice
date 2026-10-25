package com.leslie.demo.concurrency.pratices;

// failed -> will do it next time
class VersionedSignal {
    private int version = 0; // I used to think I need to use AtomicInteger here,

    public synchronized int currentVersion() {
        return version;
    }

    public synchronized int awaitNext(int observedVersion)
            throws InterruptedException {

        while (version <= observedVersion) {
            wait();
        }

        return version;
    }

    public synchronized void signal() {
        version++;
        notifyAll();
    }
}

class Main4 {
    public static void main(String[] args) throws InterruptedException {
        VersionedSignal signal = new VersionedSignal();

        Runnable signaler = () -> {
            for (int i = 0; i < 500; i++) {
                signal.signal();
            }
        };

        Thread first = new Thread(signaler);
        Thread second = new Thread(signaler);

        first.start();
        second.start();

        first.join();
        second.join();

        // All 1000 signals have committed, so awaitNext returns without
        // blocking and this always prints 1000.
        System.out.println(signal.awaitNext(999));
    }
}
