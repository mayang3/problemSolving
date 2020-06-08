package leetcode.contest.weekly_190;

public class _1455 {
	public int isPrefixOfWord(String sentence, String searchWord) {

		String [] sp = sentence.split(" ");

		for (int i = 0; i < sp.length; i++) {
			String w = sp[i];

			if (w.startsWith(searchWord)) {
				return i+1;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		_1455 t = new _1455();

		int res = t.isPrefixOfWord("i use triple pillow", "pill");

		System.out.println(res);
	}
}
