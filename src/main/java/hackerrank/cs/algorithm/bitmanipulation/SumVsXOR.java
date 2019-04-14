package hackerrank.cs.algorithm.bitmanipulation;

import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class SumVsXOR {
	static long solve(long n) {
		if (n == 0) return 1;

		long highest = (long)(Math.log(Long.highestOneBit(n)) / Math.log(2)) + 1;

		return (long)Math.pow(2, highest - Long.bitCount(n));
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long n = in.nextLong();
		long result = solve(n);
		System.out.println(result);
	}
}
