package leetcode;

public class DungeonGame_bruteForce {
	static int finalMin = Integer.MAX_VALUE;
	static int yLength;
	static int xLength;

	public int calculateMinimumHP(int[][] dungeon) {
		finalMin = Integer.MAX_VALUE;
		yLength = dungeon.length;
		xLength = dungeon[0].length;

		solve(dungeon, 0, 0, 0, Integer.MIN_VALUE);

		return finalMin;
	}

	// beforeMin 은 직전 room 까지 도달했을때의 최소한의 값이다.
	void solve(int [][] dungeon, int y, int x, int beforeSum, int beforeMin) {
		// reach the princess
		if (y == yLength-1 && x == xLength - 1) {
			int min = beforeSum + dungeon[y][x] < 0 ? Math.abs(beforeSum + dungeon[y][x]) + 1 : 1;
			finalMin = Math.min(finalMin, Math.max(min, beforeMin));
			return;
		}

		int currentSum = beforeSum + dungeon[y][x];
		int currentMin = currentSum < 0 ? Math.abs(currentSum) + 1 : 1;

		currentMin = Math.max(beforeMin, currentMin);

		// right
		if (isValid(y, x+1)) {
			solve(dungeon, y, x+1, currentSum, currentMin);
		}

		// bottom
		if (isValid(y+1, x)) {
			solve(dungeon, y+1, x, currentSum, currentMin);
		}

	}

	private boolean isValid(int y, int x) {
		if (y < 0 || y >= yLength) {
			return false;
		} else if (x < 0 || x >= xLength) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		int [][] dungeons = {
			{-2, -3, 3},
			{-5, -10, 1},
			{10, 30, -5}
		};

		DungeonGame_bruteForce dg = new DungeonGame_bruteForce();

		int ret = dg.calculateMinimumHP(dungeons);

		System.out.println(ret);
	}
}
