package stratgies2.exhaustiveSearch;

import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class BoardCover2 {

	static int whiteCount = 0;
	static int blackCount = 0;

	static int [][][] offset = {
		{{0,0}, {0,1}, {1,0}},
		{{0,0}, {0,1}, {1,1}},
		{{0,0}, {1,0}, {1,1}},
		{{0,0}, {1,0}, {1,-1}}
	};

	static int maxY;
	static int maxX;

	static int solve(int [][] board) {
		if (whiteCount % 3 != 0) {
			return 0;
		}

		XY xy = getStart(board);

		if (xy == null) {
			return 1;
		}

		int sum = 0;

		for (int i=0 ; i<4 ; i++) {
			int startY = xy.y;
			int startX = xy.x;

			int y1 = startY + offset[i][0][0];
			int x1 = startX + offset[i][0][1];

			if (y1 >= maxY || x1 >= maxX || x1 < 0 || y1 < 0) {
				continue;
			}

			int y2 = startY + offset[i][1][0];
			int x2 = startX + offset[i][1][1];

			if (y2 >= maxY || x2 >= maxX || x2 < 0 || y2 < 0) {
				continue;
			}

			int y3 = startY + offset[i][2][0];
			int x3 = startX + offset[i][2][1];


			if (y3 >= maxY || x3 >= maxX || x3 < 0 || y3 < 0) {
				continue;
			}

			if (board[y1][x1] == 0 && board[y2][x2] == 0 && board[y3][x3] == 0) {
				board[y1][x1] = board[y2][x2] = board[y3][x3] = 1;
				sum += solve(board);
				board[y1][x1] = board[y2][x2] = board[y3][x3] = 0;
			}
		}


		return sum;
	}

	private static XY getStart(int[][] board) {

		for (int y=0 ; y<board.length ; y++) {
			for (int x=0 ; x<board.length ; x++) {
				if (board[y][x] == 0) {
					return new XY(y,x);
				}
			}
		}

		return null;
	}

	static class XY {
		int y;
		int x;

		XY(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int C = scanner.nextInt();
		int H = scanner.nextInt();
		int W = scanner.nextInt();

		while (C-- > 0) {
			int [][] board = new int[H][W];

			maxY = board.length;
			maxX = board[0].length;

			for (int y = 0; y < H; y++) {
				char[] chars = scanner.next().toCharArray();

				for (int x = 0; x < W; x++) {
					if (chars[x] == '#') {
						blackCount++;
						board[y][x] = 1;
					} else {
						whiteCount++;
						board[y][x] = 0;
					}
				}
			}

			System.out.println(solve(board));
		}
	}
}
