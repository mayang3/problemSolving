package hackerrank.cs.algorithm.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 가장 메인이 되는 핵심 알고리즘은,
 *
 * 회문이 아닌 경우, 양쪽 인덱스중에 어느 인덱스를 선택하는지에 대한 알고리즘이다.
 *
 * 의외로 선택 방법은 간단한데, 양쪽 인덱스중에 한개를 건너뛰고 다음 character 부터 검사해보고 회문이 아니라면, 반대쪽 인덱스가 답이 된다.
 *
 * 예를 들어, abfeefa 라고 하면 b,f 가 다른데, b 다음 character 인 f ~ f(feef) 까지가 회문이므로, b 의 인덱스가 답이 된다.
 *
 * 위 알고리즘을 적용하고 나서도 한가지 TC 에서 계속 타임아웃이 발생해서 자잘한 최적화를 더 해주어야 했다.
 *
 * FastReader 와 불필요하게 생성되는 List 의 제거등..
 *
 */
public class PalindromeIndex {

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

	public static void main(String[] args) {
		FastReader fr = new FastReader();

		int n = fr.nextInt();


		for (int i = 0; i < n; i++) {
			String s = fr.next();

			solve(s);
		}

	}

	static void solve(String s) {
		int l = 0;
		int r = s.length()-1;

		boolean palindrome = true;

		while (l < r) {
			if (s.charAt(l) != s.charAt(r)) {
				palindrome = false;

				if (isPalindrome(s, l, r-1)) {
					System.out.println(r);
				} else {
					System.out.println(l);
				}

				break;
			}

			l++;
			r--;
		}

		if (palindrome) {
			System.out.println(-1);
		}
	}

	static boolean isPalindrome(String s, int l, int r) {

		while (l < r) {
			if (s.charAt(l) != s.charAt(r)) {
				return false;
			}

			l++;
			r--;
		}

		return true;
	}
}
