package baekjoon.implement;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/10871
 */
public class X보다_작은_수 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int X = scanner.nextInt();

		int [] arr = new int[N];

		for (int i=0 ; i<N ; i++) {
			arr[i] = scanner.nextInt();
		}

		for (int v : arr) {
			if (v < X) {
				System.out.print(v + " ");
			}
		}
	}
}
