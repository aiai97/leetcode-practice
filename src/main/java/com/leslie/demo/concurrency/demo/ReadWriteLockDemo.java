package com.leslie.demo.concurrency.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    static class Cache {
        private final Map<String, String> data = new HashMap<>();

        private final ReadWriteLock lock =
                new ReentrantReadWriteLock();

        public String get(String key) {
            lock.readLock().lock();
            try {
                return data.get(key);
            } finally {
                lock.readLock().unlock();
            }
        }

        public void put(String key, String value) {
            lock.writeLock().lock();
            try {
                data.put(key, value);
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    public static void main(String[] args) {
        Cache cache = new Cache();

        cache.put("name", "Alice");

        Thread reader1 = new Thread(
                () -> System.out.println(cache.get("name")));

        Thread reader2 = new Thread(
                () -> System.out.println(cache.get("name")));

        Thread writer = new Thread(
                () -> cache.put("name", "Bob"));

        reader1.start();
        reader2.start();
        writer.start();
    }
}