package leetcode;

public class OneEditDistance {
	public boolean isOneEditDistance(String s, String t) {
		int len1 = s.length();
		int len2 = t.length();

		for (int i = 0; i < Math.min(len1, len2); i++) {
			char c1 = s.charAt(i);
			char c2 = t.charAt(i);

			if (c1 != c2) {
				if (len1 == len2) {
					return s.substring(Math.min(i+1, len1), len1).equals(t.substring(Math.min(i+1, len2), len2));
				} else if (len1 > len2) {
					return s.substring(Math.min(i+1, len1), len1).equals(t.substring(Math.min(i, len2), len2));
				} else {
					return s.substring(Math.min(i, len1), len1).equals(t.substring(Math.min(i+1, len2), len2));
				}
			}
		}

		// 맨 마지막 캐릭터가 하나 다른 경우
		return Math.abs(len1 - len2) == 1 ? true : false;
	}

	public static void main(String[] args) {
		OneEditDistance oneEditDistance = new OneEditDistance();

		System.out.println(oneEditDistance.isOneEditDistance("ab", "ba"));
	}
}
