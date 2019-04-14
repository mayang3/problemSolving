package baekjoon.implement;

import java.util.Scanner;

public class 카잉달력 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int M = scanner.nextInt();
			int N = scanner.nextInt();
			int x = scanner.nextInt();
			int y = scanner.nextInt();

			int curX = 1;
			int year = 1;

			solve(M, N, x, y, curX, year);
		}
	}

	static void solve(int m, int n, int x, int y, int curX, int year) {
		do {
			int compare = (curX + (y - 1)) % m;

			// compare 가 0 이면 m == x 을 추가해줬더니 accept 되었다.
			// 반례만 참고하고 스스로 풀었음!
			// 다른 방법은 어떤 원리인지도 확인해보자..
			if (compare == 0 ? m == x : compare == x) {
				System.out.println(year + (y - 1));
				return;
			}

			// 다음 (x,1) 인 값으로 건너뛰자.
			// 다음 (x,y) 값은 y는 항상 1이고,
			// x 의 값은 현재 x 의 값과 항상 n-m 만큼 차이가 난다.
			// -값이 될수도 있으므로 m 을 한번 더 더해서 % m 으로 모듈러 해준다.
			curX = (curX + (n - m) + m) % m;
			year += n;

		} while (curX != 1);

		System.out.println(-1);
	}
}
