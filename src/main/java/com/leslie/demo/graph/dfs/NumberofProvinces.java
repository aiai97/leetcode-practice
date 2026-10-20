package com.leslie.demo.graph.dfs;

import java.util.*;

// easy-> but should implement union find
public class NumberofProvinces {
    Map<Integer,List<Integer>> map = new HashMap<>();
    Set<Integer> seen = new HashSet<>();
    int length;
    public int findCircleNum(int[][] isConnected) {
        length = isConnected.length;
        buildGraph(isConnected);
        int count = 0;
        for(int i = 0; i < length; i++){
            if(!seen.contains(i)){
                count++;
                dfs(i);
            }
        }
        return count;
    }

    private void dfs(int i){
        seen.add(i);
        for(int neighbor : map.get(i)){
            if(!seen.contains(neighbor)){
                dfs(neighbor);
            }
        }
    }

    private void buildGraph(int[][] isConnected){
        for(int i = 0;i < length;i++){
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

//        Initialize: Initially, each node belongs to its own set, and its parent points to itself.
//
//        Find and Union: For each connection, use find() to identify the roots of both nodes; if the roots differ, use union() to merge the sets and decrease the set count by one.
//
//        Return the result: After processing all connections, the remaining number of sets equals the number of connected components (provinces).


// should do union find again, because it is the first time you understood it
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] parent =  new int[n];
        int count = n;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    int rootA = find(i, parent);
                    int rootB = find(j, parent);
                    if( rootA != rootB){
                        parent[rootA] = rootB;
                        count--;
                    }
                }
            }
        }
        return count;
    }

    private int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }
}