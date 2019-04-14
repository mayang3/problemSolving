package baekjoon.binomial;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 여기서 배워야 할 부분이 하나 있다.
 *
 * 바로, nCr 에서 가장 가지수가 많이 나오는 경우는 r 이 n 의 절반인 경우이다.
 *
 * e.g)
 * n=100 의 경우
 *
 * 100 49
 * 98913082887808032681188722800
 *
 * 100 50
 * 100891344545564193334812497256
 *
 * 100 51
 * 98913082887808032681188722800
 *
 * n = 50 의 경우
 *
 * 50 24
 * 121548660036300
 *
 * 50 25
 * 126410606437752
 *
 * 50 26
 * 121548660036300
 *
 * n=49 의 경우처럼 n/2 가 딱 나누어 떨어지지 않는 경우는 걸쳐있는 두 수의 경우의 수가 같다.
 *
 * 즉, 49/2 = 24.5 이기 때문에, r 이 24일 경우와 25일 경우 모두 경우의 수가 같다.
 *
 * 49 23
 * 58343356817424
 *
 * 49 24
 * 63205303218876
 *
 * 49 25
 * 63205303218876
 *
 * 49 26
 * 58343356817424
 *
 */
public class 조합 {

	/**
	 * use dp
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();

		BigInteger bigInteger = new BigInteger("1");

		for (long i=n-m+1,j=1 ; i<=n ; i++, j++) {
			BigInteger multiply = bigInteger.multiply(new BigInteger(String.valueOf(i)));
			bigInteger = multiply.divide(new BigInteger(String.valueOf(j)));
		}

		System.out.println(bigInteger.toString());
	}
}
