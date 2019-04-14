package baekjoon.sliding_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * int 자료형의 양의 최대값은 2147483647 이다.
 *
 * 문제에서 int 자료형으로 커버 가능한 범위는 보통 +- 10^9 까지이다.
 *
 * 10^10 이 되면 무조건 범위가 초과함..
 * 10^9 값은 두개를 더해도 int 의 범위를 초과하지 않기 때문에,
 * 보통 이정도가 MAX 이다.
 *
 * 슬라이딩 윈도우 기법을 이용해서 풀었고,,
 * 답은 맞는것 같은데.. 자꾸 timeout 이 난다..
 *
 * TODO
 */
public class FindMinValue {

	static int N;
	static int L;
	static int [] A;
	static int INF = 987654321;
	static int [][] sliding = new int[2][2];

	static void solve() {

		int [] ret = new int[N];

		int first = INF;
		int second = INF;

		if (L == 1) {
			for (int v : A) {
				System.out.print(v + " ");
			}
			return;
		}

		for (int i=0 ; i<L ; i++) {
			first = Math.min(first, A[i]);

			if (A[i] > first) {
				second = Math.min(second, A[i]);
			}

			ret[i] = first;
			System.out.print(first + " ");
		}

		sliding[(L-1)%2][0] = first;
		sliding[(L-1)%2][1] = second;

		for (int i=L ; i<N ; i++) {
			if (sliding[(i-1)%2][0] == A[i-L]) {
				if (sliding[(i-1)%2][1] > A[i]) {
					sliding[i%2][0] = A[i];
					sliding[i%2][1] = sliding[(i-1)%2][1];
				} else {
					sliding[i%2][0] = sliding[(i-1)%2][1];
					sliding[i%2][1] = A[i];
				}
			} else {

				int ff = INF;
				int ss = INF;

				ff = Math.min(ff, sliding[(i-1)%2][0]);
				ff = Math.min(ff, sliding[(i-1)%2][1]);
				ff = Math.min(ff, A[i]);

				if (sliding[(i-1)%2][0] > ff) {
					ss = Math.min(ss, sliding[(i-1)%2][0]);
				} else if (sliding[(i-1)%2][1] > ff) {
					ss = Math.min(ss, sliding[(i-1)%2][1]);
				} else if (A[i] > ff) {
					ss = Math.min(ss, A[i]);
				}

				sliding[i%2][0] = ff;
				sliding[i%2][1] = ss;
			}

			ret[i] = sliding[i%2][0];
			System.out.print(sliding[i%2][0] + " ");
		}
	}

	public static void main(String[] args) {
		FastReader scanner = new FastReader();

		N = scanner.nextInt();
		L = scanner.nextInt();

		A = new int[N];

		for (int i=0 ; i<N ; i++) {
			A[i] = scanner.nextInt();
		}

		solve();

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
