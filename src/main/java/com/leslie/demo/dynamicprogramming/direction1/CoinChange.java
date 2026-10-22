package com.leslie.demo.dynamicprogramming.direction1;

import java.util.Arrays;

public class CoinChange {
}

//failed -> 1~amount,
class Solution22 {
    public int coinChange(int[] coins, int amount) {
        // dp[], dp[i], to reach i, the mini
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for(int i = 1; i <= amount;i++){
            for(int coin:coins){
                if(i - coin >= 0){ // if it could be reached by this coin,
                    dp[i] = Math.min(dp[i],dp[i-coin]+1);
                }
            }
        }
        return dp[amount] == amount+1 ? -1:dp[amount];
    }
}

// the recursion one is much more difficult than the iteration one
class Solution222 {
    public int coinChange(int[] coins, int amount) {
        int ans = dp(coins, amount);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int dp(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return Integer.MAX_VALUE;
        }

        int ans = Integer.MAX_VALUE;

        for (int coin : coins) {
            int sub = dp(coins, amount - coin);

            /// failed
            if (sub != Integer.MAX_VALUE) {
                ans = Math.min(ans, sub + 1);
            }
        }

        return ans;
    }
}
