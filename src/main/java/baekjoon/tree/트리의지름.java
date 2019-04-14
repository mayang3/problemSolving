package baekjoon.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * https://m.blog.naver.com/PostView.nhn?blogId=occidere&logNo=220961873786&proxyReferer=https%3A%2F%2Fwww.google.com%2F
 *
 * 핵심 알고리즘은 dfs 를 2번 수행한다는 것.
 *
 * 1. 아무 정점이나 잡고 dfs 를 수행해서 최고 거리가 먼 정점 B 를 찾은 후에,
 * 2. B 에서 다시 한번 dfs 를 수행해서 최고 거리가 먼 정점을 찾는다면 그 점이 바로 지름이 된다.
 *
 */
public class 트리의지름 {

	static List<Pair>[] adj;
	static boolean [] visited;
	static int [] globalCosts;

	static class Pair {
		int node;
		int cost;

		Pair(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int V = scanner.nextInt();

		// 실제 시작 값은 1이 되어야 한다.
		adj = new List[V];
		visited = new boolean[V];
		globalCosts = new int[V];

		for (int i=0 ; i<V ; i++) {
			adj[i] = new ArrayList<>();
		}

		while (V-- > 0) {
			int here = scanner.nextInt();

			while (true) {
				int there = scanner.nextInt();

				if (there == -1) {
					break;
				}

				int cost = scanner.nextInt();

				connect(here, there, cost);
			}
		}

//		globalCosts[0] = 0;

		dfs(0);

		int max = 0;
		int next = 0;

		for (int i=0 ; i<globalCosts.length ; i++) {
			if (max < globalCosts[i]) {
				max = globalCosts[i];
				next = i;
			}
		}

		Arrays.fill(globalCosts, 0);
		Arrays.fill(visited, false);
		dfs(next);

		max = 0;

		for (int v : globalCosts) {
			max = Math.max(max, v);
		}

		System.out.println(max);

	}

	static void dfs(int here) {
		visited[here] = true;

		for (int i=0 ; i<adj[here].size() ; i++) {
			Pair therePair = adj[here].get(i);

			int there = therePair.node;

			if (visited[there] == false) {
				globalCosts[there] = globalCosts[here] + therePair.cost;
				dfs(there);
			}

		}
	}

	static void connect(int here, int there, int cost) {
		here--;
		there--;
		adj[here].add(new Pair(there, cost));
		adj[there].add(new Pair(here, cost));
	}
}
