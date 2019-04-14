package baekjoon.dp;

import java.util.Scanner;

/**
 * 더 효율적인 알고리즘 존재한다. O(nlogn)
 *
 *
 */
public class 가장큰증가부분수열 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int [] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		int max = 0;

		for (int i = 0; i < n; i++) {
			Integer [] dp = new Integer[n];
			max = Math.max(max, lis(dp, arr, i));
		}

		System.out.println(max);
	}

	static int lis(Integer[] dp, int[] arr, int i) {
		int n = arr.length;

		if (i == n) {
			return 0;
		}

		if (dp[i] != null) {
			return dp[i];
		}

		// arr[i] 를 최초 할당해주는 부분 중요하다.
		// 만약 현재 index 부터 증가하는 하위 수열이 없다면, 현재 값을 리턴해줘야 정확한 계산값이 나올 수 있다.
		int sum = arr[i];

		for (int j = i+1; j < n; j++) {
			// 증가하는 경우만
			if (arr[i] < arr[j]) {
				sum = Math.max(sum, arr[i] + lis(dp, arr, j));
			}
		}

		return dp[i] = sum;
	}
}
