package strategies.numberTheory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baejunbeom
 */
public class PrimeFactorization {

	// 주어진 정수 n을 소인수분해하는 간단한 알고리즘
	static List<Integer> factorSimple(int n) {
		List<Integer> ret = new ArrayList<>();

		int sqrtn = (int)Math.sqrt(n);

		// 핵심!
		// div 가 합성수인 경우에는 n 이 이미 그 이전의 소수에 의해 최대한 나눠진 뒤이기 때문에,
		// n 이 div로 나누어 떨어질 일은 없다.
		// 예를 들어, 합성수 4,6,8,9,10,12,14 를 생각해보면 알 수 있다.
		for (int div=2 ; div<=sqrtn ; div++) {
			while (n % div == 0) {
				n /= div;
				ret.add(div);
			}
		}

		// n 이 소수인 경우
		if (n>1) ret.add(n);

		return ret;
	}

	public static void main(String[] args) {
		List<Integer> integers = factorSimple(14);

		System.out.println(integers);
	}


}
