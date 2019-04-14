package baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

public class Coin1 {

	static int n;
	static int k;
	static int [] coins;
	static int [][] cache = new int[101][10001];

	/**
	 * 최초 솔루션.. 이렇게 하면 답이 안나온다..
	 *
	 * (
	 * DP 에서 틀린게 아니고 cache 빼고 완전 탐색해도 답이 안나옴.. 완전 탐색 방법 자체가 틀린듯하다..
	 * 왜 그럴까???
	 * )
	 *
	 * 그런데.. 반례를 못찾겠음..
	 *
	 * https://www.acmicpc.net/board/view/9249
	 *
	 * @param i
	 * @param k
	 * @return
	 */
	static int solve(int i, int k) {
		if (k==0) {
			return 1;
		} else if (k<0) {
			return 0;
		}

		if (cache[i][k] != -1) {
			return cache[i][k];
		}

		int count = 0;

		for (int j=0 ; j<n ; j++) {
			if (coins[j] >= coins[i]) {
				count += solve(j, k - coins[j]);
			}
		}

		return cache[i][k] = count;
	}

	static int [] cache2 = new int[10001];

	/**
	 * Accpet!!
	 *
	 * 점화식 세우기가 매우 어렵다.. 어떻게 하면 점화식을 잘 세울까..?
	 *
	 * (점화식 설명 링크)
	 * https://www.acmicpc.net/board/view/10754
	 *
	 * An = An + An-2
	 *
	 * time complexity O(nk)
	 *
	 * @return
	 */
	static int solve2() {
		cache2[0] = 1;

		for (int i=0 ; i<n ; i++) {
			for (int j = coins[i]; j<=k ; j++) {
				cache2[j] += cache2[j- coins[i]];
			}
		}

		return cache2[k];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		n = scanner.nextInt();
		k = scanner.nextInt();

		coins = new int[n];

		for (int [] c : cache) {
			Arrays.fill(c, -1);
		}

		for (int i=0 ; i<n ; i++) {
			coins[i] = scanner.nextInt();
		}

		System.out.println(solve(0,k));
		System.out.println(solve2());
	}
}
