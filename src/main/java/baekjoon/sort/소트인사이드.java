package baekjoon.sort;

import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class 소트인사이드 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		char [] S = scanner.next().toCharArray();

		mergeSort(0, S.length-1, S);

		StringBuilder sb = new StringBuilder();

		for (char c : S) {
			sb.append(c);
		}

		System.out.println(sb.toString());

	}

	static void mergeSort(int l, int r, char[] s) {
		if (l < r) {
			int m = (l+r) / 2;

			mergeSort(l,m,s);
			mergeSort(m+1,r,s);

			merge(l,m,r,s);
		}
	}

	static void merge(int l, int m, int r, char[] s) {
		int n1 = m-l+1;
		int n2 = r-m;

		char L[] = new char[n1];
		char R[] = new char[n2];

		for (int i=0 ; i<n1 ; i++) {
			L[i] = s[i+l];
		}

		for (int i=0 ; i<n2 ; i++) {
			R[i] = s[m+1+i];
		}

		int i=0;
		int j=0;
		int k=l;

		while (i<n1 && j<n2) {
			if (L[i] >= R[j]) {
				s[k] = L[i];
				i++;
			} else {
				s[k] = R[j];
				j++;
			}
			k++;
		}

		while (i<n1) {
			s[k] = L[i];
			i++;
			k++;
		}

		while (j<n2) {
			s[k] = R[j];
			j++;
			k++;
		}
	}
}
