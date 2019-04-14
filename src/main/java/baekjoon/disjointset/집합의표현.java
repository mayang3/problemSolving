package baekjoon.disjointset;

import java.util.Scanner;

/**
 * 계속 fail 나다가 swap 하는 부분을 바꾸니 됐음..
 *
 * [swap 이 책에 내용이 없는데, 구현이 잘못된듯..!]
 *
 * geeksforgeeks 의 로직을 사용하자..!!
 */
public class 집합의표현 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();

		DisjointSet disjointSet = new DisjointSet(n);

		for (int i = 0; i < m; i++) {
			int cmd = scanner.nextInt();
			int a = scanner.nextInt();
			int b = scanner.nextInt();

			if (cmd == 0) {
				disjointSet.union(a, b);
			} else if (cmd == 1) {
				if (disjointSet.find(a) == disjointSet.find(b)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}
	}

	@SuppressWarnings("Duplicates")
	static class DisjointSet {
		int [] parent;
		int [] rank;


		DisjointSet(int n) {
			this.parent = new int[1000001];
			this.rank = new int[1000001];

			for (int i = 0; i < 1000001; i++) {
				parent[i] = i;
			}
		}

		int find(int u) {
			if (parent[u] == u) {
				return u;
			}

			return parent[u] = find(parent[u]);
		}

		void union(int u, int v) {
			u = find(u);
			v = find(v);

			if (u == v) {
				return;
			}

			// ranking 이 큰 녀석이 항상 부모 트리가 된다.
			if (rank[u] > rank[v]) {
				parent[v] = u;
			} else if (rank[u] < rank[v]) {
				parent[u] = v;
			} else {
				// rank[u] == rank[v]
				parent[u] = v;
				++rank[v];
			}
		}

	}
}
