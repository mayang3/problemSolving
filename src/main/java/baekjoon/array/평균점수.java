package baekjoon.array;

import java.util.Scanner;

public class 평균점수 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int [] input = new int[5];

		for (int i=0; i<5 ; i++) {
			input[i] = scanner.nextInt();
		}

		System.out.println(solve(input));
	}

	static int solve(int [] input) {
		int sum = 0;

		for (int v : input) {
			if (v < 40) {
				sum += 40;
			} else {
				sum += v;
			}
		}

		return sum / input.length;
	}
}
