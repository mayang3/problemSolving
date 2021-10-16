package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class SearchSuggestionsSystem {
	public static void main(String[] args) {
		String [] products = {"havana"};
		String searchWord = "tatiana";

		SearchSuggestionsSystem searchSuggestionsSystem = new SearchSuggestionsSystem();
		System.out.println(searchSuggestionsSystem.suggestedProducts(products, searchWord));
	}

	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		TrieNode root = new TrieNode();

		for (String product : products) {
			insert(root, product);
		}

		return searchList(root, searchWord);
	}

	void insert(TrieNode root, String key) {
		TrieNode here = root;

		for (int i = 0; i < key.length(); i++) {
			int index = key.charAt(i) - 'a';

			if (here.children[index] == null) {
				here.children[index] = new TrieNode();
			}

//			here.wordList.add(key); 여기 넣으면 안된다. 이것에 주의하자.
			here = here.children[index];
			here.wordList.add(key);
		}

		here.isEndOfWord = true;
	}

	List<List<String>> searchList(TrieNode root, String key) {
		List<List<String>> res = new ArrayList<>();
		TrieNode here = root;

		for (int i = 0; i < key.length(); i++) {
			int index = key.charAt(i) - 'a';

			here = here.children[index];

			if (here == null) {
				break;
			}

			if (here.wordList.size() > 3) {
				res.add(new ArrayList<>(here.wordList).subList(0, 3));
			} else {
				res.add(new ArrayList<>(here.wordList));
			}
		}

		int len = res.size();

		for (int i = 0; i < key.length() - len; i++) {
			res.add(new ArrayList<>());
		}

		return res;
	}


	static class TrieNode {
		TrieNode [] children = new TrieNode[26];
		TreeSet<String> wordList = new TreeSet<>();
		boolean isEndOfWord = false;
	}
}
