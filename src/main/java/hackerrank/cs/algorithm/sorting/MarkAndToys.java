package hackerrank.cs.algorithm.sorting;

import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class MarkAndToys {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int v = scanner.nextInt();

		int [] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		Arrays.sort(arr);

		for (int i = 0; i < n; i++) {
			v -= arr[i];

			if (v < 0) {
				System.out.println(i);
				return;
			}
		}
	}
}
