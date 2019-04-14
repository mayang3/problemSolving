package hackerrank.cs.algorithm.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/minimum-absolute-difference-in-an-array/problem
 *
 * absoulte difference 를 찾는 문제.
 *
 * 정렬해서 인접해 있는 녀석들만 차이를 계산하면 된다.
 *
 * 인접범위를 넘어가는 pair 는 최소값을 이미 벗어나게 되므로 유효하지 않다.
 *
 */
public class MinumumAbsoluteDifferenceInAnArray {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int [] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		Arrays.sort(arr);

		long min = Long.MAX_VALUE;

		for (int i = 1; i < n; i++) {
			min = Math.min(min, Math.abs(arr[i-1] - arr[i]));
		}

		System.out.println(min);
	}
}
