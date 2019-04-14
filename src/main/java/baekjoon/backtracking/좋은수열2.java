package baekjoon.backtracking;

import java.math.BigInteger;
import java.util.Scanner;

public class 좋은수열2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		String nums = solve("1", n);

		System.out.println(nums.substring(0, n));
	}

	private static String solve(String l, int n) {
		if (l.length() >= n) {
			return l;
		}

		String r = new BigInteger(l).add(new BigInteger("1")).toString();

		return solve(l + r, n);
	}
}
