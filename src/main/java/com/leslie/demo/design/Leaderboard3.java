package com.leslie.demo.design;

import java.util.*;

public class Leaderboard3 {
    record Entry(String userId, int score) {}

    // One current score per user.
    private final Map<String, Integer> scores = new HashMap<>();

   // sort by score descending, then by userId ascending
    private final NavigableSet<Entry> ranking =
            new TreeSet<>(Comparator.comparingInt(Entry::score).reversed().thenComparing(Entry::userId));

    // Higher score first; userId breaks ties.
    public synchronized void update(String userId, int newScore) {
        scores.put(userId, newScore);

        Iterator<Entry> iterator = ranking.iterator();
        while (iterator.hasNext()) {
            Entry element = iterator.next();
            if(element.userId.equals(userId)){
                iterator.remove();
                break;
            }
        }
        ranking.add(new Entry(userId, newScore));
    }

    public synchronized List<Entry> topK(int k) {
        List<Entry> result = new ArrayList<>();
        Iterator<Entry> iterator = ranking.iterator();
        while (iterator.hasNext() && result.size() < k) {
            result.add(iterator.next());
        }
        return result;
    }
}

//1. Create contention: Have 8 threads update the same 100 users concurrently, with each thread assigning different scores to the same users.
//
//2. Test concurrent reads and writes: While updates are in progress, have another thread repeatedly call topK(10). Check for duplicate users, incorrect rankings, or exceptions.
//
//3. Verify the final state: After all threads finish, verify that:
//- The leaderboard contains exactly 100 users.
//- Each user appears only once.
//- Scores are sorted in descending order, with userId in ascending order for ties.-> I missed this one
//- Each user's score is consistent between scores and ranking. -> I missed this one

