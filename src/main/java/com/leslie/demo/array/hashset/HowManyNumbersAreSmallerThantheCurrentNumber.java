package com.leslie.demo.array.hashset;

// failed -> but no need to do it again because you misunderstood the question
// you missed the point that the smaller element could be from any positions of the array
public class HowManyNumbersAreSmallerThantheCurrentNumber {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < nums.length;j++){
                if(nums[i] > nums[j]){
                    res[i] = res[i] + 1;
                }
            }
        }
        return res;
    }
}

// could be done with sorting + hashmap