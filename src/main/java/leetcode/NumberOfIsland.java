package leetcode;
public class NumberOfIsland {

	public int numIslands(char[][] grid) {
		if (grid.length == 0) {
			return 0;
		}

		int cnt = 0;
		boolean [][] visited = new boolean[grid.length][grid[0].length];

		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				if (grid[y][x] == '1' && visited[y][x] == false) {
					dfs(visited, grid, y, x);
					cnt++;
				}
			}
		}

		return cnt;
	}

	void dfs(boolean[][] visited, char[][] grid, int y, int x) {
		visited[y][x] = true;

		// left
		if (isValid(visited, grid, y, x-1)) {
			dfs(visited, grid, y, x-1);
		}

		// right
		if (isValid(visited, grid, y, x+1)) {
			dfs(visited, grid, y,x+1);
		}

		// up
		if (isValid(visited, grid, y-1, x)) {
			dfs(visited, grid, y-1, x);
		}

		// down
		if (isValid(visited, grid, y+1, x)) {
			dfs(visited, grid, y+1, x);
		}
	}

	boolean isValid(boolean[][] visited, char[][] grid, int y, int x) {
		if (y < 0 || y >= grid.length || x < 0 || x >= grid[0].length) {
			return false;
		}

		if (visited[y][x] || grid[y][x] == '0') {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
	}

}
