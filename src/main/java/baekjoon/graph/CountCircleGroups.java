package baekjoon.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 첫번째 시도 DFS. 이 로직의 시간복잡도는 O(N^2) 이므로, 아래 로직은 2500만개의 vertex 를 방문하게 된다.
 *
 * 이렇게는 무리일듯하다..
 */
public class CountCircleGroups {
	static final int MAX_N = 5000;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while(T-- > 0) {
			int N = scanner.nextInt();

			boolean [][] area = new boolean[MAX_N][MAX_N];

			for (int i = 0; i < N; i++) {
				int Y = scanner.nextInt();
				int X = scanner.nextInt();
				int R = scanner.nextInt();

				fill(area, Y, X, R);
			}

			boolean [][] visited = new boolean[MAX_N][MAX_N];

			int count = 0;

			for (int i = 0; i < MAX_N; i++) {
				for (int j = 0; j < MAX_N; j++) {
					if (area[i][j] && visited[i][j] == false) {
						count++;
						bfs(area, visited, i, j);
					} else {
						visited[i][j] = true;
					}
				}
			}

			System.out.println(count);
		}
	}

	static void bfs(boolean[][] area, boolean[][] visited, int startY, int startX) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(startY,startX));

		visited[startY][startX] = true;

		while (q.isEmpty() == false) {
			Pair pair = q.poll();

			int y = pair.y;
			int x = pair.x;

			putToQIfValid(area, visited, y, x);
		}
	}

	static void putToQIfValid(boolean[][] area, boolean[][] visited, int y, int x) {
		if (y < 0 || y >= area.length) {
			return;
		} else if (x < 0 || x >= area.length) {
			return;
		} else if (area[y][x] == false) {
			return;
		} else if (visited[y][x]) {
			return;
		}
	}

	static class Pair {
		int y;
		int x;

		Pair (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void dfs(boolean[][] area, boolean[][] visited, int y, int x) {
		// 이미 방문햇거나 유효한 정점이 아니라면 pass
		if (visited[y][x] || area[y][x] == false) {
			return;
		}

		visited[y][x] = true;

		// 위
		moveIfValid(area, visited, y-1, x);
		// 아래
		moveIfValid(area, visited, y+1, x);
		// 왼쪽
		moveIfValid(area, visited, y, x-1);
		// 오른쪽
		moveIfValid(area, visited, y, x+1);

	}

	static void moveIfValid(boolean[][] area, boolean[][] visited, int y, int x) {
		if (y < 0 || y >= area.length) {
			return;
		} else if (x < 0 || x >= area.length) {
			return;
		} else if (area[y][x] == false) {
			return;
		}

		dfs(area, visited, y, x);
	}

	static void fill(boolean[][] area, int y, int x, int r) {
		area[y][x] = true;

		for (int i = 1; i <= r ; i++) {
			// 위
			fillIfValid(area, y-1, x);
			// 아래
			fillIfValid(area, y+1, x);
			// 왼쪽
			fillIfValid(area, y, x-1);
			// 오른쪽
			fillIfValid(area, y, x+1);
		}
	}

	static void fillIfValid(boolean[][] area, int y, int x) {
		if (y < 0 || y >= area.length) {
			return;
		} else if (x < 0 || x >= area.length) {
			return;
		}

		area[y][x] = true;
	}
}
