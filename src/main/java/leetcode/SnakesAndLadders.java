package leetcode;

import java.util.*;

public class SnakesAndLadders {
	public int snakesAndLadders(int[][] board) {
		int len = board.length;

		List<Integer> flatten = new ArrayList<>();
		flatten.add(-1); // optimize index

		for (int y = len - 1; y >= 0 ; y--) {
			if ((len- 1 - y) % 2 == 0) {
				for (int x = 0; x < len; x++) {
					flatten.add(board[y][x]);
				}
			} else {
				for (int x = len-1; x >= 0; x--) {
					flatten.add(board[y][x]);
				}
			}
		}

		boolean [] visited = new boolean[flatten.size()];

		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		visited[0] = visited[1] = true;

		int count = 0;

		while (q.isEmpty() == false) {

			int size = Integer.valueOf(q.size());

			for (int i = 0; i < size; i++) {
				int here = q.poll();

				if (here == flatten.size() - 1) {
					return count;
				}

				for (int dice = 1; dice <= 6 ; dice++) {
					int next = here + dice;

					// when we meet snake or ladder
					if (0 < next && next < flatten.size() && flatten.get(next) > 0) {
						next = flatten.get(next);
					}

					if (0 < next && next < flatten.size() && visited[next] == false) {
						q.add(next);
						visited[next] = true;
					}
				}
			}

			count++;
		}

		return -1;
	}

	public static void main(String[] args) {
		int [][] board = {
							{-1,-1,-1,-1,-1,-1},
							{-1,-1,-1,-1,-1,-1},
							{-1,-1,-1,-1,-1,-1},
							{-1,35,-1,-1,13,-1},
							{-1,-1,-1,-1,-1,-1},
							{-1,15,-1,-1,-1,-1}};

		SnakesAndLadders snakesAndLadders = new SnakesAndLadders();
		System.out.println(snakesAndLadders.snakesAndLadders(board));
	}
}
