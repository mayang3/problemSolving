package baekjoon.graph;

import java.util.*;

@SuppressWarnings("Duplicates")
public class 타임머신 {
	static int N;
	static List<Pair>[] adj;
	static int [] upper;
	static final int INF = 987654321;

	static class Pair {
		int v;
		int cost;

		Pair(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		N = scanner.nextInt();

		adj = new List[N];
		upper = new int[N];

		Arrays.fill(upper, INF);

		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		int M = scanner.nextInt();

		for (int i = 0; i < M; i++) {
			int A = scanner.nextInt() - 1;
			int B = scanner.nextInt() - 1;
			int C = scanner.nextInt();

			adj[A].add(new Pair(B, C));
		}

		int[] ret = bellmanFord(0);

		if (ret == null || ret.length == 0) {
			System.out.println(-1);
		} else {
			for (int i = 1; i < N; i++) {
				int v = upper[i];
				System.out.println(v == INF ? -1 : v);
			}
		}
	}

	private static int [] bellmanFord(int start) {
		upper[start] = 0;

		boolean update = false;

		// vertex 수만큼 완화를 시도한다.
		for (int iter = 0; iter < N; iter++) {
			update = false;

			for (int here = 0; here < N; here++) {
				for (int i = 0; i < adj[here].size(); i++) {
					int there = adj[here].get(i).v;
					int cost = adj[here].get(i).cost;

					if (upper[there] > (upper[here] + cost)) {
						upper[there] = upper[here] + cost;
						update = true;
					}
				}
			}

			// bellmanFord 의 vertex 를 다 돌기도 전에 완화가 모두 실패한 경우 -> 즉, 최단거리 찾기가 완료된 경우
			if (!update) {
				break;
			}
		}

		if (update) {
			return null;
		}

		return upper;
	}
}
