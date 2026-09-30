package com.leslie.demo.queue;

import java.util.*;

// passed -> no need to do it again
// I prefer to use linkedlist but I cannot remember its api so I use ArrayDeque
class RecentCounter {
    int requestTime;
    Queue<Integer> queue;

    public RecentCounter() {
        this.requestTime = 3000;
        queue = new ArrayDeque<Integer>();
    }

    public int ping(int t) {

        while(!queue.isEmpty() && t - queue.peek() > this.requestTime){
            queue.poll();
        }
        queue.offer(t);
        return queue.size();
    }
}

class RecentCounter1 {
    int requestTime;
    Queue<Integer> queue;

    public RecentCounter1() {
        this.requestTime = 3000;
        queue = new LinkedList<Integer>();
    }

    public int ping(int t) {
        while (!queue.isEmpty() && t - queue.peek() > requestTime) {
            queue.poll();
        }

        queue.offer(t);
        return queue.size();
    }
}
