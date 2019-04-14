package hackerrank.cs.algorithm.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * We define subSequence as any subset of an array.
 *
 * We define subArray as a contiguous subSequence in an array.
 *
 * Given an array, find the maximum possible sum among:
 *
 * 1. all nonempty subArrays.
 * 2. all nonempty subSequences.
 *
 * Print the two values as space-separated integers on one line.
 *
 * [NOTE]
 *
 * That empty subArrays/subSequences should not be considered.
 *
 * For example, given an array arr=[-1,2,3,-4,5,10], the maximum subArray sum is comprised of element indices [1-5] and the sum is 2 + 3 + -4 + 5 + 10 = 16.
 *
 * The maximum subSequence sum is comprised of element indices [1,2,4,5] and the sum is 2 + 3 + 5 + 10 = 20
 *
 * [Function Description]
 *
 * Complete the maxSubArray function in the editor below.
 *
 * it should return an array of two integers: the maximum subarray sum and the maximum subsequence sum of arr.
 *
 * maxSubArray has the following parameter(s):
 *
 * arr : an array of integers
 *
 * [Input Format]
 *
 * The first line of input contains a single integer t, the number of test cases.
 *
 * The first line of each test case contains a single integer n.
 *
 * The second line contains n space-separated integers arr[i] where 0 <= i < n.
 *
 * [Output Format]
 *
 * Print two space-separated integers denoting the maximum sums of nonempty subArrays and nonEmpty subSequences, respectively.
 *
 */
public class TheMaximumSubArray {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int t = scanner.nextInt();

		while (t-- > 0) {
			int n = scanner.nextInt();

			int [] arr = new int[n];

			for (int i = 0; i < n; i++) {
				arr[i] = scanner.nextInt();
			}

			System.out.println(Arrays.toString(maxSubArray(arr)));

		}
	}

	static int[] maxSubArray(int[] arr) {
		int subSeq = 0;
		int subArr = 0;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] >= 0) {
				subArr = Math.max(arr[i], arr[i] + subArr);
			} else {
				if (subArr + arr[i] >= 0) {
					subArr += arr[i];
				} else {
					subArr = 0;
				}
			}

			subSeq += Math.max(arr[i], 0);
			max = Math.max(max, arr[i]);
		}

		if (subSeq == 0 && max < 0) {
			subSeq = max;
		}

		return new int[] {subArr, subSeq};
	}

}
