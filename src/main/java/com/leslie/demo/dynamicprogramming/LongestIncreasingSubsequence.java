package com.leslie.demo.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
// failed -> need to do it again , initializers not familiar _
// for every element, wanna calculate its longest increasing subsequence
class LongestIncreasingSubsequenceDPSolution {
    Map<Integer, Integer> memo = new HashMap<>();

    public int lengthOfLIS(int[] nums) {
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            result = Math.max(result, dp(i, nums));
        }

        return result;
    }

    private int dp(int i,int[] nums){
        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        int ans = 1; // not familiar

        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] > nums[i]) {
                ans = Math.max(dp(j, nums) + 1, ans);
            }
        }
        memo.put(i, ans); // not familiar
        return ans;

    }
}