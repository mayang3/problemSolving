package baekjoon.dp;

import java.util.Scanner;

/**
 * dp[i][j] = i번째 수부터 j번째 수만 있다고 했을 때, 하나의 수로 합치는 총 비용이라 정의하자.
 * dp[i][j] = (i~j 번째 수의 합) + Min(D[i][k], D[k+1][j]) , for i<=k<j
 */
public class 파일합치기 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int k = scanner.nextInt();

			int[] files = new int[k + 1];

			for (int i = 1; i <= k; i++) {
				files[i] = scanner.nextInt();
			}

			// 합계 배열로 변환
			for (int i = 1; i <= k; i++) {
				files[i] += files[i - 1];
			}

			int[][] dp = new int[k + 1][k + 1];

			for (int i = 1; i < k; i++) { // 여기서 i 는 길이를 의미하게 된다. j 부터 시작해서 + i 까지의 길이
				for (int j = 1; j <= k - i; j++) { // 2차원 배열에서 뒤의 인덱스가 길이만큼 (i만큼) + 되므로 j 는 k 까지 다 순회할 필요가 없다.
					dp[j][i + j] = 1000000000;

					// j ~ i+j 까지의 파일 합치는 최소 비용은,
					// dp[j][s] + dp[s+1][i+j] 까지를 순회한 비용중에 최소 비용이다.!
					// 그렇지... 순회한 비용중에 최소 비용 ! 이게 중요하다.!

					// 예를 들어, 30,40,50,60,70,80 이 있다면
					// 40~70 까지의 파일 합치는 것의 최소 비용은,
					// 40 + 50~70 까지 합친 것의 최소비용 부터
					// 40~60 + 70 합친 것의 최소비용중에 제일 적은 값이다.
					for (int s = j; s < i + j; s++) {
						dp[j][i + j] = Math.min(dp[j][i + j], dp[j][s] + dp[s + 1][i + j]);
					}

					dp[j][i + j] += files[i + j] - files[j - 1];
				}
			}

			System.out.println(dp[1][k]);
		}
	}
}
