package baekjoon.graph;

import java.util.*;

/**
 * 몇번 fail 했는데 dist 의 값을 int 로 했고, 정답이 없을 경우 -1을 안찍은게 원인이었음..
 *
 * 무난히 accept.
 *
 * 컨셉은, Start -> A - > B - > end  or start -> B -> A -> end 중에 비용이 적은 녀석을 구하자는 뜻이다.
 */
public class 특정한최단경로 {

	static List<Pair> [] adj;
	static long [] dist;
	static final int INF = 987654321;

	static class Pair {
		int v;
		long cost;

		Pair(int v, long cost) {
			this.v = v;
			this.cost = cost;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int E = scanner.nextInt();

		adj = new List[N];
		dist = new long[N];

		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
			dist[i] = INF;
		}

		for (int i = 0; i < E; i++) {
			int u = scanner.nextInt() -1;
			int v = scanner.nextInt() -1;
			int c = scanner.nextInt();

			adj[u].add(new Pair(v, c));
			adj[v].add(new Pair(u, c));
		}

		int A = scanner.nextInt()-1;
		int B = scanner.nextInt()-1;

		dijkstra(0);

		long sToA = dist[A];
		long sToB = dist[B];

		Arrays.fill(dist, INF);

		dijkstra(A);

		long AtoB = dist[B];

		Arrays.fill(dist, INF);

		dijkstra(N-1);

		long eToA = dist[A];
		long eToB = dist[B];

		long min = Math.min(sToA + AtoB + eToB, sToB + AtoB + eToA);

		System.out.println(min >= INF ? -1 : min);
	}

	static void dijkstra(int start) {
		PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.cost));

		pq.add(new Pair(start, 0));
		dist[start] = 0;

		while (pq.isEmpty() == false) {
			Pair herePair = pq.poll();

			int here = herePair.v;
			long hereCost = herePair.cost;

			if (dist[here] < hereCost) {
				continue;
			}

			for (int i = 0; i < adj[here].size(); i++) {
				Pair therePair = adj[here].get(i);

				int there = therePair.v;
				long nextDist = dist[here] + therePair.cost;

				if (dist[there] > nextDist) {
					dist[there] = nextDist;
					pq.add(new Pair(there, nextDist));
				}
			}
		}
	}
}
