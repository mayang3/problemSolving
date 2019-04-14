package hackerrank.cs.algorithm.string;

import java.util.Scanner;

/**
 * Suppose we want to answer a query [l,r].
 *
 * For each letter, we need to know the number of its occurrence.
 *
 * We can calculate it in O(1) time using prefix sums.
 *
 * we can bisect every palindrome.
 *
 * If its length is odd, we have "remainder" - the middle letter.
 *
 * We want to know the number of pairs of strings (w,t),
 *
 * where w is half of some maximum palindrome, and t is the palindrome remainder.
 *
 * Note that t is an empty string if the maximum palindromes length is even.
 * (만약 palindrome 이 짝수라면 t 는 empty string 이 된다.)
 *
 * The number of these pairs will be the answer because we can build palindrome w + t + reversed(w).
 *
 * and, vice versa, we can build such pair by the maximum palindrome.
 *
 * Let Ci be the number of occurrences of letter i. Consider the following cases:
 * (Ci는 문자 i 의 출현빈도이다. 다음 케이스들을 고려하자.)
 *
 * 1. Ci is even. Then a half of every maximum palindrome will contain exactly Fi=Ci/2 letters i.
 *	Ci 가 짝수이면, 최대 회문의 절반의 string 에는 정확하게 Fi=Ci/2 개의 문자 i 가 포함된다.
 *
 * 2. Ci is odd. Then a half of every maximum palindrome will contain exactly Fi=Ci-1/2 letters i.
 *    Also this means that we can set this letter to the middle of palindrome (i can be t).
 *  Ci 가 홀수이면, 최대 회문의 절반의 string 에는 정확하게 Fi=(Ci-1)/2 개의 문자 i 가 포함된다.
 *  또한 이것은 회문의 가운데에 문자를 설정할 수 있다는 것을 의미한다. (남은 i 또한 t가 될 수 있다. )
 *
 * Let m be the number of odd Ci.
 * m 을 홀수 Ci 숫자라고 정의해보자.
 *
 * If m=0, the maximum palindromes length will be even; otherwise it will be odd and there will be exactly m possible middle letters.
 * 만약 m=0 이라면 최대 회문의 길이는 짝수이다. 반대의 경우에는 홀수이다. 그리고 정확하게 m 이 가운데 문자가 된다.
 *
 * Now, it's obvious that the answer is max(1,m) * {(Fa + Fb + Fz)! / (Fa! * Fb! ... * Fz!)} * (The letter represents a multinomial coefficient.)
 *
 *
 * So, we need to precompute factorials and inverses of factorials modulo 10^9 + 7.
 *
 * This can be done in O(|s| log mod) or O(|s|).
 *
 */
public class MaximumAns {
	static final int N = 100001;
	static final int A = 'z' - 'a' + 1;
	static final long MOD = (long)1e9 + 7;

	static long power(long x, long y) {
		if (y == 0) {
			return 1;
		}

		// if odd
		// y 가 int 일 경우 이런 표현 방법도 있다.
		// y & 1 != 0 -> 짝수일 경우, y & 1 은 모조리 0 이 된다.
		if (y % 2 != 0) {
			return power(x, y-1) * x * MOD;
		}

		long temp = power(x, y/2);

		return temp * temp % MOD;
	}

	static long [] fact = new long[N];
	static long [] rFact = new long[N];

	static int nn;
	static int [][] cnt = new int[N][A];

	static long calc(int l, int r) {
		int sum = 0;
		int odd = 0;
		long res = 1;

		for (int i = 0; i < A; i++) {
			int cur = cnt[r][i] - cnt[l-1][i];
			sum += cur / 2;

			res = res * rFact[cur / 2] % MOD;

			if (cur % 2 == 1) {
				odd++;
			}
		}

		res = res * Math.max(1, odd) % MOD;
		res = res * fact[sum] % MOD;

		return res;
	}

	public static void main(String[] args) {
		fact[0] = 1;
		rFact[0] = 1;

		for (int i = 1; i < N; i++) {
			fact[i] = fact[i-1] * i % MOD; // 10000!
			rFact[i] = power(fact[i], MOD-2);
		}

		Scanner scanner = new Scanner(System.in);

		String s = scanner.next();
		int q = scanner.nextInt();

		nn = s.length();

		for (int i = 0; i < nn; i++) {
			cnt[i][s.charAt(i) - 'a']++;
		}

		for (int i = 1; i <= nn ; i++) {
			for (int j = 0; j < A; j++) {
				cnt[i][j] += cnt[i-1][j];
			}
		}

		for (int i = 0; i < q; i++) {
			int l = scanner.nextInt();
			int r = scanner.nextInt();

			System.out.println(calc(l, r));
		}
	}
}
