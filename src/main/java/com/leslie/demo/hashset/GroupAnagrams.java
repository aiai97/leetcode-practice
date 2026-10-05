package com.leslie.demo.hashset;

import java.util.*;

// failed in the beginning, but no need to do it again because you cannot remember the api detail
// map.computeIfAbsent(key, k->new ArrayList<>()).add(str);
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> counts = new HashMap();
        for(String str:strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = Arrays.toString(chars);
            if(!counts.containsKey(key)){
                counts.put(key,new ArrayList<>());
            }
            counts.get(key).add(str);

        }
        return new ArrayList<>(counts.values());
    }
}