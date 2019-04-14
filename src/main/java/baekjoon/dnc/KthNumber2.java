package baekjoon.dnc;

import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class KthNumber2 {

	static void mergeSort(int l, int r, long [] input) {
		if (l < r) {
			int m = (l+r) / 2;

			mergeSort(0, m, input);
			mergeSort(m+1, r, input);

			merge(l, r, m, input);
		}
	}

	private static void merge(int l, int r, int m, long[] input) {
		int n1 = m - l + 1;
		int n2 = r - m;

		long [] L = new long[n1];

		for (int i=0 ; i<n1 ; i++) L[i] = input[l+i];

		long [] R = new long[n2];

		for (int i=0 ; i<n2 ; i++) R[i] = input[m+1+i];

		int i=0;
		int j=0;
		int k=l;

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
		int M = scanner.nextInt();

		long [] input = new long[N];

		for (int i=0 ; i<N ; i++) {
			input[i] = scanner.nextLong();
		}

		for (int i=0 ; i<M ; i++) {

			int I = scanner.nextInt();
			int J = scanner.nextInt();
			int K = scanner.nextInt();

			long[] newInput = Arrays.copyOfRange(input, I - 1, J);

//			mergeSort(I-1, newInput.length-1, newInput);
			Arrays.sort(newInput);

			System.out.println(newInput[K-1]);
		}
	}
}
