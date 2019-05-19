package leetcode.contest.weekly_137;

/**
 * @author neo82
 */
public class RemoveAllAdjacentDuplicatesInString {
	public static void main(String[] args) {
		System.out.println(removeDuplicates("aaaaaaaa"));
	}

	public static String removeDuplicates(String S) {
		return solve(0, S);
	}


	private static String solve(int i, String s) {
		if (s.length() == 1) {
			return s;
		} else if (s.length() == 2) {
			if (s.charAt(0) == s.charAt(1)) {
				return "";
			} else {
				return s;
			}
		}

		for (int j = i; j < (s.length() - 1); j++) {
			if (s.charAt(j) == s.charAt(j+1)) {
				if (j > 0) {
					return solve(j - 1, s.substring(0, j) + s.substring(j + 2));
				} else {
					return solve(0, s.substring(j + 2));
				}
			}
		}

		return s;
	}
}
