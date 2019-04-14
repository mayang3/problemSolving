package hackerrank.cs.dataStructure.queue;

import java.util.LinkedList;
import java.util.Queue;
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
public class CastleOnTheGrid2 {
	static int n;
	static int [][] grid;
	static boolean [][] visited;

	static Queue<Point> Q = new LinkedList<Point>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		n = scanner.nextInt();

		grid = new int[n][n];
		visited = new boolean[n][n];

		for (int y = 0; y < n; y++) {
			String line = scanner.next();
			for (int x = 0; x < n; x++) {
				if (line.charAt(x) == '.') {
					grid[y][x] = Integer.MAX_VALUE;
				} else {
					grid[y][x] = -1;
				}
			}
		}

		int startY = scanner.nextInt();
		int startX = scanner.nextInt();

		int goalY = scanner.nextInt();
		int goalX = scanner.nextInt();

		solve(startY, startX);

		System.out.println(grid[goalY][goalX]);

	}

	/**
	 * 기본 컨셉은 방문 좌표를 기준으로, 위, 아래, 왼쪽, 오른쪽 네방향에 있는 라인들을 전부 다음 방문 좌표로 입력하는 것이다.
	 * 
	 * 그리고, 다음 방문 좌표로 넣어둘때, 해당 라인들의 최소 방문수를 grid[][] 배열에 기록해둔다.
	 * 
	 * "즉, grid 배열은 항상 최소의 방문횟수를 나타내는 좌표값을 가지고 있다."
	 * 
	 * 마치, 최단거리 탐색에서 최소비용이 계속 업데이트 되는 느낌.. 하지만 노드가 아닌 배열상에서의 업데이트 라고나 할까..
	 * 
	 * 
	 * @param startY
	 * @param startX
	 */
	static void solve(int startY, int startX) {
		// 시작점 초기화
		grid[startY][startX] = 0;
		Q.add(new Point(startY, startX));

		while (Q.isEmpty() == false) {
			Point poll = Q.poll();

			int y = poll.y;
			int x = poll.x;
			int step = grid[y][x];

			// 방문했던 적이 있다면 더 이상 진행할 필요 없다.
			// 아래 loop 에서 visited 를 거른다 하더라도 , 방문한 좌표가 들어갈 수 있음.
			// 왜냐하면, 방문은 한칸씩 이루어지기 때문에..

			// 예를 들어, n=3 인 상황에서 (0,0) 을 진행한다고 가정해보면,
			// queue 에 (0,1),(0,2) 가 들어가게 된다.
			// 그리고, (0,1) 을 다시 꺼내서 방문할때, (0,2) 는 아직 방문하기 전인 상태이므로, queue 에 또 다시 들어가게 된다.

			// 그러면 현재 (0,2) 가 queue 에 두개가 남아있다.

			// 이 때, 아래 조건을 걸어주면 첫번째 (0,2) 를 실행하고 두번째는 pass 하도록 할 수 있다.
			if (visited[y][x]) {
				continue;
			}

			visited[y][x] = true;

			// up
			// 위로 한칸씩 증가하면서, 장애물이 없고, 방문한적이 없는 좌표만 방문한다.
			for (int i = y-1; i >= 0 && grid[i][x] != -1 && visited[i][x] == false ; i--) {
				addStep(i, x, step);
				Q.add(new Point(i, x));
			}

			// down
			for (int i = y+1; i < n && grid[i][x] != -1 && visited[i][x] == false ; i++) {
				addStep(i, x, step);
				Q.add(new Point(i, x));
			}


			// left
			for (int i = x-1; i >= 0 && grid[y][i] != -1 && visited[y][i] == false ; i--) {
				addStep(y, i, step);
				Q.add(new Point(y, i));
			}

			// right
			for (int i = x+1; i < n && grid[y][i] != -1 && visited[y][i] == false ; i++) {
				addStep(y, i, step);
				Q.add(new Point(y, i));
			}
		}
	}

	static void addStep(int y, int x, int step) {
		if (grid[y][x] > step + 1) {
			grid[y][x] = step + 1;
		}
	}

	static class Point {
		int y;
		int x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
