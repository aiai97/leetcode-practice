package com.leslie.demo.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;

// failed-> I used brute force to solve it, but it will cause TLE.
// https://leetcode.com/problems/sliding-window-maximum/submissions/2164022176/

//class Solution {
//    public int[] maxSlidingWindow(int[] nums, int k) {
//        int n = nums.length;
//        int[] res = new int[n - k + 1];
//
//        for (int left = 0; left <= n - k; left++) {
//            int max = nums[left];
//
//            for (int i = left; i < left + k; i++) {
//                max = Math.max(max, nums[i]);
//            }
//
//            res[left] = max;
//        }
//
//        return res;
//    }
//}

public class SlidingWindowMaximum{
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < nums.length; i++){
            while(!queue.isEmpty() && nums[i] > nums[queue.peekLast()]){
                queue.pollLast(); // decreasing deque for max
            }
            if(!queue.isEmpty() && i - k == queue.getFirst()){
                queue.pollFirst(); // valid window
            }
            queue.offerLast(i);
            if(i >= k - 1){ // OK for collection
                ans[i - k + 1] = nums[queue.peekFirst()];
            }
        }
        return ans;
    }
}
