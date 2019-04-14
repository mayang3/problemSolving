package baekjoon.dp;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/10942
 */
@SuppressWarnings("ALL")
public class 팰린드롬 {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

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
		FastReader scanner = new FastReader();

		int n = scanner.nextInt();

		int [] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		int [][] dp = new int[n+1][n+1];

		solve(dp, arr);

		int m = scanner.nextInt();

		for (int i = 0; i < m; i++) {
			int S = scanner.nextInt() - 1;
			int E = scanner.nextInt() - 1;

			bw.write(dp[S][E] + "\n");
		}

		bw.flush();

	}

	static void solve(int[][] dp, int[] arr) {
		int n = arr.length;


		// length 1 is palindrome
		for (int i = 0; i < n; i++) {
			dp[i][i] = 1;
		}

		// length 2 check
		for (int i = 0; i < n - 1; i++) {
			if (arr[i] == arr[i+1]) {
				dp[i][i+1] = 1;
			}
		}

		for (int k = 3; k <= n; k++) {
			for (int i = 0; i < n-k+1; i++) {
				int j = i+k-1;

				if (dp[i+1][j-1] == 1 && (arr[i] == arr[j])) {
					dp[i][j] = 1;
				}
			}
		}
	}
}
