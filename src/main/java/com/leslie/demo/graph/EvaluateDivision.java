package com.leslie.demo.graph;

import java.util.*;

//failed -> need to do it again
// double , not string
public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String,Map<String,Double>> map = new HashMap<>(); //failed-> I used to write Map<String,String>
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double val = values[i];
            if(!map.containsKey(a)){
                map.put(a, new HashMap<>());
            }
            if(!map.containsKey(b)){
                map.put(b, new HashMap<>());
            }
            map.get(a).put(b, val);
            map.get(b).put(a, 1.0 / val); //failed -> I wrote 1
        }
        double[] res = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            Set<String> visited = new HashSet<>();
            res[i] = dfs(start, end, visited, map);
        }

        return res;
    }
// cannot write it
    private double dfs(String cur, String target, Set<String> visited, Map<String, Map<String, Double>> graph) {
        if(!graph.containsKey(cur) || !graph.containsKey(target)) return -1.0;
        if(cur.equals(target)) return 1.0;
        visited.add(cur);
        for (Map.Entry<String, Double> neighbor : graph.get(cur).entrySet()) { // failed -> have no idea of getKey and getValue
            if(visited.contains(neighbor.getKey())) continue;
            double product = dfs(neighbor.getKey(),target,visited,graph);
            if (product != -1.0) return product * neighbor.getValue(); // failed -> forgot to write the condition
        }

        return -1.0;
    }
}