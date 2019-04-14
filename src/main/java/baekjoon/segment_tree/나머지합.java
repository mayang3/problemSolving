package baekjoon.segment_tree;

import java.util.Scanner;

/**
 * TODO. time out
 */
public class 나머지합 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();

		int sum = 0;
		int [] arr = new int[n];

		for (int i = 0; i < n; i++) {
			sum += scanner.nextInt();
			arr[i] = sum;
		}

		int cnt = 0;

		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (i == 0) {
					if (arr[j] % m == 0) {
						cnt++;
					}

					continue;
				}

				if ((arr[j] - arr[i-1]) % m == 0) {
					cnt++;
				}
			}
		}

		System.out.println(cnt);

	}
}
