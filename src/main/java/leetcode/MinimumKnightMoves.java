

package leetcode;

import java.util.*;

public class MinimumKnightMoves {
	static int [][] directions = {
		{-2, -1},
		{-2, 1},
		{-1, -2},
		{-1, 2},
		{1, -2},
		{1, 2},
		{2, -1},
		{2, 1}
	};

	public int minKnightMoves(int x, int y) {
		// 1사분면만 탐색하게 해준다.
		x = Math.abs(x);
		y = Math.abs(y);

		Point start = new Point(0, 0);

		Set<Point> visited = new HashSet<>();
		visited.add(start);

		Queue<Point> q = new LinkedList<>();
		q.add(start);

		int step = 0;

		while (q.isEmpty() == false) {
			int size = Integer.valueOf(q.size());

			for (int i = 0; i < size; i++) {
				Point point = q.poll();

				if (point.y == y && point.x == x) {
					return step;
				}

				for (int [] dir : directions) {
					Point next = new Point(point.y + dir[0], point.x + dir[1]);

					// For example, to reach (1,1) from (0, 0), the best way is to get (2, -1) or (-1, 2) first, then (1,1) (two steps).
					// If we eliminate all coordinates with negative numbers, then we can't reach (1,1) from (0, 0) within two steps.
					if (visited.contains(next) == false && next.y >= -1 && next.x >= -1) {
						q.add(next);
						visited.add(next);
					}
				}
			}

			step++;
		}

		return -1;
	}


	static class Point {
		int y;
		int x;

		public Point(int y, int x) {
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
			return y == point.y &&
				x == point.x;
		}

		@Override
		public int hashCode() {
			return Objects.hash(y, x);
		}
	}

	public static void main(String[] args) {
		MinimumKnightMoves moves = new MinimumKnightMoves();
		System.out.println(moves.minKnightMoves(0, -300));
	}
}
