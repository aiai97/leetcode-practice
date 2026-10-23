package com.leslie.demo.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

//failed
public class IPO {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;


        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
        // failed, not so familiar witn comparator syntax,
        // a record for not changing the original array
        Arrays.sort(ids, (a, b) ->
                Integer.compare(capital[a], capital[b])
        );


        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>((a, b) ->
                        Integer.compare(b, a)
                );

        int i = 0;

        for (int round = 0; round < k; round++) {

            // failed,  as long as there are projects and the capital is affrodable
            while (i < n && capital[ids[i]] <= w) {
                maxHeap.offer(profits[ids[i]]);
                i++;
            }


            if (maxHeap.isEmpty()) {
                break;
            }

            w += maxHeap.poll();
        }

        return w;
    }
}
