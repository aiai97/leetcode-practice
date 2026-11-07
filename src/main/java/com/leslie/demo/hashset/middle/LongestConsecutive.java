package com.leslie.demo.hashset.middle;

import java.util.HashSet;
import java.util.Set;

// will implement later
public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num:nums){
            set.add(num);
        }

        int longest = 0;
        for(int num:set){
            int curr = 1;
            int currNum = num;
            if(!set.contains(num-1)){
                while(set.contains(currNum+1)){
                    curr++;
                    currNum++;
                }
                longest = Math.max(longest,curr);
            }
        }
        return longest;
    }
}