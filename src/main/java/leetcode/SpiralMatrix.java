package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SpiralMatrix {
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> ret = new ArrayList<>();

		if (matrix.length == 0) {
			return ret;
		}

		int m = matrix.length;
		int n = matrix[0].length;

		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(Direction.RIGHT, 0, 0));

		while (q.isEmpty() == false) {
			Pair pair = q.poll();

			int y = pair.y;
			int x = pair.x;
			Direction d = pair.d;

			set(ret,matrix,y,x);

			if (Direction.RIGHT == d) {
				if (isValid(y,x+1,matrix,m,n)) {
					q.add(new Pair(Direction.RIGHT, y,x+1));
				} else if (isValid(y+1,x,matrix,m,n)) {
					q.add(new Pair(Direction.DOWN, y+1,x));
				}
			} else if (Direction.LEFT == d) {
				if (isValid(y,x-1,matrix,m,n)) {
					q.add(new Pair(Direction.LEFT,y,x-1));
				} else if (isValid(y-1,x,matrix,m,n)) {
					q.add(new Pair(Direction.UP,y-1,x));
				}
			} else if (Direction.UP == d) {
				if (isValid(y-1,x,matrix,m,n)) {
					q.add(new Pair(Direction.UP, y-1, x));
				} else if (isValid(y,x+1,matrix,m,n)) {
					q.add(new Pair(Direction.RIGHT, y, x+1));
				}
			} else if (Direction.DOWN == d) {
				if (isValid(y+1,x,matrix,m,n)) {
					q.add(new Pair(Direction.DOWN, y+1, x));
				} else if (isValid(y,x-1,matrix,m,n)) {
					q.add(new Pair(Direction.LEFT, y, x-1));
				}
			}
		}

		return ret;
	}

	static class Pair {
		Direction d;
		int y;
		int x;

		Pair (Direction d, int y, int x) {
			this.d = d;
			this.y = y;
			this.x = x;
		}
	}


	enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	private void set(List<Integer> ret, int[][] matrix, int y, int x) {
		ret.add(matrix[y][x]);
		matrix[y][x] = Integer.MIN_VALUE;
	}

	private boolean isValid(int y, int x, int [][] matrix, int m, int n) {
		if (y < 0 || y >= m) {
			return false;
		} else if (x < 0 || x>= n) {
			return false;
		}

		return matrix[y][x] != Integer.MIN_VALUE;
	}

	public static void main(String[] args) {
		int [][] matrix = {
			{1, 2, 3, 4},
			{5, 6, 7, 8},
			{9,10,11,12}
		};

		SpiralMatrix sm = new SpiralMatrix();
		List<Integer> ret = sm.spiralOrder(matrix);

		System.out.println(ret);
	}
}