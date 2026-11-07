package com.leslie.demo.hashmap.todo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class HowManyNumbersAreSmallerThantheCurrentNumber {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < sorted.length; i++) {
            map.putIfAbsent(sorted[i], i);
        }

        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            res[i] = map.get(nums[i]);
        }

        return res;
    }
}


//class HowManyNumbersAreSmallerThantheCurrentNumber1 {
//    public int[] smallerNumbersThanCurrent(int[] nums) {
//        int[] res = new int[nums.length];
//        for(int i = 0; i < nums.length; i++){
//            for(int j = 0; j < nums.length;j++){
//                if(nums[i] > nums[j]){
//                    res[i] = res[i] + 1;
//                }
//            }
//        }
//        return res;
//    }
//}

// could be done with sorting + hashmap