package baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/2156
 */
public class 포도주시식 {
	static int N;
	static int [] wines;
	static int [][] cache = new int[10001][10001];

	static int solve(int i, int before) {
		if (i == N-1) {
			return cache[i][before] = wines[i];
		}

		if (cache[i][before] != -1) {
			return cache[i][before];
		}

		cache[i][before] = 0;

		for (int j=i+1 ; j<N ; j++) {
			if (i == 0 || j-2 != before) {
				cache[i][before] = Math.max(cache[i][before], wines[i] + solve(j, i));
			} else if (i > 0 && j-2 == before) {
				cache[i][before] = Math.max(cache[i][before], wines[i]);
			}
		}

		return cache[i][before];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		N = scanner.nextInt();
		wines = new int[N];


		for (int [] c : cache) {
			Arrays.fill(c, -1);
		}

		for (int i=0 ; i<N ; i++) {
			wines[i] = scanner.nextInt();
		}

		int max = 0;

		for (int i=0 ; i<N ; i++) {
			max = Math.max(max, solve(i, i));
		}

		System.out.println(max);
	}
}
