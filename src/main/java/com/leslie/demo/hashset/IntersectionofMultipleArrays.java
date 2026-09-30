package com.leslie.demo.hashset;

import java.util.*;

// no need to do it again because you will use ide -> for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
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
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if(entry.getValue() == numLen){
                res.add(entry.getKey());
            }
        }
            Collections.sort(res);
        return res;
    }
}
