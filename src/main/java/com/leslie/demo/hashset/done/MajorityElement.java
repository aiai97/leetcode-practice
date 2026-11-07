package com.leslie.demo.hashset.done;

import java.util.HashMap;
import java.util.Map;

// arrays.sort(); +  the middle element is the majority element
public class MajorityElement {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);

            if (counts.get(num) > nums.length / 2) {
                return num;
            }
        }

        return -1;
    }
}