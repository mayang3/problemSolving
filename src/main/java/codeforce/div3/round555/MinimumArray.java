package codeforce.div3.round555;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author neo82
 */
public class MinimumArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int [] A = new int[n];

		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}

		TreeMap<Integer, Integer> map = new TreeMap<>();

		for (int i = 0; i < n; i++) {
			map.merge(scanner.nextInt(), 1, Integer::sum);
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {

			Integer min = map.firstKey();
			Integer max = map.ceilingKey(n - A[i]);

			if (max == null) {
				sb.append((A[i] + min) % n).append(" ");
				decreaseAndRemove(map, min);
			} else if (min == null) {
				sb.append((A[i] + max) % n).append(" ");
				decreaseAndRemove(map, max);
			} else if ((A[i] + min) % n <= (A[i] + max) % n) {
				sb.append((A[i] + min) % n).append(" ");
				decreaseAndRemove(map, min);
			} else {
				sb.append((A[i] + max) % n).append(" ");
				decreaseAndRemove(map, max);
			}
		}

		System.out.println(sb.toString());
	}

	static void decreaseAndRemove(Map<Integer, Integer> map, int key) {
		map.merge(key, -1, Integer::sum);

		if (map.get(key) <= 0) {
			map.remove(key);
		}
	}
}
