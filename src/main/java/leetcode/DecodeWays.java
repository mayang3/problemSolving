package leetcode;

public class DecodeWays {
	public int numDecodings(String s) {
		Integer [] dp = new Integer[s.length()];
		return solve(dp, s, 0);
	}

	private int solve(Integer [] dp, String s, int i) {
		if (i == s.length()) {
			return 1;
		} else if (i > s.length()) {
			return 0;
		}

		if (dp[i] != null) {
			return dp[i];
		}

		int num1 = Integer.parseInt(s.substring(i,i+1));

		int cnt = 0;
		// 한개를 선택하는 경우
		if (0 < num1 && num1 < 27) {
			cnt += solve(dp, s, i + 1);
		}
		// 두개를 선택하는 경우
		if (i+1 < s.length()) {
			int num2 = Integer.parseInt(s.substring(i,i+2));

			if (s.charAt(i) != '0' && 0 < num2 && num2 < 27) {
				cnt += solve(dp, s, i + 2);
			}
		}

		return dp[i] = cnt;
	}

	public static void main(String[] args) {
		String s = "226";

		DecodeWays decodeWays = new DecodeWays();
		int res = decodeWays.numDecodings(s);
		System.out.println(res);
	}
}
