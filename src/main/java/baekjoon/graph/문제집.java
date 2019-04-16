package baekjoon.graph;


import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 위상 정렬을 사용하지만, 위상정렬과 순서를 함께 고려해주어야 하는 문제이다.
 *
 * min heap 을 사용한 topological Sort 로 풀 수 있다.
 *
 * {@link TopologicalSortIndegree} 참조하자.
 */
@SuppressWarnings("ALL")
public class 문제집 {
	static int n;
	static List<Integer>[] adj;
	static int [] indegree;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		n = scanner.nextInt();
		int m = scanner.nextInt();

		adj = new List[n];
		indegree = new int[n];

		for (int i = 0; i < n; i++) {
			adj[i] = new LinkedList<>();
		}

		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;

			adj[a].add(b);
			indegree[b]++;
		}

		topologicalSort();
	}

	private static void topologicalSort() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// 1. 최초 위상정렬 대상 그래프 노드중, 시작 노드들만 pq 에 입력해둔다.
		for (int i = 0; i < n; i++) {
			if (indegree[i] == 0) {
				pq.add(i);
			}
		}

		// 2. pq 에서 최소 노드를 빼면서 계산한다. 프로세스는 다음과 같다.
		// 2-1. 뺀 노드를 바로 print 찍는다.
		// 2-2. 뺀 노드에 연결되어 있는 노드를 가져와서, indegree 를 감소시키고, indegree 가 0인 대상은 다시 pq 에 넣는다.
		// 2-3. indegree 가 0 이 아니라면 상위에 먼저 실행되어야 하는 노드가 더 있다는 뜻이므로, pq 에 넣지 않는다.

		while (!pq.isEmpty()) {
			int here = pq.poll();

			System.out.print((here + 1) + " ");

			for (int i = 0; i < adj[here].size(); i++) {
				int there = adj[here].get(i);

				// 연결되어있는 indegree 를 하나씩 감소시킨다.
				// 왜냐하면 먼저 수행되어야 하는 번호가 여러개일수도 있는데,
				// 마지막 번호가 수행된 이후에야 indegree 가 0이 되고, 그때서야 pq 의 입력대상이 된다.
				indegree[there]--;

				if (indegree[there] == 0) {
					pq.add(there);
				}
			}
		}
	}
}
