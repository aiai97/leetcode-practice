package com.leslie.demo.concurrency.pratices.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConfigurationService {

    private final Map<String, String> config =
            new HashMap<>();

    private final ReentrantReadWriteLock lock =
            new ReentrantReadWriteLock();

    public String get(String key) {

        lock.readLock().lock();

        try {
            return config.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void update(String key, String value) {

        lock.writeLock().lock();

        try {
            config.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }
}