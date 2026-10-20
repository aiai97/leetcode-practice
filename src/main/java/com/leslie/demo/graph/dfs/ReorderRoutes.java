package com.leslie.demo.graph.dfs;

import java.util.*;


// failed -> will do it again
public class ReorderRoutes {

    Set<String> roads = new HashSet<>();
    Map<Integer, List<Integer>> graph = new HashMap<>();;
    Set<Integer> seen = new HashSet<>();

    public int minReorder(int n, int[][] connections) {
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] connection: connections) {
            int x = connection[0], y = connection[1];
            graph.get(x).add(y);
            graph.get(y).add(x);
            roads.add(convertToHash(x, y));
        }

        seen.add(0);
        return dfs(0);
    }

    public int dfs(int node) {
        int ans = 0;
        for (int neighbor: graph.get(node)) {
            if (!seen.contains(neighbor)) {
                if (roads.contains(convertToHash(node, neighbor))) {
                    // hard here because I used to think I need to flip the direction but it turns out it's not needed
                    ans++;
                }

                seen.add(neighbor);
                ans += dfs(neighbor);
            }
        }

        return ans;
    }

    public String convertToHash(int row, int col) {
        return String.valueOf(row) + "," + String.valueOf(col);
    }
}
