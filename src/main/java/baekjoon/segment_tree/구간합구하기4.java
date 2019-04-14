package baekjoon.segment_tree;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/11659
 */
public class 구간합구하기4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();

		int [] arr = new int[n];

		int sum = 0;

		for (int i = 0; i < n; i++) {
			sum += scanner.nextInt();
			arr[i] = sum;
		}

		for (int i = 0; i < m; i++) {
			int from = scanner.nextInt() - 1;
			int to = scanner.nextInt() - 1;

			System.out.println(arr[to] - (from - 1 < 0 ? 0 : arr[from-1]));
		}
	}
}
