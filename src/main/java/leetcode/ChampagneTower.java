package leetcode;

import java.util.*;

public class ChampagneTower {
	public double champagneTower(int poured, int query_row, int query_glass) {
		Map<Integer, Map<Integer, Double>> map = new HashMap<>();

		Deque<Glass> q = new LinkedList<>();
		q.addLast(new Glass(0, 0, poured));

		map.computeIfAbsent(0, t -> new HashMap<>()).put(0, (double)poured);

		while (q.isEmpty() == false) {
			int size = Integer.valueOf(q.size());

			for (int i = 0; i < size; i++) {
				Glass here = q.poll();

				if (here.row >= 100) {
					return map.getOrDefault(query_row, new HashMap<>()).getOrDefault(query_glass, 0D);
				}

				// 현재 컵에 담겨져 있는 샴페인의 양이 흘러 넘치는 경우라면, 다음 row 에 해당하는 잔에 샴페인을 흘려야 한다.
				if (here.champagne > 1) {
					double remain = here.champagne - 1D;
					int nextRow = here.row + 1;

					for (int nextCol = here.col; nextCol <= here.col+1; nextCol++) {
						double nextChampagne = remain / 2;

						if (map.get(nextRow) != null && map.get(nextRow).getOrDefault(nextCol, 0D) > 0) {
							nextChampagne += map.get(nextRow).get(nextCol);
							q.removeLast();
						}

 						q.addLast(new Glass(nextRow, nextCol, nextChampagne));
						map.computeIfAbsent(nextRow, t -> new HashMap<>()).put(nextCol, nextChampagne);
					}

					map.get(here.row).put(here.col, 1D);
				}
			}
		}

		if (!map.containsKey(query_row) || !map.get(query_row).containsKey(query_glass)) {
			return 0;
		}

		return map.get(query_row).get(query_glass);
	}

	static class Glass {
		int row;
		int col;
		double champagne;

		public Glass(int row, int col, double champagne) {
			this.row = row;
			this.col = col;
			this.champagne = champagne;
		}
	}

	public static void main(String[] args) {

		/**
		 * 1000000000
		 * 99
		 * 99
		 *
		 */
		int poured = 1000000000;
		int query_row = 99;
		int query_glass = 99;

		ChampagneTower champagneTower = new ChampagneTower();
		System.out.println(champagneTower.champagneTower(poured, query_row, query_glass));
	}
}
