package leetcode;

import java.util.*;

/**
 * @author neo82
 */
public class NetworkDelayTime {
	public int networkDelayTime(int[][] times, int N, int K) {
		Dijkstra dijkstra = new Dijkstra(N);

		for (int [] info : times) {
			int u = info[0] - 1;
			int v = info[1] - 1;
			int w = info[2];

			dijkstra.connect(u,v,w);
		}

		dijkstra.exec(K-1);

		int max = 0;

		for (int d : dijkstra.dist) {
			if (d == Integer.MAX_VALUE) {
				return -1;
			}

			max = Math.max(max, d);

		}

		return max;
	}


	private static class Dijkstra {
		final List<Node>[] adj;
		final PriorityQueue<Node> pq;
		final int [] dist;

		public Dijkstra(int N) {
			// init adj
			this.adj = new List[N];
			for (int i = 0; i < N; i++) {
				this.adj[i] = new ArrayList<>();
			}

			// init pq
			this.pq = new PriorityQueue<>(N, Comparator.comparingInt(o -> o.weight));

			// init dist
			this.dist = new int[N];
			Arrays.fill(this.dist, Integer.MAX_VALUE);
		}

		public void connect(int u, int v, int w) {
			adj[u].add(new Node(v, w));
		}

		public void exec(int start) {
			pq.add(new Node(start, 0));
			dist[start] = 0;

			while (pq.isEmpty() == false) {
				Node node = pq.poll();

				int here = node.v;
				int hereDist = node.weight;

				if (dist[here] < hereDist) {
					continue;
				}

				for (int i = 0; i < adj[here].size(); i++) {
					Node thereNode = adj[here].get(i);

					int there = thereNode.v;
					int nextDist = hereDist + thereNode.weight;

					if (dist[there] > nextDist) {
						dist[there] = nextDist;
						pq.add(new Node(there, nextDist));
					}
				}

			}
		}
	}

	static class Node {
		int v;
		int weight;

		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
	}

	public static void main(String[] args) {
		int [][] times = {{2,1,1},{2,3,1},{3,4,1}};
		int N = 4;
		int K = 2;

		// expected output : 2
		NetworkDelayTime nd = new NetworkDelayTime();
		int res = nd.networkDelayTime(times, N, K);

		System.out.println(res);
	}
}
