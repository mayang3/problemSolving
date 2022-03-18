package leetcode;

public class LongestCommonSubsequence2 {
	public static void main(String[] args) {
		LongestCommonSubsequence2 longestCommonSubsequence2 = new LongestCommonSubsequence2();
		System.out.println(longestCommonSubsequence2.longestCommonSubsequence("a", "b"));
	}

	public int longestCommonSubsequence(String text1, String text2) {
		int n = text1.length();
		int m = text2.length();

		int [][] dp = new int[n+1][m+1];

		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= m; c++) {
				if (text1.charAt(r-1) == text2.charAt(c-1)) {
					dp[r][c] = dp[r-1][c-1] + 1;
				} else {
					dp[r][c] = Math.max(dp[r-1][c], dp[r][c-1]);
				}
			}
		}

		return dp[n][m];

//		return solve(dp, text1, text2, 0, 0);
	}

//	private int solve(Integer[][] dp, String text1, String text2, int index1, int index2) {
//		if (index1 >= text1.length() || index2 >= text2.length()) {
//			return 0;
//		}
//
//		if (dp[index1][index2] != null) {
//			return dp[index1][index2];
//		}
//
//		int max = 0;
//
//		if (text1.charAt(index1) == text2.charAt(index2)) {
//			max = Math.max(max, solve(dp, text1, text2, index1+1, index2+1) + 1);
//		}
//
//		max = Math.max(max, solve(dp, text1, text2, index1+1, index2));
//		max = Math.max(max, solve(dp, text1, text2, index1, index2+1));
//
//		return dp[index1][index2] = max;
//	}
}
