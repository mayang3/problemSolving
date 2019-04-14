package hackerrank.cs.algorithm.graph;

import java.util.*;

/**
 * 이렇게 풀었는데 마지막 한문제는 계속 timeout 이 났다..
 *
 * 그래서 discussion 에 있는 {@link ShortestRich2Solution} 을 돌려봤더니 전부 Accept..
 *
 * 요 방식이랑 뭐가 다른지 분석해서 다시 한번 직접 짜보자..
 *
 * dijkstra2 방식으로 풀면 풀릴듯.. 시도해볼것..
 *
 * -> {@link DijkstraShortestRich3} 방식으로 풀었는데도 안풀림..
 *
 *
 *
 *
 * @author baejunbeom
 */
@SuppressWarnings("ALL")
public class DijkstraShortestRich2 {

	static List<Pair>[] adj;
	static int[] dist;

	static int INF = 987654321;

	static class Pair {
		int node;
		int cost;

		Pair(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}

	static void dijkstra(int start) {

		PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.cost == o2.cost) return 0;
			return o1.cost < o2.cost ? -1 : 1;
		});
		pq.add(new Pair(start, 0));

		dist[start] = 0;

		while (pq.isEmpty() == false) {
			Pair pair = pq.poll();

			int here = pair.node;

			if (dist[here] < pair.cost) continue;

			for (int i=0 ; i<adj[here].size() ; i++) {
				Pair therePair = adj[here].get(i);

				int there = therePair.node;
				int thereCost = dist[here] + therePair.cost;

				if (dist[there] > thereCost) {
					dist[there] = thereCost;
					pq.add(new Pair(there, therePair.cost));
				}
			}
		}


		for (int d : dist) {
			if (d == 0) continue;
			System.out.print((d == INF ? "-1" : d) + " ");
		}

		System.out.println("");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int t = in.nextInt();

		for(int a0 = 0; a0 < t; a0++){
			int n = in.nextInt();

			initAdj(n);
			dist = new int[n];

			Arrays.fill(dist, INF);

			int m = in.nextInt();

			for(int a1 = 0; a1 < m; a1++){
				int x = in.nextInt();
				int y = in.nextInt();
				int r = in.nextInt();

				// undirected graph
				adj[y-1].add(new Pair(x-1, r));
				adj[x-1].add(new Pair(y-1, r));
			}

			int s = in.nextInt();

			dijkstra(s -1);

		}
	}

	private static void initAdj(int n) {
		adj = new List[n];

		for (int i=0 ; i<n ; i++) {
			adj[i] = new ArrayList<>();
		}
	}
}
