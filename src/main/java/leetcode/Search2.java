package leetcode;

import java.util.Arrays;

public class Search2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        if (matrix[0][0] > target || matrix[m-1][n-1] < target) {
            return false;
        }

        int [] arr = new int[m];

        for (int row = 0; row < m; row++) {
            arr[row] = matrix[row][0];
        }

        int i = Arrays.binarySearch(arr, target);

        if (i >= 0) return true;

        return Arrays.binarySearch(matrix[-(i+2)], target) > 0;
    }

    public static void main(String[] args) {
        int [][] m = {{1}};

        Search2 search2 = new Search2();
        System.out.println(search2.searchMatrix(m, 1));
    }

}
