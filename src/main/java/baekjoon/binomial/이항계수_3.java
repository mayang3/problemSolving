package baekjoon.binomial;

import java.util.Scanner;

/**
 * 엄청나게 삽질했음 ..
 *
 * 기존의 곱셈의 역원을 통해 했는데도 다른 값이 나옴..
 *
 * 알고보니 M 의 값이 상당히 크기 때문에, 중간 계산 결과가 int 를 초과하는 결과가 나왔던 것..
 *
 * (a * b ) % M 을 해주더라도, 우선 앞의 a * b 가 자료형의 범위를 초과하면 안되므로..
 *
 * 이 부분을 눈여겨 봐야 한다.
 *
 * 아래와 같이 M 이 1억이 넘는 경우면 a * b 가 int 자료형을 초과할 수 있음
 *
 * e.g)
 *
 * 1000000006L * 1000000006L = 1000000012000000036
 * 1000000006L * 2 = 2000000012 < Integer.MAX_VALUE
 *
 * 위의 두가지 경우를 눈여겨 볼것..
 *
 */
public class 이항계수_3 {

	static int M = 1000000007;

	/**
	 *
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int K = scanner.nextInt();

//		long ans = 1;
//
//		for (int numerator=N-K+1, denominator=1 ; numerator<=N ; numerator++, denominator++) {
//			ans = ((ans*numerator %M) * modInverseByPower(factorial(K) * factorial(N-K), M-2)) % M;
//		}


		// 위 코드도 돌아는 간다. 얼핏생각하면 위 코드가 더 빠를 것이라고 생각할수도 있지만..
		// 아래코드가 더 빠르다.
		// 원인은 역원 계산을 위의 코드는 매번하는데 비해서, 아래코드는 한번만한다!! O(logM)
		// 단, modular 연산이 없는 경우에는 당연히 위의 코드가 더 빠르다. -> 참조 {@link 조합}

		// 역원 계산시 입력에 곱셈 부분에서 % M 을 해준 부분을 유의해서 보자..
		System.out.println((factorial(N) * modInverseByPower((factorial(K) * factorial(N-K)) % M, M-2)) % M);
	}

	/**
	 * e.g) x == 2, y == 4
	 *
	 *
	 * 1step) y == 4  return 4 * 4 : (p*p)
	 * 2step) y == 2, return 2 * 2 : (p*p)
	 * 3step) y == 1, return 2 * 1 : (x * (p*p))
	 * 4step) y == 0, return 1
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	static long modInverseByPower(long x, long y) {
		if (y == 0) {
			return 1;
		}

		long p = modInverseByPower(x, y/2) % M;
		p = (p * p) % M;

		if (y % 2 == 0) {
			return p;
		}

		return (x * p) % M;
	}

	static long factorial(int num) {
		long ret = 1;

		for (int i=2 ; i<=num; i++) {
			ret = ((ret * i) % M);
		}

		return ret;
	}
}
