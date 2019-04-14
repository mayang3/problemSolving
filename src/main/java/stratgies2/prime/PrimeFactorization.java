package stratgies2.prime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 소인수분해
 */
public class PrimeFactorization {

	public static void main(String[] args) {
		System.out.println(factorSimple(4));
		System.out.println(primeFactorizationFast(4));
	}

	static List<Integer> factorSimple(int n) {
		List<Integer> ret = new ArrayList<>();

		int sqrtn = (int)Math.sqrt(n);

		for (int div=2 ; div<=sqrtn ; div++) {
			while (n % div == 0) {
				n /= div;
				ret.add(div);
			}
		}

		// n 이 1보다 클 경우에, 나누고 마지막에 남은 n 을 결과값에 포함시켜준다.
		// n=1 일 경우, 1 은 소인수가 아니기 때문에 값은 공집합이 된다.
		if (n > 1) {
			ret.add(n);
		}

		return ret;
	}

	static List<Integer> primeFactorizationFast(int n) {
		boolean [] isPrime = new boolean[n+1];
		Arrays.fill(isPrime, true);

		isPrime[0] = isPrime[1] = false;

		int sqrtn = (int)Math.sqrt(n);

		List<Integer> primeList = new ArrayList<>();

		for (int i=2; i<=sqrtn ; i++) {
			if (isPrime[i]) {
				primeList.add(i);
				for (int j=i*i ; j<=n ; j*=i) {
					isPrime[j] = false;
				}
			}
		}

		// 소인수분해 결과값
		List<Integer> ret = new ArrayList<>();

		for (int prime : primeList) {
			while (n % prime == 0) {
				ret.add(prime);
				n /= prime;
			}
		}

		if (n > 1) {
			ret.add(n);
		}

		return ret;
	}
}
