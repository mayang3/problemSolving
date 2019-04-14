package baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

public class NumberTriangle {
	static int [][] triangle;
	static int [][] cache;

	static int solve(int y, int x) {
		if (y == (triangle.length - 1)) {
			return triangle[y][x];
		}

		if (cache[y][x] != -1) {
			return cache[y][x];
		}

		return cache[y][x] = triangle[y][x] + Math.max(solve(y+1, x), solve(y+1, x+1));
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		triangle = new int[N][N];
		cache = new int[N][N];

		for (int [] c : cache) {
			Arrays.fill(c, -1);
		}

		int width=1;

		for (int y=0 ; y<N ; y++) {
			for (int x=0 ; x<width ; x++) {
				triangle[y][x] = scanner.nextInt();
			}

			width++;
		}

		System.out.println(solve(0,0));

	}
}
