package hackerrank.mathematics.fundamentals;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 맨 처음에는 공집합을 제외한 (모두 불꺼진 경우) 모든 경우에 대해서 이항 계수를 구하려고 생각했었음..ㅎㅎ
 *
 * 예를 들어, 전구가 2개인 경우에는 00 인 경우는 제외하고 10, 01, 11 의 세가지 상태가 있는데,
 *
 * 전구 2개중에 1개를 선택하는 경우 + 전구 2개중에 2개를 선택하는 경우를 더하면 답임..
 *
 * 그런데, 이렇게 구하려고 보니, M 이 소수가 아님. M 이 소수가 아니면 곱셈의 역원을 이용한 방법을 사용할수가 없음..
 *
 * 그래서 다시 생각해보니.. 전구의 각 케이스는 꺼짐 or 켜짐 두 가지 상태만 존재함.
 *
 * 그래서 전구가 하나씩 추가될때마다 2가지의 상태가 늘어남.
 *
 * 즉, 전구의 숫자가 N 개라면, 전구로 만들 수 있는 상태는 2^N 개가 된다.
 *
 * 그런데, 이 중에 공집합 ( 모든 전구가 꺼진 경우 ) 는 빼주어야 하므로.. 답은 2^N - 1 이 된다.
 *
 * 중간이 큰 숫자들만 잘 처리해주면 됨..
 *
 * 이항계수와 곱셈의 역원에 대해서 다시 한번 확인해볼 수 있었음..
 *
 */
public class DiwaliLights {
	static final int M = 100000;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int N = scanner.nextInt();

			BigInteger bigInteger = new BigInteger("2");

			BigInteger BN = new BigInteger(String.valueOf(N));
			BigInteger BM = new BigInteger(String.valueOf(M));

			String ret = bigInteger.modPow(BN, BM).subtract(new BigInteger("1")).toString(10);

			System.out.println(ret);
		}
	}


}
