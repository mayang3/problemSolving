package hackerrank.cs.algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author baejunbeom
 *
 */
public class RoadsAndLibraries {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int q = scanner.nextInt();

		while (q-- > 0) {
			// vertex
			int n = scanner.nextInt();
			// edge
			int m = scanner.nextInt();

			int costLibrary = scanner.nextInt();
			int costRoad = scanner.nextInt();

			List<Integer> [] adj = new List[n];

			for (int i = 0; i < n; i++) {
				adj[i] = new ArrayList<Integer>();
			}


			for (int i = 0; i < m; i++) {
				int u = scanner.nextInt() - 1;
				int v = scanner.nextInt() - 1;

				adj[u].add(v);
				adj[v].add(u);
			}

			boolean [] visited = new boolean[n];
			int countOfGraph = 0;

			for (int here = 0; here < n; here++) {
				if (visited[here] == false) {
					countOfGraph++;
					dfs(adj, visited, here);
				}
			}

			/**
			 * costRoad >= costLibrary 인 경우는 무조건 각 지역에 도서관을 짓는게 이득이다.
			 * costRoad < costLibrary 인 경우에는 각 지역에 도서관을 하나씩 짓고, 나머지는 도로를 연결하는 것이 이득이다.
			 */

			if (costRoad >= costLibrary) {
				System.out.println((long)n * (long)costLibrary);
			} else {
				long ret = (((long)countOfGraph * (long)costLibrary)) + (((long)n - (long)countOfGraph) * (long)costRoad);
				System.out.println(ret);
			}
		}
	}


	static void dfs(List<Integer>[] adj, boolean[] visited, int here) {
		visited[here] = true;

		for (int i = 0; i < adj[here].size(); i++) {
			int there = adj[here].get(i);

			if (visited[there] == false) {
				dfs(adj, visited, there);
			}
		}
	}

}
