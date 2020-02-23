package leetcode;

/**
 * @author neo82
 */
public class LongestCommonSubsequence {
	public int longestCommonSubsequence(String text1, String text2) {
		if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
			return 0;
		}

		Integer [][] dp = new Integer[text1.length()][text2.length()];

		return solve(dp, text1, text2, 0, 0);
	}

	private int solve(Integer[][] dp, String t1, String t2, int t1Index, int t2Index) {
		if (t1Index >= t1.length() || t2Index >= t2.length()) {
			return 0;
		}

		if (dp[t1Index][t2Index] != null) {
			return dp[t1Index][t2Index];
		}

		int cnt = 0;

		if (t1.charAt(t1Index) == t2.charAt(t2Index)) {
			cnt = Math.max(cnt, 1 + solve(dp, t1, t2, t1Index + 1, t2Index + 1));
		} else {
			cnt = Math.max(solve(dp, t1, t2, t1Index + 1, t2Index), solve(dp, t1, t2, t1Index, t2Index + 1));
		}

		return dp[t1Index][t2Index] = cnt;
	}

	public int longestCommonSubsequenceBottomUp(String s1, String s2) {
		int[][] dp = new int[s1.length()][s2.length()];

		for (int i = 0; i < s1.length(); ++i) {
			for (int j = 0; j < s2.length(); ++j) {
				if (s1.charAt(i) == s2.charAt(j)) {
					dp[i + 1][j + 1] = 1 + dp[i][j];
				} else {
					dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
				}
			}
		}

		return dp[s1.length()][s2.length()];
	}


	/**
	 *
	 * "oxcpqrsvwf"
	 * "shmtulqrypy"
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();

		int res = lcs.longestCommonSubsequence("oxcpqrsvwf", "shmtulqrypy");

		System.out.println(res);
	}
}
