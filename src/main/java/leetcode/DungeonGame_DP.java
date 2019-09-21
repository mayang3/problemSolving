package leetcode;

// https://leetcode.com/problems/dungeon-game/discuss/52897/My-java-solution-with-explanation-in-detail
public class DungeonGame_DP {

	public int calculateMinimumHP(int [][] dungeon) {
		int row = dungeon.length;
		int column = dungeon[0].length;

		int[][] dp = new int[row][column];

		if (dungeon[row - 1][column - 1] >= 0) {
			dp[row - 1][column - 1] = 1;
		} else {
			dp[row - 1][column - 1] = 1 - dungeon[row - 1][column - 1];
		}

		// 맨 오른쪽 column 을 계산한다.
		for (int i = row - 2; i >= 0; i--) {
			dp[i][column - 1] = compare(dungeon[i][column-1], dp[i+1][column-1]);
		}

		for (int j = column - 2; j >= 0 ; j--) {
			dp[row - 1][j] = compare(dungeon[row-1][j], dp[row-1][j+1]);
		}

		for (int i = row - 2; i >= 0 ; i--) {
			for (int j = column - 2; j >= 0 ; j--) {
				dp[i][j] = Math.min(compare(dungeon[i][j], dp[i+1][j]), compare(dungeon[i][j], dp[i][j+1]));
			}
		}

		return dp[0][0];
	}

	// 핵심 비교 부분
	int compare(int currentValue, int preResult) {
		// 현재 값이 0 이면 이전 최소값이랑 똑같음
		if (currentValue == 0) {
			return preResult;
		}

		// 현재 값이 이전 최소값보다 크거나 같으면 현재 최소값은 1이다.
		if (currentValue > 0 && currentValue >= preResult) {
			return 1;
		}

		// 그 이외의 값은 이전 최소값 - 현재값
		return preResult - currentValue;
	}


	public static void main(String[] args) {
		int [][] dungeon = {
			{-2, -3, 3},
			{-5, -10, 1},
			{10, 30, -5}
		};

		DungeonGame_DP dp = new DungeonGame_DP();

		int ret = dp.calculateMinimumHP(dungeon);

		System.out.println(ret);

	}
}
