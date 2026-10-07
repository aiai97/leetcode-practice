package com.leslie.demo.graph.dfs;

import java.util.*;

public class NumberofProvinces {
}

// failed-> because failed to remember to traverse the neighbors
// union find is the best-> will implement it in the future
// remember set could be more light
class Solution {
    Map<Integer,List<Integer>> map = new HashMap();
    boolean[] seen;
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        buildGraph(isConnected);

        seen = new boolean[n];
        int count = 0;
        for(int i = 0;i < isConnected.length;i++){
            if(!seen[i]){
                count++;
                dfs(i);
            }
        }
        return count;
    }

    private void dfs(int node){
        seen[node] = true;
        for(int neighbor:map.get(node)){
            if(!seen[neighbor]){
                dfs(neighbor);
            }
        }
    }

    private void buildGraph(int[][] isConnected){
        for(int i = 0;i < isConnected.length;i++){
            map.putIfAbsent(i, new ArrayList<>());
            for(int j = i+1;j < isConnected[0].length;j++){
                map.putIfAbsent(j, new ArrayList<>());
                if(isConnected[i][j] == 1){
                    map.get(i).add(j);
                    map.get(j).add(i);
                }
            }
        }
    }
}