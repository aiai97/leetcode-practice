package com.leslie.demo.design;

import java.util.LinkedList;
import java.util.Queue;

class RecentCounter {

    Queue<Integer> queue;
    public RecentCounter() {
        queue = new LinkedList<Integer>();
    }

    public int ping(int t) {
        queue.offer(t);
        while(!queue.isEmpty() && t - queue.peek() > 3000){
            queue.poll();
        }
        return queue.size();
    }
}
