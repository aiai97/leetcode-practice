package com.leslie.demo.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// failed
public class LeastNumberOfUniqueIntegers {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // 4,3,1,1,3,3,2
        // 3,1,4,2
        // 3,2,1,1
        // failed, amazing, it could just load the values
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(freq.values());
        int unique = freq.size();
        while (k > 0 && !minHeap.isEmpty()) {
            int count = minHeap.poll();

            if (k >= count) {
                k -= count;
                unique--;
            } else {
                break;
            }
        }
        return unique;
    }
}