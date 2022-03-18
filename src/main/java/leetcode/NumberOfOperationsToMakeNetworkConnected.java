package leetcode;

import java.util.*;

public class NumberOfOperationsToMakeNetworkConnected {
	public int makeConnected(int n, int[][] connections) {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		UnionFind unionFind = new UnionFind(n);

		int remains = 0;

		for (int i = 0; i < connections.length; i++) {
			int a = connections[i][0];
			int b = connections[i][1];

			remains += unionFind.union(a, b);

			graph.computeIfAbsent(a, t -> new ArrayList<>()).add(b);
			graph.computeIfAbsent(b, t -> new ArrayList<>()).add(a);
		}

		int group = 0;
		boolean [] visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			if (visited[i] == false) {
				dfs(visited, graph, i);
				group++;
			}
		}

		return remains >= group -1 ? group - 1 : -1;
	}

	private void dfs(boolean[] visited, Map<Integer, List<Integer>> graph, int i) {
		visited[i] = true;

		if (graph.containsKey(i)) {
			for (int there : graph.get(i)) {
				if (visited[there] == false) {
					dfs(visited, graph, there);
				}
			}
		}
	}

	static class UnionFind {
		int [] rank;
		int [] parent;

		public UnionFind(int N) {
			this.rank = new int[N];
			this.parent = new int[N];

			for (int i = 0; i < N; i++) {
				parent[i] = i;
			}
		}

		int find(int x) {
			if (parent[x] != x) {
				parent[x] = find(parent[x]);
			}

			return parent[x];
		}

		int union(int u, int v) {
			int uRoot = find(u);
			int vRoot = find(v);

			if (uRoot == vRoot) {
				return 1;
			}

			if (this.rank[uRoot] > this.rank[vRoot]) {
				this.parent[vRoot] = uRoot;
			} else if (this.rank[uRoot] < this.rank[vRoot]) {
				this.parent[uRoot] = vRoot;
			} else {
				this.parent[vRoot] = uRoot;
				this.rank[uRoot]++;
			}

			return 0;
		}
	}


	public static void main(String[] args) {
		int n = 4;
		int [][] connections = {{0,1}, {0,2}, {1,2}};

		NumberOfOperationsToMakeNetworkConnected numberOfOperationsToMakeNetworkConnected = new NumberOfOperationsToMakeNetworkConnected();
		System.out.println(numberOfOperationsToMakeNetworkConnected.makeConnected(n, connections));
	}
}
