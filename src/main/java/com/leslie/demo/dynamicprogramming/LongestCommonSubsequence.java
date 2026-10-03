package com.leslie.demo.dynamicprogramming;

public class LongestCommonSubsequence {
}
// failed->  the index of the dp array is not the same as the index of the string, text1.charAt(i-1) == text2.charAt(j-1)
// LCS-> the length of the longest common subsequence of text1[0..i-1] and text2[0..j-1]
class Solution1 {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] res = new int[m+1][n+1];
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    res[i][j] = res[i-1][j-1]+1;  // take both
                }else{
                    res[i][j] = Math.max(res[i-1][j] ,res[i][j-1]); // skip one
                }
            }
        }
        return res[m][n];
    }
}