package com.leslie.demo.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

// passed -> beat 99%, no need to do it again
//https://leetcode.com/problems/number-of-provinces/submissions/1466425343/
public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> q = new PriorityQueue(Comparator.reverseOrder());
        for(int num:stones){
            q.add(num);
        }
        while(q.size() > 1){
            int heavierStone =  q.remove();
            int lighterStone =  q.remove();
            q.add(heavierStone - lighterStone);
        }

        if(q.isEmpty()){
            return 0;
        }else{
            return q.remove();
        }
    }
}
