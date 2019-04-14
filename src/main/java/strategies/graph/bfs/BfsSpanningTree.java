package strategies.graph.bfs;

import java.util.*;

/**
 * @author baejunbeom
 */
public class BfsSpanningTree {

	static List<Integer> [] adj;


	static void bfs(int start, int[] distance, int[] parent) {
		Arrays.fill(distance, -1);
		Arrays.fill(parent, -1);

		Queue<Integer> q = new LinkedList<>();
		distance[start] = 0;
		parent[start] = start;
		q.add(start);

		while (!q.isEmpty()) {
			int here = q.poll();

			for (int i=0 ; i<adj[here].size() ; i++) {
				int there = adj[here].get(i);

				// 처음 보는 정점이면 방문 목록에 넣는다.
				if (distance[there] == -1) {
					q.add(there);
					// root 에서 현재 정점까지의 거리는 이전 정점까지의 거리 + 1
					distance[there] = distance[here] + 1;
					// 현재 정점의 부모 기록
					parent[there] = here;
				}
			}
		}
	}

	/**
	 * 정점 v 에 도달하고자 할때, 가장 짧은 경로를 반환한다.
	 *
	 * @param v 찾고자 하는 정점
	 * @param parent 부모 정점 배열
	 * @return
	 */
	static List<Integer> shortestPath(int v, int[] parent) {
		List<Integer> path = new ArrayList<>();
		path.add(v);

		// parent[v] == v 라는 말은 root 에 도달했다는 뜻이다.
		while (parent[v] != v) {
			v = parent[v];
			path.add(v);
		}

		Collections.reverse(path);
		return path;
	}

	public static void main(String[] args) {
		adj = new List[9];

		int [] distance = new int[adj.length];
		int [] parent = new int[adj.length];

		for (int i=0; i<9; ++i)
			adj[i] = new LinkedList();

		connect('a', 'b');
		connect('a', 'd');
		connect('a', 'e');
		connect('a', 'h');

		connect('b', 'c');
		connect('b', 'd');
		connect('b', 'a');

		connect('c', 'b');
		connect('c', 'g');
		connect('c', 'f');

		connect('d', 'a');
		connect('d', 'b');
		connect('d', 'g');

		connect('e', 'a');
		connect('e', 'f');

		connect('f', 'e');
		connect('f', 'c');

		connect('g', 'd');
		connect('g', 'c');
		connect('g', 'i');

		bfs(0, distance, parent);

		List<Integer> paths = shortestPath(('i' - 97), parent);

		for (int v : paths) {
			System.out.println((char)(v + 97));
		}
	}

	static void connect(char fc, char tc) {
		int from = fc - 97;
		int to = tc - 97;

		if (!adj[from].contains(to)) {
			adj[from].add(to);
		}

		if (!adj[to].contains(from)) {
			adj[to].add(from);
		}
	}
}
