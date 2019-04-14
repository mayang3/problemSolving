package baekjoon.math;

import java.util.Scanner;

public class 플러그 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		long sum = 0;

		for (int i = 0; i < n; i++) {
			sum += scanner.nextInt();
		}

		System.out.println(sum - (n - 1));
	}
}
