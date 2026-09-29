package com.leslie.demo.array.hashset;


import java.util.HashSet;
import java.util.Set;

// failed in the interview-> I failed in the solution 2,(I missing the full version in interviews)
//3 solution: use XOR to find the missing number
// hashset -> original ones, compare with the full version of the array
// array-> fill in the array, find the missing one
class MissingNumber {
    public static void main(String[] args) {
        int[] nums = {0,1,3};
        MissingNumber m = new MissingNumber();
        System.out.println(missingNumber1(nums));
    }
    public static int missingNumber1(int[] nums) {
        int missing = nums.length;
        for(int i = 0; i < nums.length; i++){
            missing ^= i;
            missing ^= nums[i];
        }
        return missing;

    }
    public int missingNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        for (int i = 0; i <= nums.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        return -1;
    }

    public int missingNumber3(int[] nums) {
        int n = nums.length;

        int[] arr = new int[n + 1];

        for (int num : nums) {
            arr[num] = 1;
        }

        for (int i = 0; i <= n; i++) {
            if (arr[i] == 0) {
                return i;
            }
        }

        return -1;
    }
}