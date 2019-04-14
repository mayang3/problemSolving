package baekjoon.implement;

import java.util.Scanner;

@SuppressWarnings("ALL")
public class 리모컨3 {
	static boolean [] errors = new boolean[10];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();

		for (int i=0 ; i<m ; i++) {
			errors[scanner.nextInt()] = true;
		}

		// 1. 현재 채널과 옮길 채널이 같은 경우
		if (n == 100) {
			System.out.println(0);
			return;
		}

		// 2. + or - 만으로 이동하는 경우
		int min = Math.abs(n - 100);

		// 3. 채널 번호로 정확히 이동되는 경우
		if (isError(n) == false) {
			min = Math.min(min, String.valueOf(n).length());
			System.out.println(min);
			return;
		}

		// 4. 모두 에러여서 이동할 곳을 찾지 못하는 경우
		if (isAllError()) {
			System.out.println(min);
			return;
		}


		// 4. 작은 채널번호에서 큰 채널번호로 이동하는 경우

		// 5. 큰 채널 번호에서 작은 채널번호로 이동하는 경우

//		int i=n;
//		int j=n;
		int adjacent=0;

//		while (i > 0 || j < 1000000) {
//			if (i > 0 && isError(i) == false) {
//				adjacent = i;
//				break;
//			} else if (j < 1000000 && isError(j) == false) {
//				adjacent = j;
//				break;
//			} else {
//				i--;
//				j++;
//			}
//		}

		// try 3 times...
		// 기존 위의 주석되어 있는대로 n 번 채널과 인접한 채널을 위 or 아래로 분산해서 나누었는데,
		// 그렇게 할 경우, 위가 최소의 채널인데 아래가 선택된다거나, 아래가 최소의 채널인데 위가 선택되는 경우가있는듯하다.
		// 모든 경우를 탐색하고 min 값을 도출해내니 성공
		for (int i=0 ; i<1000000 ; i++) {
			if (isError(i) == false) {
				min = Math.min(min, String.valueOf(i).length() + Math.abs(n - i));
			}
		}

		System.out.println(min);
	}

	static boolean isAllError() {

		for (boolean b : errors) {
			if (b == false) {
				return false;
			}
		}

		return true;
	}

	private static int findLowChannel(int n) {
		return Integer.MAX_VALUE;
	}

	static boolean isError(int n) {
		if (n == 0) {
			return errors[0];
		}

		while (n > 0 && n % 10 >= 0) {
			if (errors[n % 10]) {
				return true;
			}

			n /= 10;
		}

		return false;
	}
}
