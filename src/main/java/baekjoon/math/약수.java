package baekjoon.math;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1037
 *
 * 1. 어떤 수를 약수로 나눈 수도 약수이다.
 *
 * 2. 가장 작은 약수로 어떤수를 나눈다면 나눠진수는 가장 큰 약수가 된다.
 *
 * 3. 즉 가장 작은 약수 * 가장 큰 약수 (1과 자기자신을 제외한) 는 어떤수 N 이 된다.
 */
public class 약수 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int C = scanner.nextInt();

		int [] arr = new int[C];

		for (int i = 0; i < C; i++) {
			arr[i] = scanner.nextInt();
		}

		Arrays.sort(arr);

		System.out.println(arr[0] * arr[arr.length-1]);
	}
}
