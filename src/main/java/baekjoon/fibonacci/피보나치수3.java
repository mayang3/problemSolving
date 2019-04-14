package baekjoon.fibonacci;

import java.util.Scanner;

/**
 * 행렬곱셈으로 구하는 피보나치수
 */
@SuppressWarnings("ALL")
public class 피보나치수3 {
	static final int MOD = 1000000;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		long m = scanner.nextLong();

		long [][] matrix = new long[2][2];

		matrix[0][0] = 0;
		matrix[0][1] = matrix[1][0] = matrix[1][1] = 1;

		System.out.println(pow(matrix, m-1)[1][1]);
	}

	static long[][] pow(long[][] matrix, long m) {
		// base case : A^0 = I
		// A 의 크기를 가지는 단위행렬(항등행렬 : Indentity Matrix) 이다.
		if (m == 0) {
			return identity(matrix.length);
		}

		if (m % 2 > 0) {
			return multiply(pow(matrix, m-1), matrix);
		}

		long [][] half = pow(matrix, m/2);

		return multiply(half, half);
	}

	static long[][] multiply(long [][] A, long [][] B) {
		int n = A.length;

		long [][] C = new long[n][n];

		for (int y=0 ; y<n ; y++) {
			for (int x=0 ; x<n ; x++) {
				for (int k=0 ; k<n ; k++) {
					C[y][x] = (C[y][x] + (A[y][k] * B[k][x]) % MOD) % MOD;
				}
			}
		}

		return C;
	}

	static long[][] identity(int n) {
		long [][] identityMatrix = new long[n][n];

		for (int y=0 ; y<n ; y++) {
			for (int x=0 ; x<n ; x++) {
				if (y == x) {
					identityMatrix[y][x] = 1;
				} else {
					identityMatrix[y][x] = 0;
				}
			}
		}

		return identityMatrix;
	}
}
