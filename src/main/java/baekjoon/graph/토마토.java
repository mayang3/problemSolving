package baekjoon.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 아이디어 :
 *
 * bfs 를 계속 돌리는 것이 아니고, 한 lv 단위 (일자단위) 로 recursive 하게 돌린다.
 *
 * 그러면 lv 이 곧 일자가 되고, 익힐 토마토가 더 이상 없다면 종료하게 된다.
 *
 */
public class 토마토 {
	static int M;
	static int N;
	static int [][] box;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		M = scanner.nextInt();
		N = scanner.nextInt();

		Queue<Pair> q = new LinkedList<>();

		box = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				box[i][j] = scanner.nextInt();

				// 시작점 입력
				if (box[i][j] == 1) {
					q.add(new Pair(i,j));
				}
			}
		}

		// 여러개의 시작점을 가지고 동시에 bfs 한다.
		int lv = bfs(q, 0);

		if (isAllOk()) {
			System.out.println(lv);
		} else {
			System.out.println(-1);
		}

	}

	static boolean isAllOk() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 0) {
					return false;
				}
			}
		}

		return true;
	}

	static class Pair {
		int y;
		int x;

		Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int bfs(Queue<Pair> q, int lv) {

		int size = new Integer(q.size());

		for (int i = 0; i < size; i++) {
			Pair poll = q.poll();

			// 위
			if (isValid(poll.y+1, poll.x)) {
				fill(poll.y+1, poll.x);
				q.add(new Pair(poll.y+1, poll.x));
			}

			// 아래
			if (isValid(poll.y-1, poll.x)) {
				fill(poll.y-1, poll.x);
				q.add(new Pair(poll.y-1, poll.x));
			}

			// 왼쪽
			if (isValid(poll.y, poll.x-1)) {
				fill(poll.y, poll.x-1);
				q.add(new Pair(poll.y, poll.x-1));
			}

			// 오른쪽
			if (isValid(poll.y, poll.x+1)) {
				fill(poll.y, poll.x+1);
				q.add(new Pair(poll.y, poll.x+1));
			}
		}

		if (q == null || q.isEmpty()) {
			return lv ;
		}

		return bfs(q, lv+1);
	}

	static void fill(int y, int x) {
		box[y][x] = 1;
	}

	static boolean isValid(int y, int x) {

		if (y < 0 || y >= N) {
			return false;
		} else if (x < 0 || x >= M) {
			return false;
		}

		return box[y][x] == 0;
	}

}
