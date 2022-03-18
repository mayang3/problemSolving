package algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MORDOR {
	static class RangeQuery {

		// root node 를 1번으로, left child -> 2*i , right child -> 2*i+1 로 표현한다.
		int[] arr;
		int n; // length of input

		public RangeQuery(int[] input) {
			this.n = input.length;
			this.arr = new int[n * 4];
			init(input, 0, n - 1, 1);
		}

		// O(N)
		int init(int[] input, int left, int right, int node) {
			if (left == right) {
				return this.arr[node] = input[left];
			}

			int mid = (left + right) / 2;

			int leftMin = init(input, left, mid, node * 2); // left child 의 최소값
			int rightMin = init(input, mid + 1, right, node * 2 + 1); // right child 의 최소값

			return this.arr[node] = Math.min(leftMin, rightMin);
		}

		// O(logN)
		public int query(int left, int right) {
			return query(left, right, 1, 0, n - 1);
		}

		// 이 메소드에서 nodeLeft, nodeRight 는 결국, arr 배열에서 node 의 위치를 찾아가는데..
		// 오른쪽 child 로 몇번, 왼쪽 child 로 몇번 가야하는지를 결정하기 위한 "인덱스" 로써 쓰인다.
		// 인덱스로써 쓰인다는 말은.. nodeLeft, nodeRight 로 값을 가져오진 않는다는 의미이다.
		int query(int left, int right, int node, int nodeLeft, int nodeRight) {
			if (right < nodeLeft || nodeRight < left) {
				return Integer.MAX_VALUE;
			}

			if (left <= nodeLeft && nodeRight <= right) {
				return this.arr[node];
			}

			int mid = (nodeLeft + nodeRight) / 2;

			return Math.min(query(left, right, node * 2, nodeLeft, mid),
				query(left, right, node * 2 + 1, mid + 1, nodeRight));
		}

		// 구간 트리를 갱신하고 노드가 표현하는 구간의 최소치를 반환한다.
		public int update(int index, int newValue, int node, int nodeLeft, int nodeRight) {
			// index 가 노드가 표현하는 구간과 상관없는 경우엔 무시한다.
			if (index < nodeLeft || nodeRight < index) {
				return this.arr[node];
			}

			// 트리의 리프까지 내려온 경우
			if (nodeLeft == nodeRight) {
				return this.arr[node] = newValue;
			}

			int mid = (nodeLeft + nodeRight) / 2;

			return Math.min(update(index, newValue, node * 2, nodeLeft, mid),
				update(index, newValue, node * 2 + 1, mid + 1, nodeRight));
		}
	}



	public static void main(String[] args) {
		FastReader fastReader = new FastReader();

		int C = fastReader.nextInt();

		while (C-- > 0) {

			int N = fastReader.nextInt();
			int Q = fastReader.nextInt();

			int [] H = new int[N];
			int [] hForMax = new int[N];

			for (int i = 0; i < N; i++) {
				H[i] = fastReader.nextInt();
				hForMax[i] = -H[i];
			}

			RangeQuery minimumQuery = new RangeQuery(H);
			RangeQuery maximumQuery = new RangeQuery(hForMax);

			for (int i = 0; i < Q; i++) {
				// 등산로의 첫번째 표지판의 번호
				int start = fastReader.nextInt();
				// 등산로의 마지막 표지판의 번호
				int end = fastReader.nextInt();

				int min = minimumQuery.query(start, end);
				int max = Math.abs(maximumQuery.query(start, end));

				System.out.println(max - min);
			}
		}
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
