package strategies.graph.shortestPath.bellmanFord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 */
public class TimeTrip {

	static class Pair {
		int vertex;
		int cost;

		Pair(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}
	}

	static int INF = 987654321;

	static int V;
	static List<Pair>[] adj;

	// reahable[u][v] : u에서 v로 가는 경로가 있는가??
	static boolean[][] reacheable;

	// src 에서 target 으로 가는 최단거리를 계산한다.
	// 가중치가 음의 무한대인 경로가 있으면 -INF 를 반환한다.
	int bellman(int src, int target) {
		// 시작점을 제외한 모든 정점까지의 최단 거리 상한을 INF 로 둔다.
		int[] upper = new int[V];
		Arrays.fill(upper, INF);

		upper[src] = 0;

		// V-1 번 순회한다.
		for (int iter = 0; iter < V - 1; iter++) {
			for (int here = 0; here < V; here++) {
				for (int i = 0; i < adj[here].size(); i++) {
					int there = adj[here].get(i).vertex;
					int cost = adj[here].get(i).cost;

					// (here,there) 간선을 따라 완화를 시도한다.
					upper[there] = Math.min(upper[there], upper[here] + cost);
				}
			}
		}

		// 음수 사이클의 존재 여부를 확인하자.
		for (int here = 0; here < V; here++) {
			for (int i=0 ; i<adj[here].size() ; i++) {
				int there = adj[here].get(i).vertex;
				int cost = adj[here].get(i).cost;

				// 완화가 성공한다면 here 는 음수 사이클에 들어 있다.
				if (upper[here] + cost < upper[there]) {
					// 이 음수 사이클을 포함하는 경로가 있는가?
					if (reacheable[src][here] && reacheable[here][target]) {
						return -INF;
					}
				}
			}
		}

		return upper[target];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int C = scanner.nextInt();
		int G = scanner.nextInt();
		int W = scanner.nextInt();

		while (C-- > 0) {
			V = G;

			adj = new List[V];
			for (int i=0 ; i<adj.length ; i++) adj[i] = new ArrayList<>();

			reacheable = new boolean[V][V];

			while (W-- > 0) {
				int from = scanner.nextInt();
				int to = scanner.nextInt();
				int d = scanner.nextInt();

				adj[from].add(new Pair(to, d));
			}
		}
	}
}
