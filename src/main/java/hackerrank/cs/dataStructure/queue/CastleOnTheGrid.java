package hackerrank.cs.dataStructure.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author neo82
 */
@SuppressWarnings("Duplicates")
public class CastleOnTheGrid {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		String [] grid = new String[n];

		for (int i = 0; i < n; i++) {
			grid[i] = scanner.next();
		}

		int startX = scanner.nextInt(); // vertical
		int startY = scanner.nextInt(); // horizontal
		int goalX = scanner.nextInt();
		int goalY = scanner.nextInt();

		System.out.println(minimumMoves(grid, startX, startY, goalX, goalY));
	}

	private static final char BLOCKED = 'X';

	static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
		int n = grid.length;

		boolean [][] discovered = new boolean[n][n];

		Queue<XY> q = new LinkedList<>();
		q.add(new XY(startX, startY, 0));

		discovered[startX][startY] = true;

		while (q.isEmpty() == false) {
			XY xy = q.poll();

			int x = xy.x;
			int y = xy.y;
			int path = xy.path;

			if (x == goalX && y == goalY) {
				return xy.path;
			}

			// top
			for (int nextX = x-1; nextX >= 0 && grid[nextX].charAt(y) != BLOCKED; nextX--) {
				if (discovered[nextX][y] == false) {
					q.add(new XY(nextX, y, path + 1));
					discovered[nextX][y] = true;
				}
			}

			// bottom
			for (int nextX = x+1; nextX < n && grid[nextX].charAt(y) != BLOCKED; nextX++) {
				if (discovered[nextX][y] == false) {
					q.add(new XY(nextX, y, path + 1));
					discovered[nextX][y] = true;
				}
			}

			// left
			for (int nextY = y-1; nextY >= 0 && grid[x].charAt(nextY) != BLOCKED; nextY--) {
				if (discovered[x][nextY] == false) {
					q.add(new XY(x, nextY, path + 1));
					discovered[x][nextY] = true;
				}
			}

			// right
			for (int nextY = y+1; nextY < n && grid[x].charAt(nextY) != BLOCKED; nextY++) {
				if (discovered[x][nextY] == false) {
					q.add(new XY(x, nextY, path + 1));
					discovered[x][nextY] = true;
				}
			}
		}

		return -1;
	}

	static class XY {
		int x;
		int y;
		int path;

		XY(int x, int y, int path) {
			this.x = x;
			this.y = y;
			this.path = path;
		}
	}
}
