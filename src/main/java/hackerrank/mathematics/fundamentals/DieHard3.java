package hackerrank.mathematics.fundamentals;

import java.util.Scanner;

public class DieHard3 {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int c = scanner.nextInt();

			int g = gcd(a, b);

			if ( (c > g && c % g == 0) || a == c || b == c) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}

		}
	}

	static int gcd(int p, int q) {
		if (q == 0) {
			return p;
		}

		return gcd(q, p % q);
	}
}
