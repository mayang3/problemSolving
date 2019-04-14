package hackerrank.compete.bitSetGo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * bear://x-callback-url/open-note?id=6BB0394D-25B0-4E2C-BD9E-83A136023C6B-54277-0002CADADDAD856F
 */
public class BeLikeBumble2 {
	static final int M = (int)1e9 + 7;
	static long [] S = new long[10001];

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

	static {
		long f = 1;
		long s = 1;
		S[1] = 1;

		for (int i = 2; i <= 10000; i++) {
			f = (f + (6 * (i-1))  % M) % M;
			s = S[i] = (s + f) % M;
		}
	}

	public static void main(String[] args) {
		FastReader scanner = new FastReader();

		int T = scanner.nextInt();

		while (T-- > 0) {
			long c = scanner.nextLong();
			int n = scanner.nextInt();

			System.out.println(c * S[n] % M);
		}
	}
}
