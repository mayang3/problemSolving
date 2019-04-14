package strategies.graph.shortestPath.floydWarshall;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 */
public class FloydPath {
	// 정점의 개수
	static int V;

	static int [][] adj = new int[V][V];

	// via[u][v] = u -> v 까지 가는 최단 경로가 경유하는 점 중 가장 번호가 큰 정점
	static int [][] via = new int[V][V];

	// 플로이드의 모든 쌍 최단 거리 알고리즘
	static void floyd() {
		for (int i=0 ; i<V ; i++) adj[i][i] = 0;

		for (int [] vi : via) {
			Arrays.fill(vi, -1);
		}

		for (int k=0 ; k<V ; k++)
			for (int i=0 ; i<V ; i++)
				for (int j=0 ; j<V ; j++)
					// k 를 경유해 가는 경로가 더 최소 비용이라면.
					if (adj[i][j] > adj[i][k] + adj[k][j]) {
						via[i][j] = k;
						adj[i][j] = adj[i][k] + adj[k][j];
					}
	}

	// u -> v 로 가는 최단 경로를 계산해 path 에 저장한다
	void reconstruct(int u, int v, LinkedList<Integer> path) {
		// base case
		if (via[u][v] == -1) {
			if (u != v) {
				path.add(v);
			} else {
				path.add(u);
			}
		} else {
			// w 는 경유점
			int w = via[u][v];
			reconstruct(u, w, path);

			path.removeLast(); // w 가 중복으로 들어가므로 지운다.

			reconstruct(w, v, path);
		}
	}
}
