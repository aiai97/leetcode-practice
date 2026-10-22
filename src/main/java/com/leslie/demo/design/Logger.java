package com.leslie.demo.design;


import java.util.HashMap;
import java.util.Map;

class Logger {
    private Map<String, Integer> lastPrinted;

    public Logger() {
        lastPrinted = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!lastPrinted.containsKey(message)
                || timestamp - lastPrinted.get(message) >= 10) {
            lastPrinted.put(message, timestamp);
            return true;
        }

        return false;
    }
}