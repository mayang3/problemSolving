package hackerrank.cs.algorithm.sorting;

import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class InsertionSort {
	static void insertionSort1(int n, int[] arr) {
		int start = arr.length - 1;
		int org = arr[start];

		while (start > 0 && org < arr[start-1]) {
			arr[start] = arr[start-1];
			start--;

			for (int a : arr) {
				System.out.print(a + " ");
			}
			System.out.println();
		}

		arr[start] = org;

		for (int a : arr) {
			System.out.print(a + " ");
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for(int arr_i = 0; arr_i < n; arr_i++){
			arr[arr_i] = in.nextInt();
		}
		insertionSort1(n, arr);
		in.close();
	}
}
