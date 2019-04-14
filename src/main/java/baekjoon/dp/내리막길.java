package baekjoon.dp;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1520
 *
 * 두번째에 성공했음. 이유는 상하좌우 에서 상을 빼먹음
 *
 * 처음에는 좌표에 따라 dp 를 적용하는게 가능할까 의아했음..
 *
 * 왜냐하면, 경우의 수기 때문에 해당 좌표에서 무조건 1을 반환할텐데,,
 *
 * 이렇게 해도 정답이 나오나 의구심이 들었음..
 *
 * 하지만, 생각해보니 해당 좌표까지 왓다는 거는, 인제 해당 좌표에서의 경우의 수를 구하면 되기 때문에,
 *
 * 이렇게 dp 를 잡아도 무조건 정답이 나온다는 것을 알 수 있음
 */
public class 내리막길 {
	static int [][] map;
	static int [][] cache;
	static int MAX_Y;
	static int MAX_X;

	static int solve(int y, int x) {
		if (y >= MAX_Y || x >= MAX_X || y<0 || x<0) {
			return 0;
		}

		if (x == MAX_X-1 && y == MAX_Y-1) {
			return 1;
		}

		if (cache[y][x] != -1) {
			return cache[y][x];
		}

		int count = 0;

		// 상
		if (y > 0 && map[y][x] > map[y-1][x]) {
			count += solve(y-1, x);
		}

		// 하
		if (y < MAX_Y-1 && map[y][x] > map[y+1][x]) {
			count += solve(y+1, x);
		}

		// 좌
		if (x > 0 && map[y][x] > map[y][x-1]) {
			count += solve(y, x-1);
		}

		// 우
		if (x < MAX_X-1 && map[y][x] > map[y][x+1]) {
			count += solve(y, x+1);
		}

		return cache[y][x] = count;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		MAX_Y = scanner.nextInt();
		MAX_X = scanner.nextInt();

		map = new int[MAX_Y][MAX_X];
		cache = new int[MAX_Y+1][MAX_X+1];

		for (int i=0 ; i<MAX_Y ; i++) {
			for (int j=0 ; j<MAX_X ; j++) {
				map[i][j] = scanner.nextInt();
				cache[i][j] = -1;
			}
		}

		System.out.println(solve(0, 0));
	}
}
