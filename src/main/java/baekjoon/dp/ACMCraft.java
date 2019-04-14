package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ACMCraft {
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


	public static void main(String[] args) {
		FastReader scanner = new FastReader();

		int T = scanner.nextInt();

		while (T-- > 0) {
			int N = scanner.nextInt();
			int K = scanner.nextInt();

			int [] costs = new int[N];
			List<Integer> [] adj = new List[N];

			for (int i = 0; i < N; i++) {
				adj[i] = new LinkedList<>();
			}

			for (int i = 0; i < N; i++) {
				costs[i] = scanner.nextInt();
			}

			for (int i = 0; i < K; i++) {
				int pre = scanner.nextInt();
				int after = scanner.nextInt();

				adj[--pre].add(--after);
			}

			int D = scanner.nextInt();

			solve(adj, costs, D);
		}
	}

	static void solve(List<Integer>[] adj, int[] costs, int D) {

		// 1. topological sort
		TopologicalSort topologicalSort = new TopologicalSort(adj);
		Stack<Integer> sortedStack = topologicalSort.tSort();

		// 2. cost calculationg
		Calculator calculator = new Calculator(sortedStack, adj, costs);
		long[] ret = calculator.exec();

		System.out.println(ret[--D]);
	}

	static class Calculator {
		final Stack<Integer> stack;
		final int [] costs;
		final boolean [] visited;
		final List<Integer> [] adj;
		final long [] ret;

		Calculator(Stack<Integer> stack, List<Integer> [] adj, int [] costs) {
			this.visited = new boolean[adj.length];
			this.stack = stack;
			this.adj = adj;
			this.costs = costs;
			this.ret = new long[adj.length];
		}

		long [] exec() {
			while (stack.isEmpty() == false) {
				Integer pop = stack.pop();

				if (visited[pop] == false) {
					ret[pop] = costs[pop];
					dfs(pop);
				}
			}

			return ret;
		}

		void dfs(int here) {
			visited[here] = true;

			for (int i = 0; i < adj[here].size(); i++) {
				Integer there = adj[here].get(i);

				ret[there] = ret[here] + costs[there];
			}
		}
	}

	static class TopologicalSort {
		final Stack<Integer> stack = new Stack<>();
		final boolean [] visited;
		final List<Integer> [] adj;

		TopologicalSort(List<Integer>[] adj) {
			this.visited = new boolean[adj.length];
			this.adj = adj;
		}

		Stack<Integer> tSort() {
			for (int i = 0; i < adj.length; i++) {
				if (visited[i] == false) {
					dfs(i);
				}
			}

			return this.stack;
		}

		private void dfs(int here) {
			visited[here] = true;

			for (int i = 0; i < adj[here].size(); i++) {
				int there = adj[here].get(i);

				if (visited[there] == false) {
					dfs(there);
				}
			}

			stack.add(here);
		}
	}
}
