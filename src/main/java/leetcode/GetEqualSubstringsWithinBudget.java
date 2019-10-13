package leetcode;

public class GetEqualSubstringsWithinBudget {
	public int equalSubstring(String s, String t, int maxCost) {
		int head = 0;
		int c = 0;
		int max = 0;

		for (int i = 0; i < s.length(); i++) {
			c += Math.abs(s.charAt(i) - t.charAt(i));

			if (c > maxCost) {
				c -= Math.abs(s.charAt(head) - t.charAt(head));
				head++;
			}

			max = Math.max(max, i-head+1);
		}

		return max;
	}

	public static void main(String[] args) {
		GetEqualSubstringsWithinBudget getEqualSubstringsWithinBudget = new GetEqualSubstringsWithinBudget();
		int ret = getEqualSubstringsWithinBudget.equalSubstring("abcd","acde",0);

		System.out.println(ret);
	}
}
