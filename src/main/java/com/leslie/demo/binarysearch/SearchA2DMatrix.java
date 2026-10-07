package com.leslie.demo.binarysearch;

// failed ->not need to do it again
public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null) return false;
        int row = matrix.length;
        int column= matrix[0].length;

        int left = 0;
        int right = row * column - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            int midpoint = matrix[mid / column][mid % column]; // failed because of typo
            if(midpoint == target){
                return true;
            }else if(midpoint > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return false;
    }
}