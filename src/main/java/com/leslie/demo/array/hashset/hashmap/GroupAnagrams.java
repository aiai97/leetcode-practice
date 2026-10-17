package com.leslie.demo.array.hashset.hashmap;

import java.util.*;

// easy -> no need to do it again
public class GroupAnagrams {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String,List<String>> counts = new HashMap();
            for(String str:strs){
                char[] chars = str.toCharArray();
                Arrays.sort(chars);
                String key = String.valueOf(chars);
                counts.computeIfAbsent(key, v->new ArrayList<>()).add(str);
            }

        return new ArrayList<>(counts.values());
    }
}
