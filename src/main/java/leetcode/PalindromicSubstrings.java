package leetcode;

public class PalindromicSubstrings {
	public int countSubstrings(String s) {
		boolean [][] dp = new boolean[s.length() + 1][s.length() + 1];

		int count = 0;

		for (int i = 0; i < s.length() ; i++) {
			dp[i][i] = true;
			count++;
		}

		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i-1) == s.charAt(i)) {
				dp[i-1][i] = true;
				count++;
			}
		}

		for (int k = 3; k <= s.length(); k++) {
			for (int j = k-1; j < s.length(); j++) {
				int i = j-k+1;

				if (dp[i+1][j-1] && s.charAt(i) == s.charAt(j)) {
					dp[i][j] = true;
					count++;
				}
			}
		}

		return count;
	}

	public static void main(String[] args) {
		PalindromicSubstrings palindromicSubstrings = new PalindromicSubstrings();
		int res = palindromicSubstrings.countSubstrings("fdsklf");
		System.out.println(res);

	}
}
