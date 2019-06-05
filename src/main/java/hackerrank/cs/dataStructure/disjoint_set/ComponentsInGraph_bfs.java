package hackerrank.cs.dataStructure.disjoint_set;

import java.util.*;

/**
 * @author neo82
 */
@SuppressWarnings("Duplicates")
public class ComponentsInGraph_bfs {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		List<Integer> [] adj = new List[n * 2];

		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}

		Set<Integer> nodeSet = new HashSet<>();

		for (int i = 0; i < n; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;

			adj[a].add(b);
			adj[b].add(a);

			nodeSet.add(a);
			nodeSet.add(b);
		}


		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		boolean [] visited = new boolean[n * 2];

		for (int start = 0; start < adj.length; start++) {
			if (nodeSet.contains(start) && visited[start] == false) {
				int connected = bfs(adj, visited, start);

				min = Math.min(min, connected);
				max = Math.max(max, connected);
			}
		}

		System.out.printf("%d %d", min, max);

	}

	static int bfs(List<Integer>[] adj, boolean[] visited, int start) {

		Queue<Integer> q = new LinkedList<>();
		q.add(start);

		visited[start] = true;

		int cnt = 1;

		while (q.isEmpty() == false) {
			int here = q.poll();

			for (int i = 0; i < adj[here].size(); i++) {
				int there = adj[here].get(i);

				if (visited[there] == false) {
					visited[there] = true;
					q.add(there);
					cnt++;
				}
			}
		}

		return cnt;
	}

}
