package baekjoon.graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 쉬워보였는데 6회 try
 *
 * 이유 1. dp 적용안함. -> 아래 로직에서 dp 빼면 timeout 남. TO-DO) 명확한 원인 찾자!!
 * => 왜냐하면 같은 점에서의 경우의 수가 여러번 계산될 수 있기 때문이다.
 *
 * 예를 들어 다음과 같은 입력이 있다고 하자.
 *
 * 4
 * 2 3 3 1
 * 1 2 1 3
 * 1 2 3 1
 * 3 1 1 0
 *
 * 그러면, 인데스 시작점을 0이라고 할때,  (0,1) -> (3,1) 로 가는 경우가 있다고 치면, 이때 (3,1) 에서부터의 경우의 수가 계산되었지만,
 * (1,1) 에서 다시 출발한다고 했을때, (1,1) -> (3,1) 로 가는 경우가 있다.
 *
 * 이유 2. dp 의 범위 dp의 맨끝칸에서 숫자 9만큼 점프뛸수 있음
 * 이유 3. 0의 처리. 맨 오른쪽 아래 도달하기 전에 0을 만난 경우의 경로는 무조건 0이다.
 * 이유 4. 자료형, 결과값이 2^63 이 될 수 있음 -> long 형 필요
 *
 */
public class 점프 {
	static int [][] board;
	static long [][] dp;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		board = new int[N][N];
		dp = new long[N+10][N+10];

		for (long [] sub : dp) {
			Arrays.fill(sub, -1);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = scanner.nextInt();
			}
		}

		System.out.println(dfs(0,0));
	}

	static long dfs(int y, int x) {

		if (y == board.length-1 && x == board.length-1) {
			return 1;
		} else if (y < 0 || y >= board.length) {
			return 0;
		} else if (x < 0 || x >= board.length) {
			return 0;
		}

		if (dp[y][x] != -1) {
			return dp[y][x];
		}

		int offSet = board[y][x];

		if (offSet == 0) {
			return dp[y][x] = 0;
		}

		long count = 0;

		// 오른쪽
		if (isValid(y, x+offSet)) {
			count += dfs(y, x+offSet);
		}

		// 아래
		if (isValid(y+offSet, x)) {
			count += dfs(y+offSet, x);
		}

		return dp[y][x] = count;
	}

	static boolean isValid(int y, int x) {
		if (y < 0 || y >= board.length) {
			return false;
		} else if (x < 0 || x >= board.length) {
			return false;
		}

		return true;
	}
}
