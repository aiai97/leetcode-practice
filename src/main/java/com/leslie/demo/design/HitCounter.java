package com.leslie.demo.design;


import java.util.LinkedList;
import java.util.Queue;


// failed
class HitCounter {
    private Queue<Integer> queue;

    public HitCounter() {
        queue = new LinkedList<>();
    }

    public void hit(int timestamp) {
        queue.offer(timestamp);
    }

    public int getHits(int timestamp) {
        // failed, I used to think there will be a time api to get the current time, I misunderstood the question
        // and I didn't realize it will be much better to add it directly while only calculate the size, remove stale data
        while (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
            queue.poll();
        }

        return queue.size();
    }
}
