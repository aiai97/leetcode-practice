package com.leslie.demo.array.twopinter;

public class SquaresofaSortedArray {
}
//failed -> should see it as a line instead of letting left and right pointer be the stopping condition
class Solution {
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;

        int[] res = new int[len];
        for(int j = len - 1;j >= 0; j--){
            if(Math.abs(nums[left]) >= Math.abs(nums[right])){
                res[j] = nums[left] * nums[left];
                left++;
            }else{
                res[j] = nums[right] * nums[right];
                right--;
            }
        }
        return res;
    }
}