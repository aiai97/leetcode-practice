package com.leslie.demo.dynamicprogramming.matrixdp;

import java.util.Arrays;

// passed-> no need to do it again
public class MatrixDP {

    public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i++){
            res[i][0] = 1;
        }
        for(int i = 0; i < n; i++){
            res[0][i] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                res[i][j] = res[i-1][j] + res[i][j-1];
            }
        }
        return res[m-1][n-1];
    }
}

class Solution3 {
    int[][] memo;

    public int uniquePaths(int m, int n) {
        memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }

        return dp(m - 1, n - 1);
    }

    public int dp(int row, int col) {
        if (row + col == 0) { // if we are at the top left corner, we have only one way to reach there
            return 1; // Base case
        }

        if (memo[row][col] != -1) {
            return memo[row][col];
        }

        int ways = 0;
        if (row > 0) {
            ways += dp(row - 1, col);
        }
        if (col > 0) {
            ways += dp(row, col - 1);
        }

        memo[row][col] = ways;
        return ways;
    }
}

class Solution31 {
    public int uniquePaths(int m, int n) {
        int dp[][] = new int[m][n];
        dp[0][0] = 1;

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (row > 0) {
                    dp[row][col] += dp[row - 1][col];
                }
                if (col > 0) {
                    dp[row][col] += dp[row][col - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
