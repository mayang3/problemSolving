package leetcode;

import java.util.*;

public class NumberOfProvinces {
	public int findCircleNum(int[][] M) {
		int n = M.length;
		int count = 0;
		boolean [] visited = new boolean[n];

		for (int y = 0; y < n; y++) {
			if (visited[y] == false) {
				Queue<Integer> q = new LinkedList<>();
				q.add(y);

				while (q.isEmpty() == false) {
					int size = Integer.valueOf(q.size());

					for (int i = 0; i < size; i++) {
						int hereY = q.poll();

						for (int x = hereY; x < n; x++) {
							if (isValid(hereY, x, M, visited)) {
								visited[x] = true;
								q.add(x);
							}
						}
					}
				}

				count++;
			}
		}

		return count;
	}

	private boolean isValid(int y, int x, int[][] M, boolean[] visited) {
		if (y < 0 || y >= M.length || x < 0 || x >= M.length) {
			return false;
		} else if (visited[x]) {
			return false;
		}

		return M[y][x] == 1;
	}

}
