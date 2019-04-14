package hackerrank.mathematics.fundamentals;

import java.util.Scanner;

/**
 * TODO 복습
 *
 * 매우 어려웠음..
 *
 * We will describe two different approach two solve this problem.
 *
 * The problem requires us to print the trailing 9 digits.
 *
 * So we need to print the result modulo 10^9.
 *
 * 1. with Combinators.
 *
 * Imagine we decide to buy Xi candies of the i-th type for all i from 1 to N.
 * (1~N 개 까지의 모든 타입의 사탕을 구입한다고 생각해보자.) -> 여기서 i 는 사탕의 type 을 의미한다.
 *
 * No Xi can be negative and X1 + X2 + X3 + ... + Xn = K must hold.
 * Xi 는 음수가 아니다. 그리고 X1 + X2 + X3 + ... + Xn = K 개를 반드시 보유해야 한다.
 *
 * We need to find out how many solutions of the equation exists.
 * 우리는 이 방정식의 해가 얼마나 존재하는지를 찾아내야 한다.
 *
 * Suppose there are K identical balls and N - 1 identical plus(+) signs arranged in a row.
 * K 개의 각각의 공과 N - 1 개의 동일한 더하기 (+) 기호가 연속적으로 배열되어 있다고 가정해 보자.
 *
 * We are going to prove that the number of solutions to that equation is equal to the number of distinct permutations of this arrangement.
 * 우리는 방정식에 대한 해답의 수는 이 배열의 별개의 순열의 수와 동일하다는 것을 증명할 것입니다.
 *
 * Proof:
 *
 * Every permutation of this arrangement can be treated as a solution to the equation.
 * 이 배열의 모든 순열은 방정식의 해답으로 취급될 수 있다.
 *
 * The N-1 plus signs divide the arrangement into N intervals (Some intervals might be empty).
 * N-1 개의 + 기호는 배열을 N 개의 interval 로 나눈다.
 *
 * The number of balls in the i-th interval denotes the value of Xi for the current solution.
 * i 번째 간격의 볼의 수는 현재 솔루션에서 Xi 를 가리킨다.
 *
 * So every possible permutation can be mapped to a unique solution to the equation.
 * 그래서 모든 가능한 순열은 방정식의 유일한 해답에 맵핑된다.
 *
 * Conversely, any solution to the equation can be shown as an arrangement of K balls and N-1 plus sign in a unique way.
 * 반대로 방정식에 대한 모든 해는 K 공과 N-1 더하기 기호의 배열로 볼 수 있다.
 *
 * Thus every possible solution can be mapped to a unique permutation of K balls and N-1 plus signs.
 * 그래서, 모든 가능한 솔루션은 K 개의 공과 N-1 개의 plus 기호의 유니크한 순열로 표현될 수 있다.
 *
 * So the number of solutions is equal to the number of permutations of that arrangement.
 * 따라서 배열의 순열의 수는 해답의 수와 같다.
 *
 * We can permute K identical balls and N-1 identical plus sign in (K+N-1)!/(N-1)!K! ways which is equal to (k+n-1)C(n-1).
 * 우리는 K 개의 공과 N-1 개의 plus 기호를 (K+N-1)!/(N-1)!K! 로 치환할 수 있다.
 *
 * (n r) = (n-1 r-1) + (n-1 r)
 *
 */
public class KCandyStore {
	static int [][] C = new int[2003][2003];
	static final int MOD = 1000000000;

	static {
		for (int n = 0; n < 2001; n++) {
			for (int r = 0; r < n + 1; r++) {
				if (r == 0 || n == r) {
					C[n][r] = 1;
				} else {
					// (n r) = (n-1 r-1) + (n-1 r)
					C[n][r] = (C[n-1][r-1] + C[n-1][r]) % MOD;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int n = scanner.nextInt();
			int k = scanner.nextInt();

			System.out.println(C[k+n-1][k]);
		}
	}
}
