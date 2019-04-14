package hackerrank.cs.algorithm.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 */
public class MaxMin {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int k = scanner.nextInt();

		int [] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		Arrays.sort(arr);

		int min = Integer.MAX_VALUE;

		for (int i = 0, j=i+k-1; j < n; i++, j=i+k-1) {
			min = Math.min(min, arr[j]-arr[i]);
		}

		System.out.println(min);

	}
}
