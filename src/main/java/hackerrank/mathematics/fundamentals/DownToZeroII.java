package hackerrank.mathematics.fundamentals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * fail
 *
 */
public class DownToZeroII {

	static boolean [] isPrime;
	static final int INF = 987654321;

	static {
		eratosthenes();
	}

	static void eratosthenes() {
		int n = (int)Math.pow(10, 6) + 1;

		isPrime = new boolean[n+1];

		Arrays.fill(isPrime, true);

		isPrime[0] = isPrime[1] = false;

		int sqrtn = (int)Math.sqrt(n);

		for (int i=2 ; i<=sqrtn ; i++) {
			if (isPrime[i]) {
				for (int j= i*i ; j<=n ; j+=i) {
					isPrime[j] = false;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int N = scanner.nextInt();

			Queue<Integer> Q = new LinkedList<Integer>();
			Q.add(N);

			int cnt = 0;

			while (Q.isEmpty() == false) {
				Integer poll = Q.poll();

				if (poll == 0) {
					break;
				}

				cnt++;

				int maxB = findMaxB(poll);

				if (maxB == -1) {
					Q.add(poll - 1);
				} else {
					if (isPrime[maxB] && isPrime[poll-1] == false) {
						maxB = poll-1;
					}

					Q.add(maxB);
				}
			}

			System.out.println(cnt);
		}
	}

	static int findMaxB(int n) {
		int sqrtn = (int)Math.sqrt(n);

		if (sqrtn < 2) {
			return -1;
		}

		if (sqrtn * sqrtn == n) {
			return sqrtn;
		}

		int ret = -1;
		int min = 987654321;

		for (int div = sqrtn; div < n; div++) {
			if (n % div == 0 && isPrime[div] == false) {
				return div;
			} else if (n % div == 0) {
				min = Math.min(min, div);
			}
		}

		return min == INF ? ret : min;
	}


}
