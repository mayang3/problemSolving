package leetcode;

public class Cracking {
	public void solve() {
		char [] chars = "Mr John Smith".toCharArray();

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < chars.length; i++) {
			char ch = chars[i];

			if (' ' != ch) {
				sb.append(chars[i]);
			} else {
				sb.append("%23");
			}
		}

		System.out.println(sb.toString());
	}

	public static void main(String[] args) {
		Cracking us = new Cracking();
		us.solve();
	}
}
