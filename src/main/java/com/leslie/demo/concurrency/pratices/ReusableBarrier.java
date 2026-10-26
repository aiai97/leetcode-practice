package com.leslie.demo.concurrency.pratices;

//failed
class ReusableBarrier {
    private final Object lock = new Object();
    private final int parties;
    private int arrived = 0;
    private int generation = 0;

    public ReusableBarrier(int parties) {
        this.parties = parties;
    }

    public void await() throws InterruptedException {
        synchronized (lock) {
            int observedGeneration = generation;
            arrived++;

            if (arrived == parties) {
                arrived = 0;
                generation++;
                lock.notifyAll();
                return;
            }
            //Each thread remembers the generation it entered.
            // When its observed generation differs from the current generation,
            // it means its generation has finished, so it no longer needs to wait.
            while (generation == observedGeneration) {
                lock.wait();
            }
        }
    }
}