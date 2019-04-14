package baekjoon.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Timeout 나서 FastReader 로 통과..
 *
 * 500 만건 Read 인데.. O(1억1천) 정도 나온다..
 *
 * 이렇게 해서 통과했지만,, 보다 근본적인 풀이는 quick selection 을 통해 풀 수 있다고 한다.
 */
public class K번째수 {
	public static void main(String[] args) {
		FastReader scanner = new FastReader();

		int N = scanner.nextInt();
		int K = scanner.nextInt();

		int [] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = scanner.nextInt();
		}

		Arrays.sort(arr);

		System.out.println(arr[K-1]);
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
