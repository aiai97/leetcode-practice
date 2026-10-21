package com.leslie.demo.design;

import java.util.*;

public class Leaderboard2 {
    // score -> users with that score
    private final NavigableMap<Integer, Set<String>> ranking =
            new TreeMap<>();

    // userId -> current score
    private final Map<String, Integer> userScores =
            new HashMap<>();

    public synchronized void update(String userId, int newScore) {
        Integer oldScore = userScores.get(userId);

        // Remove the user from the old score group.
        if (oldScore != null) {
            Set<String> users = ranking.get(oldScore);
            users.remove(userId);

            // Remove an empty score group.
            if (users.isEmpty()) {
                ranking.remove(oldScore);
            }
        }

        // Add the user to the new score group.
        ranking.computeIfAbsent(
                newScore, score -> new HashSet<>()
        ).add(userId);

        userScores.put(userId, newScore);
    }

    public synchronized List<String> topK(int k) {
        List<String> result = new ArrayList<>();

        // Highest score first.
        for (var entry : ranking.descendingMap().entrySet()) {
            for (String userId : entry.getValue()) {
                result.add(userId);

                if (result.size() == k) {
                    return result;
                }
            }
        }

        return result;
    }

    public synchronized Integer getScore(String userId) {
        return userScores.get(userId);
    }
}