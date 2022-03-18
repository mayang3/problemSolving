package leetcode;

public class MinimumDifficultOfaJobSchedule {
	public static void main(String[] args) {
		int [] jobDifficulty = {9,9,9};
		int d = 4;

		MinimumDifficultOfaJobSchedule minimumDifficultOfaJobSchedule = new MinimumDifficultOfaJobSchedule();
		int res = minimumDifficultOfaJobSchedule.minDifficulty(jobDifficulty, d);

		System.out.println(res);
	}


	public int minDifficulty(int[] jobDifficulty, int d) {
		Integer [][] dp = new Integer[jobDifficulty.length][d+1];

		int res = solve(dp, jobDifficulty, 0, d);

		return res == Integer.MAX_VALUE ? -1 : res;
	}

	private int solve(Integer[][] dp, int[] jobDifficulty, int i, int d) {
		if (i >= jobDifficulty.length) {
			return Integer.MAX_VALUE;
		}

		if (dp[i][d] != null) {
			return dp[i][d];
		}

		int max = Integer.MIN_VALUE;
		int res = Integer.MAX_VALUE;

		if (d == 1) {
			for (int j = i; j < jobDifficulty.length; j++) {
				res = max = Math.max(max, jobDifficulty[j]);
			}
		} else {
			for (int j = i; j < jobDifficulty.length - d + 1; j++) {
				max = Math.max(jobDifficulty[j], max);

				int subRes = solve(dp, jobDifficulty, j+1, d - 1);

				if (subRes != Integer.MAX_VALUE) {
					res = Math.min(res, max + subRes);
				}
			}
		}

		return dp[i][d] = res;
	}
}
