package hackerrank.cs.algorithm.implement;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * You will be given a square chess board with one queen and a number of obstacles placed on it.
 *
 * Determine how many squares the queen can attack.
 *
 * A queen is standing on a n * n chessboard. The chess board's rows are numbered from 1 to n, going from bottom to top.
 *
 * Its columns are numbered from 1 to n, going from left to right.
 *
 * Each square is referenced by a tuple, (r,c), describing the row, r, and column, c, where the square is located.
 *
 * The queen is standing at position (rq, cq).
 *
 * In a single move, she can attack any square in any of the eight directions (left, right, up, down, and the four diagonals).
 *
 * In the diagram below, the green circles denote all the cells the queen can attack from (4,4):
 *
 * [!image 1]
 *
 * There are obstacles on the chessboard, each preventing the queen from attacking any square beyond it on that path.
 *
 * For example, an obstacle at location (3,5) in the diagram above prevents the queen from attacking cells (3,5), (2,6) and (1,7)
 *
 * [!image 2]
 *
 *
 * Given the queen's position and the locations of all the obstacles,
 * find and print the number of squares the queen can attack from her position at (rq, cq).
 *
 * [Input format]
 *
 * The first line contains two space-separated integers n and k, the length of the board's sides and the number of obstacles.
 * (n is board sides, k is number of obstacles)
 *
 * The next line contains two space-separated integers rq and cq, the queen's row and column position.
 *
 * Each of the next k lines contains two space-separated integers r[i] and c[i], the row and column position of obstacle[i].
 *
 * [Constraint]
 *
 * 0 < n <= 10^5
 * 0 <= k <= 10^5
 *
 * A single cell may contain more than one obstacle.
 * There will never be an obstacle at the position where the queen is located.
 *
 * [Output Format]
 *
 * Print the number of squares that the queen can attack from position (rq, cq)
 *
 *
 * [Sample 1]
 * - Input
 * 4 0
 * 4 4
 *
 * - Output
 * 9
 *
 * [Sample 2]
 * - Input
 * 5 3
 * 4 3
 * 5 5
 * 4 2
 * 2 3
 *
 * - Output
 * 10
 *
 * [Sample 3]
 * - Input
 * 1 0
 * 1 1
 *
 * - Output
 * 0
 *
 *
 * @author baejunbeom
 */
public class QueensAttack2 {

	static class Position {
		int y;
		int x;

		Position(int y, int x) {
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

			Position position = (Position)o;

			if (y != position.y) {
				return false;
			}
			return x == position.x;
		}

		@Override
		public int hashCode() {
			int result = y;
			result = 31 * result + x;
			return result;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int k = scanner.nextInt();

		Position queenPos = new Position(scanner.nextInt() - 1, scanner.nextInt() - 1);

		Set<Position> obstacleSet = new HashSet<Position>();

		for (int i = 0; i < k; i++) {
			obstacleSet.add(new Position(scanner.nextInt() - 1, scanner.nextInt() - 1));
		}

		System.out.println(solve(n, queenPos, obstacleSet));
	}

	static int solve(int n, Position queenPos, Set<Position> obstacleSet) {
		int cnt = 0;
		// up
		cnt += calUpCount(n, queenPos, obstacleSet);

		// down
		cnt += calDownCount(n, queenPos, obstacleSet);

		// left
		cnt += calLeftCount(n, queenPos, obstacleSet);

		// right
		cnt += calRightCount(n, queenPos, obstacleSet);

		// up & left
		cnt += calUpAndLeft(n, queenPos, obstacleSet);

		// up & right
		cnt += calUpAndRight(n, queenPos, obstacleSet);

		// down & left
		cnt += calDownAndLeft(n, queenPos, obstacleSet);

		// down & right
		cnt += calDownAndRight(n, queenPos, obstacleSet);

		return cnt;
	}

	static boolean isValid(int n, Position cur) {
		int y = cur.y;
		int x = cur.x;

		if (y < 0 || y >= n) {
			return false;
		} else if (x < 0 || x >= n) {
			return false;
		}

		return true;
	}

	static int calDownAndRight(int n, Position queenPos, Set<Position> obstacleSet) {
		int cnt = 0;

		Position cur = new Position(queenPos.y-1, queenPos.x+1);

		while (!obstacleSet.contains(cur) && isValid(n, cur)) {
			cnt++;
			cur.y--;
			cur.x++;
		}

		return cnt;
	}

	private static int calDownAndLeft(int n, Position queenPos, Set<Position> obstacleSet) {
		int cnt = 0;

		Position cur = new Position(queenPos.y-1, queenPos.x-1);

		while (!obstacleSet.contains(cur) && isValid(n, cur)) {
			cnt++;
			cur.y--;
			cur.x--;
		}

		return cnt;
	}

	private static int calUpAndRight(int n, Position queenPos, Set<Position> obstacleSet) {
		int cnt = 0;

		Position cur = new Position(queenPos.y+1, queenPos.x+1);

		while (!obstacleSet.contains(cur) && isValid(n, cur)) {
			cnt++;
			cur.y++;
			cur.x++;
		}

		return cnt;
	}

	private static int calUpAndLeft(int n, Position queenPos, Set<Position> obstacleSet) {
		int cnt = 0;

		Position cur = new Position(queenPos.y+1, queenPos.x-1);

		while (!obstacleSet.contains(cur) && isValid(n, cur)) {
			cnt++;
			cur.y++;
			cur.x--;
		}

		return cnt;
	}

	private static int calRightCount(int n, Position queenPos, Set<Position> obstacleSet) {
		int cnt = 0;

		Position cur = new Position(queenPos.y, queenPos.x+1);

		while (!obstacleSet.contains(cur) && isValid(n, cur)) {
			cnt++;
			cur.x++;
		}

		return cnt;
	}

	private static int calLeftCount(int n, Position queenPos, Set<Position> obstacleSet) {
		int cnt = 0;

		Position cur = new Position(queenPos.y, queenPos.x-1);

		while (!obstacleSet.contains(cur) && isValid(n, cur)) {
			cnt++;
			cur.x--;
		}

		return cnt;
	}

	private static int calDownCount(int n, Position queenPos, Set<Position> obstacleSet) {
		int cnt = 0;

		Position cur = new Position(queenPos.y-1, queenPos.x);

		while (!obstacleSet.contains(cur) && isValid(n, cur)) {
			cnt++;
			cur.y--;
		}

		return cnt;
	}

	private static int calUpCount(int n, Position queenPos, Set<Position> obstacleSet) {
		int cnt = 0;

		Position cur = new Position(queenPos.y+1, queenPos.x);

		while (!obstacleSet.contains(cur) && isValid(n, cur)) {
			cnt++;
			cur.y++;
		}

		return cnt;
	}

}
