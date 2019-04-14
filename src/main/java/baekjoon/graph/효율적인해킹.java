package baekjoon.graph;

import java.util.*;

@SuppressWarnings("Duplicates")
public class 효율적인해킹 {
	static List<Integer>[] adj;
	static boolean [] visited;

	static int N;
	static int count;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		N = scanner.nextInt();
		int M = scanner.nextInt();

		adj = new List[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int A = scanner.nextInt() - 1;
			int B = scanner.nextInt() - 1;

			adj[B].add(A);
		}

		List<Integer> ret = new ArrayList<>();
		int max = 0;

		for (int i = 0; i < N; i++) {
			Arrays.fill(visited, false);
			count=0;
			dfs(i);

			if (max <= count) {
				if (max < count) {
					max = count;
					ret = new ArrayList<>();
				}

				ret.add(i+1);
			}
		}

		Collections.sort(ret);

		for (int v : ret) {
			System.out.print(v + " ");
		}

	}

	private static void dfs(int here) {

		visited[here] = true;
		count++;

		for (int i = 0; i < adj[here].size(); i++) {
			Integer there = adj[here].get(i);

			if (visited[there] == false) {
				dfs(there);
			}
		}

	}
}
