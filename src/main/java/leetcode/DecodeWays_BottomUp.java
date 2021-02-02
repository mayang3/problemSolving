package leetcode;

import java.util.HashSet;
import java.util.Set;

public class DecodeWays_BottomUp {
	public int numDecodings(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		Set<String> dict = new HashSet<>();

		for (int i = 1; i <= 26; i++) {
			dict.add(String.valueOf(i));
		}

		int len = s.length();

		int [] dp = new int[len];

		dp[0] = dict.contains(s.substring(0, 1)) ? 1 : 0;

		if (len > 1) {
			for (int i = 1; i < len; i++) {
				// 길이가 1인 배열을 넣을 수 있는 경우
				if (dict.contains(s.substring(i, i+1))) {
					dp[i] += dp[i-1];
				}

				// 길이가 2인 배열을 넣을 수 있는 경우
				if (dict.contains(s.substring(i-1, i+1))) {
					if (i == 1) {
						dp[i] += 1;
					} else {
						dp[i] += dp[i - 2];
					}
				}
			}
		}

		return dp[len -1];
	}

	public static void main(String[] args) {
		DecodeWays_BottomUp decodeWays3 = new DecodeWays_BottomUp();

		System.out.println(decodeWays3.numDecodings("10"));
	}
}
