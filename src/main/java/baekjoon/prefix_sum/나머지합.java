package baekjoon.prefix_sum;

import java.util.Scanner;

public class 나머지합 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();

		int [] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		PrefixSum ps = new PrefixSum(arr);

		int cnt = 0;

		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (ps.getS(j, i) % m == 0) {
					cnt++;
				}
			}
		}

		System.out.println(cnt);
	}

	static class PrefixSum {
		long [] sum;

		PrefixSum(int [] arr) {
			this.sum = new long[arr.length];

			long s = 0;

			for (int i = 0; i < arr.length; i++) {
				s += arr[i];
				sum[i] = s;
			}
		}

		long getS(int j, int i) {
			if (j == i) {
				return sum[j];
			}

			return sum[j] - sum[i];
		}
	}
}
