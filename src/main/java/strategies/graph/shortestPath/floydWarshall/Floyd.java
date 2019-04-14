package strategies.graph.shortestPath.floydWarshall;

/**
 */
public class Floyd {
	// 정점의 개수
	static int V;
	// 그래프의 인접 행렬 표현
	// adj[u][v] 는 u -> v 로 가는 간선의 가중치. 간선이 없으면 아주 큰 값을 넣는다.
	static int [][] adj;
	// 최단 경로
	static int [][][] C;


	void allPairShortestPath() {
		// C[0] 을 초기화
		for (int i=0 ; i<V ; i++) {
			for (int j=0 ; j<V ; j++) {
				if (i != j) {
					// 정점 0 을 거치는 경우와, 정점 0을 거치지 않는 경우
					C[0][i][j] = Math.min(adj[i][j], adj[i][0] + adj[0][j]);
				} else {
					C[0][i][j] = 0; // 자기 자신의 정점으로 가는 비용은 0
				}
			}
		}

		// C[k-1] 이 있으면 C[k] 를 계산할 수 있다.
		for (int k=1 ; k<V ; k++) {
			for (int i=0 ; i<V ; i++) {
				for (int j=0 ; j<V ; j++) {
					C[k][i][j] = Math.min(C[k-1][i][j], C[k-1][i][k] + C[k-1][k][j]);
				}
			}
		}
	}
}
