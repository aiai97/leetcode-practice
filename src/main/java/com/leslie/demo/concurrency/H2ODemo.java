package com.leslie.demo.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;


class H2O {
    private final Semaphore hydrogenSlots = new Semaphore(2);
    private final Semaphore oxygenSlots = new Semaphore(1);
    private final CyclicBarrier molecule = new CyclicBarrier(3);

    public H2O() {}

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        bond(hydrogenSlots, releaseHydrogen);
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        bond(oxygenSlots, releaseOxygen);
    }

    // I cannot write it out
    private void bond(Semaphore slots, Runnable releaseAtom)
            throws InterruptedException {
        slots.acquire();
        try {
            releaseAtom.run();
            awaitMolecule();
        } finally {
            slots.release();
        }
    }

    private void awaitMolecule() throws InterruptedException {
        try {
            molecule.await();
        } catch (BrokenBarrierException exception) {
            throw new IllegalStateException("Molecule barrier was broken", exception);
        }
    }
}


class H2ODemo1 {
    public static void main(String[] args) throws InterruptedException {
        H2O h2o = new H2O();
        StringBuilder output = new StringBuilder();

        Runnable releaseHydrogen = () -> {
            synchronized (output) {
                output.append('H');
            }
        };
        Runnable releaseOxygen = () -> {
            synchronized (output) {
                output.append('O');
            }
        };

        String water = "OOHHHH";
        Thread[] atoms = new Thread[water.length()];
        for (int i = 0; i < water.length(); i++) {
            if (water.charAt(i) == 'H') {
                atoms[i] = new Thread(() -> {
                    try {
                        h2o.hydrogen(releaseHydrogen);
                    } catch (InterruptedException exception) {
                        Thread.currentThread().interrupt();
                    }
                });
            } else {
                atoms[i] = new Thread(() -> {
                    try {
                        h2o.oxygen(releaseOxygen);
                    } catch (InterruptedException exception) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            atoms[i].start();
        }
        for (Thread atom : atoms) {
            atom.join();
        }

        int molecules = 0;
        for (int i = 0; i + 2 < output.length(); i += 3) {
            int hydrogens = 0;
            for (int j = i; j < i + 3; j++) {
                if (output.charAt(j) == 'H') {
                    hydrogens++;
                }
            }
            if (hydrogens == 2) {
                molecules++;
            }
        }

        // Every group of three atoms bonds as two H and one O, so this always prints 2.
        System.out.println(molecules);
    }
}