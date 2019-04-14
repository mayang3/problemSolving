package stratgies2.union_find;

public class UnionFind {

	// 트리를 이용한 단순한 상호 배타적 집합 자료 구조의 구현
	// 이 단순한 DisjointSet 은 아래 최적화 로직에 의해서 개선된다.
	static class NaiveDisjointSet {
		int [] parent;

		NaiveDisjointSet(int n) {
			parent = new int[n];

			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		// u 가 속한 트리의 루트의 번호를 반환한다.
		// 여기서 find 를 실행하는데는 트리의 높이에 비례하는 시간이 걸린다.
		int find(int u) {
			// 자신이 곧 루트면 반환
			if (parent[u] == u) {
				return u;
			}

			// 자신이 루트가 아니면 찾을 때까지 계속
			return find(parent[u]);
		}

		// u 가 속한 트리와 v가 속한 트리를 합친다.
		// merge 도 find 가 지배하기 때문에 find 와 마찬가지로 트리의 높이에 비례하는 시간이 걸린다.
		void merge(int u, int v) {
			u = find(u);
			v = find(v);

			// 이미 같은 트리면 merge 할 필요가 없다.
			if (u == v) {
				return;
			}

			parent[u] = v;
		}
	}

	/**
	 * 위의 naive 한 방법은 연산에 따라 트리가 한쪽으로 기울 가능성이 있다.
	 *
	 * 그 중 쉽게 생각할 수 있는 방법은 두 트리를 합칠 때, 항상 높이가 더 낮은 트리를 더 높은 트리 밑에
	 *
	 * 집어 넣음으로써 트리의 높이가 높아지는 상황을 방지하는 것이다.
	 *
	 */
	@SuppressWarnings("Duplicates")
	static class OptimizedDisjointSet {
		int [] parent;
		int [] rank;

		OptimizedDisjointSet(int n) {
			parent = new int[n];
			rank = new int[n];

			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		// u가 속한 트리의 루트의 번호를 반환한다.
		int find (int u) {
			if (parent[u] == u) {
				return u;
			}

			// path compression. 간단하면서도 아주 큰 효과가 있는 최적화.
			return parent[u] = find(parent[u]);
		}

		/**
		 * u 가 속한 트리와 v가 속한 트리를 합친다.
		 *
		 * 트리 높이의 균형을 잡기 위해, ranking 을 사용한다.
		 *
		 * 기본적인 개념은, rank[u] 를 항상 ranking 이 작도록 유지하고,
		 *
		 * 작은 ranking 의 부모노드를 v 의 자식 tree 로 편입시키는 것이다.
		 *
		 * 이때, 두 노드의 ranking 이 같을 경우도 고려해주어야 한다.
		 *
		 * @param u
		 * @param v
		 */
		void merge(int u, int v) {
			u = find(u);
			v = find(v);

			if (u == v) {
				return;
			}

			// 이 swap 로직 잘못됏다.. 쓰지말고 geeksforgeeks 로직을 사용할것.
			// 시간될때 왜 잘못됐는지 증명을 위해 로직은 남겨둔다.
			if (rank[u] > rank[v]) {
				swap(u, v);
			}

			// 높이가 낮은쪽을 높은쪽의 sub tree 로 포함시켜준다.
			parent[u] = v;

			// u, v 의 rank 가 같다면, 항상 u 가 v 의 subtree 로 포함되게 하기 위해,
			// v 의 ranking 을 하나 높여준다.
			if (rank[u] == rank[v]) {
				++rank[v];
			}
		}

		/**
		 *  잘못됐음.. 쓰지말자..
		 *  geeksforgeeks 로직을 사용하자.
		 *
		 *  어디서 뻑나는진 모르겠지만.. baekjoon 1717 집합의 표현에서 이 로직을 사용하면 뻑난다.
		 *
		 * @param u
		 * @param v
		 */
		void swap(int u, int v) {
			// parent
			int temp = parent[u];
			parent[u] = parent[v];
			parent[v] = temp;

			// rank
			temp = rank[u];
			rank[u] = rank[v];
			rank[v] = temp;
		}
	}
}
