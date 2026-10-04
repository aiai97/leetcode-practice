package com.leslie.demo.array.slidingWindow;

import java.util.HashMap;
import java.util.Map;

// failed->  forgot to use map.put(0,1);
//https://leetcode.com/problems/subarray-sum-equals-k/
// need a dummy position because some elements could be euqal to k
//checking previous elements for a valid subarray
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        Map<Integer,Integer> map = new HashMap<>();
        // 1,1,1. // 1,2,3 // 1,0,1
        map.put(0,1);
        int prefixSum = 0;
        for(int i = 0; i < nums.length; i++){
            prefixSum += nums[i];
            if(map.containsKey(prefixSum - k)){
                count += map.get(prefixSum - k);
            }
            map.put(prefixSum,map.getOrDefault(prefixSum,0)+1);
        }
        return count;
    }
}
