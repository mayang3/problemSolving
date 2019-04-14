package baekjoon.graph;

import java.util.*;

/**
 * 한번에 Accept 되지 못했었는데.. 이유는 어이 없게도 Input 을 잘못 읽었기 때문..
 * 어디를 잘못 읽었었는지는 아래를 확인해보자.
 *
 * 그리고, 또 한가지 주의할 부분은,
 *
 * "도로는 양방향 이고 웜홀은 단방향 이라는 것"
 *
 * 예를 들어, A-B 사이의 거리에서 도로의 비용을 1로 양방향 셋팅했는데, A-B 사이의 거리에 웜홀이 하나 존재한다고 해보자.
 *
 * 이때, 웜홀의 비용도 양방향으로 셋팅한다면, 도로의 비용이 다 날라가게 된다.
 */
@SuppressWarnings("Duplicates")
public class 웜홀 {

	static List<Pair>[] adj;
	static final int INF = 987654321;
	static int [] upper;
	static int N;

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

		int T = scanner.nextInt();

		while (T-- > 0) {
			N = scanner.nextInt();
			int M = scanner.nextInt();
			int W = scanner.nextInt();

			adj = new List[N];

			for (int i = 0; i < N; i++) {
				adj[i] = new ArrayList<>();
			}

			upper = new int[N];

			Arrays.fill(upper, INF);

			for (int i = 0; i < M; i++) {
				int u = scanner.nextInt() - 1;
				int v = scanner.nextInt() - 1;
				int plus = scanner.nextInt();

				adj[u].add(new Pair(v, plus));
				adj[v].add(new Pair(u, plus));
			}

			for (int i = 0; i < W; i++) { // 여기서 i 의 범위를, i<W-1 로 잡아서 마지막 테스트 케이스의 row 한줄을 못읽었다..
				int u = scanner.nextInt() - 1;
				int v = scanner.nextInt() - 1;
				int minus = scanner.nextInt() * -1;

				adj[u].add(new Pair(v, minus));
			}

			List ret = bellmanFord(0);

			if (ret == null || ret.size() == 0) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}

	private static List bellmanFord(int start) {
		upper[start] = 0;

		boolean updated = false;

		// V 번 순회한다.
		for (int iter = 0; iter < N; iter++) {
			updated = false;

			for (int here = 0; here < N; here++) {
				for (int i = 0; i < adj[here].size(); i++) {
					int there = adj[here].get(i).v;
					int cost = adj[here].get(i).cost;

					// here -> there 간선을 따라 완화를 시도한다.
					if (upper[there] > upper[here] + cost) {
						// 성공
						upper[there] = upper[here] + cost;
						updated = true;
					}
				}
			}

			// 모든 간선에 대해 완화가 실패했을 경우, V-1 번도 돌 필요 없이 곧장 종료한다.
			if (!updated) break;
		}

		// V 번 순회했는데도, 완화가 성공한다면 음수 사이클이 존재한다는 것이다.
		if (updated) {
			return Collections.emptyList();
		}

		return Arrays.asList(upper);
	}
}
