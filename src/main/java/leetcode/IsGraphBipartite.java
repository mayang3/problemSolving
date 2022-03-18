package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class IsGraphBipartite {
	public boolean isBipartite(int[][] graph) {
		int n = graph.length;

		boolean[] visited = new boolean[n];

		Set<Integer> groupA = new HashSet<>();
		Set<Integer> groupB = new HashSet<>();

		for (int k = 0; k < n; k++) {
			if (visited[k] == false) {
				Queue<Integer> q = new LinkedList<>();
				q.add(k);
				groupA.add(k); // start node is groupA
				visited[k] = true;
				// next nodes are group B
				boolean isGroupA = true;

				while (q.isEmpty() == false) {
					int size = Integer.valueOf(q.size());

					for (int i = 0; i < size; i++) {
						Integer here = q.poll();

						for (int next = 0; next < graph[here].length; next++) {
							int there = graph[here][next];

							if (isGroupA) {
								if (groupA.contains(there)) {
									return false;
								}
							} else {
								if (groupB.contains(there)) {
									return false;
								}
							}

							if (visited[there] == false) {
								if (isGroupA) {
									groupB.add(there);
								} else {
									groupA.add(there);
								}

								visited[there] = true;
								q.add(there);
							}
						}
					}

					isGroupA = !isGroupA;
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		int[][] graph = {{1,3},{0,2},{1,3},{0,2}};

		IsGraphBipartite isGraphBipartite = new IsGraphBipartite();
		System.out.println(isGraphBipartite.isBipartite(graph));
	}
}
