package strategies.graph.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baejunbeom
 */
public class Dfs {

	// 그래프의 인접리스트 표현
	List<List<Integer>> adj;
	// 각 정점을 방문했는지 여부를 나타낸다.
	List<Boolean> visitied;

	// 깊이 우선 탐색을 구현한다.
	void dfs(int here) {
		System.out.println("DFS visits");

		visitied.add(here, true);

		// 모든 정점을 인접순회하면서
		for (int i=0; i<adj.get(here).size(); i++) {
			int there = adj.get(here).get(i);

			// 아직 방문한적이 없다면 방문한다.
			if (!visitied.get(there)) {
				dfs(there);
			}
		}
	}

	// 모든 정점을 방문한다. (그래프가 끊어진 경우를 위해서..)
	void dfsAll() {
		// visited 를 모두 false 로 초기화한다.
		visitied = new ArrayList<>(adj.size());

		// 모든 정점을 순회하면서, 아직 방문한 적이 없다면 방문한다.
		for (int i=0; i< adj.size(); i++) {
			if (!visitied.get(i)) {
				dfs(i);
			}
		}
	}
}
