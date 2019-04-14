package baekjoon.segment_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@SuppressWarnings("ALL")
public class 최솟값찾기 {

	static class RMQ {
		int n;
		int [] rangeMin;

		RMQ(int [] array) {
			n = array.length;
			rangeMin = new int[n*4];
			init(array, 0, n-1, 1);
		}

		int init(int [] array, int l, int r, int node) {
			if (l == r) {
				return rangeMin[node] = array[l];
			}

			int m = (l + r) / 2;
			int lMin = init(array, l, m, node * 2);
			int rMin = init(array, m+1, r, node * 2 + 1);

			return rangeMin[node] = Math.min(lMin, rMin);
		}

		private int query(int l, int r, int node, int nodeL, int nodeR) {
			if (r < nodeL || nodeR < l) {
				return Integer.MAX_VALUE;
			}

			//
			if (l <= nodeL && nodeR <= r) {
				return rangeMin[node];
			}

			int nodeM = (nodeL + nodeR) / 2;

			int lQuery = query(l, r, node * 2, nodeL, nodeM);
			int rQuery = query(l, r, node * 2 + 1, nodeM + 1, nodeR);

			return Math.min(lQuery, rQuery);
		}

		public int query(int l, int r) {
			return this.query(l, r, 1, 0, this.n-1);
		}
	}



	public static void main(String[] args) {
		FastReader scanner = new FastReader();

		int n = scanner.nextInt();
		int l = scanner.nextInt();

		int [] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		RMQ rmq = new RMQ(arr);

		// string builder 로 수정하고 시간초과 해결
		StringBuilder sb = new StringBuilder();

		for (int d = 0; d < n; d++) {
			sb.append(rmq.query(Math.max(d-l+1, 0), d)).append(" ");
		}

		System.out.println(sb.toString());

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

}
