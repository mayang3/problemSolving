package algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ROUTING {

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

	static class Pair {
		int v;
		double weight;

		public Pair(int v, double weight) {
			this.v = v;
			this.weight = weight;
		}
	}


	public static void main(String[] args) {
		FastReader scanner = new FastReader();

		int C = scanner.nextInt(); // 테스트 케이스의 수

		while (C-- > 0) {
			int N = scanner.nextInt(); // 컴퓨터의 수
			int M = scanner.nextInt(); // 회선의 수

			Map<Integer, List<Pair>> graph = new HashMap<>();

			for (int i = 0; i < M; i++) {
				int a = scanner.nextInt();
				int b = scanner.nextInt();
				double c = scanner.nextDouble();

				graph.computeIfAbsent(a, t -> new ArrayList<>()).add(new Pair(b, c));
				graph.computeIfAbsent(b, t -> new ArrayList<>()).add(new Pair(a, c));
			}

			Dijkstra dijkstra = new Dijkstra(graph, N);

			System.out.println(dijkstra.solve(0));
		}
	}

	static class Dijkstra {
		Map<Integer, List<Pair>> graph;
		double [] dist;
		int N;

		public Dijkstra(Map<Integer, List<Pair>> graph, int N) {
			this.graph = graph;
			this.dist = new double[N];
			this.N = N;

			Arrays.fill(dist, Double.MAX_VALUE);
		}

		String solve(int start) {
			PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> {
				if (o1.weight == o2.weight) {
					return 0;
				}

				return o1.weight < o2.weight ? -1 : 1;
			});

			this.dist[start] = 1;
			pq.add(new Pair(start, 1));

			while (pq.isEmpty() == false) {
				Pair here = pq.poll();

				if (here.weight > dist[here.v]) {
					continue;
				}

				if (graph.containsKey(here.v)) {
					for (int i = 0; i < graph.get(here.v).size(); i++) {
						Pair there = graph.get(here.v).get(i);

						double nextWeight = dist[here.v] * there.weight;

						if (nextWeight < dist[there.v]) {
							dist[there.v] = nextWeight;
							pq.add(new Pair(there.v, nextWeight));
						}
					}
				}
			}


			return String.format("%.10f", dist[N-1]);
		}
	}
}
