package codeforce.div2.round537.A;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SuperHeroTransformation {
	private static final String YES = "Yes";
	private static final String NO = "No";

	private static final Set<Character> vowels = new HashSet<Character>();

	static {
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String s = scanner.next();
		String t = scanner.next();

		if (s.length() != t.length()) {
			System.out.println(NO);
			return;
		}

		for (int i = 0; i < s.length(); i++) {
			char c1 = s.charAt(i);
			char c2 = t.charAt(i);

			// c1 이 모음이면 c2 도 모음이여야 한다.
			if (vowels.contains(c1) && !vowels.contains(c2)) {
				System.out.println(NO);
				return;
			} else if (!vowels.contains(c1) && vowels.contains(c2)) {
				// c1 이 자음이면 c2 도 자음이어야 한다.
				System.out.println(NO);
				return;
			}
		}

		System.out.println(YES);
	}
}
