package baekjoon.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 트리의부모찾기 {

	static List<Integer> [] graph;
	static boolean [] visited;
	static int [] parents;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		graph = new List[n+1];
		visited = new boolean[n+1];
		parents = new int[n+1];

		for (int i=0 ; i<n+1; i++) {
			graph[i] = new ArrayList();
		}

		for (int i = 0; i < n-1; i++) {
			connect(scanner.nextInt(), scanner.nextInt());
		}

		for (int i=1 ; i<n+1 ; i++) {
			if (visited[i] == false) {
				dfs(i);
			}
		}

		for (int i=2 ; i<parents.length ; i++) {
			System.out.println(parents[i]);
		}
	}

	static void dfs(int here) {
		visited[here]=true;

		for (int i=0 ; i<graph[here].size() ; i++) {
			int there = graph[here].get(i);

			if (visited[there] == false) {
				parents[there] = here;
				dfs(there);
			}
		}
	}

	static void connect(int a, int b) {
		graph[a].add(b);
		graph[b].add(a);
	}
}
