package hackerrank.cs.algorithm.sorting;

import java.util.Scanner;

/**
 */
public class InsertionSort2 {

	static void insertionSort2(int n, int[] arr) {
		for (int i=1 ; i<arr.length ; i++) {
			int start = i;

			while (start > 0 && arr[start] < arr[start-1]) {
				int temp = arr[start];
				arr[start] = arr[start-1];
				arr[start-1] = temp;

				start--;

			}

			for (int v : arr) {
				System.out.print(v + " ");
			}

			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for(int arr_i = 0; arr_i < n; arr_i++){
			arr[arr_i] = in.nextInt();
		}
		insertionSort2(n, arr);
		in.close();
	}
}
