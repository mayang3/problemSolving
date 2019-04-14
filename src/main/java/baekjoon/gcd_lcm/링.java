package baekjoon.gcd_lcm;

import java.util.Scanner;

public class 링 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int [] arr = new int[n];

		for (int i=0 ; i<n ; i++) {
			arr[i] = scanner.nextInt();
		}

		int p = arr[0];

		for (int i=1 ; i<n ; i++) {
			int q = arr[i];

			int gcd = gcd(p,q);

			System.out.println(p/gcd + "/" + q/gcd);
		}
	}

	static int gcd(int p, int q) {
		if (q == 0) {
			return p;
		}

		return gcd(q, p%q);
	}
}
