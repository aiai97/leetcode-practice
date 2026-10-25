package com.leslie.demo.dynamicprogramming.direction1;

// failed
public class Tribonacci {
    public int tribonacci(int n) {  //  failed, because I wrote it to be 0:n
        if(n < 3) return n == 0? 0:1;
        int[] dp = new int[n+1];
        dp[1] = dp[2] = 1;
        for(int i = 3; i <= n; i++){
            dp[i] = dp[i-1]+ dp[i-2]+dp[i-3];
        }
        return dp[n];
    }
}