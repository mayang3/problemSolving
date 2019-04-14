package baekjoon.implement;

import java.util.Scanner;

public class 지능형기차 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int sum = 0;
		int max = 0;

		while (true) {
			int getOff = scanner.nextInt();
			int ride = scanner.nextInt();

			sum += ride - getOff;
			max = Math.max(max, sum);

			if (ride == 0) {
				break;
			}
		}

		System.out.println(max);
	}
}
