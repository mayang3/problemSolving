package hackerrank.compete.bitSetGo;

import java.util.Arrays;
import java.util.Scanner;

public class BeLikeBumble {

	static final int M = (int)1e9+7;
	static Ret [] cache = new Ret[(int)1e4+1];


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();


		while (T-- > 0) {
			long c = scanner.nextLong();
			int n = scanner.nextInt();

			Arrays.fill(cache, null);

			System.out.println(c * solve(n).sum % M);
		}
	}

	static Ret solve(int n) {
		if (n == 1) {
			return new Ret(1, 1);
		}

		if (cache[n] != null) {
			return cache[n];
		}

		Ret ret = solve(n - 1);

		long value = ret.value + (6 * (n-1));
		long sum = ret.sum + value;

		return cache[n] = new Ret(sum, value);
	}

	static class Ret {
		long sum;
		long value;

		Ret(long sum, long value) {
			this.sum = sum;
			this.value = value;
		}
	}
}
