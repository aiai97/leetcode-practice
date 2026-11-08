package com.leslie.demo.array.done.prefixsum;

import java.util.HashMap;
import java.util.Map;

// passs
//https://leetcode.com/problems/count-number-of-nice-subarrays/
public class CountNumberofNiceSubarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap();
        int prefixSum = 0;
        map.put(0, 1);
        int count = 0;
        for(int num:nums){
            prefixSum += num % 2;
            if(map.containsKey(prefixSum - k)){
                count += map.get(prefixSum - k);
            }
            map.put(prefixSum,map.getOrDefault(prefixSum,0)+1);
        }
        return count;
    }
    }