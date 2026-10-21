package com.leslie.demo.design;


import java.util.*;

public class Leaderboard {
    record Entry(String userId, int score) {}

    // One current score per user.
    private final Map<String, Integer> scores = new HashMap<>();

    // Higher score first; userId breaks ties.
    private final NavigableSet<Entry> ranking =
            new TreeSet<>(
                    Comparator.comparingInt(Entry::score).reversed()
                            .thenComparing(Entry::userId)
            );

    public synchronized void update(String userId, int newScore) {
        Integer oldScore = scores.get(userId);

        if (oldScore != null) {
            ranking.remove(new Entry(userId, oldScore));
        }

        scores.put(userId, newScore);
        ranking.add(new Entry(userId, newScore));
    }

    public synchronized List<Entry> topK(int k) {
        List<Entry> result = new ArrayList<>();

        for (Entry entry : ranking) {
            if (result.size() == k) break;
            result.add(entry);
        }

        return result;
    }
}
