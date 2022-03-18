package algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MEASURETIME {

	static long countMoves(int [] A) {
		// 인덱스 1부터 시작하므로 outbound 인 1000000 으로 초기화
		FenwickTree fenwickTree = new FenwickTree(1000000);

		long ret = 0;

		for (int i = 0; i < A.length; i++) {
			// max 값에서 현재값까지의 카운트를 뺴면 나보다 큰 녀석의 카운트가 나온다.
			ret += fenwickTree.getSum(999999) - fenwickTree.getSum(A[i]);

			// 데이터 넣는 부분
			fenwickTree.add(A[i], 1);
		}

		return ret;
	}

	static class FenwickTree {
		int [] tree;

		public FenwickTree(int n) {
			this.tree = new int[n+1];
		}

		// {1,3,5,76,8,5,3,2}
		// A[8] = 1 + 3 + 5 + 76 + 8 + 5 + 3 + 2
		public int getSum(int pos) {
			// 인덱스가 1부터 시작한다고 생각하자.
			++pos;
			int ret = 0;

			while (pos > 0) {
				ret += tree[pos];
				// 다음 구간을 찾기 위해 최종 비트를 지운다.
				pos &= (pos - 1);
			}

			return ret;
		}

		void add(int pos, int val) {
			++pos;

			while (pos < tree.length) {
				tree[pos] += val;
				// 현재 위치의 값을 변경하고 현재 값을 포함하는 상위의 모든 값들을 변경시켜준다.
				// 여기에서 (pos & -pos) 는 마지막 켜진 비트를 구하는 식이다.
				// XXXX1000 (pos) -> XXXX0111 (반전)-> XXXX1000 (-pos, 2의 보수, 이때 앞의 부호비트는 켜진 상태)
				pos += (pos & -pos);
			}
		}
	}

	public static void main(String[] args) {
		FastReader scanner = new FastReader();

		int C = scanner.nextInt();

		while (C-- > 0) {
			int N = scanner.nextInt();

			int [] A = new int[N];

			for (int i = 0; i < N; i++) {
				A[i] = scanner.nextInt();
			}

			System.out.println(countMoves(A));
		}
	}

	static class FastReader  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

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
			return Integer.parseInt(this.next());
		}
	}
}
