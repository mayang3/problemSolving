package baekjoon.array;

import java.util.Scanner;

public class OX퀴즈 {

	static int solve(String input) {
		int len = input.length();

		int sum = 0;
		int seq = 0;

		for (int i=0 ; i<len ; i++) {
			char ch = input.charAt(i);

			if ('O' == ch) {
				seq++;
				sum += seq;
			} else {
				seq = 0;
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();

		while (n-- > 0) {
			System.out.println(solve(scanner.next()));
		}
	}
}
