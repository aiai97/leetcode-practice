package com.leslie.demo.systemdesign.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    private int capacity;
    private LinkedHashMap<Integer,Integer> dic;
    LRUCache(int capacity){
        this.capacity = capacity;
        dic = new LinkedHashMap<Integer,Integer>(capacity,0.75f,true){            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
          }
        };
    }

    int get(int key){
        return dic.getOrDefault(key,-1);
    }
    void put(int key, int value){
        dic.put(key,value);
    }


}
