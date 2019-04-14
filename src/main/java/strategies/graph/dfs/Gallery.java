package strategies.graph.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 이 알고리즘의 시간복잡도는 O(V+E) 가 되는데,
 *
 * 그 이유는 최초 installCamera() method 에서 Vertex 만큼 순회를 하고,
 *
 * dfs() method 내부에서 Edge 만큼 순회를 하지만, 방문 여부를 체크해서 정확히 한번씩만 순회하기 때문이다.
 *
 * @author baejunbeom
 */
public class Gallery {
	static final int MAX_V = 1000;

	static int V;

	static List<Integer>[] adj = new List[MAX_V];
	static boolean [] visited;

	static final int UNWATCHED = 0;
	static final int WATCHED = 1;
	static final int INSTALLED = 2;

	// 지금까지 설치한 카메라의 총 수
	static int installed;

	// here 로부터 깊이 우선 탐색을 하고, here 의 정보를 반환한다.
	// 깊이 우선 탐색을 하기 때문에 leaf 부터 정보를 확인하게 된다.
	// (here 정점에 감시카메라가 설치되었는지, 안되었는지..)
	static int dfs(int here) {
		visited[here] = true;

		// 인덱스 순서대로 0:감시 안된 자식 노드의 갯수, 1:감시된 자식 노드의 갯수, 2:카메라 설치된 자식 노드의 갯수
		int [] children = {0,0,0};

		for (int i=0 ; i<adj[here].size() ; i++) {
			int there = adj[here].get(i);
			if (!visited[there]) {
				++children[dfs(there)];
			}
		}

		// 자손 노드 중 감시되지 않은 노드가 있을 경우, 카메라를 설치한다.
		if (children[UNWATCHED] > 0) {
			++installed;
			return INSTALLED;
		}

		// 자손 노드 중 카메라가 설치된 노드가 있을 경우 설치할 필요가 없다.
		if (children[INSTALLED] > 0) {
			return WATCHED;
		}

		return UNWATCHED;
	}

	// 그래프를 감시하는 데 필요한 카메라의 최소 수를 구한다.
	static int installCamera() {
		installed = 0;
		visited = new boolean[V];

		for (int u=0 ; u<V ; u++) {
			if (!visited[u] && dfs(u) == UNWATCHED) {
				++installed; // 끊어진 그래프일 경우
			}
		}

		return installed;
	}

	static void connect(int from, int to) {
		adj[from].add(to);
		adj[to].add(from);
	}

	static void adjInit() {
		for (int i=0 ; i<MAX_V ; i++) {
			adj[i] = new ArrayList<>();
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int testCases = scanner.nextInt();

		for (int tc=0 ; tc<testCases ; tc++) {
			V = scanner.nextInt();

			int h = scanner.nextInt();

			adjInit();

			for (int j=0 ; j<h ; j++) {
				connect(scanner.nextInt(), scanner.nextInt());
			}

			System.out.println(installCamera());
		}
	}

}
