package com.leslie.demo.dynamicprogramming.direction1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestIncreasingSubsequence {
}


class Solution1 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res,1); // the base cases I need to watch out for
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
// thank God. I wrote it down correctly.
class LongestIncreasingSubsequenceDPSolution {
    Map<Integer, Integer> memo = new HashMap<>();

    public int lengthOfLIS(int[] nums) {
        int ans = 0;
        for(int i = 0; i < nums.length; i++){
            ans = Math.max(ans,dp(i,nums));
        }
        return ans;
    }

    private int dp(int index, int[] nums) {

        if(memo.containsKey(index)){
            return memo.get(index);
        }
        int ans = 1;
        for(int i = 0; i < index; i++){
            if(nums[index] > nums[i]){
                ans = Math.max(dp(i,nums)+ 1,ans);
            }
        }
        memo.put(index, ans);
        return ans;
    }

    // only need to track index i , I need to get familiar with regional variable
    private int dp1(int index,int[] nums){
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        int ans = 1; // not familiar, its base cases are not obvious here

        //every number
        for (int j = index + 1; j < nums.length; j++) {
            if (nums[j] > nums[index]) {
                ans = Math.max(dp1(j, nums) + 1, ans);
            }
        }
        memo.put(index, ans); // not familiar
        return ans;

    }
}