package strategies.graph.shortestPath.bellmanFord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 다익스트라 : 모든 정점을 한번씩 검사한다.
 * 벨만포드 : 모든 정점을 V-1 번씩 검사한다.
 * ( 음수 사이클의 체크를 위해서는 V번 검사한다.)
 *
 */
public class BellmanFord {
	// 정점의 개수
	static int V;
	// 그래프의 인접 리스트 (연결된 정점 번호, 간선 가중치) 쌍을 담는다.
	static List<Pair> [] adj;

	static final int INF = 987654321;

	static class Pair {
		int vertex;
		int cost;

		Pair(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}
	}

	List<Integer> bellmanFord(int src) {
		// 시작점을 제외한 모든 정점까지의 최단 거리 상한을 INF 로 둔다.
		int [] upper = new int[V];
		Arrays.fill(upper, INF);

		boolean updated = false;

		// V번 순회한다.
		for (int iter=0 ; iter<V ; iter++) {
			updated = false;

			// 0번 정점부터 V 정점까지의 one-iteration 순회
			for (int here=0 ; here<V ; here++) {
				// 현재 정점에 인접 정점 가져오기
				for (int i=0 ; i<adj[here].size() ; i++) {
					int there = adj[here].get(i).vertex;
					int cost = adj[here].get(i).cost;

					// (here, there) 간선을 따라 완화를 시도한다.
					if (upper[there] > upper[here] + cost) {
						upper[there] = upper[here] + cost;
						updated = true;
					}
				}
			}

			// one-iteration 을 돌때,
			// 모든 간선에 대해 완화가 실패했을 경우에는 V-1 번도 돌 필요 없이 곧장 종료한다.
			// V-1 번의 간선을 체크하기 전에 모든 간선/정점에 대한 값이 이미 최소가 된 경우이다.
			if (!updated) break;
		}

		// V번째 순회에도 완화가 성공했다면 음수 사이클이 있다.
		if (updated) return Collections.emptyList();

		List<Integer> ret = new ArrayList<>();

		for (int u : upper) {
			ret.add(u);
		}

		return ret;
	}

}
