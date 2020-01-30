package leetcode;

/**
 * @author neo82
 */
public class LongestPalindrome {
	public String longestPalindrome(String s) {
		int n = s.length();

		if (n == 1) {
			return s;
		}

		boolean [][] dp = new boolean[n][n];

		String res = "";

		// length 1
		for (int i = 0; i < s.length(); i++) {
			dp[i][i] = true;
			res = String.valueOf(s.charAt(i));
		}

		// length 2
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i-1) == s.charAt(i)) {
				dp[i - 1][i] = true;

				if (res.length() < 2) {
					res = s.substring(i - 1, i + 1);
				}
			}
		}

		// greater than length 2, that is, >= 3
		for (int k = 3; k <= s.length() ; k++) {
			for (int i = 0; i <= s.length() - k; i++) {
				int j = i + k - 1;

				if (j >= s.length()) {
					break;
				}

				// palindrome
				if (dp[i+1][j-1] && s.charAt(i) == s.charAt(j)) {
					dp[i][j] = true;

					if (res.length() < k) {
						res = s.substring(i, j+1);
					}
				}
			}
		}

		return res;
	}

	public static void main(String[] args) {
		LongestPalindrome longestPalindrome = new LongestPalindrome();

		String res = longestPalindrome.longestPalindrome("babad");

		System.out.println(res);
	}
}
