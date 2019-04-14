package baekjoon.sort;

import java.util.Arrays;
import java.util.Scanner;

public class 수정렬하기 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		int [] arr = new int[N];

		for (int i=0 ; i<N ; i++) {
			arr[i] = scanner.nextInt();
		}

		for (int i=1 ; i<arr.length ; i++) {
			int j=i;
			int k=i-1;

			while (k >= 0) {
				if (arr[j] < arr[k]) {
					int temp = arr[j];
					arr[j] = arr[k];
					arr[k] = temp;

					j=k;
				}

				k--;
			}
		}

		for (int v : arr) {
			System.out.println(v);
		}
	}
}
