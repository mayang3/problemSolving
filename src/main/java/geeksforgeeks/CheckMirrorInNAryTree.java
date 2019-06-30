package geeksforgeeks;

import java.util.*;

/**
 * @author neo82
 */
public class CheckMirrorInNAryTree {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int t = scanner.nextInt();
		while (t-- > 0) {
			int n = scanner.nextInt();
			int e = scanner.nextInt();

			Map<Integer, List<Integer>> map1 = new HashMap<Integer, List<Integer>>();
			Map<Integer, List<Integer>> map2 = new HashMap<Integer, List<Integer>>();

			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2 * e; j += 2) {
					int u = scanner.nextInt();
					int v = scanner.nextInt();

					if (i == 0) {
						put(map1, u, v);
						put(map1, v, u);
					} else {
						put(map2, u, v);
						put(map2, v, u);
					}
				}
			}

			if (solve(map1, false).equals(solve(map2, true))) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}

	private static String solve(Map<Integer, List<Integer>> map, boolean reverse) {

		StringBuilder sb = new StringBuilder();
		sb.append("1").append(" ");

		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);

		Set<Integer> discovered = new HashSet<Integer>();
		discovered.add(1);

		while (q.isEmpty() == false) {
			int here = q.poll();

			if (map.containsKey(here)) {

				if (reverse) {
					for (int i = map.get(here).size()-1; i >= 0; i--) {
						discoverInBfs(map, sb, q, discovered, here, i);
					}
				} else {
					for (int i = 0; i < map.get(here).size(); i++) {
						discoverInBfs(map, sb, q, discovered, here, i);
					}
				}
			}
		}

		return sb.toString();
	}

	static void discoverInBfs(Map<Integer, List<Integer>> map, StringBuilder sb, Queue<Integer> q, Set<Integer> discovered, int here, int i) {
		int there = map.get(here).get(i);

		if (discovered.contains(there) == false) {
			discovered.add(there);
			q.add(there);
			sb.append(there).append(" ");
		}
	}

	static void put(Map<Integer, List<Integer>> map, int u, int v) {
		if (map.containsKey(u)) {
			map.get(u).add(v);
		} else {
			List<Integer> adj = new ArrayList<Integer>();
			adj.add(v);

			map.put(u, adj);
		}
	}
}
