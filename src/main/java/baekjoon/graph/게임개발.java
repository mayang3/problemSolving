package baekjoon.graph;

import java.util.*;

/**
 * 이 문제는 두가지 함정이 있었다.
 *
 * 한 가지는, 위상정렬을 통해 방문순서를 정해야 한다는 것이다.
 *
 * 예를 들어 2->1 의 순서로 방문해야만 할 경우를 보자.
 *
 * (입력 예시)
 * 2
 * 10 2 -1
 * 10 -1
 *
 *
 * 그냥 방문하게 되면, dfs 의 최초 로직에서 visited 여부가 true 가 되어서, 2->1 방문때 방문이 되지 않는다.
 *
 * 그리고 두 번째 함정은, 다이아몬드 구조일때, 비용이 더 높은 노드는 재방문이 가능해야 한다는 것이다.
 *
 * 예를 들어,
 *
 * 2->1, 2->3, 1->4, 3->4 의 순서로 방문되어야 한다고 생각해보자.
 *
 * 그렇다면 마지막인 4를 방문할때는, 1 or 3 중에 더 오래 걸리는 녀석이 방문되어야 하므로 재방문이 가능해야 한다.
 *
 */
public class 게임개발 {
	static List<Pair>[] adj;
	static boolean [] visited;
	static int [] costs;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		adj = new List[N];
		visited = new boolean[N];
		costs = new int[N];

		for (int i = 0; i < N; i++) {
			adj[i] = new LinkedList<>();
		}

		for (int here = 0; here < N; here++) {
			int cost = scanner.nextInt();

			costs[here] = cost;

			while (true) {
				// 먼저 지어져야 하는 건물들
				int v = scanner.nextInt();

				if (v == -1) {
					break;
				}

				adj[--v].add(new Pair(here, cost));
			}
		}


		Stack<Integer> st = new Stack<>();
		for (int i = 0; i < N; i++) {
			if (visited[i] == false) {
				dfs(i, st);
			}
		}

		Arrays.fill(visited, false);

		while (st.isEmpty() == false) {
			int pop = st.pop();

			if (visited[pop] == false) {
				dfs(pop);
			}
		}

		for (int v : costs) {
			System.out.println(v);
		}
	}

	static void dfs(int here) {
		visited[here] = true;

		for (int i = 0; i < adj[here].size(); i++) {
			Pair pair = adj[here].get(i);

			int there = pair.v;

			if (visited[there] == false || costs[there] < costs[here] + pair.cost) {
				costs[there] = costs[here] + pair.cost;
				dfs(there);
			}
		}
	}

	private static void dfs(int here, Stack<Integer> stack) {
		// 그냥 방문하면 순서에 의존적이지 않게 방문 될 수 있다.
		// 예를 들어, 2->1 순으로 방문되어야 한다고 할 때,
		// 그냥 방문하면 아래 visited[here] = true 로직을 통해 1번이 먼저 방문될 수 있다.
		visited[here] = true;

		for (int i = 0; i < adj[here].size(); i++) {
			Pair pair = adj[here].get(i);

			int there = pair.v;

			if (visited[there] == false) {
				dfs(there, stack);
			}
		}

		stack.add(here);
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
