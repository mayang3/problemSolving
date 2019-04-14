package baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 완전이진트리 {
	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		/**
		 * StringTokenizer 는 " \t\n\r\f"를 기준으로 문자열을 잘라서 저장해둔다.
		 *
		 * stringTokenizer 의 hasMoreElements 와 hasMoreTokens 는 기본적으로 같으나,
		 *
		 * hasMoreElemtns 는 Enumeration interface 를 지원하기 위해 추가된 메소드이다.
		 *
		 * @return
		 */
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
			try {
				return br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return "";
		}

	}

	public static void main(String[] args) {
		FastReader fr = new FastReader();

		int N = fr.nextInt();

		// 0 레벨은 배열을 순서대로 order 하고 division 2 한 값으로 취하자.

		// 1 레벨은 홀수 짝수로 나누면 무조건 차이는 1이 된다.
		// 그런데, 루트에서 하나를 사용했으므로, 짝수, 홀수중에 한가지 값은 모자르게 된다.
		// 그러므로, 짝수 홀수 리스트 중에 많은 노드중 하나를 빼와서 나머지 하나에 넣어주어야 한다.
		// 아무값이나 상관없으므로, 맨 끝이나 맨 앞에서 하나를 빼서 넣어주자.

		// 2 레벨부터는 사진첩 참조.. 1시간 고민해보고 일단 스킵..

	}

}
