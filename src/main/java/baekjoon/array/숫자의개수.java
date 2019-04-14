package baekjoon.array;

import java.util.Scanner;

public class 숫자의개수 {
	static int [] arr = new int[10];

	static void solve(int A, int B, int C) {
		int ret = A * B * C;

		while (ret > 0) {
			arr[ret % 10]++;
			ret /= 10;
		}

		for (int v : arr) {
			System.out.println(v);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int A = scanner.nextInt();
		int B = scanner.nextInt();
		int C = scanner.nextInt();

		solve(A, B, C);
	}
}
