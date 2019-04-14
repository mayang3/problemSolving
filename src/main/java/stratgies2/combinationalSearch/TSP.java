package stratgies2.combinationalSearch;

import java.util.LinkedList;

/**
 * 여행하는 외판원 문제의 조합탐색 해결법
 */
public class TSP {
	// 1 * 10^200을 의미
	// eg) 1 * 10^2 = 100
	static double INF = 1e200;
	static int MAX = 30;
	// 도시의 수
	static int n;
	// 두 도시간의 거리를 저장하는 배열
	static double [][] dist = new double[MAX][MAX];
	// 지금까지 찾은 최적해
	static double best;


	static void search(LinkedList<Integer> path, boolean[] visited, double currentLength) {
		int here = path.getLast();

		// base case : 모든 도시를 다 방문했을 때는 0번 도시로 돌아가고 종료한다.
		if (path.size() == n) {
			best = Math.min(best, currentLength + dist[here][0]);
			return;
		}

		// 다음 방문할 도시를 전부 시도해 본다.
		for (int next=0 ; next<n ; next++) {
			if (visited[next]) {
				continue;
			}

			path.addLast(next);

			visited[next] = true;
			search(path, visited, currentLength + dist[here][next]);
			visited[next] = false;

			path.removeLast();

		}
	}

	static double solve() {
		// best 를 매우 큰 값으로 초기화한다.
		best = INF;

		boolean [] visited = new boolean[n];
		LinkedList<Integer> path = new LinkedList<>();
		path.add(0);
		visited[0] = true;

		search(path, visited, 0);

		return best;
	}
}
