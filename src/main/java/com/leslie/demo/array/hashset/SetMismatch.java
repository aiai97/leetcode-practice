package com.leslie.demo.array.hashset;

import java.util.HashSet;
import java.util.Set;

// passed -> beat 34%, no need to do it again
//Q1. Set Mismatch
public class SetMismatch {
    public int[] findErrorNums(int[] nums) {
        Set set = new HashSet<Integer>();
        int duplicate = 1;
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])) duplicate = nums[i];
            set.add(nums[i]);
        }

        int missing = 0;
        for(int i = 0; i <= nums.length; i++){
            if(!set.contains(i)) missing = i;
        }

        return new int[]{duplicate,missing};
    }
}