package baekjoon.search;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
	static int [] arr;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		Arrays.sort(arr);

		int m = scanner.nextInt();

		for (int i = 0; i < m; i++) {
			System.out.println(search(0, arr.length-1, scanner.nextInt()));
		}

	}

	static int search(int l, int r, int val) {
		if (l > r) {
			return 0;
		}

		int m = (l + r) / 2;

		if (arr[m] == val) {
			return 1;
		} else if (arr[m] < val) {
			return search(m+1, r, val);
		} else {
			// 여기서 m-1 을 하지 않으면 런타임 오류가 난다.
			// 반례) l, r 이 0,0 일 경우 무한히 돌수 있기 때문이다.
			return search(l, m-1, val);
		}

	}
}
