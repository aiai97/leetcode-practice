package com.leslie.demo.graph.bfs.matrix;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInAMatrix {
}

// failed, just a simeple typo, I bet in interviews, I can write it perfectly
class State {
    int row;
    int col;
    int steps;
    State(int row, int col, int steps) {
        this.row = row;
        this.col = col;
        this.steps = steps;
    }
}

class Solution {
    int m;
    int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid == null) return -1;
        if(grid[0][0] == 1) return -1;
        m = grid.length;

        boolean[][] seen = new boolean[m][m];
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0, 0, 1));
        seen[0][0] = true;

        while(!queue.isEmpty()){
            State state = queue.poll();
            if(state.row == m - 1 && state.col == m - 1) return state.steps;
            for(int[] direction : directions){
                int nextRow = state.row + direction[0];
                int nextCol = state.col + direction[1];
                if(isValid(nextRow,nextCol,grid) && !seen[nextRow][nextCol]){
                    queue.add(new State(nextRow,nextCol,state.steps+1));
                    seen[nextRow][nextCol] = true;
                }
            }
        }
        return -1;
    }

    public boolean isValid(int row, int col, int[][] grid) {
        return 0 <= row && row < m && 0 <= col && col < m && grid[row][col] == 0; // here failed because I forgot to initialize m
    }
}
