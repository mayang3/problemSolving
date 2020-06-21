package leetcode.contest.weekly_190;

public class MaximumNumber {

	public int maxVowels(String s, int k) {

		int count = 0;

		for (int i = 0; i < k; i++) {
			if (isVowels(s.charAt(i))) {
				count++;
			}
		}

		int max = count;

		for (int i = k; i < s.length(); i++) {
			char remove = s.charAt(i-k);
			char add = s.charAt(i);

			if (isVowels(remove)) {
				count--;
			}

			if (isVowels(add)) {
				count++;
			}

			max = Math.max(max, count);
		}

		return max;
	}

	private boolean isVowels(char c) {
		switch (c) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				return true;
		}

		return false;
	}

	public static void main(String[] args) {
		MaximumNumber mn = new MaximumNumber();

		int res = mn.maxVowels("weallloveyou", 7);

		System.out.println(res);
	}
}
