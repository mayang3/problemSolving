package baekjoon.graph;

import java.util.*;

/**
 * dfs 는 재귀호출에서 실제 그 정점을 방문했을때 방문여부가 true 이고,
 * bfs 는 방문 전에 미리 한번 볼때 방문여부가 true 이다.
 * @author baejunbeom
 */
public class DfsAndBfs {
	static List<Integer>[] adj;
	static boolean [] visited;

	static void dfs(int start, List<Integer> ret) {
		visited[start] = true;
		ret.add(start+1);

		for (int i=0 ; i<adj[start].size() ; i++) {
			int there = adj[start].get(i);

			if (visited[there] == false) {
				dfs(there, ret);
			}
		}
	}

	static void bfs(int start, List<Integer> ret) {

		Queue<Integer> q = new LinkedList<>();
		q.add(start);

		visited[start] = true;
		ret.add(start+1);

		while (!q.isEmpty()) {
			int here = q.poll();

			for (int i=0 ; i<adj[here].size() ; i++) {
				int there = adj[here].get(i);

				if (!visited[there]) {
					q.add(there);
					visited[there] = true;
					ret.add(there+1);
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int S = scanner.nextInt();

		adj = new List[N];

		for (int i=0 ; i<adj.length ; i++) {
			adj[i] = new ArrayList<>();
		}

		visited = new boolean[N];

		while (M-- > 0) {
			int from = scanner.nextInt() - 1;
			int to = scanner.nextInt() - 1;

			adj[from].add(to);
			adj[to].add(from);
		}

		for (int i=0 ; i<adj.length ; i++) {
			Collections.sort(adj[i]);
		}

		List<Integer> dfsList = new ArrayList<>();
		List<Integer> bfsList = new ArrayList<>();


		dfs(S - 1, dfsList);
		Arrays.fill(visited, false);
		bfs(S - 1, bfsList);

		for (int i=0 ; i<dfsList.size() ; i++) {
			System.out.print(dfsList.get(i));
			if (i<dfsList.size() -1) {
				System.out.print(" ");
			}
		}

		System.out.println();

		for (int i=0 ; i<bfsList.size() ; i++) {
			System.out.print(bfsList.get(i));
			if (i<bfsList.size() -1) {
				System.out.print(" ");
			}
		}
	}
}
