package leetcode;

public class DeleteOperationForTwoStrings {

	public int minDistance(String word1, String word2) {
		int n1 = word1.length();
		int n2 = word2.length();

		int [][] dp = new int[n1+1][n2+1];

		for (int i = 0; i <= n1; i++) {
			dp[i][0] = i;
		}

		for (int j = 0; j <= n2; j++) {
			dp[0][j] = j;
		}

		for (int i = 1; i <= word1.length(); i++) {
			for (int j = 1; j <= word2.length(); j++) {
				if (word1.charAt(i-1) == word2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					dp[i][j] = Math.min(dp[i-1][j]+1, dp[i][j-1]+1);
				}
			}
		}

		return dp[n1][n2];
	}

//	public int minDistance(String word1, String word2) {
//		Integer [][] dp = new Integer[word1.length()][word2.length()];
//		return solve(dp, word1, word2, 0, 0);
//	}

	private int solve(Integer [][] dp, String word1, String word2, int word1Index, int word2Index) {
		int n1 = word1.length();
		int n2 = word2.length();

		if (n1 <= word1Index && n2 <= word2Index) {
			return 0;
		} else if (n1 <= word1Index) {
			return n2 - word2Index;
		} else if (n2 <= word2Index) {
			return n1 - word1Index;
		}

		if (dp[word1Index][word2Index] != null) {
			return dp[word1Index][word2Index];
		}

		char ch1 = word1.charAt(word1Index);
		char ch2 = word2.charAt(word2Index);

		int ret = Integer.MAX_VALUE;

		if (ch1 == ch2) {
			ret = solve(dp, word1, word2, word1Index+1, word2Index+1);
		} else {
			ret = Math.min(ret, solve(dp, word1, word2, word1Index+1, word2Index) + 1);
			ret = Math.min(ret, solve(dp, word1, word2, word1Index, word2Index+1) + 1);
		}

		return dp[word1Index][word2Index] = ret;
	}

	public static void main(String[] args) {
		DeleteOperationForTwoStrings deleteOperationForTwoStrings = new DeleteOperationForTwoStrings();
		System.out.println(deleteOperationForTwoStrings.minDistance("sea", "eak"));
	}
}
