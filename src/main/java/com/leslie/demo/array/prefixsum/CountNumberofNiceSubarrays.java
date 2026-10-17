package com.leslie.demo.array.prefixsum;

import java.util.HashMap;
import java.util.Map;

// failed-> need to do it again -> I failed to notice it asks me to count how many ways and I used to write count++;
//https://leetcode.com/problems/count-number-of-nice-subarrays/
// best solution -> to use prefix sum
public class CountNumberofNiceSubarrays {

    // easy -> not need to do it again
    public int numberOfSubarrays(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap();
        int prefixSum = 0;
        // 1,1,2,1,1
        // 1,1,0,1,1
        // 1,2,2,3,4
        map.put(0, 1);
        int count = 0;
        for(int num:nums){
            prefixSum += num % 2;
            count += map.getOrDefault(prefixSum - k, 0);
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }

    public int numberOfSubarrays1(int[] nums, int k) {
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