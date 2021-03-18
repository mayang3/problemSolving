package leetcode;

public class NumberOfProvinces {
	static class UnionFind {
		int [] parent;
		int [] rank;
		int count;

		UnionFind(int n) {
			this.parent = new int[n];
			this.rank = new int[n];
			this.count = n;

			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		public int find(int u) {
			if (u != parent[u]) {
				parent[u] = find(parent[u]);
			}

			return parent[u];
		}

		public void union(int u, int v) {
			int uRoot = find(u);
			int vRoot = find(v);

			if (uRoot == vRoot) {
				return;
			}

			if (rank[uRoot] < rank[vRoot]) {
				parent[uRoot] = vRoot;
			} else if (rank[uRoot] > rank[vRoot]) {
				parent[vRoot] = uRoot;
			} else {
				parent[uRoot] = vRoot;
				rank[vRoot]++;
			}

			// union 이 안된 상태로 그대로 남아있는 아이도 있기 때문에 0 에서 ++ 를 하면 안되고
			// 전체 노드의 수에서 -- 를 해주어야 한다.
			count--;
		}

		int getCount() {
			return this.count;
		}

	}

	public int findCircleNum(int[][] M) {
		UnionFind unionFind = new UnionFind(M.length);

		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M.length; j++) {
				if (M[i][j] == 1) {
					unionFind.union(i, j);
				}
			}
		}

		return unionFind.getCount();
	}

	public static void main(String[] args) {
		int [][] M = {{1,1,0},{1,1,0},{0,0,1}};

		NumberOfProvinces numberOfProvinces = new NumberOfProvinces();

		System.out.println(numberOfProvinces.findCircleNum(M));
	}
}
