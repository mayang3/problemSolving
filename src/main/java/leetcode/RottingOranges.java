package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {
	static int [][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public int orangesRotting(int[][] grid) {
		int freshOranges = 0;
		int minutes = 0;

		Queue<Point> q = new LinkedList<>();

		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				if (grid[y][x] == 2) {
					q.add(new Point(y, x));
				}

				if (grid[y][x] == 1) {
					freshOranges += 1;
				}
			}
		}

		while (q.isEmpty() == false) {
			int size = Integer.valueOf(q.size());
			boolean changed = false;

			for (int i = 0; i < size; i++) {
				Point currentPoint = q.poll();

				int y = currentPoint.y;
				int x = currentPoint.x;

				for (int [] direction : DIRECTIONS) {
					int nextY = y + direction[0];
					int nextX = x + direction[1];

					if (isPossible(nextY, nextX, grid)) {
						changed = true;
						grid[nextY][nextX] = 2;
						freshOranges -= 1;
						q.add(new Point(nextY, nextX));
					}
				}
			}

			if (changed) {
				minutes++;
			}
		}

		if (freshOranges > 0) {
			return -1;
		}

		return minutes;
	}

	private boolean isPossible(int y, int x, int[][] grid) {
		if (y < 0 || y >= grid.length) {
			return false;
		} else if (x < 0 || x >= grid[y].length) {
			return false;
		}

		return grid[y][x] == 1;
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
		int[][] grid = {{1}, {2}};

		RottingOranges rottingOranges = new RottingOranges();
		System.out.println(rottingOranges.orangesRotting(grid));
	}
}
