package leetcode;

/**
 * @author neo82
 */
public class FriendCircles {
	public int findCircleNum(int[][] M) {
		DisjointSet disjointSet = new DisjointSet(M.length);

		for (int y = 0; y < M.length; y++) {
			for (int x = 0; x < M[0].length; x++) {
				if (M[y][x] == 1) {
					disjointSet.union(y, x);
				}
			}
		}

		return disjointSet.size;
	}

	public class DisjointSet {
		int [] parent;
		int [] rank;
		int size;

		DisjointSet(int n) {
			this.parent = new int[n];
			this.rank = new int[n];
			this.size = n;

			for (int i = 0; i < n; i++) {
				this.parent[i] = i;
				this.rank[i] = 1;
			}
		}

		int find(int x) {
			while (parent[x] != x) {
				x = parent[x] = parent[x];
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
			} else if (rank[xRoot] > rank[yRoot]) {
				parent[yRoot] = xRoot;
 			} else {
				parent[xRoot] = yRoot;
				rank[yRoot]++;
			}

			size--;
		}
	}

	public static void main(String[] args) {
		int[][] M = {
			{1, 0, 0, 1},
			{0, 1, 1, 0},
			{0, 1, 1, 1},
			{1, 0, 1, 1}};

		FriendCircles fc = new FriendCircles();
		int res = fc.findCircleNum(M);

		System.out.println(res);
	}
}
