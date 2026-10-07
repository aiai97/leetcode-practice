package com.leslie.demo.graph.dfs;
// will do it again because I focus on optimization
public class NumberofIslands {
    int m;
    int n;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        int count = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    if(changeLandToWater(grid,i,j))
                        count += 1;
                }
            }
        }
        return count;
    }

    private boolean changeLandToWater(char[][] grid,int row,int col){
        if(!isValid(grid, row, col)) return false;
        grid[row][col] = '0';
        for (int[] direction : directions) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];
            changeLandToWater(grid, nextRow, nextCol);
        }
        return true;
    }

    private boolean isValid(char[][] grid,int row,int col){
        return 0 <= row && row < m && 0 <= col && col < n && grid[row][col] == '1';
    }
}