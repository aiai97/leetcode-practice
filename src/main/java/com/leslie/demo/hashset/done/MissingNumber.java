package com.leslie.demo.hashset.done;


import java.util.HashSet;
import java.util.Set;

// failed in the interview-> I failed in the solution 2,(I missing the full version in interviews)
//3 solution: use XOR to find the missing number
// hashset -> original ones, compare with the full version of the array
// array-> fill in the array, find the missing one
class MissingNumber {
    public static void main(String[] args) {
        int[] nums = {0, 1, 3};
        MissingNumber m = new MissingNumber();
    }

    // I understood this solution
    class Solution {
        public int missingNumber(int[] nums) {
            int missing = nums.length;
            for (int i = 0; i < nums.length; i++) {
                missing ^= nums[i] ^ i;
            }
            return missing;
        }
    }

    //latest
    public int missingNumber(int[] nums) {
        int n = nums.length;
        Set set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 0; i <= n; i++) {
            if (!set.contains(i)) return i;
        }
        return 0;
    }
}

