package com.leslie.demo.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
}
//failed, need to do it again
// https://leetcode.com/problems/combinations/description/
class Solution2 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList();
        backtrack(n,k,1,new ArrayList<Integer>(),res);
        return res;
    }

    private void backtrack(int n,int k,int j,List<Integer> curr,List<List<Integer>> res){
        if(curr.size() == k){
            res.add(new ArrayList<>(curr));
            return;
        }

        for(int i = j;i <= n;i++){// failed because I used if curr.contains(i). it will cause duplicates
            curr.add(i);
            backtrack(n,k,i+1,curr,res);
            curr.remove(curr.size()-1);
        }

    }
}