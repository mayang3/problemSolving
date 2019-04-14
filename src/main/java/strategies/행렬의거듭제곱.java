package strategies;

import java.util.Arrays;

/**
 * A^m 꼴의 정방행렬(n*n)의 빠른 거듭제곱 DvideNConqure 방법을 이용한다.
 */
public class 행렬의거듭제곱 {


	static int [][] identity(int size) {
		int [][] matrix = new int[size][size];

		for (int y=0 ; y<size ; y++) {
			for (int x=0 ; x<size ; x++) {
				if (y == x) {
					matrix[y][x] = 1;
				} else {
					matrix[y][x] = 0;
				}
			}
		}

		return matrix;
	}

	static int [][] multiply(int [][] A, int [][] B) {
		int n = A.length;

		int [][] C = new int[n][n];

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				for (int k = 0; k < n; k++) {
					C[y][x] += A[y][k] * B[k][x];
				}
			}
		}

		return C;
	}

	// A^m 을 반환한다.
	static int [][] pow(int [][] A, int m) {
		// base case : A^0 = I
		// A 의 크기를 가지는 단위행렬(항등행렬 : Indentity Matrix) 이다.
		if (m == 0) {
			return identity(A.length);
		}

		// odd
		if (m % 2 > 0) {
			return multiply(pow(A, m-1), A);
		}

		int [][] half = pow(A, m/2);

		return multiply(half, half);
	}

	public static void main(String[] args) {
		int [][] A = {
			{0, 1},
			{1, 0}
		};

		System.out.println(Arrays.deepToString(pow(A, 2)));
	}
}
