package com.leslie.demo.dynamicprogramming.direction1;


import java.util.HashMap;

// easy -> passed
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        // to reach step i, how much it will cost
        int n = cost.length;
        int[] res = new int[n+1];
        for (int i = 2; i <= n; i++) {
            res[i] = Math.min(res[i-1] + cost[i-1],res[i-2] + cost[i-2]);
        }
        return res[n];
    }
}

class Solution {
    HashMap<Integer,Integer> memo;
    public int minCostClimbingStairs(int[] cost) {
        memo = new HashMap<>();
        return dfs(cost.length, cost);
    }

    // failed, the first namings was "n" + cost , which deluded me
    private int dfs(int i,int[] cost){
        if(i < 2) return 0;
        if(memo.containsKey(i)){
            return memo.get(i);
        }
        int minCost = Math.min(dfs(i-1,cost)+cost[i-1],dfs(i-2,cost)+cost[i-2]);
        memo.put(i,minCost);
        return minCost;
    }
}