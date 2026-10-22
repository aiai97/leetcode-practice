package com.leslie.demo.dynamicprogramming.direction1;

import java.util.HashMap;


//  failed -> need to do it again
public class HouseRobber {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0]; // I usually failed at initial values
        // at house i, how much at most I can rob
        if(n >= 2) dp[1] =  Math.max(nums[0],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        return dp[n-1];
    }
}


//failed -> need to do it again
class HouseRobberDPSolution {
    HashMap<Integer, Integer> memo;

    public int rob(int[] nums) {
        memo = new HashMap<>();
        return dp(nums.length - 1, nums); // failed -> index is not right
    }

    private int dp(int i, int[] nums) {
        if (i == 0) return nums[i]; // failed -> index is not right
        if (i == 1) return Math.max(nums[0], nums[1]); // failed -> index is not right
        if (memo.containsKey(i)) {
            return memo.get(i);
        }
        int ans = Math.max(dp(i - 2, nums) + nums[i], dp(i - 1, nums));
        memo.put(i, ans);
        return ans;
    }
}
