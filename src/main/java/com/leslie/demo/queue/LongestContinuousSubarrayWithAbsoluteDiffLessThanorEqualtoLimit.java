package com.leslie.demo.queue;

import java.util.ArrayDeque;

// failed even with brute force
// will do it again
//https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/submissions/
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit {
}
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int res = 0;
        for(int i = 0; i < nums.length;i++){
            int min = nums[i];
            int max = nums[i];
            for(int j = i; j < nums.length;j++){
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                if(max - min <= limit){
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }
}
