package algospot;

import java.util.Arrays;
import java.util.Scanner;

public class BISHOPS {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int N = scanner.nextInt();

			char[][] board = new char[N][N];

			for (int y = 0; y < N; y++) {
				String s = scanner.next();

				for (int x = 0; x < N; x++) {
					board[y][x] = s.charAt(x);
				}
			}

			id = new int[2][8][8];
			adj = new boolean[64][64];

			System.out.println(placeBishops(board));
		}
	}

	// 대각선의 방향 : 왼쪽 아래로 내려가는 대각선, 오른쪽 아래로 내려가는 대각선
	static int[] dx = {-1, 1};
	static int[] dy = {1, 1};

	// id[dir][y][x] = dir 방향 대각선을 따라 인접한 빈 칸 묶음들 중 (y,x) 가 속한 묶음의 번호
	static int[][][] id;

	// 이분 그래프의 정보
	static int N, M;
	static boolean [][] adj;

	// 각 정점에 매칭된 상대 정점의 번호를 저장한다.
	static int [] aMatch;
	static int [] bMatch;
	// dfs 의 방문 여부
	static boolean [] visited;

	static int placeBishops(char[][] board) {
		// 각 묶음에 번호를 붙인다.
		for (int dir = 0; dir < 2; dir++) {
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					id[dir][y][x] = -1;
				}
			}
		}

		int[] count = {0, 0};

		for (int dir = 0; dir < 2; dir++) {
			for (int y = 0; y < board.length; y++) {
				for (int x = 0; x < board.length; x++) {
					if (board[y][x] == '.' && id[dir][y][x] == -1) {
						int cy = y, cx = x;

						while (0 <= cy && cy < board.length
							&& 0 <= cx && cx < board.length
							&& board[cy][cx] == '.') {
							id[dir][cy][cx] = count[dir];
							cy += dy[dir];
							cx += dx[dir];
						}
						count[dir]++;
					}
				}
			}
		}

		// 이분 그래프를 만든다.
		N = count[0];
		M = count[1];

		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board.length; x++) {
				if (board[y][x] == '.') {
					adj[id[0][y][x]][id[1][y][x]] = true;
				}
			}
		}

		return bipartiteMatch();
	}

	static boolean dfs(int a) {
		// visited 는 dfs 를 호출하는 bipartiteMatch 에서 각 루프마다 초기화 하기 때문에.. 이전에 매치된 정점에 다시 가더라도 visited = false 가 된다.
		// 무한루프는 방지되는것이.. 한 증가경로마다 방문되는 정점들은 true 로 설정되기 때문에 무한루프는 방지된다.
		if (visited[a]) {
			return false;
		}

		visited[a] = true;

		for (int b = 0; b < M; b++) {
			if (adj[a][b]) {
				// b 가 이미 매칭되어 있다면 bMatch[b] 에서부터 시작해 증가 경로를 찾는다.
				// 즉, 내가 현재 매칭되어 있는 정점과 매칭하기 위해서 이전에 매칭된 정점 a 를 다른 정점 b 와 매칭할 수 있는지를 확인하는 것이다.
				if (bMatch[b] == -1 || dfs(bMatch[b])) {
					// 증가 경로 발견!! a 와 b 를 매치 시킨다.
					aMatch[a] = b; // a 노드와 매치되는 노드는 b
					bMatch[b] = a; // b 노드와 매치되는 노드는 a
					return true;
				}
			}
		}

		return false;
	}

	// aMatch, bMatch 배열을 계산하고 최대 매칭을 크기를 반환한다.
	static int bipartiteMatch() {
		// 처음에는 어떤 정점도 연결되어 있지 않다.
		aMatch = new int[N];
		bMatch = new int[M];

		Arrays.fill(aMatch, -1);
		Arrays.fill(bMatch, -1);

		int size = 0;

		for (int start = 0; start < N; start++) {
			visited = new boolean[N];
			// DFS 를 이용해 start 에서 시작하는 증가 경로를 찾는다.
			if (dfs(start)) {
				size++;
			}
		}

		return size;
	}



}
