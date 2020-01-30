package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NetworkDelayTime {
	public int networkDelayTime(int[][] times, int N, int K) {
		List<Pair>[] graph = new List[N];

		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int [] connection : times) {
			int u = connection[0]-1;
			int v = connection[1]-1;

			graph[u].add(new Pair(v, connection[2]));
		}

		int delay = 0;
		int visitCount = 0;
		boolean [] visited = new boolean[N];

		K--;

		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(K, 0));

		visitCount++;
		visited[K] = true;

		while (q.isEmpty() == false) {
			Pair pair = q.poll();

			int here = pair.v;

			int max = Integer.MIN_VALUE;

			for (int i = 0; i < graph[here].size(); i++) {
				Pair there = graph[here].get(i);

				if (visited[there.v] == false) {
					visited[there.v] = true;
					visitCount++;

					max = Math.max(max,there.weight);
					q.add(new Pair(there.v, there.weight));
				}
			}

			if (max != Integer.MIN_VALUE) {
				delay += max;
			}

		}

		return visitCount != N ? -1 : delay;
	}

	static class Pair {
		int v;
		int weight;

		Pair(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
	}

	public static void main(String[] args) {
		int [][] times = {{1,2,1}};
		int N = 2;
		int K = 1;

		NetworkDelayTime ndt = new NetworkDelayTime();
		int res = ndt.networkDelayTime(times, N, K);

		System.out.println(res);
	}
}
