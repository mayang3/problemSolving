package baekjoon.graph.shortest_path;

import java.util.*;

@SuppressWarnings("ALL")
public class 최소비용구하기 {
	private static final int INF = 1000000001;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();

		List<Pair> [] adj = new List[n];

		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;
			int cost = scanner.nextInt();

			adj[a].add(new Pair(b, cost));
		}

		int start = scanner.nextInt()-1;
		int end = scanner.nextInt()-1;

		int [] distnace = new Dijkstra(adj).execute(start);

		System.out.println(distnace[end]);
	}

	static class Pair {
		int v;
		int cost;

		Pair(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}


	static class Dijkstra {
		List<Pair> [] adj;

		Dijkstra(List<Pair> [] adj) {
			this.adj = adj;
		}

		int [] execute(int start) {
			int [] distance = makeDistance(start);
			PriorityQueue<Pair> pq = makePriorityQueue(start);

			while (pq.isEmpty() == false) {
				Pair herePiar = pq.poll();

				int here = herePiar.v;
				int cost = herePiar.cost;

				if (distance[here] < cost) {
					continue;
				}

				for (int i = 0; i < adj[here].size(); i++) {
					Pair therePair = adj[here].get(i);

					int there = therePair.v;
					int nextDistance = distance[here] + therePair.cost;

					if (nextDistance < distance[there]) {
						distance[there] = nextDistance;
						pq.add(new Pair(there, nextDistance));
					}
				}

			}

			return distance;
		}

		PriorityQueue<Pair> makePriorityQueue(int start) {
			PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
				@Override
				public int compare(Pair o1, Pair o2) {
					return o1.cost - o2.cost;
				}
			});

			pq.add(new Pair(start, 0));

			return pq;
		}

		int [] makeDistance(int start) {
			int [] distance = new int[adj.length];

			Arrays.fill(distance, INF);
			distance[start] = 0;

			return distance;
		}
	}
}
