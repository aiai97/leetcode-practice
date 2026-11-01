package com.leslie.demo.graph.bfs.matrix.hard;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPath {
}

class State {
    int row;
    int col;
    int remain;
    int steps;

    State(int row, int col, int remain, int steps) {
        this.row = row;
        this.col = col;
        this.remain = remain;
        this.steps = steps;
    }
}

// failed, but it is not that hard
class Solution {
    int m;
    int n;
    int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int shortestPath(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        Queue<State> queue = new LinkedList<>();
        boolean[][][] seen = new boolean[m][n][k + 1];

        queue.add(new State(0, 0, k, 0));
        seen[0][0][k] = true; // failed, how many remain operations we have, we can use it to mark the state we have seen

        while (!queue.isEmpty()) {
            State state = queue.remove();
            int row = state.row, col = state.col, remain = state.remain, steps = state.steps;
            if (row == m - 1 && col == n - 1) {
                return steps;
            }

            for (int[] direction : directions) {
                int nextRow = row + direction[0], nextCol = col + direction[1];

                int nextRemain = remain;
                if (valid(nextRow, nextCol)) {
                    if (grid[nextRow][nextCol] == 1) { // 1, how many operations we have left, eliminate the obstacle directly
                        nextRemain--;
                    }

                    if (nextRemain >= 0 && !seen[nextRow][nextCol][nextRemain]) { // failed, 2, need to check if it is valid to move the obstacle
                        queue.add(new State(nextRow, nextCol, nextRemain, steps + 1));
                        seen[nextRow][nextCol][nextRemain] = true;
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