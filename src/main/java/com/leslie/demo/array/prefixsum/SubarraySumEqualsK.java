package com.leslie.demo.array.prefixsum;

import java.util.HashMap;
import java.util.Map;

// I used sliding window and that  negative number are allowed made me failed
//A prefix sum is the cumulative sum of the elements from the beginning of an array up to the current position.
//nums = [1, 2, 3, 4]
//prefix sums = [1, 3, 6, 10]
//2. How do we use a HashMap?

//We use a HashMap to store:
//Prefix Sum → Frequency (number of occurrences)
//When we reach the current position:
//Current Prefix Sum - k = Previous Prefix Sum to Find
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(map.containsKey(sum-k)){
                count += map.get(sum - k);
            }
            map.put(sum,map.getOrDefault(sum, 0)+1);
        }
        return count;
    }
}