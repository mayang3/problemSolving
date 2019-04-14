package strategies.numberTheory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baejunbeom
 */
public class EratosthenesPrimeFactorization {
	static int n;
	// minFactor[i] = i의 가장 작은 소인수
	static int [] minFactor;

	// 에라토스테네스의 체를 수행하면서 소인수 분해를 위한 정보도 저장한다.
	static void eratosthenes() {
		minFactor[0] = minFactor[1] = -1;

		// 모든 숫자를 처음에는 소수로 표시해 둔다.
		for (int i=2 ; i<=n ; i++) {
			minFactor[i] = i;
		}

		// 에라토스테네스의 체를 수행한다.
		int sqrtn = (int)Math.sqrt(n);

		for (int i=2 ; i<=sqrtn ; i++) {
			if (minFactor[i] == i) {
				for (int j=i*i ; j<=n ; j+=i) {
					// 아직 약수를 본 적 없는 숫자인 경우 i 를 써둔다.
					if (minFactor[j] == j) {
						minFactor[j] = i;
					}
				}
			}
		}
	}

	// 2 이상의 자연수 n 을 소인수분해한 결과를 반환한다.
	static List<Integer> factor(int n) {
		List<Integer> ret = new ArrayList<>();

		// n 이 1이 될때까지 가장 작은 소인수로 나누기를 반복한다.
		while (n > 1) {
			ret.add(minFactor[n]);
			n /= minFactor[n];
		}

		return ret;
	}

	public static void main(String[] args) {
		n = 100;
		minFactor = new int[n+1];
		eratosthenes();
		System.out.println(factor(10));
	}
}
