package leetcode;

public class PermutationInString {
	public boolean checkInclusion(String s1, String s2) {

		if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0 || s1.length() > s2.length()) {
			return false;
		}

		int n1 = s1.length();
		int n2 = s2.length();

		int [] alpha = new int[26];

		for (int i = 0; i < n1; i++) {
			alpha[s1.charAt(i) - 'a']++;
		}

		for (int i = 0; i < n1; i++) {
			alpha[s2.charAt(i) - 'a']--;
		}

		if (isPermutation(alpha)) {
			return true;
		}

		for (int right = n1; right < n2; right++) {
			int left = right - n1;

			alpha[s2.charAt(right) - 'a']--;
			alpha[s2.charAt(left) - 'a']++;

			if (isPermutation(alpha)) {
				return true;
			}
		}


		return false;
	}

	private boolean isPermutation(int[] alpha) {
		for (int i = 0; i < alpha.length; i++) {
			if (alpha[i] != 0) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		String s1 = "ab";
		String s2 = "eidboaoo";

		PermutationInString ps = new PermutationInString();

		System.out.println(ps.checkInclusion(s1, s2));

	}
}
