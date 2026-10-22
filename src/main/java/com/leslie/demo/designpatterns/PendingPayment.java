package com.leslie.demo.designpatterns;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

interface OrderState {
    void pay(Order order);
    void cancel(Order order);
    void ship(Order order);
    void confirmReceipt(Order order);
}

class PendingPayment implements OrderState {
    public void pay(Order order) {
        order.setState(new PendingShipment());
    }

    public void cancel(Order order) {
        order.setState(new Cancelled());
    }

    public void ship(Order order) {
        throw new IllegalStateException("Payment required first");
    }

    public void confirmReceipt(Order order) {
        throw new IllegalStateException("Cannot receive before shipping");
    }
}

class PendingShipment implements OrderState {
    public void pay(Order order) {
        throw new IllegalStateException("Already paid");
    }

    public void cancel(Order order) {
        throw new IllegalStateException("Cannot cancel in this state");
    }

    public void ship(Order order) {
        order.setState(new InTransit());
    }

    public void confirmReceipt(Order order) {
        throw new IllegalStateException("Not shipped yet");
    }
}

class InTransit implements OrderState {
    public void pay(Order order) {
        throw new IllegalStateException("Already paid");
    }

    public void cancel(Order order) {
        throw new IllegalStateException("Already shipped");
    }

    public void ship(Order order) {
        throw new IllegalStateException("Already shipped");
    }

    public void confirmReceipt(Order order) {
        order.setState(new Completed());
    }
}

class Completed implements OrderState {
    public void pay(Order order) {
        reject();
    }

    public void cancel(Order order) {
        reject();
    }

    public void ship(Order order) {
        reject();
    }

    public void confirmReceipt(Order order) {
        reject();
    }

    private void reject() {
        throw new IllegalStateException("Order completed");
    }
}

class Cancelled implements OrderState {
    public void pay(Order order) {
        reject();
    }

    public void cancel(Order order) {
        reject();
    }

    public void ship(Order order) {
        reject();
    }

    public void confirmReceipt(Order order) {
        reject();
    }

    private void reject() {
        throw new IllegalStateException("Order cancelled");
    }
}

class Order {
    private OrderState state = new PendingPayment();

    public synchronized void pay() {
        state.pay(this);
    }

    public synchronized void cancel() {
        state.cancel(this);
    }

    public synchronized void ship() {
        state.ship(this);
    }

    public synchronized void confirmReceipt() {
        state.confirmReceipt(this);
    }

    void setState(OrderState state) {
        this.state = state;
    }

    public synchronized String getStateName() {
        return state.getClass().getSimpleName();
    }

    static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    static void expectFailure(Runnable action) {
        try {
            action.run();
        } catch (IllegalStateException expected) {
            System.out.println("Rejected invalid transition.");
            return;
        }

        throw new AssertionError("Expected operation to fail");
    }

    public static void main(String[] args) throws Exception {
        // 1. Test valid transitions
        Order order = new Order();

        order.pay();
        check(order.getStateName().equals("PendingShipment"),
                "Payment should move order to PendingShipment");

        order.ship();
        check(order.getStateName().equals("InTransit"),
                "Shipping should move order to InTransit");

        order.confirmReceipt();
        check(order.getStateName().equals("Completed"),
                "Receipt should move order to Completed");

        // 2. Test invalid transition
        expectFailure(order::cancel);
        check(order.getStateName().equals("Completed"),
                "Completed order must remain unchanged");

        System.out.println("Valid/invalid transition tests passed.");

        // 3. Race: payment vs cancellation
        Order concurrentOrder = new Order();
        ExecutorService pool = Executors.newFixedThreadPool(2);
        CountDownLatch ready = new CountDownLatch(2);
        CountDownLatch start = new CountDownLatch(1);
        AtomicInteger successes = new AtomicInteger();

        try {
            Future<?> payment = pool.submit(() -> {
                ready.countDown();
                start.await();

                try {
                    concurrentOrder.pay();
                    successes.incrementAndGet();
                } catch (IllegalStateException ignored) {
                    // Another transition won.
                }

                return null;
            });

            Future<?> cancellation = pool.submit(() -> {
                ready.countDown();
                start.await();

                try {
                    concurrentOrder.cancel();
                    successes.incrementAndGet();
                } catch (IllegalStateException ignored) {
                    // Another transition won.
                }

                return null;
            });

            ready.await();
            start.countDown();

            payment.get();
            cancellation.get();

            check(successes.get() == 1,
                    "Exactly one transition should succeed");

            String state = concurrentOrder.getStateName();
            check(state.equals("PendingShipment")
                            || state.equals("Cancelled"),
                    "Unexpected final state: " + state);

            System.out.println("Concurrent test passed.");
            System.out.println("Final state: " + state);
        } finally {
            pool.shutdownNow();
        }
    }
}
