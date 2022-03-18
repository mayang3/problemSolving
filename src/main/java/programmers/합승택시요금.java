package programmers;

import java.util.*;

public class 합승택시요금 {
	public int solution(int n, int s, int a, int b, int[][] fares) {
		s--;
		a--;
		b--;

		Map<Integer, List<Pair>> graph = new HashMap<>();

		for (int [] fare : fares) {
			int c = fare[0]-1;
			int d = fare[1]-1;
			int f = fare[2];

			graph.computeIfAbsent(c, t -> new ArrayList<>()).add(new Pair(d, f));
			graph.computeIfAbsent(d, t -> new ArrayList<>()).add(new Pair(c, f));
		}

		PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
		int [] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);

		pq.add(new Pair(s, 0));
		dist[s] = 0;

		while (pq.isEmpty() == false) {
			Pair here = pq.poll();

			if (here.cost > dist[here.v]) {
				continue;
			}

			if (graph.containsKey(here.v)) {
				for (int i = 0; i < graph.get(here.v).size(); i++) {
					Pair there = graph.get(here.v).get(i);

					int nextCost = there.cost + dist[here.v];

					if (nextCost < dist[there.v]) {
						dist[there.v] = nextCost;
						pq.add(new Pair(there.v, nextCost));
					}
				}
			}
		}





		int answer = 0;

		return answer;
	}

	static class Pair {
		int v;
		int cost;

		public Pair(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}


	public static void main(String[] args) {
		합승택시요금 합승택시요금 = new 합승택시요금();

		int [][] fares = {{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}};

		System.out.println(합승택시요금.solution(6,4,5,6, fares));
	}
}
