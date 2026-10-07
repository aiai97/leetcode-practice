package com.leslie.demo.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;
// failed
public class LongestContinuousSubarray {
}
//https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
class Solution2 {
    public int longestSubarray(int[] nums, int limit) {
        int res = 0;
        Deque<Integer> decreasingQueue = new ArrayDeque<>();
        Deque<Integer> increasingQueue = new ArrayDeque<>();
        int left = 0;
        for(int i = 0; i < nums.length; i++){
            while(!decreasingQueue.isEmpty() && nums[i] > nums[decreasingQueue.peekLast()]){
                decreasingQueue.pollLast();
            }
            while(!increasingQueue.isEmpty() && nums[i] < nums[increasingQueue.peekLast()]){
                increasingQueue.pollLast();
            }

            decreasingQueue.offerLast(i);
            increasingQueue.offerLast(i);
            while(nums[decreasingQueue.peekFirst()]- nums[increasingQueue.peekFirst()] > limit){
                // failed in checking valid window
                // left represents the earliest position that can still remain in the current window.
                if (decreasingQueue.peekFirst() == left) {
                    decreasingQueue.pollFirst();
                }

                if (increasingQueue.peekFirst() == left) {
                    increasingQueue.pollFirst();
                }

                left++;

            }
            res = Math.max(res,i-left+1);
        }
        return res;
    }
}