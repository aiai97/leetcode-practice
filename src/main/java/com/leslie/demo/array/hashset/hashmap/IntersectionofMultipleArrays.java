package com.leslie.demo.array.hashset.hashmap;

import java.util.*;

// easy -> no need to do it again
public class IntersectionofMultipleArrays {
    public List<Integer> intersection(int[][] nums) {
        Map<Integer,Integer> counts = new HashMap<>();
        for(int[] arr: nums){
            for(int num: arr){
                counts.put(num,counts.getOrDefault(num, 0)+1);
            }
        }

        List<Integer> res = new ArrayList<>();
        int numLen = nums.length;
        for (Map.Entry<Integer,Integer> entry : counts.entrySet()) {
            if(entry.getValue() == numLen){
                res.add(entry.getKey());
            }
        }
        Collections.sort(res);
        return res;
    }
}