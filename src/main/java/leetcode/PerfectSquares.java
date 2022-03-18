package leetcode;

import java.util.*;

public class PerfectSquares {

	TreeSet<Integer> treeSet = new TreeSet<>();

	public PerfectSquares() {
		for (int i = 1; i <= 1e4; i++) {
			int num = (int)Math.pow(i, 2);

			if (num > 1e4) {
				break;
			}

			treeSet.add(num);
		}
	}

	public int numSquares(int n) {
		List<Integer> list = new ArrayList<>(treeSet.subSet(0, n + 1));

		Collections.reverse(list);

		return solve(new Integer[n+1][list.size()], list, n, 0);
	}

	private int solve(Integer[][] dp, List<Integer> squares, int n, int i) {
		if (squares.size() <= i || n < 0) {
			return Integer.MAX_VALUE;
		} else if (n == 0) {
			return 0;
		}

		if (dp[n][i] != null) {
			return dp[n][i];
		}

		int min = Integer.MAX_VALUE;

		if (n >= squares.get(i)) {
			int res1 = solve(dp, squares, n-squares.get(i), i);

			if (res1 != Integer.MAX_VALUE) {
				min = Math.min(min, 1 + res1);
			}
		}

		min = Math.min(min, solve(dp, squares, n, i+1));

		return dp[n][i] = min;
	}


	public static void main(String[] args) {
		PerfectSquares perfectSquares = new PerfectSquares();
		System.out.println(perfectSquares.numSquares(9999));
	}
}
