package baekjoon.implement;

import java.util.Scanner;

public class 분수찾기 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int X = scanner.nextInt();

		int sum = 0;

		for (int i=1 ; i<10000000 ; i++) {
			if (sum > 10000000) {
				break;
			}

			sum += i;

			if (X <= sum) {

				// 공식..
				// i 가짝수일때와 홀수일때 순서가 틀리므로 swap 해준다.
				int numerator = (sum - (sum - i) - (sum - X));
				int denominator = (sum - X + 1);

				if (i % 2 != 0) {
					int temp = numerator;
					numerator = denominator;
					denominator = temp;
				}

				System.out.println(numerator + "/" + denominator);

				return;
			}
		}

	}

}
