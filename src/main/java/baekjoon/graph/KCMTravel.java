package baekjoon.graph;

import java.util.*;

/**
 * 많은 실패 끝에 Accept.
 *
 * 핵심은 [현재노드][현재 비용] 의 2차원 배열로 현재 노드의 시간을 기록해 두는것이다.
 *
 * 즉, 현재 노드는 "여러개의 시간 상태 정보" 를 가지고 있다.
 */
@SuppressWarnings("Duplicates")
public class KCMTravel {

	private static final int INF = 987654321;
	static List<Pair>[] adj;
	static int N;
	static int M;

	static class Pair {
		int v;
		int cost;
		int time;

		Pair(int v, int cost, int time) {
			this.v = v;
			this.cost = cost;
			this.time = time;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			N = scanner.nextInt();
			M = scanner.nextInt();
			int K = scanner.nextInt();

			adj = new List[N];

			for (int i = 0; i < N; i++) {
				adj[i] = new ArrayList<>();
			}

			for (int i = 0; i < K; i++) {
				int u = scanner.nextInt() - 1;
				int v = scanner.nextInt() - 1;
				int c = scanner.nextInt();
				int t = scanner.nextInt();

				adj[u].add(new Pair(v, c, t));
			}

			int [][] memo = dijkstra(0);

			int min = INF;

			for (int c=0 ; c<=M ; c++) {
				min = Math.min(min, memo[N-1][c]);
			}

			System.out.println(min == INF ? "Poor KCM" : min);
		}
	}

	private static int[][] dijkstra(int start) {
		int [][] memo = new int[102][100002];

		for (int [] m : memo) {
			Arrays.fill(m, INF);
		}


		PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> o1.time == o2.time ? 0 : o1.time < o2.time ? -1 : 1);
		pq.add(new Pair(start, 0, 0));

		while (pq.isEmpty() == false) {
			Pair pair = pq.poll();

			int here = pair.v;
			int hereCost = pair.cost;
			int hereTime = pair.time;

			// [현재 노드][현재 비용] 으로 기록된 시간이 현재 노드의 시간보다 최소라면, 현재 노드 다음 노드들은 진행할 필요가 없다.
			if (memo[here][hereCost] != INF && memo[here][hereCost] < hereTime) {
				continue;
			}

			// [현재 노드][현재 비용] 으로 갈 수 있는 시간을 [현재 시간] 으로 설정해 둔다.
			memo[here][hereCost] = hereTime;

			for (int i = 0; i < adj[here].size(); i++) {
				Pair therePair = adj[here].get(i);

				int there = therePair.v;
				int thereTime = therePair.time;
				int thereCost = therePair.cost;

				// cost check
				// 다음 비용이 초과한다면 진행하지 않는다.
				int nextCost = hereCost + thereCost;
				if (nextCost > M) {
					continue;
				}

				// 비용만 초과하지 않는다면, 다음 비용을 계산한다.
				int nextTime = hereTime + thereTime;

				// [there 경로][nextCost] 로 가보지 않은 경로이거나,
				// 가본 경로라 하더라도, 같은 비용의 다음 경로보다 시간이 오래 걸리는 경우라면 재 방문 합니다.
				if (memo[there][nextCost] == INF || memo[there][nextCost] > nextTime) {
					memo[there][nextCost] = nextTime;
					pq.add(new Pair(there, nextCost, nextTime));
				}

			}

		}

		return memo;
	}
}
