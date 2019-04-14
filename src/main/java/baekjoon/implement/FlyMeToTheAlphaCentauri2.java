package baekjoon.implement;

import java.util.Scanner;

/**
 * hint) https://www.acmicpc.net/board/view/13747
 */
public class FlyMeToTheAlphaCentauri2 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		for (int i=0 ; i<T ; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();

			int distance = Math.abs(x-y);

			double sqrt = Math.sqrt(distance);

			int maxSpeed = (int)sqrt;
			int warpCnt = 2 * (int)sqrt - 1;

			if (maxSpeed == sqrt) {
				System.out.println(warpCnt);
			} else {
				int remain = distance - (int)Math.pow(maxSpeed, 2);
				int addCnt = (int)(Math.ceil((double)remain / (double)maxSpeed));

				System.out.println(warpCnt + addCnt);
			}

		}
	}
}
