package baekjoon.tree;

import java.util.*;

public class 트리색칠하기 {

	static List<Integer>[] adj;
	static boolean [] visited;
	static int [] cost;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		adj = new List[N];
		visited = new boolean[N];
		cost = new int[N];

		for (int i = 0; i < N; i++) {
			adj[i] = new LinkedList<>();
		}

		for (int i = 0; i < N-1; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();

			u--;
			v--;

			adj[u].add(v);
			adj[v].add(u);
		}

		for (int i = 0; i < N; i++) {
			if (visited[i] == false) {
				dfs(i);
			}
		}

		long sum = 0;

		for (int v : cost) {
			sum += v;
		}

		System.out.println(sum);
	}

	private static int dfs(int here) {
		visited[here] = true;

		// leaf node 일 경우 1
		if (adj[here].size() == 0) {
			return 1;
		}

		int max = 0;
		Set<Integer> set = new HashSet<>();

		for (int i = 0; i < adj[here].size(); i++) {
			int there = adj[here].get(i);


			if (visited[there] == false) {
				int childColor = dfs(there);
				set.add(childColor);
				max = Math.max(max, childColor);
			}
		}

		Set<Integer> diffSet = new HashSet<>();

		for (int i = 1; i <= max ; i++) {
			diffSet.add(i);
		}

		diffSet.removeAll(set);

		int next = 0;

		if (diffSet.size() > 0) {
			Object[] objects = diffSet.toArray();
			Arrays.sort(objects);
			next = (int)objects[0];
		} else {
			next = max + 1;
		}

		// child 가 있는 그래프일 경우, 현재 노드의 최소값은 child 노드의 최대색상값 + 1 이다
		return cost[here] = next;
	}
}
