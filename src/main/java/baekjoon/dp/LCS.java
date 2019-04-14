package baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Longest Common Subsequence
 *
 * Accepted!!
 */
public class LCS {

	static char [] A;
	static char [] B;
	static int [][] cache;

	/**
	 * 기본적인 아이디어는 A 의 현재위치 i 와 B 의 현재 위치 j 의 비교 후 나머지 부분 수열들에 대한 비교를 한다는 것이다.
	 *
	 * 몇번 실패했는데, 그 이유는 다음 몇가지 부분에 있었다.
	 *
	 * 1. 기저 사례에서 종료조건이 정확히 동작하지 않았음. -> i 와 j 가 마지막 인덱스일때 가장 마지막 값도 비교가 되어야 한다.
	 * 2. DP 를 적용하지 않았음 -> DP 적용
	 * 3. 값이 넘어가는 경우에 대한 처리가 필요함
	 *
	 * e.g) AA A 가 입력되었을시에 1이 출력되어야 하는데 2가 출력되었었음
	 *
	 * @param i
	 * @param j
	 * @return
	 */
	static int solve(int i, int j) {
		// base case : A, B 모두 끝까지 도착했다면 종료한다.
		if (i==A.length-1 && j==B.length-1) {
			return A[i] == B[j] ? 1 : 0;
		}

		// cache 되어있는 경우.
		if (cache[i][j] != -1) {
			return cache[i][j];
		}

		cache[i][j] = 0;

		// 1. A[i] == B[j] 인 경우 ( 부분 문자열이 일치하는 경우 ) 둘다 다음 character 를 검사한다.
		if (j < B.length && i < A.length && A[i] == B[j]) {
			cache[i][j] = Math.max(cache[i][j], 1 + solve(i+1, j+1));
		} else {
			// 2. A[i] != B[j] 인 경우.

			// 2-1. j 가 아직 끝까지 도달하지 않았다면 j 를 증가시켜 검사.
			if (j < B.length-1) {
				cache[i][j] = Math.max(cache[i][j], solve(i, j + 1));
			}

			// 2-2. i 가 아직 끝까지 도달하지 않았다면 i 를 증가시켜서 검사.
			if (i < A.length-1) {
				cache[i][j] = Math.max(cache[i][j], solve(i + 1, j));
			}
		}

		return cache[i][j];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		A = scanner.next().toCharArray();
		B = scanner.next().toCharArray();

		cache = new int[A.length+1][B.length+1];

		for (int [] c : cache) {
			Arrays.fill(c, -1);
		}

		System.out.println(solve(0,0));

//		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA".length());
	}
}
