package baekjoon.string;

import java.util.Scanner;

public class 광고 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int m = scanner.nextInt();
		String S = scanner.next();

		int[] pi = computeMinPi(S);

		// 이 문제의 답이 왜 m - pi[m-1] 이 되는지를 이해해보자.
		// 우선 m 만큼의 스크린의 길이가 있다면, 접두사와 접미사가 일치하는 pi 배열의 마지막 인덱스의 길이가,
		// 원본 문자열 이후에 반복된 순서가 된다.
		// 예를 들어, aabaaa 라면,
		// aaba 라는 원본 문자열 이후에 스크린의 길이를 맞추기 위해 aa 가 추가로 나온 것이다.
		// 그러므로 전체 문자열의 길이인 m 에서 aa 의 길이 2를 제외하면 원본 문자열의 길이가 나온다.

		// abaabaa or abaabaab 와 같은 케이스를 눈여겨 보자.
		// 위의 케이스에서 제일 작은 덩어리의 단위는 aba 인데,
		// 결국 pi 배열에서 접두사, 접미사의 일치는 가장 작은 덩어리의 단위의 반복중 전체 or 일부만 되풀이 되도 일치가 된다는 뜻
		// "aba aba a" or "aba aba ab" or "aba aba aba"
		System.out.println(m - pi[m-1]);
	}

	/**
	 *
	 * @param S
	 * @return
	 */
	static int [] computeMinPi(String S) {
		int m = S.length();

		int i=1;
		int longest=0;

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
