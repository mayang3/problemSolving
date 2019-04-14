package baekjoon.string;

import java.util.Scanner;

public class 다이얼 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String S = scanner.next();

		int sum = 0;

		for (int i=0 ; i<S.length() ; i++) {
			char ch = S.charAt(i);

			if (ch < 'P') {
				// (A or B or C) / 3 = 1초가 나오도록 계산식을 만들어준다.
				// 단, 4개의 값이 매핑되어 있는 알파벳들은 별도 예외처리 해준다.
				int add = (ch - 65 + 3) / 3;
				// 1까지 걸리는 시간 2초를 더해준다.
				sum += add + 2;
			} else if ('P' <= ch && ch <= 'S') {
				sum += 6 + 2;
			} else if ('T' <= ch && ch <= 'V') {
				sum += 7 + 2;
			} else if ('W' <= ch && ch <= 'Z') {
				sum += 8 + 2;
			}
		}

		System.out.println(sum);
	}
}
