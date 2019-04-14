package baekjoon.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class 줄세우기 {
	static List<Integer>[] adj;
	static boolean [] visited;
	static Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int M = scanner.nextInt();

		adj = new List[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			adj[i] = new LinkedList<>();
		}

		for (int i = 0; i < M; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();

			adj[--u].add(--v);
		}

		for (int here = 0; here < N; here++) {
			if (visited[here] == false) {
				dfs(here);
			}
		}

		while (stack.empty() == false) {
			System.out.print((stack.pop() + 1) + " ");
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

		stack.add(here);
	}
}
