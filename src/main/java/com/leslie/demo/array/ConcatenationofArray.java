package com.leslie.demo.array;

//done -> no need to do it again
class Solution {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] res = new int[2*n];
        for(int i = 0; i < n;i++){
            res[i] = nums[i];
        }
        for(int i = n; i < 2*n;i++){
            res[i] = nums[i-n];
        }
        return res;
    }
}