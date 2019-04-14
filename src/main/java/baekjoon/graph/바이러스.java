package baekjoon.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class 바이러스 {
	static List<Integer> [] adj;
	static boolean [] visited;
	static int count = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int K = scanner.nextInt();

		adj = new List[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			adj[i] = new LinkedList<>();
		}

		for (int i = 0; i < K; i++) {
			int u = scanner.nextInt()-1;
			int v = scanner.nextInt()-1;

			adj[u].add(v);
			adj[v].add(u);
		}

		dfs(0);
		System.out.println(count-1);
	}

	private static void dfs(int here) {
		visited[here] = true;
		count++;

		for (int i = 0; i < adj[here].size(); i++) {
			int there = adj[here].get(i);

			if (visited[there] == false) {
				dfs(there);
			}
		}
	}
}
