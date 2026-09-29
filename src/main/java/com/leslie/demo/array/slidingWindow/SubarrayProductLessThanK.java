package com.leslie.demo.array.slidingWindow;

// failed because I didn't read the question carefully -> the product of all the elements in the subarray is strictly less than k.
// I used to misunderstand it as the sum of all the elements in the subarray is equal to k.
// no need to do it again
public class SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k <=1) return 0;
        int count = 0;
        int currProduct = 1;
        int j = 0;
        for(int i = 0; i < nums.length; i++){
            currProduct *= nums[i];
            while(currProduct >= k){
                currProduct = currProduct / nums[j];
                j++;
            }
            count += i - j + 1;
        }
        return count;
    }
}