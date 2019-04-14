package stratgies2.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baejunbeom
 */
@SuppressWarnings("ALL")
public class Naming {

	public static void main(String[] args) {
		System.out.println(getPrefixSuffix("ababbaba"));
	}

	static List<Integer> getPrefixSuffix(String s) {
		List<Integer> ret = new ArrayList<>();

		int [] pi = getPartialMatch(s);

		int k = s.length();

		while (k > 0) {
			// s[..k-1] 은 답이다.
			ret.add(k);
			// s[..k-1] 의 접미사도 되고 접두사도 되는 문자열도 답이다.
			k = pi[k-1];
		}

		return ret;
	}

	private static int[] getPartialMatch(String s) {
		int len = s.length();

		int idx = 1;
		int matched = 0;

		int [] pi = new int[len];
		pi[0] = 0;

		while (idx < len) {
			if (s.charAt(idx) == s.charAt(matched)) {
				matched++;
				pi[idx] = matched;
				idx++;
			} else {
				// 첫글자가 아닌 부분에서 불일치가 발생했다면, 다음 비교 대상까지 비교할 matched idx 를 끌어당긴다.
				if (matched != 0) {
					matched = pi[matched-1];
				} else {
					// 맨 첫글자부터 틀리다면, 현재 pi[idx] 를 결정하고, 다음 배열로 넘어간다.
					pi[idx] = matched;
					idx++;
				}
			}
		}

		return pi;
	}
}
