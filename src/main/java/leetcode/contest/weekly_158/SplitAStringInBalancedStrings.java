package leetcode.contest.weekly_158;

public class SplitAStringInBalancedStrings {
	public int balancedStringSplit(String s) {

		int lCount = 0;
		int rCount = 0;

		int totalCount = 0;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'L') {
				lCount++;
			} else {
				rCount++;
			}

			if (lCount == rCount) {
				totalCount++;
				lCount = 0;
				rCount = 0;
			}
		}

		return totalCount;
	}

	public static void main(String[] args) {
		SplitAStringInBalancedStrings splitAStringInBalancedStrings = new SplitAStringInBalancedStrings();

		int cnt = splitAStringInBalancedStrings.balancedStringSplit("LLLLRRRR");

		System.out.println(cnt);
	}
}
