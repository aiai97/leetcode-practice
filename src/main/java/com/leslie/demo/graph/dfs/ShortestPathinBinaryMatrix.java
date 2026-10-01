package com.leslie.demo.graph.dfs;

import java.util.LinkedList;
import java.util.Queue;
// failed -> need to do it again
public class ShortestPathinBinaryMatrix {
}
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

class Solution2 {
    int n;
    int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }

        n = grid.length;

        // more convenient to use a 2d array instead of a set for seen
        boolean[][] seen = new boolean[n][n];
        seen[0][0] = true;
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0, 0, 1)); // row, col, steps

        while (!queue.isEmpty()) {
            State state = queue.remove();
            int row = state.row, col = state.col, steps = state.steps;
            if (row == n - 1 && col == n - 1) {
                return steps;
            }

            for (int[] direction: directions) {
                int nextRow = row + direction[0], nextCol = col + direction[1];
                if (valid(nextRow, nextCol, grid) && !seen[nextRow][nextCol]) {
                    seen[nextRow][nextCol] = true;
                    queue.add(new State(nextRow, nextCol, steps + 1));
                }
            }
        }

        return -1;
    }

    public boolean valid(int row, int col, int[][] grid) {
        return 0 <= row && row < n && 0 <= col && col < n && grid[row][col] == 0;
    }
}