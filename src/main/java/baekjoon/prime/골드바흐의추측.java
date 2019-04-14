package baekjoon.prime;

import java.util.*;

/**
 * time out!!
 */
public class 골드바흐의추측 {
	static int MAX_N = 10000;
	static boolean [] isPrime = new boolean[MAX_N+1];

	static {
		Arrays.fill(isPrime, true);
	}

	static void eratonsthenes() {
		isPrime[0] = isPrime[1] = false;

		int sqrtn = (int)Math.sqrt(MAX_N);

		for (int i=2 ; i<=sqrtn ; i++) {
			if (isPrime[i]) {
				for (int j=i*i ; j<=MAX_N ; j+=i) {
					isPrime[j] = false;
				}
			}
		}
	}

	public static void main(String[] args) {
		eratonsthenes();

		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int GN = scanner.nextInt();

			List<Pair> retList = new ArrayList<>();

			for (int i=2 ; i<=GN ; i++) {
				// 이 부분 바꾸고 accept 되었다.
				// 원래는 i~GN 까지의 이중 for 문으로 Pair 를 걸러냈었음
				if (isPrime[i] && isPrime[GN-i]) {
					retList.add(new Pair(i, GN-i));
				}
			}

			Collections.sort(retList, (o1, o2) -> {
				int diff1 = Math.abs(o1.a - o1.b);
				int diff2 = Math.abs(o2.a - o2.b);

				if (diff1 == diff2) {
					return 0;
				}

				return diff1 < diff2 ? -1 : 1;
			});

			System.out.println(retList.get(0).a + " " + retList.get(0).b);
		}
	}

	static class Pair {
		int a;
		int b;

		Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

}
