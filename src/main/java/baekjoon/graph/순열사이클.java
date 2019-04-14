package baekjoon.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 순열사이클 {

	static List<Integer>[] adj;
	static boolean [] visited;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int N = scanner.nextInt();

			adj = new List[N+1];
			visited = new boolean[N+1];

			for (int i = 1; i <= N; i++) {
				adj[i] = new ArrayList<>();
			}

			for (int i = 1; i <= N; i++) {
				adj[i].add(scanner.nextInt());
			}

			int count = 0;

			for (int i = 1; i <= N; i++) {
				if (visited[i] == false) {
					count++;
					dfs(i);
				}
			}

			System.out.println(count);
		}
	}

	static void dfs(int here) {
		visited[here] = true;

		for (int i = 0; i < adj[here].size(); i++) {
			int there = adj[here].get(i);

			if (visited[there] == false) {
				dfs(there);
			}
		}
	}
}
