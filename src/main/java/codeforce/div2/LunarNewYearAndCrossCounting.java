package codeforce.div2;

import java.util.Scanner;

public class LunarNewYearAndCrossCounting {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		char [][] matrix = new char[n][n];

		for (int i = 0; i < n; i++) {
			String line = scanner.next();

			for (int j = 0; j < n; j++) {
				matrix[i][j] = line.charAt(j);
			}
		}

		System.out.println(solve(matrix, n));
	}


	static int solve(char [][] matrix, int n) {
		int cnt = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (find(matrix, i, j)) {
					cnt++;
				}
			}
		}

		return cnt;
	}

	private static boolean find(char[][] matrix, int y, int x) {
		int n = matrix.length;

		if (y < 1 || y >= n-1) {
			return false;
		}

		if (x < 1 || x >= n-1) {
			return false;
		}

		if (matrix[y][x] != 'X') {
			return false;
		}

		return matrix[y-1][x-1] == 'X' && matrix[y-1][x+1] == 'X' && matrix[y+1][x-1] == 'X' && matrix[y+1][x+1] == 'X';
	}

}
