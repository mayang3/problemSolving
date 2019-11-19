package leetcode;

import java.util.*;

public class SmallestStringWithSwaps {

	static class UnionFind {

		int [] rank;
		int [] parent;

		UnionFind(int n) {
			this.rank = new int[n];
			this.parent = new int[n];

			for (int i = 0; i < n ; i++) {
				parent[i] = i;
			}
		}

		public int find(int x) {
			if (parent[x] != x) {
				parent[x] = find(parent[x]);
			}

			return parent[x];
		}

		public void union(int x, int y) {
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
				parent[yRoot] = xRoot;
				rank[xRoot]++;
			}
		}
	}

	/**
	 * "vbjjxgdfnru"
	 * [[8,6],[3,4],[5,2],[5,5],[3,5],[7,10],[6,0],[10,0],[7,1],[4,8],[6,2]]
	 *
	 * @param s
	 * @param pairs
	 * @return
	 */
	public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
		int n = s.length();

		UnionFind unionFind = new UnionFind(n);

		for (List<Integer> pair : pairs) {
			unionFind.union(pair.get(0), pair.get(1));
		}

		Map<Integer, PriorityQueue<Character>> groupMap = new HashMap<>();

		for (int i = 0; i < n; i++) {
			PriorityQueue<Character> pq = groupMap.computeIfAbsent(unionFind.find(i), (dummy) -> new PriorityQueue<>());
			pq.offer(s.charAt(i));
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			PriorityQueue<Character> pq = groupMap.get(unionFind.find(i));
			sb.append(pq.poll());
		}

		return sb.toString();
	}

	public static void main(String[] args) {

		String s = "tklkxyizmlqf";
		int [][] pairArr = {{2,10},{3,5},{8,11},{1,2},{10,6},{4,1},{1,10},{5,8},{8,3},{10,4},{7,3},{10,11}};

		List<List<Integer>> pairs = new ArrayList<>();

		for (int [] pair : pairArr) {
			List<Integer> list = new ArrayList<>();
			list.add(pair[0]);
			list.add(pair[1]);

			pairs.add(list);
		}

		SmallestStringWithSwaps smallestStringWithSwaps = new SmallestStringWithSwaps();
		String res = smallestStringWithSwaps.smallestStringWithSwaps(s, pairs);

		System.out.println(res);
	}
}
