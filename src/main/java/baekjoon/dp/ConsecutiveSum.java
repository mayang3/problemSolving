package baekjoon.dp;

import java.util.Scanner;

public class ConsecutiveSum {


	static int solve(int [] arr) {
		int max = -1001;
		int next = -1001;

		for (int i=0 ; i<arr.length ; i++) {
			next = Math.max(arr[i], next+arr[i]);
			max = Math.max(next, max);
		}

		return max;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int [] arr = new int[n];

		for (int i=0 ; i<n ; i++) {
			arr[i] = scanner.nextInt();
		}

		System.out.println(solve(arr));

	}
}
