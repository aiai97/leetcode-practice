package com.leslie.demo.graph.bfs.matrix;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInAMatrix {
}

// failed
class State {
    int row;
    int col;
    int remain;
    int steps;
    State(int row, int col, int remain, int steps) {
        this.row = row;
        this.col = col;
        this.remain = remain;
        this.steps = steps; // so many stuffs
    }
}

class Solution2 {
    int m;
    int n;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int shortestPath(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        Queue<State> queue = new LinkedList<>();
        boolean[][][] seen = new boolean[m][n][k + 1];
        queue.add(new State(0, 0, k, 0));
        seen[0][0][k] = true;

        while (!queue.isEmpty()) {
            State state = queue.remove();
            int row = state.row, col = state.col, remain = state.remain, steps = state.steps;
            if (row == m - 1 && col == n - 1) {
                return steps;
            }

            for (int[] direction: directions) {
                int nextRow = row + direction[0], nextCol = col + direction[1];
                if (valid(nextRow, nextCol)) {
                    if (grid[nextRow][nextCol] == 0) {
                        if (!seen[nextRow][nextCol][remain]) {
                            seen[nextRow][nextCol][remain] = true;
                            queue.add(new State(nextRow, nextCol, remain, steps + 1));
                        }
                        // otherwise, it is an obstacle and we can only pass if we have remaining removals
                    } else if (remain > 0 && !seen[nextRow][nextCol][remain - 1]) {
                        seen[nextRow][nextCol][remain - 1] = true;
                        queue.add(new State(nextRow, nextCol, remain - 1, steps + 1));
                    }
                }
            }
        }

        return -1;
    }

    public boolean valid(int row, int col) {
        return 0 <= row && row < m && 0 <= col && col < n;
    }
}