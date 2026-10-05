package com.leslie.demo.array.prefixsum;

// failed-> need to do it again -> I failed to notice it asks me to count how many ways and I used to write count++;
//https://leetcode.com/problems/count-number-of-nice-subarrays/
// best solution -> to use prefix sum
public class CountNumberofNiceSubarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        int left = 0;
        int oddCount = 0;
        int evenCount = 0;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {

            if (nums[right] % 2 != 0) {
                oddCount++;
            }

            while (oddCount > k) {
                if (nums[left] % 2 != 0) {
                    oddCount--;
                }
                left++;
            }

            if (oddCount == k) { //failed
                evenCount = 0;
                int temp = left;

                while (temp <= right && nums[temp] % 2 == 0) {
                    evenCount++;
                    temp++;
                }

                count += evenCount + 1; // failed
            }
        }

        return count;
    }
}