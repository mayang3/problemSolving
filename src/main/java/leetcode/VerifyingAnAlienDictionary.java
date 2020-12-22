package leetcode;

public class VerifyingAnAlienDictionary {


	public boolean isAlienSorted(String[] words, String order) {
		int [] dic = new int[26];

		for (int i = 0; i < dic.length; i++) {
			dic[order.charAt(i) - 'a'] = i;
		}

		for (int i = 1; i < words.length; i++) {
			if (isOrdered(words[i-1], words[i], dic) == false) {
				return false;
			}
		}

		return true;
	}

	private boolean isOrdered(String word1, String word2, int[] dic) {

		for (int i = 0; i < Math.max(word1.length(), word2.length()); i++) {
			if (i >= word2.length()) {
				return false;
			} else if (i >= word1.length()) {
				return true;
			}

			int order1 = dic[word1.charAt(i) - 'a'];
			int order2 = dic[word2.charAt(i) - 'a'];

			if (order1 > order2) {
				return false;
			} else if (order1 < order2) {
				return true;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		String [] words = {"apple","app"};
		String order = "abcdefghijklmnopqrstuvwxyz";

		VerifyingAnAlienDictionary dictionary = new VerifyingAnAlienDictionary();
		boolean res = dictionary.isAlienSorted(words, order);

		System.out.println(res);
	}
}
