package strategies.graph.bfs;

import java.util.*;

/**
 */
public class Bfs {
	// 그래프의 인접리스트 표현
	static LinkedList<Integer>[] adj;
	static int N;

	// start 에서 시작해 graph 를 bfs 하고 각 정점의 방문 순서를 반환한다.
	static List<Integer> bfs(int start) {
		// 각 정점의 방문여부
		boolean [] discovered = new boolean[N];
		// 방문할 정점 목록을 유지하는 큐
		Queue<Integer> q = new LinkedList<>();
		// 정점의 방문순서
		List<Integer> order = new ArrayList<>();

		discovered[start] = true;
		q.add(start);

		while (!q.isEmpty()) {
			int here = q.poll();

			// here 를 방문한다.
			order.add(here);

			for (int i=0 ; i<adj[here].size() ; i++) {
				int there = adj[here].get(i);

				// (중요) 처음 보는 정점이면 방문할 목록에 집어넣는다.
				if (!discovered[there]) {
					q.add(there);
					discovered[there] = true;
				}
			}
		}

		return order;
	}

	public static void main(String[] args) {
		N = 9;
		adj = new LinkedList[N];

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

		List<Integer> result = bfs(0);

		for (int v : result) {
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
