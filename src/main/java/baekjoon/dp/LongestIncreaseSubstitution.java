package baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 간단한 문제라고 생각했는데, 계속 오답이 떴음..
 *
 * 이유는 처음 시작점 start 에 대해서 모든 경우 해봐야 하는데,
 * "무조건 맨 처음 시작점이 제일 작은점이라고 가정되고 있었음"
 *
 * 다음 반례로 오류를 찾아냄
 *
 * 6
 * 10 1 10 30 20 20
 */
public class LongestIncreaseSubstitution {
	static int [] cache = new int[1010];
	static int [] arr;

	static int solve(int start) {
		if (cache[start] != -1) {
			return cache[start];
		}

		cache[start] = 1;

		for (int i=start+1 ; i<arr.length ; i++) {
			if (arr[start] < arr[i]) {
				cache[start] = Math.max(cache[start], 1 + solve(i));
			}
		}

		return cache[start];
	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		arr = new int[n];

		Arrays.fill(cache, -1);

		for (int i=0 ; i<n ; i++) {
			arr[i] = scanner.nextInt();
		}

		int max = 0;

		for (int i=0 ; i<arr.length ; i++) {
			max = Math.max(max, solve(i));
		}

		System.out.println(max);
	}
}
