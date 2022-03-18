package leetcode;

import java.util.*;

public class MinimumHeightTrees {
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		// base case
		if (n <= 2) {
			List<Integer> res = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				res.add(i);
			}

			return res;
		}

		Map<Integer, List<Integer>> graph = new HashMap<>();

		for (int i = 0; i < edges.length; i++) {
			graph.computeIfAbsent(edges[i][0], t->new ArrayList<>()).add(edges[i][1]);
			graph.computeIfAbsent(edges[i][1], t->new ArrayList<>()).add(edges[i][0]);
		}

		LinkedList<Integer> leaves = new LinkedList<>();

		for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
			if (entry.getValue().size() == 1) {
				leaves.add(entry.getKey());
			}
		}

		int remainingNodes = n;

		while (remainingNodes > 2) {
			remainingNodes -= leaves.size();

			int size = Integer.valueOf(leaves.size());

			for (int i = 0; i < size; i++) {
				Integer leaf = leaves.pollFirst();

				// degree 가 1인 leaf 이기 때문에 list 의 size 는 항상 0이다.
				int neighbor = graph.remove(leaf).get(0);

				if (graph.get(neighbor).contains(leaf)) {
					graph.get(neighbor).remove(leaf);
				}

				if (graph.get(neighbor).size() == 1) {
					leaves.add(neighbor);
				}
			}
		}

		return leaves;
	}

	public static void main(String[] args) {
		int n = 6;
		int [][] edges = {{3,0},{3,1},{3,2},{3,4},{5,4}};

		MinimumHeightTrees minimumHeightTrees = new MinimumHeightTrees();
		System.out.println(minimumHeightTrees.findMinHeightTrees(n, edges));
	}
}
