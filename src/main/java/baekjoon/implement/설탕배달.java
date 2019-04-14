package baekjoon.implement;

import java.util.Scanner;

public class 설탕배달 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		if (n == 3) {
			System.out.println(1);
			return;
		}

		int min = Integer.MAX_VALUE;

		for (int i=0; i<= n ; i++) {
			for (int j=0; j<= n; j++) {
				int cnt5 = i * 5;
				int cnt3 = j * 3;

				if (cnt5 + cnt3 == n) {
					min = Math.min(min, i+j);
				}
			}
		}

		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}

}
