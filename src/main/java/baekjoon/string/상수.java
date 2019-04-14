package baekjoon.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class 상수 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String A = scanner.next();
		String B = scanner.next();

		solve(A, B);
	}

	static void solve(String a, String b) {
		char[] arrA = a.toCharArray();
		char[] arrB = b.toCharArray();


		// 앞자리부터 비교해서 큰수가 항상 더 크다.
		for (int i = arrA.length-1 ; i>=0 ; i--) {
			if (arrA[i] < arrB[i]) {
				printReverse(arrB);
				break;
			} else if (arrA[i] > arrB[i]) {
				printReverse(arrA);
				break;
			}
		}
	}

	static void printReverse(char[] arrB) {
		StringBuilder sb = new StringBuilder();

		for (int i=arrB.length-1 ; i>= 0 ; i--) {
			sb.append(arrB[i]);
		}

		System.out.println(sb.toString());
	}
}
