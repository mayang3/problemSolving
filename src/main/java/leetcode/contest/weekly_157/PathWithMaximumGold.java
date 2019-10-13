package leetcode.contest.weekly_157;

public class PathWithMaximumGold {

	public int getMaximumGold(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		int max = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] != 0) {
					max = Math.max(max, solve(grid, m, n, i, j));
				}
			}
		}

		return max;
	}

	private int solve(int[][] grid, int m, int n, int y, int x) {

		int max = 0;
		int gold = grid[y][x];

		if (!isValid(grid, m,n,y-1,x) && !isValid(grid, m,n,y+1,x) && !isValid(grid, m,n,y,x-1) && !isValid(grid, m,n,y,x+1)) {
			return gold;
		}

		// up
		if (isValid(grid, m, n, y-1, x)) {
			grid[y][x] = 0;
			max = Math.max(max, gold + solve(grid, m, n, y-1, x));
			grid[y][x] = gold;
		}

		// down
		if (isValid(grid, m, n, y+1, x)) {
			grid[y][x] = 0;
			max = Math.max(max, gold + solve(grid, m, n, y+1, x));
			grid[y][x] = gold;
		}

		// left
		if (isValid(grid,m,n,y,x-1)) {
			grid[y][x] = 0;
			max = Math.max(max, gold + solve(grid, m, n, y, x-1));
			grid[y][x] = gold;
		}

		// right
		if (isValid(grid,m,n,y,x+1)) {
			grid[y][x] = 0;
			max = Math.max(max, gold + solve(grid, m, n, y, x+1));
			grid[y][x] = gold;
		}

		return max;
	}

	private boolean isValid(int[][] grid, int m, int n, int y, int x) {
		if (y >= m || x >= n) {
			return false;
		} else if (y < 0 || x < 0) {
			return false;
		} else if (grid[y][x] == 0) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		int [][] grid = {{0,6,0},{5,8,7},{0,9,0}};

		PathWithMaximumGold pathWithMaximumGold = new PathWithMaximumGold();
		int maximumGold = pathWithMaximumGold.getMaximumGold(grid);

		System.out.println(maximumGold);
	}
}
