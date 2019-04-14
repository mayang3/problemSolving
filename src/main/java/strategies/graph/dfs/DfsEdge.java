package strategies.graph.dfs;

/**
 * @author baejunbeom
 */
public class DfsEdge {
	// 그래프의 인접리스트 표현
	static int[][] adj;

	// discovered[i] = i 번 정점의 발견 순서
	static int[] discovered;
	// finished[i] = dfs(i) 가 종료했으면 1, 아니면 0
	static int[] finished;

	// 지금까지 발견한 정점의 수
	static int counter;

	static void dfs2(int here) {
		discovered[here] = counter++;

		// 모든 인접 정점을 순회하면서,
		for (int i=0 ; i<adj[here].length ; i++) {
			int there = adj[here][i];

			if (there != 1) {
				continue;
			}

			System.out.print(here + "," + there + "is a ");

			// 아직 방문한 적이 없다면 방문한다.
			if (discovered[there] == -1) {
				System.out.println("tree edge");
				dfs2(there);
			}

			// 만약 there 가 here 보다 늦게 발견되었으면, there 는 here 의 후손이다. here->there 의 방향으로 탐색하므로..
			else if (discovered[here] < discovered[there]) {
				System.out.println("forward edge"); // 순방향 간선
			}

			// 만약 dfs2(there) 가 아직 종료하지 않았으면 there 는 here 의 선조이다.
			// 종료하지 않았다면 there -> here 을 호출하고 있다는 말이 되므로..
			else if (finished[there] == 0) {
				System.out.println("back edge"); // 역방향 간선
			}

			// 이외에는 모두 교차 간선이다.
			else {
				System.out.println("cross edge");
			}

			finished[here] = 1;
		}
	}
}
