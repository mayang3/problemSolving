package baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

public class StairNumber {
	static int MOD = 1000000000;
	static long [][] cache = new long[10][101];

	static long solve(int i, int n) {
		if (i >= 0 && n == 0) {
			return 1;
		} else if (i < 0 || i > 9) {
			return 0;
		}

		if (cache[i][n] != -1) {
			return cache[i][n];
		}

		long sum = 0;

		sum = (sum + solve(i+1, n-1)) % MOD;
		if (n > 1) {
			sum = (sum + solve(i - 1, n - 1)) % MOD;
		}

		return cache[i][n] = (sum % MOD);
	}

	/**
	 * 3번 실패 후, Accept 받았다..
	 *
	 * 확인해보니 점화식은 잘 세웠는데.. 계속 오류가 나서 MOD 로 나누는 부분을 모든 결과 덧셈 연산에 지정해주니 해결되었다.
	 *
	 * 덧셈연산을 하는 모든 곳에서 값을 초과할 수 있기 때문에, MOD 로 나누는 부분을 추가해주어야 한다.
	 *
	 * 그리고, 또 한가지! 주의해야 할 부분은 mod 연산할시에 괄호를 잘 지정해주자!
	 *
	 * 덧셈을 모두 한 후에! MOD 연산을 실시할것!!
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();

		long sum = 0;

		for (long [] c : cache) {
			Arrays.fill(c, -1);
		}

		for (int i=1 ; i<10 ; i++) {
			sum = (sum + solve(i, n)) % MOD;
		}

		System.out.println(sum);

	}
}
