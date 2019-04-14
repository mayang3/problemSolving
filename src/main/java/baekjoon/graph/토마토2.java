package baekjoon.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * layer 의 개념이 들어갔다.
 *
 * O(N^3) 이지만 N,M,H 모두 100 이므로 충분하다.
 */
public class 토마토2 {
	static List<int [][]> boxes = new LinkedList<>();
	static int N;
	static int M;
	static int H;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		M = scanner.nextInt();
		N = scanner.nextInt();
		H = scanner.nextInt();

		Queue<Pair> q = new LinkedList<>();

		for (int i = 0; i < H; i++) {
			int [][] box = new int[N][M];

			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					box[j][k] = scanner.nextInt();

					if (box[j][k] == 1) {
						q.add(new Pair(i, j, k));
					}
				}
			}

			boxes.add(box);
		}

		int lv = bfs(q);

		if (isAllOk()) {
			System.out.println(lv);
		} else {
			System.out.println(-1);
		}
	}

	private static boolean isAllOk() {
		for (int [][] box : boxes) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (box[i][j] == 0) {
						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * 기존 토마토1의 Recursive 방식으로 인한, Stack 깊이 때문에 RuntimeException 1회 발생.
	 * bfs 로 바꾼다음에는 발생하지 않음..
	 *
	 * @param q
	 * @return
	 */
	private static int bfs(Queue<Pair> q) {
		int lv = 0;

		while (q.isEmpty() == false) {

			int size = new Integer(q.size());

			for (int i = 0; i < size; i++) {
				Pair poll = q.poll();

				// 위층
				checkAndFill(q, poll.floor - 1, poll.y, poll.x);

				// 아래층
				checkAndFill(q, poll.floor + 1, poll.y, poll.x);

				// 같은 층 위
				checkAndFill(q, poll.floor, poll.y - 1, poll.x);

				// 같은 층 아래
				checkAndFill(q, poll.floor, poll.y + 1, poll.x);

				// 같은 층 왼쪽
				checkAndFill(q, poll.floor, poll.y, poll.x - 1);

				// 같은 층 오른쪽
				checkAndFill(q, poll.floor, poll.y, poll.x + 1);

			}

			if (q == null || q.isEmpty()) {
				break;
			}

			lv++;
		}

		return lv;
	}

	private static void checkAndFill(Queue<Pair> q, int floor, int y, int x) {
		if (isValid(floor, y, x)) {
			fill(floor, y, x);
			q.add(new Pair(floor, y, x));
		}
	}

	private static void fill(int floor, int y, int x) {
		boxes.get(floor)[y][x] = 1;
	}

	private static boolean isValid(int floor, int y, int x) {
		if (floor < 0 || floor >= H) {
			return false;
		} else if (y < 0 || y >= N) {
			return false;
		} else if (x < 0 || x >= M) {
			return false;
		}

		return boxes.get(floor)[y][x] == 0;
	}

	static class Pair {
		int floor;
		int y;
		int x;

		Pair(int floor, int y, int x) {
			this.floor = floor;
			this.y = y;
			this.x = x;
		}
	}
}
