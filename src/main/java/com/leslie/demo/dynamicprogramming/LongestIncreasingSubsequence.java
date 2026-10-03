package com.leslie.demo.dynamicprogramming;

import java.util.Arrays;
//failed-> state definition is too complex and it only needs one var + for every element, res[i] >= 1, forgot to res[i] could be the answer
//res[i]-> the length of the of Longest Increasing Subsequence that ends at index i

public class LongestIncreasingSubsequence {
}
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res,1);
        for(int i = 0;i < n;i++){
            for(int j = i;j < n;j++ ){
                if(nums[i] < nums[j]){
                    res[j] = Math.max(res[j],res[i]+1);
                }
            }
        }
        int result = 0;
        for (int x : res) {
            result = Math.max(result, x);
        }

        return result;
    }
}