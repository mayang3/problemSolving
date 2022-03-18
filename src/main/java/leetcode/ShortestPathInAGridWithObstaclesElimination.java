package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInAGridWithObstaclesElimination {
	int[] dirs = {0, 1, 0, -1, 0};
	public int shortestPath(int[][] grid, int k) {
		int m = grid.length, n = grid[0].length;
		if (k >= m+n-2) return m+n-2; // if we can go by manhattan distance -> let's go

		boolean[][][] visited = new boolean[m][n][k+1];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[]{0, 0, k, 0}); // r, c, k, dist
		visited[0][0][k] = true;

		while (!q.isEmpty()) {
			int[] top = q.poll();
			int r = top[0], c = top[1], curK = top[2], dist = top[3];
			if (r == m - 1 && c == n - 1) return dist; // Found the result
			for (int i = 0; i < 4; i++) {
				int nr = r + dirs[i], nc = c + dirs[i + 1];
				if (nr < 0 || nr == m || nc < 0 || nc == n) continue; // skip out of bound cells!
				int newK = curK - grid[nr][nc];
				if (newK >= 0 && !visited[nr][nc][newK]) {
					visited[nr][nc][newK] = true;
					q.offer(new int[]{nr, nc, newK, dist + 1});
				}
			}
		}
		return -1; // Not found
	}

	public static void main(String[] args) {
		int [][] grid = {{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}};
		int k = 1;

		ShortestPathInAGridWithObstaclesElimination pathInAGridWithObstaclesElimination = new ShortestPathInAGridWithObstaclesElimination();
		System.out.println(pathInAGridWithObstaclesElimination.shortestPath(grid, k));
	}
}
