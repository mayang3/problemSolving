package strategies.numberTheory;

import java.util.Arrays;

/**
 * @author baejunbeom
 */
public class Eratosthenes {
	static int n;
	static boolean [] isPrime;

	// 가장 단순한 형태의 에라토스테네스의 체 구현
	// 종료한 뒤 isPrime[i]=i가 소수인가?
	static void eratosthenes() {
		Arrays.fill(isPrime, true);

		// 0 과 1은 소수가 아니다.
		isPrime[0] = isPrime[1] = false;

		int sqrtn = (int)Math.sqrt(n);

		for (int i=2 ; i<=sqrtn ; i++) {
			// 이 수가 아직 지워지지 않았다면
			if (isPrime[i]) {
				// i 의 배수 j 들에 대해 isPrime[i] = false 로 둔다.
				// i*i 미만의 배수는 이미 지워졌으므로 신경 쓰지 않는다. !?
				for (int j= i*i ; j<=n ; j+=i) {
					isPrime[j] = false;
				}
			}
		}
	}

	public static void main(String[] args) {
		n = 10;
		isPrime = new boolean[n+1];
		eratosthenes();
		System.out.println(isPrime[0]);
	}
}
