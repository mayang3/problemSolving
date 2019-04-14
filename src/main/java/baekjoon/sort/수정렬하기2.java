package baekjoon.sort;

import java.util.Scanner;

/**
 * N 의 상한이 1000000 이므로, O(N^2) 알고리즘은 무조건 안된다.
 *
 * O(NlogN) 은 1000000 * log1000000 = 1000000 * 약 20 = 2000 만 정도이므로 충분히 가능하다.
 */
@SuppressWarnings("ALL")
public class 수정렬하기2 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		int [] arr = new int[N];

		for (int i=0 ; i<N ; i++) {
			arr[i] = scanner.nextInt();
		}

		mergeSort(0, arr.length-1, arr);


		for (int v : arr) {
			System.out.println(v);
		}
	}

	static void mergeSort(int l, int r, int [] arr) {
		if (l < r) {
			int m = (l+r) / 2;

			mergeSort(l, m, arr);
			mergeSort(m+1, r, arr);

			merge(l, m, r, arr);
		}
	}

	static void merge(int l, int m, int r, int[] arr) {
		int n1 = m-l+1;
		int n2 = r-m;

		int [] L = new int[n1];
		int [] R = new int[n2];

		for (int i=0 ; i<n1 ; i++) {
			L[i] = arr[i+l];
		}

		for (int i=0 ; i<n2 ; i++) {
			R[i] = arr[m+1+i];
		}


		int i=0;
		int j=0;

		int k=l;

		while (i<n1 && j<n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				k++;
				i++;
			} else {
				arr[k] = R[j];
				j++;
				k++;
			}
		}

		while (i<n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		while (j<n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}
}
