package leetcode.contest;

import java.util.Scanner;

/**
 */
public class ValidPalindrome2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String s = scanner.next();

		validPalindrome(s);
	}

	public static void validPalindrome(String s) {
		int l = 0;
		int r = s.length() - 1;

		while (l <= r) {
			char lc = s.charAt(l);
			char rc = s.charAt(r);

			if (lc != rc) {
				if (isSubPalindrome(l, r-1, s) || isSubPalindrome(l+1, r, s)) {
					System.out.println("True");
					return;
				} else {
					System.out.println("False");
					return;
				}
			}

			l++;
			r--;
		}

		System.out.println("True");
	}

	static boolean isSubPalindrome(int l, int r, String s) {
		if (l > r) {
			return false;
		}

		while (l <= r) {
			char lc = s.charAt(l);
			char rc = s.charAt(r);

			if (lc != rc) {
				return false;
			}

			l++;
			r--;
		}

		return true;
	}
}
