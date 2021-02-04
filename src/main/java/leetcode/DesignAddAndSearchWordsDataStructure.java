package leetcode;

public class DesignAddAndSearchWordsDataStructure {

	public static void main(String[] args) {
		DesignAddAndSearchWordsDataStructure structure = new DesignAddAndSearchWordsDataStructure();

		structure.addWord("at");
		structure.addWord("and");
		structure.addWord("an");
		structure.addWord("add");

		System.out.println(structure.search("a"));
		System.out.println(structure.search(".at"));

		structure.addWord("bat");

		System.out.println(structure.search(".at"));
	}
	Node root = new Node();

	public DesignAddAndSearchWordsDataStructure() {

	}

	public void addWord(String word) {
		Node cur = this.root;

		for (int i = 0; i < word.length(); i++) {
			int idx = word.charAt(i) - 'a';

			if (cur.children[idx] == null) {
				cur.children[idx] = new Node();
			}

			if (i == word.length() - 1) {
				cur.children[idx].endOfWord = true;
			}

			cur = cur.children[idx];
		}
	}

	public boolean search(String word) {
		return solve(word, this.root);
	}

	private boolean solve(String word, Node node) {
		Node cur = node;

		for (int i = 0; i < word.length(); i++) {
			int idx = word.charAt(i) - 'a';

			if (idx == -51) {
				for (int j = 0; j < cur.children.length; j++) {
					if (cur.children[j] != null && solve(word.substring(i+1), cur.children[j])) {
						return true;
					}
				}

				return false;
			}

			Node next = cur.children[idx];

			if (next == null) {
				return false;
			}

			cur = next;
		}

		return cur.endOfWord;
	}

	static class Node {
		Node [] children = new Node[26];
		boolean endOfWord;
	}
}
