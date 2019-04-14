package baekjoon.dp;

import java.io.*;
import java.util.*;

/**
 * Timeout 난다. 왜?
 */
public class ACMCraft_Dijkstra {
	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new
				InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}


	public static void main(String[] args) throws IOException {
		FastReader scanner = new FastReader();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = scanner.nextInt();

		while (T-- > 0) {
			int N = scanner.nextInt();
			int K = scanner.nextInt();

			int [] costs = new int[N];
			List<Pair>[] adj = new List[N];

			for (int i = 0; i < N; i++) {
				adj[i] = new LinkedList<>();
			}

			Set<Integer> allSet = new HashSet<>();

			for (int i = 0; i < N; i++) {
				costs[i] = scanner.nextInt();
				allSet.add(i);
			}

			Set<Integer> rmSet = new HashSet<>();

			for (int i = 0; i < K; i++) {
				int pre = scanner.nextInt();
				int after = scanner.nextInt();

				after--;
				adj[--pre].add(new Pair(after, costs[after]));
				rmSet.add(after);
			}

			allSet.removeAll(rmSet);
			int start = allSet.iterator().next();

			int D = scanner.nextInt();

			Dijkstra dijkstra = new Dijkstra(adj);
			long[] ret = dijkstra.search(start, costs[start]);

			bw.write(Long.toString(ret[--D]) + "\n");
			bw.flush();
		}
	}

	static class Dijkstra {
		final List<Pair> [] adj;
		final long [] dist;
		final PriorityQueue<Pair> pq;

		Dijkstra(List<Pair> [] adj) {
			this.adj = adj;
			this.dist = new long[adj.length];
			// 큰 순서대로 출력
			this.pq = new PriorityQueue<>((o1, o2) -> o1.cost == o2.cost ? 0 : (o1.cost > o2.cost ? -1 : 1));

			Arrays.fill(dist, 0);
		}

		long [] search(int start, int cost) {
			dist[start] = 0;
			pq.add(new Pair(start, cost));

			while (pq.isEmpty() == false) {
				Pair pair = pq.poll();

				int here = pair.v;
				int hereCost = pair.cost;

				// 이미 처리되어서 현재 정점의 거리의 값이 다음에 처리될 정점의 cost 보다도 크다면 pass
				if (dist[here] > hereCost) {
					continue;
				}

				for (int i = 0; i < adj[here].size(); i++) {
					Pair therePair = adj[here].get(i);

					int there = therePair.v;
					int thereCost = therePair.cost;

					int nextDist = hereCost + thereCost;

					if (dist[there] < nextDist) {
						dist[there] = nextDist;
						pq.add(new Pair(there, nextDist));
					}

				}
			}

			return dist;
		}
	}

	static class Pair {
		int v;
		int cost;

		Pair(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}
}
