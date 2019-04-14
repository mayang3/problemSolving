package codeforce.div2.round149;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * http://codeforces.com/problemset/problem/242/B
 *
 * 아이디어는 간단하다. 해당 범위의 l,r 이 모든 l,r 을 커버하려면,
 *
 * l,r 은 반드시 가장 큰 length 를 가져야 한다는 것이다.
 *
 * 때문에, r-l 로 정렬을 한다면, 맨 앞의 pair 가 무조건 가장 큰 범위를 커버하는 pair 가 된다.
 *
 * 단, 이 pair 가 모든 l,r 를 커버하지 못하는 경우일수도 있으므로,
 *
 * 가장 큰 pair 로 모든 l,r 를 검사하면 된다.
 *
 * Time Complexity : O(n log n)
 *
 *
 */
public class BigSegment {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		Pair [] arr = new Pair[n];

		for (int i = 0; i < n; i++) {
			int l = scanner.nextInt();
			int r = scanner.nextInt();

			arr[i] = new Pair(l, r, i);
		}


		Arrays.sort(arr, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return o2.getWidth() - o1.getWidth();
			}
		});


		Pair biggest = arr[0];

		for (int i = 1; i < n; i++) {
			Pair next = arr[i];

			if (biggest.contains(next) == false) {
				System.out.println(-1);
				return;
			}
		}

		System.out.println(biggest.index + 1);
	}

	static class Pair {
		int l;
		int r;
		int index;

		Pair(int l, int r, int index) {
			this.l = l;
			this.r = r;
			this.index = index;
		}

		int getWidth() {
			return r-l;
		}

		boolean contains(Pair p) {
			if (this.l > p.l || p.r > this.r) {
				return false;
			}

			return true;
		}
	}
}
