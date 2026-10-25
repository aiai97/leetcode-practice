package com.leslie.demo.dynamicprogramming.iteration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// failed
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];

        dp[0] = true; // failed, empty string

        Set<String> words = new HashSet<>(wordDict);

        for (int i = 1; i <= n; i++) {
            for (String word : words) {
                int len = word.length();

                if (i >= len // failed because I don't check the length here
                        && dp[i - len]
                        && words.contains(s.substring(i - len, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
