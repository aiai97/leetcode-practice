package com.leslie.demo.array.globalres;

// passed-> beat 45%
//done -> no need to do it again
//Q3. Max Consecutive Ones
public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int curr = 0;
        int res = 0;
        for(int i = 0;i < nums.length;i++){
            if(nums[i] == 1){
                curr++;
                res = Math.max(res,curr);
            }else{
                curr = 0;
            }
        }
        return res;
    }
}