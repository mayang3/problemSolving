package hackerrank.cs.dataStructure.stack;

import java.util.Scanner;

/**
 * Skyline Real Estate Developers is planing to demolish a number of old,
 *
 * unoccupied buildings and construct a shopping mall in their place.
 *
 * Your task is to find the largest solid area in which the mall can be constructed.
 *
 * There are a number of buildings in certain two-dimensional landscape.
 *
 * Each building has a height, given by h[i] where i <- [1, n].
 *
 * If you join k adjacent buildings, they will form a solid rectangle of area.
 *
 * k * min(h[i], h[i+1], ...., h[i+k+1]).
 *
 * For example, the heights array h = [3,2,3].
 *
 * A rectangle of height h = 2 and length k = 3
 *
 * can be constructed within the boundaries.
 *
 * The area formed is h*k = 2*3 = 6
 *
 * [input]
 *
 * The first line contains n, the number of buildings.
 *
 * The second line contains n space-separated integers,
 *
 * each representing the height of a building.
 *
 * [constraint]
 *
 * 1 <= n <= 10^5
 * 1 <= h[i] <= 10^6
 *
 * [output]
 *
 * Print a long integer representing the maximum area of rectangle formed.
 *
 */
public class LargestRectangle {
	static int n;
	static long [] arr;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		n = scanner.nextInt();

		arr = new long[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextLong();
		}

		long ret = dnc(0, n-1);

		System.out.println(ret);
	}

	static long dnc(int i, int j) {
		if (i == j) {
			return arr[i];
		}

		int m = (i+j) / 2;

		long leftArea = dnc(i, m);
		long rightArea = dnc(m+1, j);

		long acrossArea = 0;

		int k = m;
		int l = m;

		long minHeight = arr[k]; // 중요 - min height 를 계속 유지해야 한다

		// 항상 높이가 더 큰쪽으로 이동한다.
		while (k >= i || l <= j) {
			// 높이 * 너비
			long width = l-k+1;

			acrossArea = Math.max(acrossArea, minHeight * width);

			if (k == i && l == j) {
				break;
			}

			if (k == i && l < j) {
				l++;
				minHeight = Math.min(minHeight, arr[l]);
			} else if (k > i && l == j) {
				k--;
				minHeight = Math.min(minHeight, arr[k]);
			} else if (arr[k-1] <= arr[l+1]) {
				l++;
				minHeight = Math.min(minHeight, arr[l]);
			} else {
				k--;
				minHeight = Math.min(minHeight, arr[k]);
			}
		}

		return Math.max(acrossArea, Math.max(leftArea, rightArea));
	}
}
