package baekjoon.backtracking;

import java.util.Scanner;

/**
 * 유명한 n-Queen 문제.
 *
 * 첫번째 포인트) n * n 사이즈의 체스판 위에 n 개의 퀸이 놓여져야 하므로, 최소 한개의 열에 하나의 퀸이 놓여져야 한다.
 * 두번째 포인트) 각 대각선에 놓여져 있는지를 판단할 때, 각 대각선의 y,x 값의 합이 같은 부분이 있음을 이용한다.
 * http://blockdmask.tistory.com/181 설명 참조.
 * 세번째 포인트) 백트래킹 -> 다음 경우를 시도해보고 다시 아니라면 복원시킨다.
 *
 */
public class NQueen {
	static int n;
	static int count;
	static boolean [] c1;
	// 오른쪽 위를 향하는 대각선
	static boolean [] d1;
	// 왼쪽 아래를 향하는 대각선. 배열의 인덱스에는 음수가 없으므로, (n-1)+(y-x) 로 구분
	static boolean [] d2;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		n = scanner.nextInt();
		// 같은 행에 이미 퀸이 놓여져 있는지를 체크한다. (x방향)
		c1 = new boolean[n];
		// n * n 배열에서, y+x 좌표의 합으로 접근할 수 있는 최대 인덱스의 값은, y(n-1) + x(n-1) = (n-1) * 2 이다.
		// 그런데, 배열의 길이는 최대 인덱스의 값보다 1 커야 하므로 2n-2+1 = 2n-1 이 된다.
		d1 = new boolean[2 * n - 1];
		d2 = new boolean[2 * n - 1];

		solve(0);

		System.out.println(count);
	}

	static void solve(int y) {
		// 모든 행을 검사했다면 종료
		if (y >= n) {
			count++;
			return;
		}

		for (int x = 0; x < n; x++) {
			// 1. 같은 column 퀸이 놓여있는지를 체크
			// 2. 하나의 row 에 하나의 queen 이 무조건 위치하게 되므로 row 방향 체크는 필요 없다.
			if (c1[x]) {
				continue;
			}

			// 2. 대각선에 놓여져 있는 경우
			if (d1[y+x] || d2[n-1+(y-x)]) {
				continue;
			}

			c1[x] = d1[y+x] = d2[n-1+(y-x)] = true;
			solve(y+1);
			c1[x] = d1[y+x] = d2[n-1+(y-x)] = false;
		}
	}
}
