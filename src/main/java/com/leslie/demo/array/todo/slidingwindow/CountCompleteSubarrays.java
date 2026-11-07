package com.leslie.demo.array.todo.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// failed
public class CountCompleteSubarrays {
    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int totalDistinct = set.size();

        Map<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {

            // Add the rightmost element into the window
            map.put(nums[right],
                    map.getOrDefault(nums[right], 0) + 1);

            // Shrink the window while it contains all distinct elements
            while (map.size() == totalDistinct) {

                int num = nums[left];

                map.put(num, map.get(num) - 1);

                if (map.get(num) == 0) {
                    map.remove(num);
                }

                left++;
            }

            // failed here
            ans += left;
        }

        return ans;
    }
}
