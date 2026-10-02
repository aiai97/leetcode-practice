package com.leslie.demo.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumOperationstoHalveArraySum {
}
//failed -> should use double  + every operation should count + should calculate the reduced sum instead of the remaining
// misunderstood the question, Note that you may choose this reduced number in future operations.(this means I can push the elemment back to the queue)
// https://leetcode.com/problems/minimum-operations-to-halve-array-sum/description/
class Solution {
    public int halveArray(int[] nums) {
        PriorityQueue<Double> q = new PriorityQueue(Comparator.reverseOrder());
        double sum = 0;
        for(int num:nums){
            q.add((double)num);
            sum += num;
        }

        double target = sum / 2;
        double reduced = 0;
        int count = 0;
        while(!q.isEmpty()){
            double num = q.remove();
            reduced += num / 2;
            q.add(num / 2);
            count++;
            if(reduced >= target) break;
        }
        return count;
    }
}