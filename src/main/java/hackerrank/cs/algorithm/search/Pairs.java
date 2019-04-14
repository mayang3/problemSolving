package hackerrank.cs.algorithm.search;

import java.util.Arrays;
import java.util.Scanner;

/**
 * You will given an array of integers and a target value.
 *
 * Determine the number of pairs of array elements that have a difference equal to a target value.
 *
 * (difference equal)
 *
 * For example, given an array of [1,2,3,4] and a target value of 1, we have three values meeting the condition:
 *
 * 2 - 1 = 1, 3 - 2 = 1, and 4 - 3 = 1.
 *
 * [Function Description]
 * Complete the pairs function below. It must return an integer representing the number of element pairs having the required difference.
 *
 * pairs has the following parameter(s):
 *
 * k: an integer, the target differencev
 * arr: an array of integers
 *
 * [Input Format]
 * The first line contains two space-separated integers n and k, the size of arr and the target value.
 *
 * The second line contains n space-separated integers of the array arr.
 *
 * [Constraints]
 * 2 <= n <= 10^5
 * 0 < k < 10^9
 * 0 < arr[i] < 2^31 - 1
 * each integer arr[i] will be unique
 *
 * [Output Format]
 * An integer representing the number of pairs of integers whose difference is k.
 *
 */
public class Pairs {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int k = scanner.nextInt();

		int [] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		Arrays.sort(arr);

		int cnt = 0;

		for (int i = 1; i < n; i++) {
			// 1. 바로 앞의 숫자와의 difference value 가 target value 보다 크다면 그 이상은 볼거 없다.
			if (arr[i] - arr[i-1] > k) {
				continue;
			}

			// 2. 만약 바로 앞의 숫자가 target value 보다 적거나 같다면, 계속해서 그 앞의 숫자를 봐야 한다.
			int diff = i-1;

			while (diff >= 0 && arr[i] - arr[diff] <= k) {
				if (arr[i] - arr[diff] == k) {
					cnt++;
				}

				diff--;
			}
		}

		System.out.println(cnt);

	}
}
