package baekjoon.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 단지번호붙이기 {
	static boolean [][] matrix;
	static boolean [][] visited;
	static int N;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		N = scanner.nextInt();

		matrix = new boolean[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String line = scanner.next();

			for (int j = 0; j < line.length(); j++) {
				char c = line.charAt(j);

				if (c == '0') {
					matrix[i][j] = false;
				} else {
					matrix[i][j] = true;
				}
			}
		}

		List<Integer> order = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == false) {
					int cnt = dfs(i, j);
					if (cnt >= 1) { // 1이 포함되어야 하는것을 모르고 최초 실패, 수정 후 바로 accept..
						order.add(cnt);
					}
				}
			}
		}

		Collections.sort(order);

		System.out.println(order.size());

		for (int v : order) {
			System.out.println(v);
		}
	}

	static int dfs(int y, int x) {
		visited[y][x] = true;

		if (matrix[y][x] == false) {
			return 0;
		}

		int count = 1;

		// up
		if (isValid(y-1,x)) {
			count += dfs(y-1, x);
		}

		// bottom
		if (isValid(y+1,x)) {
			count += dfs(y+1, x);
		}

		// left
		if (isValid(y, x-1)) {
			count += dfs(y, x-1);
		}

		// right
		if (isValid(y, x+1)) {
			count += dfs(y, x+1);
		}

		return count;
	}

	private static boolean isValid(int y, int x) {
		if (y < 0 || y >= N) {
			return false;
		} else if (x < 0 || x >= N) {
			return false;
		} else if (matrix[y][x] == false) {
			return false;
		}

		return visited[y][x] == false;
	}
}
