package com.leslie.demo.concurrency.demo;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteDemo {

    interface Listener {
        void onEvent(String event);
    }

    static class EventBus {
        private final CopyOnWriteArrayList<Listener> listeners =
                new CopyOnWriteArrayList<>();

        public void register(Listener listener) {
            listeners.add(listener);
        }

        public void unregister(Listener listener) {
            listeners.remove(listener);
        }

        public void publish(String event) {
            for (Listener listener : listeners) {
                listener.onEvent(event);
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        EventBus bus = new EventBus();

        Listener alice = event ->
                System.out.println("Alice received: " + event);

        bus.register(alice);

        Thread publisher = new Thread(
                () -> bus.publish("OrderCreated"));

        publisher.start();

        System.out.println("------");

        Listener bob = event ->
                System.out.println("Bob received: " + event);

        bus.register(bob);
        System.out.println("------1");

        publisher.join();
        System.out.println("------2");

        bus.publish("OrderPaid");
    }
}