package hackerrank.cs.algorithm.string;

import java.util.Scanner;

public class AlternatingCharacters {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		while (n-- > 0) {
			String s = scanner.next();

			char before = s.charAt(0);

			int cnt = 0;

			for (int i = 1; i < s.length(); i++) {
				char c = s.charAt(i);

				if (before == c) {
					cnt++;
				} else {
					before = c;
				}
			}

			System.out.println(cnt);
		}
	}
}
