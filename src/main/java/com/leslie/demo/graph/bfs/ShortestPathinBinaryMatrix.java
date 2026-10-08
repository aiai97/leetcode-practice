package com.leslie.demo.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;


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

// failed-> before adding it to the queue, I should mark it as visited instead of when processing the current node
class ShortestPathinBinaryMatrix{
    int n;
    int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        n = grid.length;

        if(grid[0][0] == 1) return -1;

        boolean[][] seen = new boolean[n][n];
        seen[0][0] = true;
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0,0,1));

        while (!queue.isEmpty()) {
            State state = queue.remove();
            int row = state.row, col = state.col, steps = state.steps;

            if(row == n - 1 && col == n - 1){
                return steps;
            }
            for(int[] direction:directions){
                int nextRow = direction[0] + row;
                int nextCol = direction[1] + col;
                if(isValid(nextRow,nextCol,grid) && !seen[nextRow][nextCol]){
                    seen[nextRow][nextCol] = true; // failed
                    queue.add(new State(nextRow,nextCol,steps+1));
                }
            }
        }

        return -1;
    }

    public boolean isValid(int row, int col, int[][] grid) {
        return 0 <= row && row < n && 0 <= col && col < n && grid[row][col] == 0;
    }
}