package com.leslie.demo.queue;

import java.util.*;

// easy -> no need to do it again
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
