package com.leslie.demo.array.done.twopointer;

// no need to do it again
public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;

        while(i < j){
            int sum = numbers[i] + numbers[j];

            if(sum == target){
                return new int[]{i + 1, j + 1};
            } else if(sum < target){
                i++;
            } else {
                j--;
            }
        }
        return new int[]{};
    }
}