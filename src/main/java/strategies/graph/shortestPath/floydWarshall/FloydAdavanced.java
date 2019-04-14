package strategies.graph.shortestPath.floydWarshall;

/**
 * 모든 정점쌍 간의 최단경로를 구하고 싶을때 사용한다.
 */
public class FloydAdavanced {
	static int V;
	static int MAX_V;

	// u -> v 로 가는 간선의 가중치. 간선이 없으면 아주 큰 값을 넣는다.
	static int [][] adj = new int[MAX_V][MAX_V];


	// 플로이드 알고리즘을 외울때 다음 포인트를 기억하자.
	// 1. 간선의 가중치를 나타내는 초기 인접행렬
	// 2. 3중 loop
	// 3. 조건 adj[u][v] = Math.min(adj[u][v], adj[u][k] + adj[k][v])
	void floyd() {
		for (int i=0 ; i<V ; i++) adj[i][i] = 0;

		for (int k=0 ; k<V ; k++)
			for (int u=0 ; u<V ; u++)
				for (int v=0 ; v<V ; v++)
					adj[u][v] = Math.min(adj[u][v], adj[u][k] + adj[k][v]);
	}
}
