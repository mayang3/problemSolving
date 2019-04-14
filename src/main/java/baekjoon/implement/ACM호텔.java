package baekjoon.implement;

import java.util.Scanner;

public class ACM호텔 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();

		while (T-- > 0) {
			int H = scanner.nextInt();
			int W = scanner.nextInt();
			int N = scanner.nextInt();

			String prefix = String.valueOf(N%H == 0 ? H : N%H);
			String postfix = String.valueOf((int)Math.ceil((double)N / (double)H));

			// H 의 길이와 상관없이 1층 1호는 항상 101호 이다.
			while (postfix.length() < 2) {
				postfix = "0" + postfix;
			}

			System.out.println(prefix + postfix);
		}
	}
}
