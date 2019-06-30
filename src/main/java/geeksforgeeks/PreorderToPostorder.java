package geeksforgeeks;

import java.util.Scanner;

/**
 * @author neo82
 */
public class PreorderToPostorder {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int t = scanner.nextInt();

		while (t-- > 0) {
			int n = scanner.nextInt();

			int [] arr = new int[n];

			for (int i = 0; i < n; i++) {
				arr[i] = scanner.nextInt();
			}

			solve(0, arr.length-1, arr);

			System.out.println();
		}
	}

	static void solve(int start, int end, int[] arr) {

		if (start == end) {
			System.out.print(arr[start] + " ");
			return;
		}

		int root = arr[start];

		int m = getM(root, start+1, arr);

		if (start < m) {
			solve(start + 1, m, arr);
		}

		if (m < end) {
			solve(m + 1, end, arr);
		}

		System.out.print(root + " ");
	}

	private static int getM(int root, int start, int[] arr) {
		for (int i = start; i < arr.length; i++) {
			if (root < arr[i]) {
				return i-1;
			}
		}

		return arr.length - 1;
	}
}
