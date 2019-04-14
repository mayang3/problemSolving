package baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

public class RGB {
	static int N;

	static int R = 0;
	static int G = 1;
	static int B = 2;

	static int INF = 987654321;

	static int [][] cost;
	static int [][] cache;

	static int solve(int i, int beforeColor) {
		if (i >= N) {
			return 0;
		}

		int min = INF;

		for (int color=0 ; color<3 ; color++) {
			if (i != 0 && beforeColor == color) {
				continue;
			}

			if (cache[i][color] == INF) {
				cache[i][color] = Math.min(cache[i][color], cost[i][color] + solve(i + 1, color));
			}

			min = Math.min(min, cache[i][color]);
		}

		return min;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		N = scanner.nextInt();

		cost = new int[N][3];
		cache = new int[N][3];

		for (int [] c : cache) {
			Arrays.fill(c, INF);
		}

		for (int i=0 ; i<N ; i++) {
			cost[i][R] = scanner.nextInt();
			cost[i][G] = scanner.nextInt();
			cost[i][B] = scanner.nextInt();
		}

		System.out.println(solve(0, 0));
	}
}
