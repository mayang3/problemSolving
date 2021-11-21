package algospot;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 승부조작
// https://algospot.com/judge/problem/read/MATCHFIX
public class MATCHFIX {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int C = scanner.nextInt();

		while (C-- > 0) {
			int N = scanner.nextInt(); // 참가하는 선수의 수
			int M = scanner.nextInt(); // 남아있는 경기의 수

			// 각 선수의 현재 승수
			int[] wins = new int[20];
			// [경기번호][0] = 해당 경기의 첫번째 선수 번호
			// [경기번호][1] = 해당 경기의 두번째 선수 번호
			int[][] matches = new int[200][2];

			for (int i = 0; i < N; i++) {
				wins[i] = scanner.nextInt();
			}

			// 선수 0 이 가능한 추가 매치수
			int zeroPlayerMatchCount = 0;

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < 2; j++) {
					matches[i][j] = scanner.nextInt();

					if (matches[i][j] == 0) {
						zeroPlayerMatchCount++;
					}
				}
			}

			boolean success = false;

			for (int totalWins = wins[0]; totalWins <= wins[0] + zeroPlayerMatchCount; totalWins++) {
				success = canWinWith(wins, matches, N, M, totalWins);
				if (success) {
					System.out.println(totalWins);
					break;
				}
			}

			if (!success) {
				System.out.println(-1);
			}
		}
	}

	// 0번 선수가 총 totalWins 승으로 우승할 수 있는지 여부를 확인한다.
	static boolean canWinWith(int[] wins, int[][] matches, int N, int M, int totalWins) {
		if (getMaxValue(wins) >= totalWins) {
			return false;
		}

		// 0번은 소스, 1번은 싱크
		int V = 2 + N + M;
		int[][] capacity = new int[V][V];

		for (int matchNode = 0; matchNode < M; matchNode++) {
			// source 에서 각 경기로 가는 간선
			capacity[0][2 + matchNode] = 1;

			// 각 경기에서 두 선수로 가는 간선
			for (int player = 0; player < 2; player++) {
				// 앞에서 각 경기만큼의 index 를 사용했기 때문에 M 을 더해주어, 그 다음 index 부터 카운팅 해준다.
				capacity[2 + matchNode][2 + M + matches[matchNode][player]] = 1;
			}
		}

		// 각 선수에서 싱크로, 가능한 최대 승수를 용량으로 하는 간선을 추가
		for (int i = 0; i < N; i++) {
			int maxWin = (i == 0 ? totalWins : totalWins - 1);
			capacity[2 + M + i][1] = maxWin - wins[i];
		}

		FordFulkerson fordFulkerson = new FordFulkerson(V, capacity);
		// 이때 모든 경기에 승자를 지정할 수 있는가?
		// 승수는 곧 경기수와 똑같다고 볼 수 있다.
		// 두 사람이 하는 경기마다, 승수는 한개가 나오기 때문에 하나의 경기는 하나의 승수와 똑같다.
		int totalNetworkFlow = fordFulkerson.networkFlow(0, 1);
		return totalNetworkFlow == M;
	}

	private static int getMaxValue(int[] wins) {
		int max = Integer.MIN_VALUE;

		for (int i = 1; i < wins.length; i++) {
			max = Math.max(max, wins[i]);
		}

		return max;
	}

	static class FordFulkerson {
		static int INF = 987654321;
		int V;
		int [][] capacity;
		int [][] flow;
		int networkFlow(int source, int sink) {
			int totalFlow = 0;

			while (true) {
				// BFS 로 증가경로 를 찾는다.
				int [] parent = new int[V];
				Arrays.fill(parent, -1);
				parent[source] = source;

				Queue<Integer> q = new LinkedList<>();
				q.add(source);

				while (!q.isEmpty() && parent[sink] == -1) {
					int here = q.poll();

					for (int there = 0; there < V; there++) {
						// 잔여 용량이 남아 있는 간선을 따라 탐색한다.
						if (capacity[here][there] - flow[here][there] > 0 && parent[there] == -1) {
							q.add(there);
							parent[there] = here;
						}
					}
				}
				
				// 증가 경로가 없다면 종료한다.
				if (parent[sink] == -1) {
					break;
				}
				
				// 증가 경로를 통해 유량을 얼마나 보낼지 결정한다.
				int amount = INF;

				for (int p = sink; p != source; p = parent[p]) {
					amount = Math.min(capacity[parent[p]][p] - flow[parent[p]][p], amount);
				}
				
				// 증가 경로를 통해 유량을 보낸다.
				for (int p = sink; p != source; p = parent[p]) {
					flow[parent[p]][p] += amount;
					flow[p][parent[p]] -= amount;
				}

				totalFlow += amount;
			}

			return totalFlow;
		}

		public FordFulkerson(int V, int[][] capacity) {
			this.V = V;
			this.capacity = capacity;
			this.flow = new int[V][V];
		}
	}
}
