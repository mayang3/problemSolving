package hackerrank.cs.algorithm.string;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 */
public class CommonChild {
	static char [] s1;
	static char [] s2;
	static int [][] cache;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		s1 = scanner.next().toCharArray();
		s2 = scanner.next().toCharArray();

		// cache 배열은 i,j 위치에서의 Longest Common subSequence 를 의미한다.
		cache = new int[s1.length+1][s2.length+1];

		for (int [] ca : cache) {
			Arrays.fill(ca, -1);
		}

		System.out.println(solve(0, 0));
	}

	/**
	 * s1 의 위치와 s2 의 위치에 따른 최대 길이 부분문자열 을 저장하는 cache[i][j] 값을 리턴한다.
	 *
	 * @param i s1 의 위치
	 * @param j s2 의 위치
	 * @return
	 */
	private static int solve(int i, int j) {
		if (isEnd(i, j)) {
			return s1[i] == s2[j] ? 1 : 0;
		}

		if (cache[i][j] != -1) {
			return cache[i][j];
		}

		cache[i][j] = 0;

		// 한칸씩 전진하며 확인한다.
		// 1. s1[i] == s2[j] 인 경우 다음 character 를 확인한다.
		if (i < s1.length && j < s2.length && s1[i] == s2[j]) {
			cache[i][j] = Math.max(cache[i][j], 1 + solve(i+1, j+1));
		} else {
			// 2. 만약 s1[i] != s2[j] 라면,
			// i 와 j 를 각각 1씩 증가시킨 모든 경우를 확인해서 최대값을 구한다.
			if (i < s1.length) {
				cache[i][j] = Math.max(cache[i][j], solve(i+1, j));
			}

			if (j < s2.length) {
				cache[i][j] = Math.max(cache[i][j], solve(i, j+1));
			}
		}

		return cache[i][j];
	}

	private static boolean isEnd(int i, int j) {
		return (i == s1.length - 1) && (j == s2.length - 1);
	}
}
