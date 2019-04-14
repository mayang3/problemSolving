package hackerrank.cs.dataStructure.array;

import java.util.Scanner;

/**
 * bear://x-callback-url/open-note?id=F6ADEABA-242D-43AC-B302-92D3562D8774-22609-00001C00EF45ED34
 */
public class NewYearChaos {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int t = scanner.nextInt();

		while (t-- > 0) {
			int n = scanner.nextInt();

			int [] arr = new int[n];

			for (int i = 0; i < n; i++) {
				arr[i] = scanner.nextInt();
			}

			solve(arr);

		}
	}

	static void solve(int [] arr) {
		int cnt = 0;

		for (int i = arr.length - 1; i >= 0 ; i--) {
			// chaotic 인 경우
			if (arr[i] - (i + 1) > 2) {
				System.out.println("Too chaotic");
				return;
			}

			for (int j = Math.max(0, arr[i] - 3) ; j < i; j++) {
				if (arr[j] > arr[i]) {
					cnt++;
				}
			}
		}

		System.out.println(cnt);
	}
}
