package baekjoon.string;

import java.util.Scanner;

/**
 * 1 try ~ accept
 *
 * {@link 광고} 문제와 연계해서,
 * 최소 묶음 단위를 찾은 다음에, 최소 묶음으로 n 이 나누어 떨어진다면 그 몫을,
 * 나누어 떨어지지 않는다면 1을 출력한다.
 */
@SuppressWarnings("ALL")
public class 문자열제곱 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String S = scanner.next();

			if (".".equals(S)) {
				break;
			}

			int[] pi = computePi(S);

			int len = S.length();

			int min = len - pi[len-1];

			if (len % min == 0) {
				System.out.println(len / min);
			} else {
				System.out.println(1);
			}
		}
	}


	static int [] computePi(String S) {
		int m = S.length();

		int i = 1;
		int longest = 0;

		int [] pi = new int[m];
		pi[0] = 0;

		while (i<m) {
			if (S.charAt(i) == S.charAt(longest)) {
				longest++;
				pi[i] = longest;
				i++;
			} else {
				if (longest != 0) {
					longest = pi[longest-1];
				} else {
					pi[i] = longest;
					i++;
				}
			}
		}

		return pi;
	}
}
