package baekjoon.implement;

import java.util.LinkedList;
import java.util.Scanner;

public class 수열의합 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		long N = scanner.nextLong();
		int L = scanner.nextInt();

		LinkedList<Long> ret = null;

		for (int k=L ; k<=100 ; k++) {
			ret = solve(N, k);
			if (ret != null && !ret.isEmpty()) {
				break;
			}
		}

		if (ret == null || ret.isEmpty()) {
			System.out.println(-1);
		} else {
			print(ret);
		}
	}

	private static LinkedList<Long> solve(long n, int k) {
		long [] arr = new long[k];

		int mid = k/2;
		long quotient = n /k;

		arr[mid] = quotient;

		for (int i = mid - 1; i >= 0; i--) {
			arr[i] = arr[i + 1] - 1;
		}

		for (int i = mid + 1; i < k; i++) {
			arr[i] = arr[i - 1] + 1;
		}

		LinkedList<Long> ll = new LinkedList<>();

		for (long v : arr) {
			ll.add(v);
		}

		for (int i=0 ; i<=mid ; i++) {
			long sum = 0;
			boolean minus = false;

			for (long v : ll) {
				if (v < 0) {
					minus = true;
					break;
				}

				sum += v;
			}

			if (minus) {
				rotate(ll);
				continue;
			}

			if (sum == n && ll.size() <= 100) {
				return ll;
			} else if (sum > n || ll.size() > 100 ) {
				break;
			}

			rotate(ll);

		}

		return null;
	}

	private static void rotate(LinkedList<Long> ll) {
		// left rotate
		ll.poll();
		ll.add(ll.peekLast()+1);
	}

	private static void print(LinkedList<Long> ll) {
		for (long v : ll) {
			System.out.print(v + " ");
		}
	}
}
