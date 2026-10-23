package com.leslie.demo.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
}
// in the morning I failed, in the afternoon I succeeded.
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        backtrack(nums,new ArrayList<Integer>(),res);
        return res;
    }

    private void backtrack(int[] nums,List<Integer> curr,List<List<Integer>> res){
        if(curr.size() == nums.length){
            res.add(new ArrayList<>(curr)); // failed before in copying the list
            return;
        }

        for(int num:nums){
            if(curr.contains(num)) continue;
            curr.add(num);
            backtrack(nums,curr,res);
            curr.remove(curr.size()-1);
        }
    }
}