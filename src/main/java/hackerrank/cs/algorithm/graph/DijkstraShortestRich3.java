package hackerrank.cs.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author baejunbeom
 */
@SuppressWarnings("ALL")
public class DijkstraShortestRich3 {
	static List<Pair>[] adj;
	static int[] dist;

	static int INF = 987654321;

	static class Pair {
		int node;
		int cost;

		Pair(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}

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

	static void dijkstra(int start) {

		dist[start] = 0;

		boolean [] visited = new boolean[adj.length];

		while (true) {
			// 현재 정점에서 가장 가까운 정점과 비용
			int here = 0;
			int closet = INF;

			for (int i=0 ; i<adj.length ; i++) {
				if (dist[i] < closet && !visited[i]) {
					here = i;
					closet = dist[i];
				}
			}

			// 모두 방문했다면 종료
			if (closet == INF) break;

			// 방문은 실제 가장 작은 정점에 다가갔을때가 방문한 것!
			visited[here] = true; // 마지막에 빼먹은 조건

			for (int i=0 ; i<adj[here].size() ; i++) {
				Pair pair = adj[here].get(i);

				int there = pair.node;

				if (visited[there]) continue;

				// 다음 정점까지의 비용은 미리 합산해서 반영해 놓는다.
				// 아직 정점 방문 여부는 반영하지 않은 상태이다.
				// 여기서 방문했다고 반영해버리면 위의 제일 거리가 짧은 정점을 찾는 부분에서 가져올 수 없으므로..
				int nextCost = dist[here] + pair.cost;
				dist[there] = Math.min(nextCost, dist[there]);
			}

		}

		for (int d : dist) {
			if (d == 0) continue;
			System.out.print((d == INF ? "-1" : d) + " ");
		}

		System.out.println("");
	}

	public static void main(String[] args) {
		// 이걸로 바꾸니 Accept..
		FastReader in = new FastReader();

		int t = in.nextInt();

		for(int a0 = 0; a0 < t; a0++){
			int n = in.nextInt();

			initAdj(n);
			dist = new int[n];

			Arrays.fill(dist, INF);

			int m = in.nextInt();

			for(int a1 = 0; a1 < m; a1++){
				int x = in.nextInt();
				int y = in.nextInt();
				int r = in.nextInt();

				// undirected graph
				adj[y-1].add(new Pair(x-1, r));
				adj[x-1].add(new Pair(y-1, r));
			}

			int s = in.nextInt();

			dijkstra(s -1);

		}
	}

	private static void initAdj(int n) {
		adj = new List[n];

		for (int i=0 ; i<n ; i++) {
			adj[i] = new ArrayList<>();
		}
	}
}
