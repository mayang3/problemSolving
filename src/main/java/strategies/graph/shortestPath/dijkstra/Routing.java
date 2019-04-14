package strategies.graph.shortestPath.dijkstra;

import java.util.*;

/**
 * @author baejunbeom
 */
public class Routing {

	static List<Pair> [] adj;
	static int N;

	static class Pair {
		int vertex;
		double cost;

		Pair(int vertex, double cost) {
			this.vertex = vertex;
			this.cost = cost;
		}
	}


	public static double solve() {
		double [] dist = new double[N];

		Arrays.fill(dist, 987654321);

		PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.cost == o2.cost) return 0;
			return o1.cost < o2.cost ? -1 : 1;
		});
		pq.add(new Pair(0, 0));

		dist[0] = 1;

		while (pq.isEmpty() == false) {
			Pair pair = pq.poll();

			int here = pair.vertex;
			double cost = pair.cost;

			if (dist[here] < cost) continue;

			// here 여 연결된 정점 탐색
			for (int i=0 ; i<adj[here].size() ; i++) {
				Pair therePair = adj[here].get(i);

				int there = therePair.vertex;
				double nextCost = dist[here] * therePair.cost;

				if (dist[there] > nextCost) {
					dist[there] = nextCost;
					pq.add(new Pair(there, nextCost));
				}
			}
		}

		return dist[N-1];
	}

	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//
//		int T = scanner.nextInt();
//
//		while (T-- > 0) {
//			N =  scanner.nextInt();
//			int M = scanner.nextInt();
//
//			adj = new List[N];
//
//			for (int i=0 ; i<adj.length ; i++) {
//				adj[i] = new ArrayList<>();
//			}
//
//			while (M-- > 0) {
//				int a = scanner.nextInt();
//				int b = scanner.nextInt();
//				double c = scanner.nextDouble();
//
//				adj[a].add(new Pair(b, c));
//				adj[b].add(new Pair(a, c));
//			}
//
//			System.out.println(solve());
//		}

		N = 7;
		adj = new List[N];

		for (int i=0 ; i<adj.length ; i++) {
			adj[i] = new ArrayList<>();
		}

		double [][] pair = {
		{0,1,1.3}
		,{0,2,1.1}
		,{0,3,1.24}
		,{3,4,1.17}
		,{3,5,1.24}
		,{3,1,2}
		,{1,2,1.31}
		,{1,2,1.26}
		,{1,4,1.11}
		,{1,5,1.37}
		,{5,4,1.24}
		,{4,6,1.77}
		,{5,6,1.11}
		,{2,6,1.2}
		};

		for (double [] p : pair) {
			int a = (int)p[0];
			int b = (int)p[1];
			double c = p[2];

			adj[a].add(new Pair(b, c));
			adj[b].add(new Pair(a, c));
		}

		System.out.printf("%.10f", solve());

	}
}
