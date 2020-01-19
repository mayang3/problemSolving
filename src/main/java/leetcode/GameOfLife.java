package leetcode;

import java.util.Arrays;

public class GameOfLife {

	public void gameOfLife(int[][] board) {
		int n = board.length;
		int m = board[0].length;

		int [][] res = new int[n][m];

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < m; x++) {
				int sum = 0;
				// top
				sum += getCellValue(y-1,x,board,n,m);
				// left top
				sum += getCellValue(y-1,x-1,board,n,m);
				// right top
				sum += getCellValue(y-1,x+1,board,n,m);
				// down
				sum += getCellValue(y+1,x,board,n,m);
				// left down
				sum += getCellValue(y+1,x-1,board,n,m);
				// right down
				sum += getCellValue(y+1,x+1,board,n,m);
				// left
				sum += getCellValue(y,x-1,board,n,m);
				// right
				sum += getCellValue(y,x+1,board,n,m);

				int cur = board[y][x];

				if (cur == 0) {
					if (sum == 3) {
						res[y][x] = 1;
					}
				} else {
					if (sum < 2) {
						res[y][x] = 0;
					} else if (sum == 2 || sum == 3) {
						// Any live cell with two or three live neighbors lives on to the next generation.
						res[y][x] = 1;
					} else if (sum > 3) {
						// Any live cell with more than three live neighbors dies, as if by over-population..
						res[y][x] = 0;
					}
				}
			}
		}

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < m; x++) {
				board[y][x] = res[y][x];
			}
		}
	}

	private int  getCellValue(int y, int x, int [][] board, int n, int m) {
		if (y < 0 || y >= n || x < 0 || x >= m) {
			return 0;
		}

		return board[y][x];
	}

	public static void main(String[] args) {
		int [][] board = {
			{0,1,0},
  			{0,0,1},
  			{1,1,1},
  			{0,0,0}
		};

		GameOfLife gameOfLife = new GameOfLife();
		gameOfLife.gameOfLife(board);

		System.out.println(Arrays.deepToString(board));
	}
}
