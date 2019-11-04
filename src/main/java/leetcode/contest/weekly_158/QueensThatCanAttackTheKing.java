package leetcode.contest.weekly_158;

import java.util.*;

/**
 * @author neo82
 */
public class QueensThatCanAttackTheKing {
	public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
		int n = 8;

		boolean leftUp = false;
		boolean rightUp = false;
		boolean leftDown = false;
		boolean rightDown =false;
		boolean up = false;
		boolean down = false;
		boolean left = false;
		boolean right = false;

		Set<Point> set = new HashSet<>();

		for (int [] queen : queens) {
			set.add(new Point(null, queen[0], queen[1]));
		}

		Queue<Point> q = new LinkedList<>();
		q.add(new Point(Direction.ALL, king[0], king[1]));

		List<List<Integer>> outputList = new ArrayList<>();

		while (!q.isEmpty()) {
			Point point = q.poll();

			Direction direction = point.direction;
			int y = point.y;
			int x = point.x;

			// left up
			if (isValid(n, y-1, x-1) && (direction.isValid(Direction.LEFT_UP) && !leftUp)) {
				if (set.contains(new Point(null, y-1, x-1)) && !leftUp) {
					leftUp = true;
					addOutput(outputList, y-1, x-1);
				} else {
					q.add(new Point(Direction.LEFT_UP, y-1, x-1));
				}
			}

			// right up
			if (isValid(n, y-1, x+1) && (direction.isValid(Direction.RIGHT_UP) && !rightUp)) {
				if (set.contains(new Point(null, y-1, x+1))) {
					rightUp = true;
					addOutput(outputList, y-1, x+1);
				} else {
					q.add(new Point(Direction.RIGHT_UP, y-1, x+1));
				}
			}

			// left down
			if (isValid(n, y+1, x-1) && (direction.isValid(Direction.LEFT_DOWN) && !leftDown)) {
				if (set.contains(new Point(null, y+1, x-1))) {
					leftDown = true;
					addOutput(outputList, y+1, x-1);
				} else {
					q.add(new Point(Direction.LEFT_DOWN, y+1, x-1));
				}
			}

			// right down
			if (isValid(n, y+1, x+1) && (direction.isValid(Direction.RIGHT_DOWN) && !rightDown)) {
				if (set.contains(new Point(null, y+1, x+1))) {
					rightDown = true;
					addOutput(outputList, y+1, x+1);
				} else {
					q.add(new Point(Direction.RIGHT_DOWN, y+1, x+1));
				}
			}

			// up
			if (isValid(n, y+1, x) && (direction.isValid(Direction.UP) && !up)) {
				if (set.contains(new Point(null, y+1, x))) {
					up = true;
					addOutput(outputList, y+1, x);
				} else {
					q.add(new Point(Direction.UP, y+1, x));
				}
			}

			// down
			if (isValid(n, y-1, x) && (direction.isValid(Direction.DOWN) && !down)) {
				if (set.contains(new Point(null, y-1, x))) {
					down = true;
					addOutput(outputList, y-1, x);
				} else {
					q.add(new Point(Direction.DOWN, y-1, x));
				}
			}

			// left
			if (isValid(n, y, x-1) && (direction.isValid(Direction.LEFT) && !left)) {
				if (set.contains(new Point(null, y, x-1))) {
					left = true;
					addOutput(outputList, y, x-1);
				} else {
					q.add(new Point(Direction.LEFT, y, x-1));
				}
			}

			// right
			if (isValid(n, y, x+1) && (direction.isValid(Direction.RIGHT) && !right)) {
				if (set.contains(new Point(null, y, x+1))) {
					right = true;
					addOutput(outputList, y, x+1);
				} else {
					q.add(new Point(Direction.RIGHT, y, x+1));
				}
			}
		}

		return outputList;
	}

	private void addOutput(List<List<Integer>> outputList, int y, int x) {
		List<Integer> list = new ArrayList<>();

		list.add(y);
		list.add(x);

		outputList.add(list);
	}

	private boolean isValid(int n, int y, int x) {
		if (y < 0 || x < 0 || y >= n || x >= n) {
			return false;
		}

		return true;
	}

	enum Direction {
		ALL,
		LEFT_UP,
		RIGHT_UP,
		LEFT_DOWN,
		RIGHT_DOWN,
		UP,
		DOWN,
		LEFT,
		RIGHT;

		public boolean isValid(Direction direction) {
			return this == ALL || this == direction;
		}
	}

	static class Point {
		Direction direction;
		int y;
		int x;

		public Point(Direction direction, int y, int x) {
			this.direction = direction;
			this.y = y;
			this.x = x;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			Point point = (Point)o;

			if (y != point.y) {
				return false;
			}
			return x == point.x;
		}

		@Override
		public int hashCode() {
			int result = y;
			result = 31 * result + x;
			return result;
		}
	}

	public static void main(String[] args) {

		int [][] queens = {{5,6},{7,7},{2,1},{0,7},{1,6},{5,1},{3,7},{0,3},{4,0},{1,2},{6,3},{5,0},{0,4},{2,2},{1,1},{6,4},{5,4},{0,0},{2,6},{4,5},{5,2},{1,4},{7,5},{2,3},{0,5},{4,2},{1,0},{2,7},{0,1},{4,6},{6,1},{0,6},{4,3},{1,7}};
		int [] king = {3,4};

		QueensThatCanAttackTheKing queensThatCanAttackTheKing = new QueensThatCanAttackTheKing();

		System.out.println(queensThatCanAttackTheKing.queensAttacktheKing(queens,  king));

	}
}
