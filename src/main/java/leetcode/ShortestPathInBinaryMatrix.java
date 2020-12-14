package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
	public int shortestPathBinaryMatrix(int[][] grid) {
		if (grid == null || grid.length == 0) {
			return -1;
		}

		int n = grid.length;

		if (grid[0][0] != 0 || grid[n-1][n-1] != 0) {
			return -1;
		}

		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0,0));

		boolean [][] visited = new boolean[n][n];
		visited[0][0] = true;

		int path = 0;

		while (q.isEmpty() == false) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				Point p = q.poll();

				if (p.y == n-1 && p.x == n-1) {
					return path+1;
				}

				for (int addY = -1; addY < 2; addY++) {
					for (int addX = -1; addX < 2; addX++) {
						if (addY == 0 && addX == 0) continue;

						int nextY = p.y + addY;
						int nextX = p.x + addX;

						if (isPossibleToVisit(nextY, nextX, grid, visited)) {
							q.add(new Point(nextY, nextX));
							visited[nextY][nextX] = true;
						}
					}
				}
			}

			// 한 depth 가 지날때마다 path 도 1씩 증가한다.
			path++;
		}

		// 위의 if 문을 타지 못했다면 무조건 답이 없는것이다.
		return -1;
	}

	private boolean isPossibleToVisit(int nextY, int nextX, int[][] grid, boolean[][] visited) {
		int n = grid.length;

		if (nextX < 0 || nextY < 0 || nextY >= n || nextX >= n) {
			return false;
		} else if (visited[nextY][nextX]) {
			return false;
		}

		return grid[nextY][nextX] == 0;
	}

	static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) {
		int [][] grid ={{0,0,1,0},{1,0,1,0},{1,1,0,1},{0,0,0,0}};

		ShortestPathInBinaryMatrix shortestPathInBinaryMatrix = new ShortestPathInBinaryMatrix();

		System.out.println(shortestPathInBinaryMatrix.shortestPathBinaryMatrix(grid));
	}
}
