package com.leslie.demo.dynamicprogramming.direction2;

// failed,
public class MaximumScore {
    public int maximumScore(int[] nums, int[] multipliers) {
        int m = multipliers.length;
        int n = nums.length;
        int[][] dp = new int[m+1][n+1];
        for(int i = m - 1; i >= 0; i--){
            for(int left = i; left >= 0; left--){ // how many numbers have been used from the left
                int mult = multipliers[i];
                int right = n - 1 - (i - left);  // failed, hard to think it out
                dp[i][left] = Math.max(mult*nums[left]+dp[i+1][left+1], // failed, hard to think it out in the beginning
                        mult*nums[right]+dp[i+1][left]);
            }
        }
        return dp[0][0];
    }
}