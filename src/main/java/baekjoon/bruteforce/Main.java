package baekjoon.bruteforce;

import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class Main {

	static int I;
	static int N;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		I = scanner.nextInt();
		N = String.valueOf(I).length();
		if (N < 1 || N > 1000000) {
			return;
		}
		scanner.close();

		recursive(1, 0);
		if (min == Integer.MAX_VALUE) {
			System.out.print(0);
		} else {
			System.out.print(min);
		}
	}

	private static void recursive(int turn, int val) {
		if (turn == N + 1) {
			int sum = val;
			for (int i = 1; i < turn; i++) {
				int digit = val / (int) Math.pow((double) 10, (double) N - i) % 10;
				sum += digit;
			}
			if (sum == I) {
				min = Math.min(min, val);
			}
		} else {
			int i;
			if (turn == 1) {
				i = 1;
			} else {
				i = 0;
			}
			for (int j = i; j <= 9; j++) {
				int digit = j * (int) Math.pow((double) 10, (double) N - turn);
				recursive(turn + 1, digit + val);
			}
		}
	}
}
