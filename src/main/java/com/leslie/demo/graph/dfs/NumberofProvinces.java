package com.leslie.demo.graph.dfs;

import java.util.*;

public class NumberofProvinces {
}

// failed-> because failed to remember to traverse the neighbors
// union find is the best-> will implement it in the future
// remember set could be more light
class Solution {
    // for every node in the group,
    //let it merge its neighbors, if its neighbor is seen before, skip traversal
    public int findCircleNum(int[][] isConnected) {
        Map<Integer, List<Integer>> map = new HashMap();
        for(int i = 0;i < isConnected.length;i++){
            map.putIfAbsent(i, new ArrayList<>());
            for(int j = 0;j < isConnected[0].length;j++){
                if(i != j && isConnected[i][j] == 1){
                    map.get(i).add(j);
                }
            }
        }

        Set<Integer> seen = new HashSet();
        int count = 0;
        for(int i = 0;i < isConnected.length;i++){
            if(!seen.contains(i)){
                count++;
                dfs(i,map,seen);
            }
        }
        return count;
    }

    private void dfs(int node,Map<Integer,List<Integer>> map,Set<Integer> seen){
        seen.add(node);
        for(int neighbor:map.get(node)){
            if(!seen.contains(neighbor)){
                dfs(neighbor,map,seen);
            }
        }
    }
}