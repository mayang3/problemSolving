package hackerrank.cs.dataStructure.queue;

import java.util.Arrays;
import java.util.Scanner;

/**
 * You are given a square grid with some cells open (.) and some blocked (X).
 *
 * Your playing piece can move along any row or column until it reaches the edge of the grid or a blocked cell.
 *
 * Given a grid, a start and an end position, determine the number of moves it will take to get to the end position.
 *
 * For example, you are given a grid with sides n=3 described as follow:
 *
 * ...
 * .X.
 * ...
 *
 * Your starting position (startX, startY) = (0,0) so you start in the top left corner.
 *
 * The ending position is (goalX, goalY) = (1,2).
 *
 * The path is (0,0) -> (0,2) -> (1,2).
 *
 * It takes 2 moves to get to the goal.
 *
 * another (e.g)
 *
 * 3
 *
 * .X.
 * .X.
 * ...
 *
 * 0 0 0 2
 *
 */
public class CastleOnTheGrid {
	static int n;

	static int startY = 0;
	static int startX = 0;

	static int goalY = 0;
	static int goalX = 0;

	static YX goalYX;

	static char [][] globalGrid;

	static final char BLOCK = 'X';

	static int cnt = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		n = scanner.nextInt();

		globalGrid = new char[n][n];

		for (int y = 0; y < n; y++) {
			String line = scanner.next();
			for (int x = 0; x < n; x++) {
				globalGrid[y][x] = line.charAt(x);
			}
		}

		startY = scanner.nextInt();
		startX = scanner.nextInt();

		goalY = scanner.nextInt();
		goalX = scanner.nextInt();

		goalYX = new YX(goalY, goalX);

		System.out.println(solve(globalGrid, startY, startX, null));
		System.out.println(cnt);
	}

	enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT;

		int getAdd(Direction direction) {
			return this == direction ? 0 : 1;
		}
	}

	/**
	 * 무조건 끝까지 가는게 아니고..
	 *
	 * 한칸만 앞으로 가고, 나머지는 옆으로 가는것이 최선의 방법일 수도 있다..
	 *
	 * 즉, 한칸마다 다음방향을 탐색하되, 방향을 바꾼다면 카운트가 +1 이 되고, 방향을 바꾸지 않는다면 카운트가 +0 이 된다.
	 *
	 * 이렇게 하면 중복 탐색이 너무 많아진다..
	 *
	 * 한번 방문한 좌표도 수십번 재방문이 가능하다.
	 *
	 * 예를 들어, 다음과 같은 예제는 끝나질 않는다.
	 *
	 * 10
	 * .X..XX...X
	 * X.........
	 * .X.......X
	 * ..........
	 * ........X.
	 * .X...XXX..
	 * .....X..XX
	 * .....X.X..
	 * ..........
	 * .....X..XX
	 * 9 1 9 6
	 *
	 * @param grid
	 * @param y
	 * @param x
	 * @return
	 */
	static int solve(char [][] grid, int y, int x, Direction direction) {
		cnt++;

		if (isGoal(y, x)) {
			return 0;
		}

		int min = 987654321;

		grid[y][x] = BLOCK;

		// up
		if (isValid(grid, y-1, x)) {
			min = Math.min(min, solve(newGrid(grid), y-1, x, Direction.UP) + Direction.UP.getAdd(direction));
		}

		// down
		if (isValid(grid, y+1, x)) {
			min = Math.min(min, solve(newGrid(grid), y+1, x, Direction.DOWN) + Direction.DOWN.getAdd(direction));
		}

		// left
		if (isValid(grid, y, x-1)) {
			min = Math.min(min, solve(newGrid(grid), y, x-1, Direction.LEFT) + Direction.LEFT.getAdd(direction));
		}

		// right
		if (isValid(grid, y, x+1)) {
			min = Math.min(min, solve(newGrid(grid), y, x+1, Direction.RIGHT) + Direction.RIGHT.getAdd(direction));
		}

		return min;
	}


	static char[][] newGrid(char[][] grid) {
		char [][] newGrid = new char[grid.length][];

		for (int i = 0; i < grid.length; i++) {
			newGrid[i] = Arrays.copyOf(grid[i], grid[i].length);
		}

		return newGrid;
	}


	static boolean isValid(char[][] grid, int y, int x) {
		if (y < 0 || y >= n) {
			return false;
		} else if (x < 0 || x >= n) {
			return false;
		} else if (grid[y][x] == BLOCK) {
			return false;
		}

		return true;
	}

	static boolean isGoal(int y, int x) {
		return goalY == y && goalX == x;
	}

	static class YX {
		int y;
		int x;

		YX(int y, int x) {
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

			YX yx = (YX)o;

			if (y != yx.y) {
				return false;
			}
			return x == yx.x;
		}

		@Override
		public int hashCode() {
			int result = y;
			result = 31 * result + x;
			return result;
		}
	}
}
