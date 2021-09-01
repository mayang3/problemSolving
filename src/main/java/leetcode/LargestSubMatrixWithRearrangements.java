package leetcode;

import java.util.Arrays;
import java.util.Collections;

public class LargestSubMatrixWithRearrangements {
	public int largestSubmatrix(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;

		Integer [][] countArr = new Integer[m][n];

		for (int x = 0; x < n; x++) {
			countArr[0][x] = matrix[0][x];
		}

		for (int x = 0; x < n; x++) {
			for (int y = 1; y < m; y++) {
				if (matrix[y][x] == 1) {
					countArr[y][x] = countArr[y - 1][x] + 1;
				} else {
					countArr[y][x] = 0;
				}
			}
		}

		for (int y = 0; y < m; y++) {
			Arrays.sort(countArr[y], Collections.reverseOrder());
		}

		int max = 0;

		for (int y = 0; y < m; y++) {
			max = Math.max(max, getMaxArea(countArr[y]));
		}

		return max;
	}

	private int getMaxArea(Integer[] countArr) {
		int max = 0;
		int maxHeight = Integer.MAX_VALUE;

		for (int i = 0; i < countArr.length && countArr[i] != 0; i++) {
			maxHeight = Math.min(maxHeight, countArr[i]);
			max = Math.max(max, Math.max(countArr[i], (i+1) * maxHeight));
		}

		return max;
	}

	public static void main(String[] args) {
		int [][] mat = {
						{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1},
						{0,1,1,0,1,1,1,1,0,1,1,0,0,1,0,1,1,1,1,0,1,1,1,1,1,1}
		               };

		LargestSubMatrixWithRearrangements largestSubMatrixWithRearrangements = new LargestSubMatrixWithRearrangements();
		System.out.println(largestSubMatrixWithRearrangements.largestSubmatrix(mat));
	}
}
