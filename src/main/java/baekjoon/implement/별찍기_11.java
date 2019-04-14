package baekjoon.implement;

import java.util.Scanner;

/**
 * N = 3*2^k -> 3번의 재귀호출을 하는 함수를 k번 만큼 재귀호출 하라는 뜻.
 */
public class 별찍기_11 {

	static void solve(int k) {

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int k = N / 3 / 2;
		solve(k);
	}
}
