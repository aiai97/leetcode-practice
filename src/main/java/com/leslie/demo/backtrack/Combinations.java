package com.leslie.demo.backtrack;

import java.util.ArrayList;
import java.util.List;

// passed
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(1,n,k,new ArrayList<Integer>(),ans);
        return ans;
    }

    private void backtrack(int i,int n,int k,List<Integer> curr,List<List<Integer>> ans){
        if(curr.size() == k){
            ans.add(new ArrayList<>(curr));
            return;
        }

        for(int j = i;j <= n;j++){
            curr.add(j);
            backtrack(j + 1,n,k,curr,ans);
            curr.remove(curr.size()-1);
        }
    }
}