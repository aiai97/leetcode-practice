package com.leslie.demo.graph.dfs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// failed, need to do it again
public class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> seen = new HashSet<>();
        dfs(0, rooms, seen);
        return seen.size() == rooms.size();
    }

    private void dfs(int room, List<List<Integer>> rooms, Set<Integer> seen) {
        if (seen.contains(room)) {
            return;
        }
        seen.add(room);

        for (int key : rooms.get(room)) {
            dfs(key, rooms, seen);
        }
    }
}