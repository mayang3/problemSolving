package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {
	public static void main(String[] args) {
		int[][] grid = {{1}, {2}};

		RottingOranges ro = new RottingOranges();
		int res = ro.orangesRotting(grid);

		System.out.println(res);
	}

	public int orangesRotting(int[][] grid) {
		if (grid == null || grid.length == 0) {
			return -1;
		}

		Queue<Point> q = new LinkedList<>();

		int oranges = 0;
		int rottingOranges = 0;

		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				if (grid[y][x] == 2) {
					rottingOranges++;
					q.add(new Point(y, x));
				}

				if (grid[y][x] == 1) {
					oranges++;
				}
			}
		}

		if (oranges == 0) {
			return 0;
		}

		if (rottingOranges == 0) {
			return oranges == 0 ? 0 : -1;
		}

		int time = -1;

		while (q.isEmpty() == false) {

			Integer size = new Integer(q.size());

			time++;

			for (int i = 0; i < size; i++) {

				Point p = q.poll();

				int y = p.y;
				int x = p.x;

				// up
				if (isValid(grid, y - 1, x)) {
					oranges--;
					grid[y - 1][x] = 2;
					q.add(new Point(y - 1, x));
				}

				// down
				if (isValid(grid, y + 1, x)) {
					oranges--;
					grid[y + 1][x] = 2;
					q.add(new Point(y + 1, x));
				}

				// left
				if (isValid(grid, y, x - 1)) {
					oranges--;
					grid[y][x - 1] = 2;
					q.add(new Point(y, x - 1));
				}

				// right
				if (isValid(grid, y, x + 1)) {
					oranges--;
					grid[y][x + 1] = 2;
					q.add(new Point(y, x + 1));
				}

			}
		}

		return oranges == 0 ? time : -1;
	}

	public boolean isValid(int[][] grid, int y, int x) {
		if (y < 0 || y >= grid.length) {
			return false;
		} else if (x < 0 || x >= grid[0].length) {
			return false;
		}

		return grid[y][x] == 1;
	}

	public static class Point {
		int y;
		int x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
