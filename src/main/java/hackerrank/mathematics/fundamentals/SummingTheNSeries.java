package hackerrank.mathematics.fundamentals;

import java.util.Scanner;

/**
 * 처음엔 n 개 만큼 loop 돌다가 timeout 발생.
 *
 * O(n) 이기 때문에, 한번씩만 돌아도 1억 6천만번을 돌게 된다..
 *
 * 알고 보니 수식을 풀면 정답은 n^2 라는것을 알 수 있다.
 *
 * Tn = 2n - 1 이다.
 *
 * 그러므로 Sn = 1 + 3 + 5 + ... + 2n - 1 이다. , 여기서 항의 개수는 n 개이다.
 *
 * = (2n - 1 + 1 ) + (2n - 3 + 3 ) + (2n-5+5) * n/2 , 위 식에서 항의 개수는 n 개 이므로 곱해지는 수는 n/2 가 된다. (반으로 줄였으니 당연하다)
 * = 2n * n/2
 *
 * = n*n
 *
 *
 *
 */
public class SummingTheNSeries {

	static final int MOD = 1000000007;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			long n = scanner.nextLong();

			System.out.println(((n % MOD) * (n % MOD)) % MOD);
		}
	}
}
