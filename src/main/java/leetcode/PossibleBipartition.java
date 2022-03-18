package leetcode;

import java.util.*;

public class PossibleBipartition {

	public boolean possibleBipartition(int N, int [][] dislikes) {
		Map<Integer, Set<Integer>> graph = new HashMap<>();

		for (int i = 0; i < dislikes.length; i++) {
			int me = dislikes[i][0]-1;
			int adjacent = dislikes[i][1]-1;

			graph.computeIfAbsent(me, t -> new HashSet<>()).add(adjacent);
			graph.computeIfAbsent(adjacent, t -> new HashSet<>()).add(me);
		}

		int [] group  = new int[N];

		for (int i = 0; i < N; i++) {
			if (group[i] == 0 && dfs(graph, group, i, 1) == false) {
				return false;
			}
		}

		return true;
	}

	private boolean dfs(Map<Integer, Set<Integer>> graph, int[] group, int i, int g) {
		group[i] = g;

		if (graph.containsKey(i)) {
			for (int there : graph.get(i)) {
				if (group[there] == group[i]) {
					return false;
				}

				if (group[there] == 0 && dfs(graph, group, there, g * -1) == false) {
					return false;
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		int [][] dislikes = {{1,2},{1,3},{2,3}};

		PossibleBipartition possibleBipartition = new PossibleBipartition();
		boolean b = possibleBipartition.possibleBipartition(3, dislikes);
		System.out.println(b);
	}
}
