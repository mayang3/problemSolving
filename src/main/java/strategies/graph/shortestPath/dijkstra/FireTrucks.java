package strategies.graph.shortestPath.dijkstra;

import java.util.*;

/**
 */
public class FireTrucks {

	static List<Pair>[] adj;
	static int [] dist;

	static final int INF = 987654321;

	static class Pair {
		int vertex;
		int cost;

		Pair(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}
	}

	static int solve(int [] fl, int [] fs) {
		// 소방서까지의 거리를 최단거리로 갖는 비용 0 짜리 vertex 를 생성한다.
		for (int f : fs) {
			adj[0].add(new Pair(f, 0));
		}

		dist[0] = 0;

		PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.cost == o2.cost) return 0;
			return o1.cost < o2.cost ? -1 : 1;
		});
		pq.add(new Pair(0, 0));

		while (pq.isEmpty() == false) {
			Pair herePair = pq.poll();

			int here = herePair.vertex;
			int cost = herePair.cost;

			// 중복해서 추가된 경로보다 현재 경로가 오래걸린다면..
			if (dist[here] < cost) continue;

			for (int i=0 ; i<adj[here].size() ; i++) {
				Pair therePair = adj[here].get(i);

				int there = therePair.vertex;
				int nextCost = dist[here] + therePair.cost;

				// 더 작은 비용의 경로 발견 (이미 우선순위 큐에 있더라도 중복해서 추가)
				if (dist[there] > nextCost) {
					dist[there] = nextCost;
					pq.add(new Pair(there, nextCost));
				}
			}
		}


		// result
		int sum = 0;

		for (int f : fl) {
			sum += dist[f];
		}

		return sum;
	}

	public static void main(String[] args) {
		int C = 1;

		int V = 8; // 장소의 수
		int E = 12; // 도로의 수

		int n = 3; // 화재 장소의 수
		int m = 2; // 소방서의 수

		int [][] pair = {
			{1,2,3},
			{1,6,9},
			{2,3,6},
			{3,4,4},
			{3,5,2},
			{4,5,7},
			{6,5,5},
			{8,6,5},
			{6,7,3},
			{8,7,3},
			{7,5,1},
			{2,8,3},
			{2,3,5}, // 화재 장소의 수
			{4,6} // 소방서의 수
		};

		adj = new List[V+1];

		for (int i=0 ; i<V+1 ; i++) {
			adj[i] = new ArrayList<>();
		}

		dist = new int[V+1];

		Arrays.fill(dist, INF);

		for (int i=0 ; i<E ; i++) {
			int a = pair[i][0];
			int b = pair[i][1];
			int t = pair[i][2];

			adj[a].add(new Pair(b, t));
			adj[b].add(new Pair(a, t));
		}

		int [] fireLocation = new int[n];
		int [] fireStation = new int[m];

		for (int i=0 ; i<n ; i++) {
			fireLocation[i] = pair[E][i];
		}

		for (int i=0 ; i<m ; i++) {
			fireStation[i] = pair[E+1][i];
		}

		System.out.println(solve(fireLocation, fireStation));


	}

}
