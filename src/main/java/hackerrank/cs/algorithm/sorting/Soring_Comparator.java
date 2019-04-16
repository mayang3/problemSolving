package hackerrank.cs.algorithm.sorting;

import java.util.Arrays;
import java.util.Scanner;

public class Soring_Comparator {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		Player[] arr = new Player[n];

		for (int i = 0; i < n; i++) {
			String name = scanner.next();
			int score  = scanner.nextInt();

			arr[i] = new Player(name, score);
		}

		Arrays.sort(arr, (a, b) -> {

			int diffScore = b.score - a.score;

			if (diffScore == 0) {
				return a.name.compareTo(b.name);
			}

			return diffScore;
		});

		for (Player p : arr) {
			System.out.println(p.name + " " + p.score);
		}
	}

	static class Player {
		String name;
		int score;

		Player(String name, int score) {
			this.name = name;
			this.score = score;
		}
	}
}
