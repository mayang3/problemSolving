package strategies;

import java.util.Arrays;

public class WildCardMemoized {

	private static int [][] cache = new int[101][101];

	// pattern
	private String W = "**********a";
	// string to match
	private String S = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";

	int match(int w, int s) {
		// 1. 메모이제이션 적용
		int cacheVal = cache[w][s];

		if (cacheVal != -1) {
			return cacheVal;
		}

		char[] cW = W.toCharArray();
		char[] cS = S.toCharArray();

		// 2. 패턴과 스트링을 확인하면서 증가시킨다.
//		while (w < cW.length && s < cS.length && (cW[w] == '?' || cW[w] == cS[s])) {
//			w++; s++;
//		}

		if (w < cW.length && s < cS.length && (cW[w] == '?' || cW[w] == cS[s])) {
			return cache[w][s] = match(w+1, s+1);
		}

		// 3. w 끝에 도달했다
		if (w == cW.length) {
			return cache[w][s] = (s == cS.length ? 1 : 0);
		}

		// 4. 패턴중에 * 를 만났다.
		if (cW[w] == '*') {
//			for (int skip = 0 ; s + skip < cS.length ; skip++) {
//				if (match(w + 1, skip) == 1) {
//					return 1;
//				}
//			}

			if (match(w+1, s) == 1 || (s < cS.length && match(w, s+1) == 1)) {
				return cache[w][s] = 1;
			}
		}

		return cache[w][s] = 0;
	}

	public static void main(String[] args) {
		WildCardMemoized wildCardMemoized = new WildCardMemoized();

		for (int [] cache : cache) {
			Arrays.fill(cache, -1);
		}

		int match = wildCardMemoized.match(0, 0);

		System.out.println(match);
	}
}
