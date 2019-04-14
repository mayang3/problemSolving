package baekjoon.math;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/2163
 *
 * 자르기를 한번도 하지 않았을 때 이미 초콜릿은 1조각이다.
 *
 * 자르기를 한번 하게 되면 초콜렛은 2조각.
 * 자르기를 두번 하게 되면 초콜렛은 3조각.
 * 자르기를 세번 하게 되면 초콜렛은 4조각.
 * 자르기를 네번 하게 되면 초콜렛은 5조각이 된다.
 *
 * 즉 n*m 의 조각을 만드려면 n*m-1 번의 자르기가 필요하다.
 *
 */
public class 초콜릿자르기 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();

		System.out.println(n*m-1);
	}
}
