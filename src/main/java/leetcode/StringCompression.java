
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class StringCompression {

	public int compress(char[] chars) {
		List<Pair> pairList = new ArrayList<>();

		pairList.add(new Pair(chars[0], 1));

		for (int i = 1; i < chars.length; i++) {
			// 이전과 같지 않은 문자가 나올 경우
			if (chars[i-1] != chars[i]) {
				pairList.add(new Pair(chars[i], 1));
			} else {
				// 계속 같은 문자가 나올 경우
				pairList.get(pairList.size()-1).cnt += 1;
			}
		}

		int i = 0;

		for (Pair pair : pairList) {
			chars[i++] = pair.c;

			int cnt = pair.cnt;

			if (cnt > 1) {
				String cntStr = String.valueOf(cnt);

				for (int j = 0; j < cntStr.length(); j++) {
					chars[i++] = cntStr.charAt(j);
				}
			}
		}

		return i;
	}

	static class Pair {
		char c;
		int cnt;

		public Pair(char c, int cnt) {
			this.c = c;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) {
		char [] chars = {'a','a','a','b','b','a','a'};
		
		StringCompression stringCompression = new StringCompression();
		System.out.println(stringCompression.compress(chars));
		System.out.println(chars);
	}
}
