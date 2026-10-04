package com.leslie.demo.array.prefixsum;

//failed -> forgot to use long to store the sum, need to do it again
public class NumberofWaystoSplitArray {
}
class Solution {
    public int waysToSplitArray(int[] nums) {
        int n = nums.length;
        long[] prefixSum = new long[n];
        prefixSum[0] = nums[0];
        long sum = nums[0];
        for(int i = 1; i < n; i++){
            prefixSum[i] = prefixSum[i -1] + nums[i];
            sum += nums[i];
        }

        int count = 0;
        for(int i = 0; i < n - 1; i++){
            if(prefixSum[i] >= sum - prefixSum[i]){
                count++;
            }
        }
        return count;

    }
}