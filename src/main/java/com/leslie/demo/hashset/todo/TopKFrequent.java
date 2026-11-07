package com.leslie.demo.hashset.todo;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// failed
public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> count = new HashMap<>();
        for(int num:nums){
            count.put(num, count.getOrDefault(num, 0)+1);
        }

        // failed cannot think of this comparator
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
                (n1,n2)->count.get(n1)- count.get(n2)
        );
        for(int n: count.keySet()){
            minHeap.add(n);
            if(minHeap.size() > k) minHeap.poll();
        }


        int[] top = new int[k];
        for(int i = k - 1; i >= 0; i--){
            // heap: add + poll, while deque: offer + poll
            top[i] = minHeap.poll();
        }

        return top;
    }
}

// 1，1，1，2，2，3
// 1，2，3
// 3，2，1

// heap, maxHeap
//