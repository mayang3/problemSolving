package algospot;

import java.util.*;

public class FIRETRUCKS {

	/**
	 * 1
	 * 8 12 3 2
	 * 1 2 3
	 * 1 6 9
	 * 2 3 6
	 * 3 4 4
	 * 3 5 2
	 * 4 5 7
	 * 6 5 5
	 * 8 6 5
	 * 6 7 3
	 * 8 7 3
	 * 7 5 1
	 * 2 8 3
	 * 2 3 5 - 화재가 발생한 장소
	 * 4 6 - 소방서의 위치 번호
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int C = scanner.nextInt();

		while (C-- > 0) {
			int V = scanner.nextInt();
			int E = scanner.nextInt();

			int n = scanner.nextInt();
			int m = scanner.nextInt();

			Map<Integer, List<Pair>> graph = new HashMap<>();
			int [] firePlaces = new int[n];
			int [] fireStations = new int[m];

			for (int i = 0; i < E; i++) {
				int a = scanner.nextInt();
				int b = scanner.nextInt();
				int t = scanner.nextInt();

				graph.computeIfAbsent(a, temp -> new ArrayList<>()).add(new Pair(b, t));
				graph.computeIfAbsent(b, temp -> new ArrayList<>()).add(new Pair(a, t));
			}

			for (int i = 0; i < n; i++) {
				firePlaces[i] = scanner.nextInt();
			}

			for (int i = 0; i < m; i++) {
				fireStations[i] = scanner.nextInt();
				graph.computeIfAbsent(0, temp -> new ArrayList<>()).add(new Pair(fireStations[i], 0));
			}

			Dijkstra dijkstra = new Dijkstra(graph, V);
			int[] dist = dijkstra.solve(0);

			int sum = 0;

			for (int i = 0; i < n; i++) {
				sum += dist[firePlaces[i]];
			}

			System.out.println(sum);
		}
	}

	static class Dijkstra {
		Map<Integer, List<Pair>> graph;
		int [] dist;
		int N;

		public Dijkstra(Map<Integer, List<Pair>> graph, int n) {
			this.graph = graph;
			this.dist = new int[n+1];
			this.N = n;

			Arrays.fill(dist, Integer.MAX_VALUE);
		}

		int [] solve(int start) {
			PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

			dist[start] = 0;
			pq.add(new Pair(start, 0));

			while (pq.isEmpty() == false) {
				Pair here = pq.poll();

				int u = here.v;
				int cost = here.cost;

				if (dist[u] < cost) {
					continue;
				}

				if (graph.containsKey(u)) {
					for (int i = 0; i < graph.get(u).size(); i++) {
						Pair there = graph.get(u).get(i);

						int nextCost = there.cost + dist[u];

						if (nextCost < dist[there.v]) {
							dist[there.v] = nextCost;
							pq.add(new Pair(there.v, nextCost));
						}
					}
				}
			}

			return dist;
		}
	}

	static class Pair {
		int v;
		int cost;

		public Pair(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}
}
