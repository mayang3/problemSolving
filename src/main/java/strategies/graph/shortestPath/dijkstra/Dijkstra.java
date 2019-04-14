package strategies.graph.shortestPath.dijkstra;

import java.util.*;

/**
 * 다익스트라 알고리즘의 정의 : 음수간선이 없는 가중치 그래프의 최단거리를 구하고자 할때 사용하는 알고리즘이다.
 * @author baejunbeom
 */
public class Dijkstra {
	// 정점의 개수
	static int V = 7;

	static int INF = 987654321;

	static class Pair {
		int first;
		int second;

		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

	// 그래프의 인접리스트. (연결된 정점 번호, 간선 가중치) 쌍을 담는다.
	static List<Pair> [] adj = new List[V];

	static void makeGraph() {
		for (int i=0 ; i<V ; i++) {
			adj[i] = new ArrayList<>();
		}

		// a
		adj[0].add(new Pair(1, 5)); // b
		adj[0].add(new Pair(2, 1)); // c

		// b
		adj[1].add(new Pair(5, 3)); // f
		adj[1].add(new Pair(6, 3)); // g

		// c
		adj[2].add(new Pair(3, 2)); // d

		// d
		adj[3].add(new Pair(1, 1)); // b
		adj[3].add(new Pair(4, 5)); // e
		adj[3].add(new Pair(5, 3)); // f

		// g
		adj[6].add(new Pair(5, 2)); // f
	}

	/**
	 * 다익스트라 알고리즘을 구현하기 위해서는 세가지 자료구조를 기억하자.
	 *
	 * 각 정점과 비용을 나타내는 Pair 와 그 비용들의 구간합을 저장하는 dist[V]
	 * 그리고, 각 Pair 를 비교할 때 사용할 우선순위 큐(힙)
	 *
	 * 시간복잡도 O(E * log V)
	 *
	 * 다익스트라의 동작방식 추상화
	 *
	 * 1. 시작점으로 부터 각 정점 자신까지 오는 총 거리의 합을 표현하는 배열 dist 를 선언한다.
	 * 2. 인접리스트 방식으로 배열과 비용 쌍을 표현한다.
	 * 3. 우선순위 큐에 시작점을 넣는다.
	 * 4. 우선순위 큐에서 가장 작은 비용의 정점을 뽑은 후, 정점 방문 전에!! 이미 더 적은 비용으로 방문한 적이 있는지를 확인한다.
	 * 5. 이미 더 적은 비용으로 방문한 적이 없다면, 정점을 순회하면서 인접 정점들의 비용을 미리 계산해서 dist 에 넣어둔다.
	 * 6. 그리고 dist[there] 가 더 적은 비용으로 방문된 적이 없다면 다음 방문 정점 리스트에 추가해둔다.
	 *
	 * @param src
	 * @return
	 */
	static int [] dijkstra(int src) {
		int [] dist = new int[V];

		Arrays.fill(dist, INF);

		dist[src] = 0;

		PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.second == o2.second) return 0;
			return o1.second > o2.second ? 1 : -1;
		});

		pq.add(new Pair(src, 0));

		while (pq.isEmpty() == false) {
			Pair pair = pq.poll();

			int here = pair.first;
			int cost = pair.second;

			// 현재 정점이 앞에서 이미 처리되서, 현재의 cost 보다 작은 값을 가지고 있다면 무시한다.
			if (dist[here] < cost) {
				continue;
			}

			// 인접한 정점들을 모두 검사한다.
			for (int i=0 ; i<adj[here].size() ; i++) {
				int there = adj[here].get(i).first;
				// 다음 경로의 비용은 "현재 정점까지 오는데 걸린 비용" 과 "현재 정점의 비용" 을 더한 비용이다.
				int nextDist = cost + adj[here].get(i).second;

				// 더 짧은 경로를 발견하면, dist[] 를 갱신하고 우선순위 큐에 넣는다.
				if (dist[there] > nextDist) {
					dist[there] = nextDist;
					pq.add(new Pair(there, nextDist)); // 중요! 여기에 nextDist 가 들어가야 한다.. 그래야 말이 됨!!
				}
			}

		}

		return dist;
	}

	/**
	 * 우선순위 큐를 사용하지 않는 다익스트라 알고리즘의 구현
	 *
	 * 시간복잡도 O(V^2 + E)
	 * @param src
	 * @return
	 */
	static int[] dijkstra2(int src) {
		int [] dist = new int[V];
		Arrays.fill(dist, INF);

		// 각 정점을 방문했는지 여부를 저장한다.
		boolean [] visited = new boolean[V];

		dist[src] = 0;
		visited[src] = false;

		while (true) {

			int here = 0; // 가장 가까운 정점
			int closet = INF; // 가장 가까운 정점의 비용

			// 아직 방문하지 않은 정점 중 가장 가까운 정점을 찾는다.
			for (int i=0 ; i<V ; i++) {
				if (dist[i] < closet && !visited[i]) {
					here = i;
					closet = dist[i];
				}
			}

			if (closet == INF) break;

			// 가장 가까운 정점을 방문한다.
			visited[here] = true;

			for (int i=0 ; i<adj[here].size() ; i++) {
				// 가장 가까운 정점에 연결되어있는 정점
				int there = adj[here].get(i).first;

				// 가장 가까운 정점에 연결되어있는 정점을 방문한적이 있다면 pass~
				if (visited[there]) continue;

				// there 정점의 비용 합산
				int nextDist = dist[here] + adj[here].get(i).second;
				// 지금까지 발견된 there 정점의 비용과 다음 there 정점의 비용중 최하값
				dist[there] = Math.min(dist[there], nextDist);
			}
		}

		return dist;
	}

	public static void main(String[] args) {
		makeGraph();

		System.out.println(Arrays.toString(dijkstra(0)));
		System.out.println(Arrays.toString(dijkstra2(0)));
	}
}
