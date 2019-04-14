package olympiad.KOI_2017;

import java.util.Scanner;

@SuppressWarnings("Duplicates")
public class 방배정하기 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int A = scanner.nextInt();
		int B = scanner.nextInt();
		int C = scanner.nextInt();

		int N = scanner.nextInt();

		Integer [] dp = new Integer[N+1];

		System.out.println(solve(dp, A, B, C, N));
	}

	private static int solve(Integer [] dp, int A, int B, int C, int N) {
		if (N == 0) {
			return 1;
		}

		if (dp[N] != null) {
			return dp[N];
		}

		// A 를 선택하는 경우
		if (A <= N) {
			if (solve(dp, A, B, C, N-A) == 1) {
				return dp[N] = 1;
			}
		}

		// B 를 선택하는 경우
		if (B <= N) {
			if (solve(dp, A, B, C, N-B) == 1) {
				return dp[N] = 1;
			}
		}

		// C 를 선택하는 경우
		if (C <= N) {
			if (solve(dp, A, B, C,  N-C) == 1) {
				return dp[N] = 1;
			}
		}

		return dp[N] = 0;
	}
}
