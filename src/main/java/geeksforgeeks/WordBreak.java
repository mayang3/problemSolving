package geeksforgeeks;

import java.util.Scanner;

/**
 * @author neo82
 */
public class WordBreak {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int t = scanner.nextInt();

		while (t-- > 0) {
			int n = scanner.nextInt();

			String [] dic = new String[n];

			for (int i = 0; i < n; i++) {
				dic[i] = scanner.next();
			}

			String word = scanner.next();

			Boolean [] dp = new Boolean[word.length()];

			if (solve(dp, dic, word, 0)) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}

	static boolean solve(Boolean[] dp, String[] dic, String word, int currentIndex) {
		if (word.length() == currentIndex) {
			return true;
		} else if (word.length() < currentIndex) {
			return false;
		}


		if (dp[currentIndex] != null) {
			return dp[currentIndex];
		}

		for (String dicWord : dic) {
			if (word.length() - currentIndex >= dicWord.length()
				&& word.substring(currentIndex, currentIndex + dicWord.length()).equals(dicWord)
			    && solve(dp, dic, word, currentIndex + dicWord.length())) {
					return dp[currentIndex] = true;
			}
		}

		return dp[currentIndex] = false;
	}
}
