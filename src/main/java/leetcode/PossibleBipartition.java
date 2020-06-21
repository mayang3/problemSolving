package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PossibleBipartition {

	public boolean possibleBipartition(int N, int [][] dislikes) {
		List<Integer>[] graph = new List[N];

		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int [] d : dislikes) {
			graph[d[0]-1].add(d[1]-1);
			graph[d[1]-1].add(d[0]-1);
		}

		int [] group = new int[N];

		for (int i = 0; i < N; i++) {
			if (group[i] == 0 && !dfs(graph, group, i, 1)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * i 번째 node 에서 탐색하여 biPartition 가능한지 여부를 리턴.
	 * @param graph
	 * @param group
	 * @param i
	 * @param g
	 * @return
	 */
	private boolean dfs(List<Integer>[] graph, int[] group, int i, int g) {
		group[i] = g;

		for (int j = 0; j < graph[i].size(); j++) {
			int next = graph[i].get(j);

			// if the node connected with me is in the same group, then false.
			if (group[next] == g) {
				return false;
			}

			if (group[next] == 0 && !dfs(graph, group, next, -g)) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		int [][] dislikes = {{1,2}, {1,3}, {2,4}};

		PossibleBipartition possibleBipartition = new PossibleBipartition();
		boolean b = possibleBipartition.possibleBipartition(4, dislikes);
		System.out.println(b);
	}
}
