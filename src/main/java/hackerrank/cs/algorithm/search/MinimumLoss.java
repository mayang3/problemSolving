package hackerrank.cs.algorithm.search;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * bear://x-callback-url/open-note?id=4CCF4E68-B5FD-450D-8678-3CC59FA66130-3049-00000250F7C977EB
 */
public class MinimumLoss {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		long [] arr = new long[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextLong();
		}

		long ans = Long.MAX_VALUE;

		TreeSet<Long> ts = new TreeSet<>();

		for (int i = n-1; i >= 0 ; i--) {
			Long lower = ts.lower(arr[i]);

			ts.add(arr[i]);

			if (lower == null) {
				continue;
			}

			ans = Math.min(ans, arr[i] - lower);
		}

		System.out.println(ans);

	}
}
