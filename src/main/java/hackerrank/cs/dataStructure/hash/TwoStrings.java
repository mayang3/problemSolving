package hackerrank.cs.dataStructure.hash;

import java.util.Scanner;

public class TwoStrings {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int p = scanner.nextInt();

		for (int i = 0; i < p; i++) {
			String s1 = scanner.next();
			String s2 = scanner.next();

			int [] alpha = new int[26];

			for (int j = 0; j < s1.length(); j++) {
				alpha[s1.charAt(j)-97]++;
			}

			int cnt = 0;

			for (int j = 0; j < s2.length(); j++) {
				if (alpha[s2.charAt(j)-97] > 0) {
					cnt++;
				}

				if (cnt > 0) {
					break;
				}
			}

			if (cnt > 0) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}

		}

	}
}
