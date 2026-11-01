package com.leslie.demo.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// passed, but will do it again
public class LeastNumberOfUniqueIntegers {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(freq.values());
        int unique = freq.size();
        while(!minHeap.isEmpty()){
            int min = minHeap.poll();
            if(min <= k){
                k -= min;
                unique--;
            }
        }
        return unique;
    }
}