package baekjoon.graph;

import java.util.Arrays;
import java.util.Scanner;

public class 플로이드 {
	static int INF = 987654321;


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();

		int [][] adj = new int[n][n];

		for (int [] a : adj) {
			Arrays.fill(a, INF);
		}

		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt()-1;
			int b = scanner.nextInt()-1;
			int c = scanner.nextInt();

			adj[a][b] = Math.min(adj[a][b], c);
		}

		Floyd floyd = new Floyd(n, adj);

		for (int [] a : floyd.floyd()) {
			for (int b : a) {
				System.out.print((b == INF ? 0 : b) + " ");
			}

			System.out.println();
		}
	}

	static class Floyd {
		int V;
		int [][] adj;

		public Floyd(int V, int[][] adj) {
			this.V = V;
			this.adj = adj;
		}

		int [][] floyd() {
			for (int i = 0; i < V; i++) {
				adj[i][i] = 0;
			}

			for (int k = 0; k < V; k++) {
				for (int i = 0; i < V; i++) {
					for (int j = 0; j < V; j++) {
						adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
					}
				}
			}

			return adj;
		}
	}
}
