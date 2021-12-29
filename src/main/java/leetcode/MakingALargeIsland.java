package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MakingALargeIsland {

	int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	public int largestIsland(int[][] grid) {
		int n = grid.length;
		int mark = 1;
		int max = 0;

		Map<Integer, Integer> map = new HashMap<>();
		boolean[][] visited = new boolean[n][n];

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				if (visited[y][x] == false && grid[y][x] == 1) {
					int area = dfs(visited, grid, y, x, mark);
					max = Math.max(max, area);
					map.put(mark, area);
					mark++;
				}
			}
		}

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				if (grid[y][x] == 0) {
					int area = 0;
					Set<Integer> visitedSet = new HashSet<>();

					for (int [] dir : DIRECTIONS) {
						int nextY = y + dir[0];
						int nextX = x + dir[1];

						if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < n && grid[nextY][nextX] > 0 && visitedSet.contains(grid[nextY][nextX]) == false) {
							area += map.getOrDefault(grid[nextY][nextX], 0);
							visitedSet.add(grid[nextY][nextX]);
						}
					}

					max = Math.max(max, ++area);
				}
			}
		}

		return max;
	}

	private int dfs(boolean[][] visited, int[][] grid, int y, int x, int mark) {
		int n = grid.length;
		visited[y][x] = true;
		grid[y][x] = mark;

		int area = 1;

		for (int [] dir : DIRECTIONS) {
			int nextY = y + dir[0];
			int nextX = x + dir[1];

			if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < n && visited[nextY][nextX] == false && grid[nextY][nextX] == 1) {
				area += dfs(visited, grid, nextY, nextX, mark);
			}
		}

		return area;
	}

	public static void main(String[] args) {
		int[][] grid = {{1, 1}, {1, 0}};

		MakingALargeIsland makingALargeIsland = new MakingALargeIsland();
		System.out.println(makingALargeIsland.largestIsland(grid));
	}
}
