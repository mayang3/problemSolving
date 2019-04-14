package hackerrank.cs.algorithm.string;

import java.util.Scanner;

/**
 * hackerank 의 leaderboard 에 나와있던 솔루션..
 *
 * 부분 문제로 쪼개어 dp 를 활용했다.
 *
 *
 */
public class CommonChildAwsome {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		char [] s1 = scanner.next().toCharArray();
		char [] s2 = scanner.next().toCharArray();

		// dp[i][j] 는 s1[i-1] "OR" s2[j-1] 까지의 문자열에서 가지고 있는 최대 공통 부분수열의 개수로 정의할 수 있다.
		int [][] dp = new int[s1.length+1][s2.length+1];

		// 이중 for 문을 통해 모든 문자의 경우를 체크한다.
		for (int i = 0; i < s1.length; i++) {
			for (int j = 0; j < s2.length; j++) {
				// 현재 character 가 모두 같다면 다음 캐릭터에서 가질 수 있는 Longest Common Subsequence 의 개수는 현재 위치에서 가지고 있는 개수 + 1 이다.
				if (s1[i] == s2[j]) {
					dp[i+1][j+1] = 1 + dp[i][j];
				} else {
					// 현재 character 가 다르다면, s2 를 하나증가시킨것의 경우의 수와 s1 을 하나 증가시킨것의 경우의 수중 큰것이 다음 캐릭터에서 가질수 있는 개수이다.
					dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
				}
			}
		}

		System.out.println(dp[s1.length][s2.length]);
	}
}
