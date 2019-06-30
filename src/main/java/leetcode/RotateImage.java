package leetcode;

import java.util.Arrays;

/**
 * @author neo82
 */
public class RotateImage {
	public static void main(String[] args) {
		int [][] matrix = {
			{5,1,9,11},
			{2,4,8,10},
			{13,3,6,7},
			{15,14,12,16}
		};

		RotateImage rotateImage = new RotateImage();
		rotateImage.rotate(matrix);

		for (int [] dot : matrix) {
			System.out.println(Arrays.toString(dot));
		}
	}

	public void rotate(int[][] matrix) {
		int n = matrix.length;
		int m = (int)Math.ceil((float)matrix.length/(float)2);
		int k = 1;

		for (int y = 0; y < m ; y++) {
			for (int x = n-y-1; x >= k ; x--) {
				// 1. top <-> right swap
				swap(matrix, y, x, x, n-y-1);

				// 2. top <-> left swap
				swap(matrix, y, x, n-x-1, y);

				// 3. left <-> bottom swap
				swap(matrix, n-x-1, y, n-y-1, n-x-1);
			}

			k++;
		}
	}

	private void swap(int [][] matrix, int y, int x, int yy, int xx) {
		int temp = matrix[y][x];

		matrix[y][x] = matrix[yy][xx];
		matrix[yy][xx] = temp;
	}
}
