package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미로탐색 {

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String read() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(read());
		}

		long nextLong() {
			return Long.parseLong(read());
		}

		double nextDouble() {
			return Double.parseDouble(read());
		}

		String nextLine() {
			try {
				return br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return "";
		}
	}

	static int N;
	static int M;
	static boolean [][] maze;
	static boolean [][] visited;

	public static void main(String[] args) {
		FastReader fastReader = new FastReader();

		N = fastReader.nextInt();
		M = fastReader.nextInt();

		maze = new boolean[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String line = fastReader.nextLine();

			for (int j = 0; j < line.length(); j++) {
				String token = Character.toString(line.charAt(j));

				maze[i][j] = token.equals("1") ? true : false;
			}
		}

		visited[0][0] = true;
		bfs(new YX(0,0,1));
	}

	static void bfs(YX yx) {
		Queue<YX> q = new LinkedList<>();
		q.add(yx);


		while (q.isEmpty() == false) {
			YX here = q.poll();

			if (here.y == N-1 && here.x == M-1) {
				System.out.println(here.dist);
				break;
			}
			/**
			 * BFS 일 경우 여기서 visited 를 체크하면 안된다.
			 *
			 * 왜냐하면, BFS 특성상 here 에 직접 방문하지 않더라도 다음 노드에서 큐에 중복된 값이 쌓일수 있기 때문이다.
			 *
			 * 예를 들어, 다음과 같은 미로의 케이스를 살펴보자.
			 *
			 * 1 1 1 1
			 * 1 1 1 0
			 * 1 1 0 0
			 * 1 0 0 0
			 *
			 * y,x 의 인덱스가 0 부터 시작한다고 할때,
			 *
			 * (0,2) 번에서 (0,3) (1,2) 번의 노드를 큐에 넣을테고,
			 * (1,1) 번의 노드를 꺼내어 다시 탐색을 할텐데,
			 *
			 * (1,1) 번의 노드를 꺼냈을때, (1,2) 의 노드가 방문함으로 표시되어있지 않다면,
			 * 중복해서 큐에 넣게 된다.
			 *
			 */

//			visited[here.y][here.x] = true;

			// up
			if (isValid(here.y+1, here.x)) {
				visited[here.y+1][here.x] = true;
				q.add(here.moveToUp());
			}

			// down
			if (isValid(here.y-1, here.x)) {
				visited[here.y-1][here.x] = true;
				q.add(here.moveToDown());
			}

			// left
			if (isValid(here.y, here.x-1)) {
				visited[here.y][here.x-1] = true;
				q.add(here.moveToLeft());
			}

			// right
			if (isValid(here.y, here.x+1)) {
				visited[here.y][here.x+1] = true;
				q.add(here.moveToRight());
			}
		}

	}

	static boolean isValid(int y, int x) {
		if (y < 0 || y >= N) {
			return false;
		} else if (x < 0 || x >= M) {
			return false;
		} else if (visited[y][x]) {
			return false;
		}

		return maze[y][x] == true;
	}

	static class YX {
		int y;
		int x;
		int dist;

		YX(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}

		YX moveToUp() {
			return new YX(this.y+1, this.x, this.dist+1);
		}

		YX moveToDown() {
			return new YX(this.y-1, this.x, this.dist+1);
		}

		YX moveToLeft() {
			return new YX(this.y, this.x-1, this.dist+1);
		}

		YX moveToRight() {
			return new YX(this.y, this.x+1, this.dist+1);
		}
	}
}
