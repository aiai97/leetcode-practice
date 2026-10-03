package com.leslie.demo.dynamicprogramming;

//failed -> too difficult
// have no clues at all and need to do it again
public class BestTimetoBuyandSellStockIV {
}
class Solution2 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][k + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int remain = 1; remain <= k; remain++) {
                for (int holding = 0; holding < 2; holding++) {
                    int ans = dp[i + 1][holding][remain]; // do nothing
                    if (holding == 1) {
                        ans = Math.max(ans, prices[i] + dp[i + 1][0][remain - 1]); // sell
                    }
                    else {
                        ans = Math.max(ans, -prices[i] + dp[i + 1][1][remain]); // buy
                    }
                    dp[i][holding][remain] = ans;
                }
            }
        }

        return dp[0][0][k];
    }
}