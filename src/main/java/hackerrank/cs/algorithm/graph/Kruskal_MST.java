package hackerrank.cs.algorithm.graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 */
public class Kruskal_MST {
	static class Graph {
		int V, E;
		Edge edge[];

		Graph(int v, int e, Edge [] edge) {

			this.V = v;
			this.E = e;
			this.edge = edge;
		}

		int kruskalMST() {
			Edge [] result  = new Edge[V-1];

			Arrays.sort(edge);

			DisjoinSet disjoinSet = new DisjoinSet(V);

			int e = 0;
			int i = 0;

			// 모든 edge 를 순회하면서, cycle 이 없는 경우에만 result 에 포함시킨다.
			while (e < V - 1) {
				Edge next = edge[i++];

				int x = disjoinSet.find(next.from);
				int y = disjoinSet.find(next.to);

				if (x != y) {
					result[e++] = next;
					disjoinSet.union(x, y);
				}
			}

			int sum = 0;

			for (i = 0; i < V-1; i++) {
				sum += result[i].weight;
			}

			return sum;
		}
	}

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static class DisjoinSet {
		int [] parent;
		int [] rank;

		DisjoinSet(int n) {
			parent = new int[n];
			rank = new int[n];

			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		int find(int x) {
			if (parent[x] != x) {
				parent[x] = find(parent[x]);
			}

			return parent[x];
		}

		void union(int x, int y) {
			int xRoot = find(x);
			int yRoot = find(y);

			if (xRoot == yRoot) {
				return;
			}

			if (rank[xRoot] < rank[yRoot]) {
				parent[xRoot] = yRoot;
			} else if (rank[yRoot] < rank[xRoot]) {
				parent[yRoot] = xRoot;
			} else {
				parent[yRoot] = xRoot;
				rank[xRoot]++;
			}
		}
	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int v = scanner.nextInt();
		int e = scanner.nextInt();

		Edge [] edges = new Edge[e];

		for (int i = 0; i < e; i++) {
			int from = scanner.nextInt() - 1;
			int to = scanner.nextInt() - 1;
			int weight = scanner.nextInt();

			edges[i] = new Edge(from, to, weight);
		}

		Graph graph = new Graph(v, e, edges);

		System.out.println(graph.kruskalMST());
	}
}
