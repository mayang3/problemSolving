package strategies.graph.shortestPath.floydWarshall;

import java.util.*;

/**
 */
public class Drunken {
	static int INF = 987654321;

	// graph
	static int V = 8;
	static int MAX_V = 500;
	static int [][] adj = new int[V][V];

	// 각 정점에서 음주 운전 단속을 할때 걸리는 시간
	static int [] delay = new int[V];
	static int [][] W = new int[V][V];


	static class Pair {
		int vertex;
		int cost;

		Pair(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}
	}

	// 입력을 받을 때, 1부터 시작하는 정점 번호를 0부터 시작하도록 변경했다고 가정한다.
	static void solve() {
		// 모든 정점들을 예상 시간 별로 정렬한다.
		List<Pair> order = new ArrayList<>();

		for (int i=0; i<V ; i++)
			order.add(new Pair(i, delay[i]));

		Collections.sort(order, (o1, o2) -> {
			if (o1.cost == o2.cost) return 0;
			return o1.cost < o2.cost ? -1 : 1;
		});

		// 정점을 하나도 거치지 않고 얻을 수 있는 최단 경로
		for (int i=0 ; i<V ; i++) {
			for (int j=0 ; j<V ; j++) {
				if (i==j) {
					W[i][j] = adj[i][j] = 0;
				} else {
					W[i][j] = adj[i][j];
				}
			}
		}

		// i->v 로 갈때 정점 k 에서 단속을 할경우 걸리는 최소 시간을 계산해서 배열 W[i][j] 에 저장해둔다.
		for (int k=0 ; k<V ; k++) {
			// k 번째로 예상 시간이 적게 걸리는 정점 w 까지를 지나서 얻을 수 있는 최단거리
			int w = order.get(k).vertex;

			for (int i=0 ; i<V ; i++) {
				for (int j=0 ; j<V ; j++) {
					// 정석 플로이드 알고리즘에서 k 대신 w 를 경유점으로 사용
					adj[i][j] = Math.min(adj[i][j], adj[i][w] + adj[w][j]);

					// 플로이드 알고리즘의 변형. 단속에 걸리는 시간이 적게 걸리는 정점들을 경유지에 추가해서 비교한다.
					// i - > j 로 갈때, 정점 w 에서 단속을 할 경우, 걸리는 최소 시간
					W[i][j] = Math.min(adj[i][w] + delay[w] + adj[w][j], W[i][j]);
				}
			}
		}
	}

	public static void main(String[] args) {
		V = 8;

		int [] d = {
			8,6,5,8,3,5,8,4
		};

		for (int i=0 ; i<d.length ; i++) {
			delay[i] = d[i];
		}

		// 중요! 이거 안해주면 Math.min 비교가 절대 일어나지 않는다.
		for (int [] a : adj) {
			Arrays.fill(a, INF);
		}

		connect(1,6,9);
		connect(1,2,3);
		connect(2,8,3);
		connect(6,8,5);
		connect(6,7,3);
		connect(8,7,3);
		connect(6,5,5);
		connect(4,5,7);
		connect(3,4,4);
		connect(3,5,2);
		connect(2,3,6);
		connect(7,5,1);

		solve();

		System.out.println(find(1,5));
		System.out.println(find(6,3));
	}

	static int find(int u, int v) {
		return W[u-1][v-1];
	}

	static void connect(int u, int v, int c) {
		adj[u-1][v-1] = c;
		adj[v-1][u-1] = c; // 이 문제는 무향 그래프 문제이다. 이 부분이 꼭 들어가야함..
	}
}
