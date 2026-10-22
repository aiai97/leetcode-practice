package com.leslie.demo.dynamicprogramming.direction2;

import java.util.Arrays;

public class LongestCommonSubsequence {
}

// failed
class Solution1 {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] res = new int[m+1][n+1]; // failed in realize dp's size need to be this way to calculate the last element
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    res[i][j] = res[i-1][j-1]+1;
                }else{
                    res[i][j] = Math.max(res[i-1][j] ,res[i][j-1]); // misunderstood the dps' constraints, it does't say text2 must be the substring,
                }
            }
        }
        return res[m][n];
    }
}

// will do it next time
class Solution2 {
    int m;
    int n;
    int[][] memo;
    String text1;
    String text2;

    public int longestCommonSubsequence(String text1, String text2) {
        m = text1.length();
        n = text2.length();

        this.text1 = text1;
        this.text2 = text2;

        memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }

        return dp(m - 1, n - 1);
    }

    public int dp(int i, int j) {
        if (i < 0 || j < 0) { // failed,  base cases
            return 0;
        }

        if (memo[i][j] != -1) { //failed, check whether it exists in the memo
            return memo[i][j];
        }

        int ans;
        if (text1.charAt(i) == text2.charAt(j)) {
            ans = 1 + dp(i - 1, j - 1);
        } else {
            ans = Math.max(
                    dp(i - 1, j),
                    dp(i, j - 1)
            );
        }

        memo[i][j] = ans;
        return ans;
    }
}


class Solution {
    int m;
    int n;
    int[][] memo;
    String text1;
    String text2;

    public int longestCommonSubsequence(String text1, String text2) {
        m = text1.length();
        n = text2.length();
        this.text1 = text1;
        this.text2 = text2;
        memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }

        return dp(0, 0);
    }

    public int dp(int i, int j) {
        if (i == m || j == n) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int ans = 0;
        if (text1.charAt(i) == text2.charAt(j)) {
            ans = 1 + dp(i + 1, j + 1);
        } else {
            ans = Math.max(dp(i + 1, j), dp(i, j + 1));
        }

        memo[i][j] = ans;
        return ans;
    }
}