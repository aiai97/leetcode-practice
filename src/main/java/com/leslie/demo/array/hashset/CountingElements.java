package com.leslie.demo.array.hashset;

import java.util.HashSet;
import java.util.Set;

// easy -> not need to do it again
public class CountingElements {
    public int countElements(int[] arr) {
        int count = 0;
        Set<Integer> seen = new HashSet<>();
        for(int num: arr){
            seen.add(num);
        }
        for(int i=0; i<arr.length;i++){
            if(seen.contains(arr[i]+1)){
                count++;
            }
        }
        return count;
    }
}