package baekjoon.bruteforce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Accepted
 */
public class Hansu {

	static int solve(int n) {
		int sum = 0;

		for (int i=1 ; i<=n ; i++) {
			if (isArithmeticSequence(i)) {
				sum++;
			}
		}

		return sum;
	}

	private static boolean isArithmeticSequence(int n) {

		List<Integer> remainderList = new ArrayList<>();

		while (n > 0) {
			remainderList.add(n % 10);
			n /= 10;
		}

		if (remainderList.size() <= 2) {
			return true;
		}

		int diff = remainderList.get(0) - remainderList.get(1);

		for (int i=1 ; i<remainderList.size()-1 ; i++) {
			if (diff != remainderList.get(i) - remainderList.get(i+1)) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();

		System.out.println(solve(N));
	}
}
