package strategies;

import java.util.Arrays;

public class JumpGame {

	static final int[][] matrix = {
		{2, 5, 1, 6, 1, 4, 1},
		{6, 1, 1, 2, 2, 9, 3},
		{7, 2, 3, 2, 1, 3, 1},
		{1, 1, 3, 1, 7, 1, 2},
		{4, 1, 2, 3, 4, 1, 2},
		{3, 3, 1, 2, 3, 4, 1},
		{1, 5, 2, 9, 4, 7, -50}
	};

	static int [][] cache = new int[7][7];

	public boolean jump(int x, int y) {
		int maxX = matrix.length;
		int maxY = matrix[0].length;

		// base case 1 : x, y 의 최대범위 초과
		if (x >= maxX || y >= maxY) {
			return false;
		}

		int offset = matrix[x][y];

		if (offset == -50) {
			return true;
		}

		return jump(x + offset, y) || jump(x, y + offset);
	}

	public int jump2(int x, int y) {
		int maxX = matrix.length;
		int maxY = matrix[0].length;

		// base case 1 : x, y 의 최대범위 초과
		if (x >= maxX || y >= maxY) {
			return -99;
		}

		int offset = matrix[x][y];

		if (offset == -50) {
			return -100;
		}

		if (cache[x][y] != -1) {
			return cache[x][y];
		}

		int i = jump2(x + offset, y);
		int i1 = jump2(x, y + offset);

		int ret = i == -100 || i1 == -100 ? -100 : -99;

		return cache[x][y] = ret;
	}

	public static void main(String[] args) {
		JumpGame jumpGame = new JumpGame();

		for (int [] subCache : cache) {
			Arrays.fill(subCache, -1);
		}

		System.out.println(jumpGame.jump(0, 0));
		System.out.println(jumpGame.jump2(0, 0));

	}
}
