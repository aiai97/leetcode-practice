package com.leslie.demo.hashmap.done;

import java.util.HashMap;
import java.util.Map;

//Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

// I prefer hashmap instead of array.sort + compare neighbor
public class ContainsNearbyDuplicate {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int curr = nums[i];
            if(map.containsKey(curr)){
                int prevIndex = map.get(curr);
                if(i - prevIndex <= k){
                    return true;
                }
            }
            map.put(curr,i);
        }
        return false;
    }
}


//class Solution {
//    public boolean containsDuplicate(int[] nums) {
//        Arrays.sort(nums);
//        for(int i = 1; i < nums.length; i++){
//            if(nums[i] == nums[i-1]){
//                return true;
//            }
//        }
//        return false;
//    }
//}