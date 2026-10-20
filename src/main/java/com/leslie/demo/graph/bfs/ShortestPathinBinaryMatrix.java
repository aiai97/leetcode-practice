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

// easy -> no need to do it again
class ShortestPathinBinaryMatrix{
    int n;
    int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        n = grid.length;
        if(grid[0][0] == 1) return -1;

        boolean[][] seen = new boolean[n][n];
        seen[0][0] = true;
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0, 0, 1));

        while(!queue.isEmpty()){
            State state = queue.poll();
            if(state.row == n - 1 && state.col == n - 1) return state.steps;
            for(int[] direction : directions){
                int nextRow = state.row + direction[0];
                int nextCol = state.col + direction[1];
                if(isValid(nextRow, nextCol, grid) && !seen[nextRow][nextCol]) {
                    seen[nextRow][nextCol] = true;
                    queue.add(new State(nextRow, nextCol, state.steps + 1));
                }
            }
        }
        return -1;
    }

    public boolean isValid(int row, int col, int[][] grid) {
        return 0 <= row && row < n && 0 <= col && col < n && grid[row][col] == 0;
    }
}