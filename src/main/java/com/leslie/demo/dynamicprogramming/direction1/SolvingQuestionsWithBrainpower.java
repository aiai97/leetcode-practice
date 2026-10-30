package com.leslie.demo.dynamicprogramming.direction1;

import java.util.HashMap;
import java.util.Map;

// failed, but I almost wrote it correctly
public class SolvingQuestionsWithBrainpower {
    Map<Integer,Long> memo = new HashMap<>();
    public long mostPoints(int[][] questions) {
        return dp(0,questions);
    }

    public long dp(int i,int[][] questions){
        if(i >= questions.length){
            return 0;
        }
        if(memo.containsKey(i)) return memo.get(i);
        long ans = 0;
        int j = i+questions[i][1] + 1; // failed , forgot to add 1
        ans = Math.max(questions[i][0]+dp(j,questions),dp(i+1,questions));
        memo.put(i,ans);
        return memo.get(i);
    }
}