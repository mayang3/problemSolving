package baekjoon.implement;

import java.util.Scanner;

public class 방번호 {
	static int [] arr = new int[10];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String S = scanner.next();

		int max = 0;

		for (int i=0 ; i<S.length() ; i++) {
			int index = S.charAt(i)-48;

			arr[index]++;
			if (index != 6 && index != 9) {
				max = Math.max(max, arr[index]);
			}
		}

		// 최초 fail.. 2.0 을 그냥 2로 해서 arr[6] + arr[9] / 2 의 연산이 정수 2로 떨어졌다.
		// 정답이 되려면 2.5 로 떨어져야 함
		int sixAndNine = (int)Math.ceil((arr[6] + arr[9]) / 2.0);

		System.out.println(Math.max(max, sixAndNine));
	}
}
