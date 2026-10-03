package com.leslie.demo.backtrack;

import java.util.ArrayList;
import java.util.List;
/// failed
public class Permutations {
}
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        backtrack(nums,new ArrayList<Integer>(),res);
        return res;
    }

    // failed： remember too many states
    private void backtrack(int[] nums,List<Integer> curr,List<List<Integer>> res){
        if(curr.size() == nums.length){
            res.add(new ArrayList<>(curr)); // failed
            return;
        }
        for(int num: nums){
            if(curr.contains(num)) continue;
            curr.add(num);
            backtrack(nums,curr,res);
            curr.remove(curr.size()-1);
        }
    }
}