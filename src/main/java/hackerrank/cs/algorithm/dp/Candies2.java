package hackerrank.cs.algorithm.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/candies/problem
 *
 * @author baejunbeom
 */
public class Candies2 {

	private static long[] cache;

	static long candies(int n, int[] arr) {
		cache = new long[n];

		Arrays.fill(cache, -1);

		long sum = 0;

		for (int cur=0 ; cur<arr.length ; cur++) {
			sum += sumCandies(cur, arr, n);
		}

		return sum;
	}

	private static long sumCandies(int cur, int[] arr, int n) {

		if (cache[cur] != -1) {
			return cache[cur];
		}

		if (cur == n || cur < 0) {
			return 0;
		}

		int leftChildren = arr[Math.max(cur-1, 0)];
		int rightChildren = arr[Math.min(cur+1, n-1)];
		int currentChildren = arr[cur];

		long myCandySum = 0;

		// 왼쪽에 있는 아이들의 수가 현재 아이들수보다 적고, 오른쪽에 앉아있는 아이들의 수도 현재 아이들수보다 적은 경우
		if (leftChildren < currentChildren && currentChildren > rightChildren) {
			myCandySum = Math.max(sumCandies(cur-1, arr, n), sumCandies(cur+1, arr, n)) + 1;
		} else if (leftChildren >= currentChildren && currentChildren <= rightChildren) {
			// 왼쪽에 있는 아이들의 수가 현재 아이들의수보다 많고, 오른쪽에 앉아있는 아이들의 수도 현재 아이들 수보다 많은 경우
			myCandySum = 1;
		} else if (leftChildren < currentChildren) {
			// 왼쪽에 있는 아이들의 수만 현재 아이들의 수보다 적은 경우
			myCandySum = sumCandies(cur-1, arr, n) + 1;
		} else if (rightChildren < currentChildren) {
			// 오른쪽에 있는 아이들의 수만 현재 아이들의 수보다 적은 경우
			myCandySum = sumCandies(cur+1, arr, n) + 1;
		}

		return cache[cur] = myCandySum;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		int[] arr = new int[n];
		for(int arr_i = 0; arr_i < n; arr_i++){
			arr[arr_i] = in.nextInt();
		}

		long result = candies(n, arr);

		// expected output
		System.out.println(result);
		in.close();
	}
}
