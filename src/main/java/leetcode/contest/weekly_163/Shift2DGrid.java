package leetcode.contest.weekly_163;

import java.util.ArrayList;
import java.util.List;

public class Shift2DGrid {
	public List<List<Integer>> shiftGrid(int[][] grid, int k) {
		int n = grid.length;
		int m = grid[0].length;
		k %= (n*m);

		List<List<Integer>> ret = new ArrayList<>();

		for (int op = 0; op < k; op++) {
			int [] temp = new int[n];

			for (int y = 0; y < n; y++) {
				temp[y] = grid[y][m-1];
			}

			for (int y = 0; y < n; y++) {
				for (int x = m-2; x >= 0; x--) {
					grid[y][x+1] = grid[y][x];
				}
			}

			for (int y = 1; y < n; y++) {
				grid[y][0] = temp[y-1];
			}

			grid[0][0] = temp[temp.length-1];
		}

		for (int y = 0; y < n; y++) {
			List<Integer> row = new ArrayList<>();

			for (int x = 0; x < m; x++) {
				row.add(grid[y][x]);
			}

			ret.add(row);
		}

		return ret;
	}

	public static void main(String[] args) {
		int [][] grid = {{1},{2},{3},{4},{7},{6},{5}};
		int k = 23;

		Shift2DGrid shift2DGrid = new Shift2DGrid();

		List<List<Integer>> resultList = shift2DGrid.shiftGrid(grid, k);

		System.out.println(resultList);
	}
}
