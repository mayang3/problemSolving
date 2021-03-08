package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WordLadder {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		boolean [] visited = new boolean[wordList.size()];

		Queue<String> q = new LinkedList<>();
		q.add(beginWord);

		int count = 1;

		while (q.isEmpty() == false) {
			int size = Integer.valueOf(q.size());

			for (int i = 0; i < size; i++) {
				String word = q.poll();

				if (word.equals(endWord)) {
					return count;
				}

				for (int j = 0; j < wordList.size(); j++) {
					String nextWord = wordList.get(j);

					if (visited[j] == false && isTransformable(word, nextWord)) {
						visited[j] = true;
						q.add(nextWord);
					}
				}
			}

			count++;
		}

		return 0;
	}

	private boolean isTransformable(String word, String s) {
		int count = 0;

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != s.charAt(i)) {
				count++;
			}
		}

		return count == 1;
	}

	public static void main(String[] args) {
		String beginWord = "hit";
		String endWord = "cog";

		String [] wordArr = {"hot","dot","dog","lot","log"};
		List<String> wordList = Arrays.asList(wordArr);

		WordLadder wl = new WordLadder();

		System.out.println(wl.ladderLength(beginWord, endWord, wordList));
	}
}
