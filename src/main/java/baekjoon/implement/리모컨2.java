package baekjoon.implement;

import java.util.Scanner;

/**
 * 경우의 수.
 *
 * 1. 채널을 이동하지 않아도 되는 경우 (현재 채널과 목표 채널이 같은 경우)
 * 2. 특정 채널로 이동 후, 나머지 채널을 버튼으로 이동하는 경우
 * 2-1. 특정 채널로 이동했을 때, 목표 채널에 정확히 도착
 * 2-2. 특정 채널로 이동했을 때, 목표 채널에 어긋나서 + or - 버튼으로 이동
 *
 * 3. 특정 채널로 이동이 불가능하거나, 더 비용이 높아서, 순수 버튼으로만 이동하는 경우
 *
 */
public class 리모컨2 {
	static boolean [] errors = new boolean[10];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();

		for (int i=0 ; i<m ; i++) {
			errors[scanner.nextInt()] = true;
		}

		// 1. 현재 채널과 목표 채널이같은 경우
		if (n == 100) {
			System.out.println(0);
			return;
		}

		int i=n;
		int j=n;
		int adjacent=0;

		while (i > 0 || j <= Integer.MAX_VALUE) {
			if (i > 0 && isError(i) == false) {
				adjacent = i;
				break;
			} else if (j <= Integer.MAX_VALUE && isError(j) == false) {
				adjacent = j;
				break;
			} else {
				i--;
				j++;
			}
		}

		int cnt = String.valueOf(adjacent).length();

		if (adjacent == 0 || (cnt + Math.abs(n - adjacent)) > Math.abs(n - 100)) {
			System.out.println(Math.abs(n-100));
		} else {
			System.out.println(cnt + Math.abs(n - adjacent));
		}
	}

	static boolean isError(int n) {

		while (n > 0 && n % 10 >= 0) {
			if (errors[n % 10]) {
				return true;
			}

			n /= 10;
		}

		return false;
	}
}
