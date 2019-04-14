package hackerrank.cs.dataStructure.disjoint_set;

import java.util.*;

/**
 * There are 2N values to represent nodes in a graph.
 * They are divided into two sets G and B.
 *
 * Each set has exactly N values.
 *
 * Set G is represent by {G1, G2, ..., Gn}.
 *
 * G can contain any value between 1 to N (inclusive).
 *
 * Set B is represented by {B1, B2, ..., Bn}
 *
 * B can contain any value between N+1 to 2N (inclusive).
 *
 * Same value can be chosen any number of times.
 *
 * Here (G1, B1), (G2, B2), ... (Gn, Bn) represents the edges of the graph.
 *
 * Your task is to print the number of vertices in the smallest and the largest connected components of the graph.
 *
 * [Note]
 *
 * Single nodes should not be considered in the answer.
 *
 * For more clarity look at the following figure.
 *
 * [image link 깨짐..]
 *
 * For the above graph smallest connected components is 7 and largest connected components is 17.
 *
 * (example)
 *
 * (input)
 *  5
 *  1 6
 *  2 7
 *  3 8
 *  4 9
 *  2 6
 *
 * (output)
 *  2 4
 *
 *  The number of vertices in the smallest connected component in the graph is 2. (i.e) either (3,8) or (4,9)
 *  The number of vertices in the largest connected component in the graph is 4. (i.e) 1-2-6-7
 *
 *  disjoint set 인데, normal version 으로 품.. ㅎㅎ
 */
public class ComponentsInAGraph {
	static List<Integer>[] adj;
	static boolean [] visited;
	static int cnt = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int E = scanner.nextInt();
		int N = 2*E;

		adj = new List[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < E; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;

			adj[a].add(b);
			adj[b].add(a);
		}


		int min = 987654321;
		int max = 0;

		for (int node = 0; node < N; node++) {
			if (visited[node] == false) {
				cnt = -1;
				dfs(node);

				if (cnt != -1) {
					min = Math.min(min, cnt+1);
				}
				max = Math.max(max, cnt+1);
			}
		}

		System.out.println(min + " " + max);
	}

	static void dfs(int here) {

		visited[here] = true;
		if (adj[here].size() > 0) {
			cnt++;
		}

		for (int i = 0; i < adj[here].size(); i++) {
			Integer there = adj[here].get(i);

			if (visited[there] == false) {
				dfs(there);
			}
		}
	}
}
