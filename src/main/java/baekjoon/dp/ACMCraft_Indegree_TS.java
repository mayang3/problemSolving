package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * try 1 time. Accepted!
 */
public class ACMCraft_Indegree_TS {

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
		FastReader fr = new FastReader();

		int T = fr.nextInt();

		while (T-- > 0) {
			int N = fr.nextInt();
			int K = fr.nextInt();

			int [] indegree = new int[N];
			int [] costs = new int[N];
			List<Pair> [] adj = new List[N];

			for (int i = 0; i < N; i++) {
				adj[i] = new LinkedList<>();
			}

			for (int i = 0; i < N; i++) {
				costs[i] = fr.nextInt();
			}

			for (int i = 0; i < K; i++) {
				int X = fr.nextInt() - 1;
				int Y = fr.nextInt() - 1;

				adj[X].add(new Pair(Y, costs[Y]));
				indegree[Y]++;
			}

			int D = fr.nextInt() - 1;

			topologicalSort(adj, indegree, costs, D);

		}

	}

	/**
	 * indegree TS 의 핵심은, indegree 가 0인 녀석들을 queue 에 넣고,
	 *
	 * 큐의 front 에서 꺼내어 indegree 를 감소시키며, indegree 가 0인 건들은 또 다시 큐에 넣는다는 것이다.
	 *
	 * @param adj
	 * @param indegree
	 * @param costs
	 * @param D
	 */
	static void topologicalSort(List<Pair>[] adj, int[] indegree, int [] costs, int D) {
		// 가장 큰 비용을 체크하는데 여기서 min heap 을 쓰는 이유는,
		// indegree 가 0 이 될 경우에 최종 값이 max 값으로 덮어쓰여야 하므로,
		// 임의의 정점 X 에 indegree 가 2일 경우 작은 순서대로 indegree 를 decrease 시켜줘야만, 최종적으로 indegree 가 0이 될때 가장 큰값이 쓰인다.
		PriorityQueue<Pair> q = new PriorityQueue<>((o1, o2) -> o1.cost == o2.cost ? 0 : o1.cost < o2.cost ? -1 : 1);

		int [] sumCosts = new int[adj.length];

		// 1. 최초 indegree 가 0인 리스트를 큐에 삽입한다.
		for (int i = 0; i < adj.length; i++) {
			if (indegree[i] == 0) {
				q.add(new Pair(i, costs[i]));
				sumCosts[i] = costs[i];
			}
		}


		// 2. ts
		while (q.isEmpty() == false) {
			Pair herePair = q.poll();

			int here = herePair.v;

			if (here == D) {
				System.out.println(herePair.cost);
				break;
			}

			for (int i = 0; i < adj[here].size(); i++) {
				Pair therePair = adj[here].get(i);

				int there = therePair.v;

				indegree[there]--;

				if (indegree[therePair.v] == 0) {
					sumCosts[there] = sumCosts[here] + therePair.cost;
					q.add(new Pair(there, sumCosts[there]));
				}
			}
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
