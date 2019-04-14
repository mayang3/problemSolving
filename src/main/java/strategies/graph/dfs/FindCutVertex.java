package strategies.graph.dfs;

import java.util.Arrays;

/**
 * @author baejunbeom
 */
public class FindCutVertex {
	// 그래프의 인접행렬 표현
	static int[][] adj;
	// 각 정점의 발견 순서
	static int[] discovered;
	// 각 정점이 절단점인지 여부를 저장한다. false 로 초기화한다.
	static boolean[] isCutVertex;

	static int counter = 0;

	public static void main(String[] args) {
		init(8);
		// root 에서의 반환값은 항상 0 이다.
		// 이 문제에서의 결과값은 반환 값을 보는게 아니고, isCutVertex 변수의 값을 본다.
		findCutVertex(2, true);

		// isCutVertex 의 true 값을 가지고 있는 index 가 절단점이다.
		for (int i=0 ; i<isCutVertex.length ; i++) {
			if (isCutVertex[i]) {
				System.out.println(i);
			}
		}
	}

	static void init(int n) {
		adj = new int[n][n];
		connect(0, 1);
		connect(1, 2);
		connect(1, 3);
		connect(2, 1);
		connect(2, 3);
		connect(2, 5);
		connect(3, 1);
		connect(3, 2);
		connect(3, 4);
		connect(3, 5);
		connect(5, 2);
		connect(5, 3);
		connect(5, 6);
		connect(5, 7);

		discovered = new int[n];
		Arrays.fill(discovered, -1);

		isCutVertex = new boolean[n];
	}

	static void connect(int from, int to) {
		adj[from][to] = 1;
		adj[to][from] = 1;
	}


	// here 를 root 로 하는 서브트리에 있는 절단점들을 찾는다.
	// 반환 값은 해당 서브트리에서 역방향 간선으로 갈 수 있는 정점 중, 가장 일찍 발견된 정점의 발견 순서를 반환한다.
	// 처음 호출할 때는 isRoot = true 로 둔다.
	static int findCutVertex(int here, boolean isRoot) {
		// 발견 순서를 기록한다.
		discovered[here] = counter++;
		int ret = discovered[here];

		// 루트인 경우의 절단점 판정을 위해 자손 서브트리의 개수를 센다.
		int children = 0;

		// 인접한 정점들을 모두 방문하면서, 방문시점을 가져온다.
		// 방문 시점중 가장 먼저 방문한 최소값을 리턴한다.
		for (int there=0; there<adj[here].length; there++) {
			if (adj[here][there] != 1) {
				continue;
			}

			if (discovered[there] == -1) {
				children++;
				// 이 서브트리에서 갈 수 있는 가장 높은 정점의 발견시점
				int subtree = findCutVertex(there, false);
				// 그 노드가 자기 자신 이하에 있다면(즉, 가장 먼저 발견된 발견시점이 현재 노드의 발견시점 이후라면..) 현재 위치는 절단점!
				if (!isRoot && subtree >= discovered[here]) {
					isCutVertex[here] = true;
				}

				// 자기 자신 노드의 발견시점과 주변 노드의 발견시점 중, 가장 일찍 발견된 정점의 발견시점
				ret = Math.min(ret, subtree);
			} else {
				ret = Math.min(ret, discovered[there]);
			}
		}

		// 루트인 경우 절단점 판정은 서브트리의 개수로 한다.
		if (isRoot) {
			isCutVertex[here] = (children >= 2);
		}

		return ret;
	}
}
