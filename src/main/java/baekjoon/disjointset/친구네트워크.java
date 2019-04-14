package baekjoon.disjointset;

import java.util.Scanner;

public class 친구네트워크 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int F = scanner.nextInt();

			DisjointSet ds = new DisjointSet(100001);

			for (int i = 0; i < F; i++) {
				String f1 = scanner.next();
				String f2 = scanner.next();
			}
		}
	}


	static class DisjointSet {
		int [] parent;
		int [] rank;

		DisjointSet(int n) {
			parent = new int[n];

			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		int find(int x) {
			if (parent[x] == x) {
				return x;
			}

			return parent[x] = find(parent[x]);
		}

		void union(int x, int y) {
			x = find(x);
			y = find(y);

			if (x == y) {
				return;
			}

			if (rank[x] < rank[y]) {
				rank[x] = y;
			} else if (rank[x] > rank[y]) {
				rank[y] = x;
			} else {
				rank[y] = x;
				rank[x]++;
			}
		}
	}
}
