package com.leslie.demo.backtrack;

import java.util.ArrayList;
import java.util.List;

// failed
public class Subset {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        backtrack(nums,0,new ArrayList<Integer>(),res);
        return res;
    }

    // failed, it should recurse the number after it
    private void backtrack(int[] nums,int i,List<Integer> curr,List<List<Integer>> res){
        if(i > nums.length) return; // failed because I didn't write stop condition

        res.add(new ArrayList<>(curr));
        for(int j = i; j < nums.length;j++){
            curr.add(nums[j]);
            backtrack(nums,j+1,curr,res);
            curr.remove(curr.size()-1);
        }
    }
}