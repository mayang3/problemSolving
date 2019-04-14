package baekjoon.string;

import java.util.Scanner;

public class 단어공부 {
	static int [] arr = new int[26];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String S = scanner.next().toUpperCase();

		for (int i=0 ; i<S.length() ; i++) {
			char c = S.charAt(i);
			arr[(int)c-65]++;
		}

		int max = 0;
		char ret = '?';

		for (int i=0 ; i<arr.length ; i++) {
			if (max < arr[i]) {
				max = arr[i];
				ret = (char)(i+65);
			}
		}

		int cnt = 0;

		for (int i=0 ; i<arr.length ; i++) {
			if (cnt > 1) {
				ret = '?';
				break;
			}

			if (max == arr[i]) {
				cnt++;
			}
		}

		System.out.println(ret);
	}
}
