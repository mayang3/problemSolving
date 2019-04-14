package hackerrank.cs.dataStructure.queue;

import java.util.Scanner;

public class MergingCommunities {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int Q = scanner.nextInt();

		DisjointSet ds = new DisjointSet(N);

		while (Q-- > 0) {
			char cmd = scanner.next().charAt(0);

			if (cmd == 'Q') {
				int x = scanner.nextInt() - 1;
				System.out.println(ds.getSize(x));
			} else if (cmd == 'M') {
				int x = scanner.nextInt() - 1;
				int y = scanner.nextInt() - 1;

				ds.union(x, y);
			}
		}
	}

	static class DisjointSet {
		int [] parent;
		int [] rank;
		int [] size;

		DisjointSet(int n) {
			parent = new int[n];
			rank = new int[n];
			size = new int[n];

			for (int i = 0; i < n; i++) {
				parent[i] = i;
				size[i] = 1;
			}
		}

		int find(int x) {
			if (parent[x] == x) {
				return x;
			}

			return parent[x] = find(parent[x]);
		}

		void union(int x, int y) {
			int xRoot = this.find(x);
			int yRoot = this.find(y);

			if (xRoot == yRoot) {
				return;
			}

			if (rank[xRoot] < rank[yRoot]) {
				parent[xRoot] = yRoot;
				size[yRoot] += size[xRoot];
			} else if (rank[xRoot] > rank[yRoot]) {
				parent[yRoot] = xRoot;
				size[xRoot] += size[yRoot];
			} else {
				parent[yRoot] = xRoot;
				++rank[xRoot];
				size[xRoot] += size[yRoot];
			}
		}

		int getSize(int x) {
			return size[find(x)];
		}

	}
}
