package strategies.numberTheory;

/**
 * @author baejunbeom
 */
public class Prime {

	/**
	 * 주어진 자연수 n 이 소수인지 확인한다.
	 *
	 * @param n
	 * @return
	 */
	static boolean isPrime(int n) {
		// 예외 처리 : 1과 2는 예외로 처리한다.
		if (n <= 1) return false;
		if (n == 2) return true;

		// 2를 제외한 모든 짝수는 소수가 아니다.
		if (n % 2 == 0) return false;

		int sqrtn = (int)Math.sqrt(n);
		// 2를 제외했으니 3 이상의 모든 홀수로 나누어보자.
		for (int div=3 ; div<=sqrtn ; div+=2) {
			if (n % div == 0) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(isPrime(12));
	}
}
