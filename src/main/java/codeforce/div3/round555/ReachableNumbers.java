package codeforce.div3.round555;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 */
public class ReachableNumbers {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		reachableSet.add(n);

		while (true) {
			int y = fx(n);

			if (reachableSet.contains(y)) {
				break;
			}

			reachableSet.add(y);

			n = y;
		}

		System.out.println(reachableSet.size());
	}

	static Set<Integer> reachableSet = new HashSet<>();

	static int fx(int x) {

		x += 1;

		while (x / 10 > 0 && x % 10 == 0) {
			x /= 10;
		}

		return x;
	}
}
