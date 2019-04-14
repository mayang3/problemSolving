package baekjoon.dnc;

import java.util.Arrays;
import java.util.Scanner;

/**
 */
public class KthNumber {
	static long solve(long[] input, int i) {

		Arrays.sort(input);

		return input[i];
	}

	static void mergeSort(int left, int right, long [] input) {
		if (left < right) {

			int mid = (left + right) / 2;

			mergeSort(left, mid, input);
			mergeSort(mid+1, right, input);

			merge(left, right, mid, input);
		}
	}

	/**
	 * L[left ... mid]
	 * R[mid+1 ... right]
	 *
	 * @param left
	 * @param right
	 * @param mid
	 * @param input
	 */
	private static void merge(int left, int right, int mid, long[] input) {
		int n1 = (mid - left + 1);
		int n2 = right - mid;

		// 1. 왼쪽 배열 생성
		long [] L = new long[n1];

		for (int i=0 ; i<n1 ; i++) {
			L[i] = input[left+i];
		}

		// 2. 오른쪽 배열 생성
		long [] R = new long[n2];

		for (int i=0 ; i<n2 ; i++) {
			R[i] = input[mid+1+i];
		}

		int i=0;
		int j=0;
		int k=left;

		// 3. 두 스택을 비교하며 원본 배열에 입력
		while (i < n1 && j < n2) {
			if (L[i] < R[j]) {
				input[k] = L[i];
				i++;
			} else {
				input[k] = R[j];
				j++;
			}
			k++;
		}

		// 4. 남은 배열들을 flush
		while (i < n1) {
			input[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			input[k] = R[j];
			j++;
			k++;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int K = scanner.nextInt();

		long [] arr = new long[N];

		for (int i=0 ; i<N ; i++) {
			arr[i] = scanner.nextLong();
		}

		mergeSort(0, arr.length-1, arr);
		System.out.println(arr[K-1]);
	}
}
