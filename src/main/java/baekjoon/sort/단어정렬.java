package baekjoon.sort;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@SuppressWarnings("ALL")
public class 단어정렬 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		Set<String> set = new HashSet<>();

		for (int i=0 ; i<n ; i++) {
			set.add(scanner.next());
		}

		String [] arr = new String[set.size()];

		int i=0;

		for (String s : set) {
			arr[i++] = s;
		}

		mergeSort(0,arr.length-1,arr);


		for (String s : arr) {
			System.out.println(s);
		}

	}

	static void mergeSort(int l, int r, String[] arr) {
		if (l < r) {
			int m = (l+r) / 2;

			mergeSort(l, m, arr);
			mergeSort(m+1, r, arr);


			merge(l,m,r,arr);
		}
	}

	static void merge(int l, int m, int r, String[] arr) {
		int n1 = m-l+1;
		int n2= r-m;

		String [] L = new String[n1];
		String [] R = new String[n2];

		for (int i=0 ; i<n1 ; i++) {
			L[i] = arr[i+l];
		}

		for (int i=0 ; i<n2 ; i++) {
			R[i] = arr[m+1+i];
		}

		int i=0;
		int j=0;
		int k=l;

		while (i<n1 && j <n2) {
			if (L[i].length() < R[j].length()) {
				arr[k] = L[i];
				i++;
			} else if (L[i].length() > R[j].length()) {
				arr[k] = R[j];
				j++;
			} else {
				// 길이가 같은 경우, 사전순 정렬한다.
				if (isLeftFirst(L[i], R[j])) {
					arr[k] = L[i];
					i++;
				} else {
					arr[k] = R[j];
					j++;
				}
			}

			k++;
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

	static boolean isLeftFirst(String l, String r) {
		// l.length == r.length
		int length = l.length();

		for (int i=0 ; i<length ; i++) {
			// 앞에서부터 한글자라도 빠르면 사전순으로 빠르다.
			if (l.charAt(i) < r.charAt(i)) {
				return true;
			} else if (l.charAt(i) > r.charAt(i)){
				// 앞에서부터 한글자라도 느리면 사전순으로 느리다.
				return false;
			}

			// 같을 경우, 다음단어 검색한다.
		}

		// 사전순으로도 완전히 같은 문자일 경우
		return true;
	}
}
