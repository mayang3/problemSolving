package leetcode;

public class ReorganizingString_2 {
	public String reorganizeString(String S) {
		int [] count = new int[26];

		for (int i = 0; i < S.length(); i++) {
			count[S.charAt(i) - 'a']++;
		}

		int loop = S.length();

		StringBuilder sb = new StringBuilder();

		while (loop > 0) {
			for (int i = 0; i < count.length; i++) {
				if (count[i] > (S.length() + 1) / 2) {
					return "";
				}

				if (count[i] > 0) {
					sb.append((char)(i + 'a'));
					count[i]--;
					loop--;
				}
			}
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		ReorganizingString_2 reorganizingString_2 = new ReorganizingString_2();

		System.out.println(reorganizingString_2.reorganizeString("aab"));
	}
}
