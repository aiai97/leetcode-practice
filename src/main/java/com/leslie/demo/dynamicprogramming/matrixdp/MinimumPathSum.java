package com.leslie.demo.dynamicprogramming.matrixdp;


import java.util.Arrays;

public class MinimumPathSum {
}
// I like my original solution
// but I am practice recursion
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];

        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i]; // failed
        }

        for (int i = 1; i < m; i++) {
            dp[i][0]= dp[i-1][0] + grid[i][0]; // failed
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) +  grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }
}
class Solution2 {
    int m;
    int n;
    int[][] memo;
    int[][] grid;

    public int minPathSum(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }

        this.grid = grid;
        return dp(m - 1, n - 1);
    }

    public int dp(int row, int col) {
        if (row + col == 0) {
            return grid[row][col];
        }

        if (memo[row][col] != -1) {
            return memo[row][col];
        }

        int ans = Integer.MAX_VALUE;
        if (row > 0) {
            ans = Math.min(ans, dp(row - 1, col));
        }
        if (col > 0) {
            ans = Math.min(ans, dp(row, col - 1));
        }

        memo[row][col] = grid[row][col] + ans;
        return memo[row][col];
    }
}
class Solution22 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (row + col == 0) {
                    continue;
                }

                int ans = Integer.MAX_VALUE;
                if (row > 0) {
                    ans = Math.min(ans, dp[row - 1][col]);
                }
                if (col > 0) {
                    ans = Math.min(ans, dp[row][col - 1]);
                }

                dp[row][col] = grid[row][col] + ans;
            }
        }

        return dp[m - 1][n - 1];
    }
}




