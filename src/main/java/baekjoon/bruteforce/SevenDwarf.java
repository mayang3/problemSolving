package baekjoon.bruteforce;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * accepted
 */
public class SevenDwarf {

	static int [] heights = new int[9];
	static boolean find = false;

	static void solve(int sum, int i, LinkedList<Integer> list) {
		if (sum == 0) {
			for (int v : list) {
				System.out.println(v);
			}

			find = true;
			return;
		} else if (sum < 0) {
			return;
		} else if (list.size() >= 7) {
			return;
		}

		for (int j=i ; j<9 ; j++) {
			if (find) break;
			list.addLast(heights[j]);
			solve(sum - heights[j], j + 1, list);
			list.removeLast();
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		for (int i=0 ; i<9 ; i++ ) {
			heights[i] = scanner.nextInt();
		}

		Arrays.sort(heights);

		LinkedList<Integer> list = new LinkedList<>();

		solve(100, 0, list);
	}
}
