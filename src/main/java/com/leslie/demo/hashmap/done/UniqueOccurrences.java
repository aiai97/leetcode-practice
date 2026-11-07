package com.leslie.demo.hashmap.done;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {
            map.put(num,map.getOrDefault(num,0)+1);
        }

        Set<Integer> set = new HashSet<>(map.values());

        return set.size() == map.size();
    }
}