package baekjoon.implement;

import java.util.Scanner;

/**
 * 이 문제는.. 고기를 하나하나씩 뒤집는다면.. 어떤 방식으로 뒤집던지 간에, 겹칠수 밖에 없다.
 *
 * 그러면 어떻게 뒤집을 것인가?
 *
 * 불판을 좌/우 또는 위/아래로 동시에 뒤집으면 무조건 클리어 된다.
 *
 */
public class MeatOnTheGrill {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int H = scanner.nextInt();
			int W = scanner.nextInt();

			char [][] board = new char[H][W];

			for (int i = 0; i < H; i++) {
				String column = scanner.next();
				for (int j = 0; j < W; j++) {
					board[i][j] = column.charAt(j);
				}
			}

			char [][] ret = solve(H, W, board);

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(ret[i][j]);
				}

				System.out.println();
			}
		}
	}

	static char[][] solve(int h, int w, char[][] board) {

		for (int i = 0; i < h; i++) {
			int start = 0;
			int end = w-1;

			while (start < end) {
				char temp = board[i][start];
				board[i][start] = board[i][end];
				board[i][end] = temp;

				start++;
				end--;
			}
		}

		return board;
	}
}
