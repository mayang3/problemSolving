package baekjoon.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 트리의지름2 {

	static class Node {
		int node;
		int cost;

		Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}

	static List<Node>[] adj;
	static boolean [] visited;
	static int [] costs;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		adj = new List[n];
		visited = new boolean[n];
		costs = new int[n];

		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < (n-1); i++) {
			int root = scanner.nextInt();
			int node = scanner.nextInt();
			int cost = scanner.nextInt();

			connect(root, node, cost);
		}

		Node firstMaxNode = dfsWrapper(0);
		Node secondMaxNode = dfsWrapper(firstMaxNode.node);

		System.out.println(secondMaxNode.cost);
	}

	static Node dfsWrapper(int here) {
		Arrays.fill(visited, false);
		Arrays.fill(costs, 0);

		dfs(here);

		int max = 0;
		int node = 0;

		for (int i=0 ; i<costs.length ; i++) {
			if (max < costs[i]) {
				max = costs[i];
				node = i;
			}
		}

		return new Node(node, max);
	}

	private static void dfs(int here) {
		visited[here] = true;

		for (int i=0 ; i<adj[here].size() ; i++) {
			Node there = adj[here].get(i);

			if (visited[there.node] == false) {
				costs[there.node] = costs[here] + there.cost;
				dfs(there.node);
			}
		}
	}

	static void connect(int here, int there, int cost) {
		here--;
		there--;

		adj[here].add(new Node(there, cost));
		adj[there].add(new Node(here, cost));
	}
}
