package com.leslie.demo.concurrency.pratices.basic;

class BankAccount {
    private long balance;
    private final Object lock;

    public BankAccount(long initialBalance) {
        balance = initialBalance;
        lock = new Object();
    }

    public void deposit(long amount) {
        synchronized (lock) {
            balance += amount;
        }
    }

    public boolean withdraw(long amount) {
        synchronized (lock) {
            if (balance < amount) {
                return false;
            }
            balance -= amount;
            return true;
        }
    }

    public long getBalance() {
        synchronized (lock) {
            return balance;
        }
    }
}



class Main {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount(100000);

        // do operation for 1000 times,
        Runnable customer = () -> {
            for (int i = 0; i < 1000; i++) {
                account.deposit(7);
                account.withdraw(5);
            }
        };

        // split the tasks into 2 threads
        Thread first = new Thread(customer);
        Thread second = new Thread(customer);

        first.start();
        second.start();

        first.join();
        second.join();

        // Each iteration adds 7 and removes 5, so this always prints 104000.
        System.out.println(account.getBalance());
    }
}
