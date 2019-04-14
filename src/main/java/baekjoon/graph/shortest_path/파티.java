package baekjoon.graph.shortest_path;

import java.util.*;

/**
 * forward 2->1 == reverse 2->1
 *
 * @author baejunbeom
 */
@SuppressWarnings("Duplicates")
public class 파티 {
	static final int INF = 987654321;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int x = scanner.nextInt()-1;

		List<Pair>[] forward = makeAdjacentList(n);
		List<Pair>[] reverse = makeAdjacentList(n);


		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;
			int cost = scanner.nextInt();

			forward[a].add(new Pair(b, cost));
			reverse[b].add(new Pair(a, cost));
		}

		int [] forwardDistances = new Dijkstra(forward).execute(x);
		int [] reverseDistances = new Dijkstra(reverse).execute(x);

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			max = Math.max(max, forwardDistances[i] + reverseDistances[i]);
		}

		System.out.println(max);

	}

	private static List<Pair>[] makeAdjacentList(int n) {
		List<Pair> [] forward = new List[n];

		for (int i = 0; i < n; i++) {
			forward[i] = new ArrayList<>();
		}
		return forward;
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
			boolean [] visited = new boolean[adj.length];
			int [] distane = makeDistance(start);
			PriorityQueue<Pair> pq = makePiorityQueue(start);


			while (pq.isEmpty() == false) {
				Pair herePair = pq.poll();

				int here = herePair.v;
				int cost = herePair.cost;

				if (distane[here] < cost) {
					continue;
				}

				for (int i = 0; i < adj[here].size(); i++) {
					Pair therePair = adj[here].get(i);

					int there = therePair.v;
					int nextDistance = distane[here] + therePair.cost;


					if (nextDistance < distane[there]) {
						distane[there] = nextDistance;
						pq.add(new Pair(there, nextDistance));
					}
				}
			}

			return distane;
		}

		int [] makeDistance(int start) {
			int [] dist = new int[adj.length];

			Arrays.fill(dist, INF);
			dist[start] = 0;

			return dist;
		}

		PriorityQueue<Pair> makePiorityQueue(int start) {
			PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

			pq.add(new Pair(start, 0));

			return pq;
		}

	}
}
