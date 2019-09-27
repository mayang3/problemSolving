package algospot;

import java.util.Scanner;
import java.util.TreeMap;

// https://algospot.com/judge/problem/read/MATCHORDER
@SuppressWarnings("ALL")
public class 출전순서정하기 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int c = scanner.nextInt();

		while (c-- > 0) {
			int n = scanner.nextInt();

			Integer [] russian = new Integer[n];

			for (int i = 0; i < n; i++) {
				russian[i] = scanner.nextInt();
			}

			TreeMap<Integer, Integer> treeMap = new TreeMap<>();

			for (int i = 0; i < n; i++) {
				treeMap.merge(scanner.nextInt(), 1, Integer::sum);
			}

			int cnt = 0;

			for (int r : russian) {
				if (treeMap.lastKey() < r) {
					decreaseAndRemove(treeMap, treeMap.firstKey());
				} else {
					cnt++;
					decreaseAndRemove(treeMap, treeMap.ceilingKey(r));
				}
			}

			System.out.println(cnt);
		}
	}

	private static void decreaseAndRemove(TreeMap<Integer, Integer> treeMap, int key) {
		treeMap.merge(key, -1, Integer::sum);

		if (treeMap.get(key) == 0) {
			treeMap.remove(key);
		}
	}
}
