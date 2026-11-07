package com.leslie.demo.hashset.done;

import java.util.HashSet;
import java.util.Set;

// easy -> not need to do it again
//Given an integer array arr, count how many elements x there are, such that x + 1 is also in arr
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