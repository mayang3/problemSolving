package hackerrank.cs.algorithm.string;

import java.util.Scanner;

/**
 * Madam Hannah Otto, the CEO of Reviver Corp. is fond of palindromes, or words that read the same forwards or backwards.
 *
 * She thinks palindromic brand names are appealing to millennials.
 *
 * As part of the marketing campaign for the company's new juicer called the Rotator,
 *
 * Hannah decided to push the marketing team's palindrome-searching skills to a new level with a new challenge.
 *
 * In this challenge Hannah provides a string s consisting of lowercase English letters.
 *
 * Every day, for q days, she would select two integers l and r, take the substring Sl...Sr (the substring of s from index l to index r),
 *
 * and ask the following question:
 *
 * Consider all the palindromes that can be constructed from some of the letters from Sl...Sr.
 *
 * You can reorder the letters as you need.
 *
 * Some of these palindromes have the maximum length among all these palindromes.
 *
 * How many maximum-length palindromes are there?
 *
 * For example, if S=madamimadam, l=4 and r=7, then we have,
 *
 * S=madamimadam -> substring(4,7) -> Sl...Sr=amim -> (palindrome) mam, min -> Number of maximum length palindromes = 2
 *
 * Your job as the head of the marketing team is to answer all the queries.
 *
 * Since the answers can be very large, you are only required to find the answer modulo 10^9+7.
 *
 * Complete the functions initialize and answerQuery and return the number of maximum-length palindromes modulo 10^9+7.
 *
 * [Input Format]
 *
 * The first line contains the string s.
 *
 * the second line contains a single integer q.
 *
 * The ith of the next q lines contains two space-separated integers li, ri, denoting the l and r values Anna selected on the ith day.
 *
 * [Constraints]
 *
 * Here, [s] denotes the length of s.
 *
 * 1 <= |s| <= 10^5
 * 1 <= q <= 10^5
 * 1 <= li <= ri <= |s|
 *
 * [Output Format]
 * For each query, print a single line containing a single integer denoting the answer.
 *
 * 
 *
 */
public class Maximum {
	static final int M = 10000007;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String s = scanner.next();
		int q = scanner.nextInt();

		for (int i = 0; i < q; i++) {
			int l = scanner.nextInt() - 1;
			int r = scanner.nextInt() - 1;

			System.out.println(solve(s.substring(l, r+1)));
		}
	}

	static long solve(String subString) {
		int [] aToz = new int[26];

		for (int i = 0; i < subString.length(); i++) {
			int idx = subString.charAt(i) - 97;
			aToz[idx]++;
		}

		int n = 0;
		int r = 0;
		int cntSum = 0;

		for (int i = 0; i < aToz.length; i++) {
			if (aToz[i] >= 2) {
				n += (aToz[i] / 2); // e.g) qqqq
				r++;
				cntSum += aToz[i];
			}
		}

		// 같은 수가 하나도 없다면, palindrome 의 최대 길이는 1이다.
		if (n == 0) {
			return subString.length();
		}

		if (n == 1 && cntSum == subString.length()) {
			return 1;
		}

		// e.g ) 또 다른 예외 qqy -> 3가지..
		// 단순히 3! 하면 6가지 나온다..
		return nCr(n, r) * (subString.length() - n * 2) % M;
	}

	// n 총 개수
	// 다른 문자인 개수
	static long nCr(int n, int r) {
		return factorial(n) * modInverse(factorial(r) * factorial(n-r) , M) % M;
	}

	static long modInverse(long a, long m) {
		a = a % M;

		long m0 = m;
		long y =0, x=1;

		if (m == 1) {
			return 0;
		}

		while (a > 1) {
			// q is quotient
			long q = a / m;
			long t = m;

			// m is remainder now, process
			// same as Euclid's algo
			m = a % m;
			a = t;
			t = y;

			// Update x and y
			y = x - q * y;
			x = t;
		}

		// Make x positive
		if (x < 0) {
			x += m0;
		}

		return x;
	}

	static long factorial(int num) {
		long ret = 1;

		for (int i = 2; i <= num ; i++) {
			ret = (ret * i % M);
		}

		return ret;
	}
}
