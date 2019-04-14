package hackerrank.cs.algorithm.bitmanipulation;

import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class CounterGame {

	/**
	 * 1. if N is not a power of 2, reduce the counter by the largest power of 2 less than N.
	 *
	 * 2. if N is power of 2, reduce the counter by half of N.
	 *
	 * 3. The resultant value is the new N wich is again used for subsequent operations.
	 *
	 * 10 (2)(Louise) - a power of 2
	 * 11 (3)(Louise)
	 * 100 (4)(Richard) - a power of 2
	 * 101 (5)(Louise)
	 * 110 (6)(Richard)
	 * 111 (7)(Richard)
	 * 1000 (8)(Louise) - a power of 2
	 * 1001 (9)(Louise)
	 *
	 * 여기서 아래 point 부분의 두가지 실수 때문에, 시간을 많이 잡아먹음..
	 *
	 * 1. 문제 해석 실패 : 2의 제곱수가 아닌경우, N보다 작은 수중에 가장 큰 2의 제곱수를 N 에서 빼는게 문제였는데
	 *    N보다 작은 수중에 가장 큰 2의 제곱수의 "지수" 를 다음 N 으로 설정했었음..
	 *    ( Math.log(1L << highst) / Math.log(2) )
	 *
	 * 2. 1을 shift 연산 하는데 있어서, 1L 을 붙이지 않았기 때문에,
	 *    int 형에서 shift 되어서 32비트를 넘은 숫자는 다시 되돌아와서 쉬프트 되었음..
	 *
	 *    이 부분은 실무에서도 발생하기 쉽고, 대회에서도 자주 할 수 있는 실수인만큼 주의하자.
	 *
	 * @param n
	 * @return
	 */
	static String counterGame(long n) {

		boolean louise = false;

		while (n != 1) {
			// a power of 2
			if (Long.bitCount(n) == 1) {
				n >>= 1L;
			} else {
				long highest = (long)(Math.log(Long.highestOneBit(n)) / Math.log(2));
				n -= (1L << highest); // point 여기서 reduce 해석을 잘못해서 오래걸림.... 고친 후 답은 맞지만 timeout!!
			}

			louise = !louise;
		}

		if (louise) {
			return "Louise";
		}

		return "Richard";
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int a0 = 0; a0 < t; a0++){
			long n = in.nextLong();
			String result = counterGame(n);
			System.out.println(result);
		}
		in.close();
	}
}
