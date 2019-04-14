package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 계속 시간초과가 발생했었다.. 이유인즉슨, adj 를 최초 LinkedList 로 만들었는데, 이 때문에 탐색에 N(k) 만큼의 비용이 들었던것...
 *
 * adj 를 ArrayList 로 변경했더니 바로 통과했다..
 *
 * 삽입/삭제의 속도가 중요한 곳이라면 LinkedList, access 가 중요한 곳이라면 ArrayList .. 잊지 말자..
 *
 */
public class 최단경로 {
	static List<Pair>[] adj;
	static int [] costs;
	static final int INF = 98754321;

	static class Pair {
		int v;
		int cost;

		Pair(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String next() {
			while (st == null || st.hasMoreTokens() == false) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}

	public static void main(String[] args) {
		FastReader scanner = new FastReader();

		// 정점의 개수
		int V = scanner.nextInt();
		// 간선의 개수
		int E = scanner.nextInt();

		// 시작 번호
		int K = scanner.nextInt();


		adj = new List[V];
		costs = new int[V];

		// init
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<>();
			costs[i] = INF;
		}

		for (int i = 0; i < E; i++) {
			int u = scanner.nextInt() -  1;
			int v = scanner.nextInt() - 1;

			int w = scanner.nextInt();

			adj[u].add(new Pair(v, w));
		}

		dijkstra(--K);

		for (int v : costs) {
			if (v == INF) {
				System.out.println("INF");
			} else {
				System.out.println(v);
			}
		}
	}

	static void dijkstra(int start) {
		PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> o1.cost == o2.cost ? 0 : (o1.cost < o2.cost ? -1 : 1));
		pq.add(new Pair(start, 0));

		costs[start] = 0;

		while (pq.isEmpty() == false) {
			// 다익스트라 알고리즘은 이전 노드로부터 최단거리에 위치에 있는 노드를 항상 먼저 찾는다.
			Pair pair = pq.poll();

			int here = pair.v;
			int hereCost = pair.cost;

			// costs[here] 가 최단거리 비교로 인해, 갱신된 경우 최초값은 무시한다.

			// 예를 들어, 1->2 비용 30 이 들고, 1->3 은 비용 1이, 3->2 는 비용 2가 든다고 하자.
			// 최초, costs[2] 와 costs[3] 은 모두 0 이므로,
			// costs[2] = 30, costs[3] = 1 이 들어간다.
			// 그 후에는, 우선순위 큐에 의해서 3->2 가 탐색되게 되는데, 이때 시작점 1->3->2 까지 비용은 3이다.

			// 기존의 비용인 costs[2] = 30 > 1 + 2 이므로 costs[2] = 3 으로 갱신하고,
			// 나중에 정점 2 가 꺼내어졌을때, 이 경로를 무시한다.
			if (costs[here] < hereCost) {
				continue;
			}

			for (int i = 0; i < adj[here].size(); i++) {
				Pair therePair = adj[here].get(i);

				int there = therePair.v;
				int thereCost = hereCost + therePair.cost;

				// 이전 정점 + 현재정점의 비용이, 여태까지 계산된 현재 정점까지의 비용보다 적다면 계산 대상이다.
				if (costs[there] > thereCost) {
					costs[there] = thereCost;
					pq.add(new Pair(there, thereCost));
				}
			}
		}
	}
}
