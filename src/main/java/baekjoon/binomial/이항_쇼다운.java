package baekjoon.binomial;

import java.util.Scanner;

public class 이항_쇼다운 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			long N = scanner.nextInt();
			long K = scanner.nextInt();

			if (N == 0 && K == 0) {
				break;
			}

			// 이 부분이 핵심 포인트
			// 분자와 분모의 약분을 최대한 많이 하기 위해서 K * (N-K) 의 분모 식에서
			// N-K 가 K 보다 크다면 K 를 바꾸어 준다.
			// 예를 들어 5C4 일 경우 분모는 다음과 같이 되는데, (4 * 3 * 2 * 1 ) * 1
			// K 를 1로 바꾸어두면, 아래 계산식에서 5 / 1 로 바뀌게 된다.
			// -> 어차피 i 를 계산할때 K 를 활용하므로..
			if (K > N - K) {
				K = N - K;
			}

			long ans = 1;

			for (long i=N-K+1, j=1 ; i<=N ; i++,j++) {
				ans = (ans*i) / j;
			}

			System.out.println(ans);
		}
	}

}
