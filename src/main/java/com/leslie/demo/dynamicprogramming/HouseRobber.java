package com.leslie.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

// passed-> no need to do it again
public class HouseRobber {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        int[] res = new int[n];
        res[0] = nums[0];
        res[1] = Math.max(nums[0],nums[1]);
        for(int i = 2; i < n;i++){
            res[i] = Math.max(res[i-1],res[i-2]+nums[i]);
        }
        return res[n-1];
    }
}

//failed -> not need to do it again
class HouseRobberDPSolution {
    public int rob(int[] nums) {
        int n = nums.length;
        Map<Integer,Integer> map = new HashMap();
        return dp(n-1,nums,map);
    }

    private int dp(int i, int[] nums,Map<Integer,Integer> map){
        if(i == 0) return nums[0];
        if(i == 1) return Math.max(nums[0],nums[1]);
        if(map.containsKey(i)) return map.get(i);
        map.put(i,Math.max(dp(i-2,nums,map)+nums[i], dp(i-1,nums,map)));
        return map.get(i);
    }
}
// better-> maintain a global variable
class HouseRobberDPSolution2 {
    Map<Integer, Integer> memo = new HashMap<>();

    public int rob(int[] nums) {
        return dp(nums.length - 1, nums);
    }

    public int dp(int i, int[] nums) {
        // Base cases
        if (i == 0) {
            return nums[0];
        }
        if (i == 1) {
            return Math.max(nums[0], nums[1]);
        }

        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        // Recurrence relation
        memo.put(i, Math.max(dp(i - 1, nums), dp(i - 2, nums) + nums[i]));
        return memo.get(i);
    }
}