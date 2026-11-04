package com.leslie.demo.concurrency.pratices.demo;

import java.util.concurrent.ConcurrentHashMap;

public class UserSessionRegistry {

    private final ConcurrentHashMap<String, String> sessions =
            new ConcurrentHashMap<>();

    public void login(String userId, String sessionId) {
        sessions.put(userId, sessionId);
    }

    public String getSession(String userId) {
        return sessions.get(userId);
    }

    public void logout(String userId) {
        sessions.remove(userId);
    }

    public int onlineUsers() {
        return sessions.size();
    }
}