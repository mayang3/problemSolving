package hackerrank.cs.algorithm.recursion;

import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class ThePowerSum {
	/**
	 * X 를 어떤 수들의 ^N 으로 표현할 수 있는 방법의 수를 찾는다.
	 *
	 * @param X
	 * @param N
	 * @return
	 */
	static int powerSum(int X, int N) {
		return powerSum2(X, N, 1);
	}

	static int powerSum2(int X, int N, int idx) {
		if (X == 0) {
			return 1;
		} else if (X < 0) {
			return 0;
		}

		int sum = 0;
		int k = 0;

		for (int i=idx ; k <X ; i++) {
			k=(int)Math.pow(i, N);

			X -= k;

			sum += powerSum2(X, N, i+1);

			X += k;
		}

		return sum;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int X = in.nextInt();
		int N = in.nextInt();
		int result = powerSum(X, N);
		System.out.println(result);
		in.close();
	}
}
