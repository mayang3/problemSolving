package leetcode;

import java.util.HashMap;
import java.util.Map;

public class DecodeWays_TopDown {
	public int numDecodings(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		Integer [] dp = new Integer[s.length()];

		Map<String, Boolean> dict = new HashMap<>();

		for (int i = 1; i <= 26; i++) {
			dict.put(String.valueOf(i), true);
		}

		return solve(dict, dp, s, 0);
	}

	private int solve(Map<String, Boolean> dict, Integer[] dp, String s, int i) {
		int len = s.length();

		if (i == len) {
			return 1;
		} else if (i > len) {
			return 0;
		}

		if (dp[i] != null) {
			return dp[i];
		}

		int cnt = 0;

		// 한 자리수를 decode 할 수 있는 경우
		if (i <= len - 1 && dict.getOrDefault(s.substring(i, i+1), false)) {
			cnt += solve(dict, dp, s, i + 1);
		}

		// 두 자리수를 decode 할 수 있는 경우
		if (i <= len - 2 && dict.getOrDefault(s.substring(i, i+2), false)) {
			cnt += solve(dict, dp, s, i + 2);
		}

		return dp[i] = cnt;
	}

	public static void main(String[] args) {
		DecodeWays_TopDown decodeWaysTopDown2 = new DecodeWays_TopDown();

		System.out.println(decodeWaysTopDown2.numDecodings("06"));
	}
}
