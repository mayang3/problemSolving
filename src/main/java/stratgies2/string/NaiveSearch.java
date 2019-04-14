package stratgies2.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baejunbeom
 */
public class NaiveSearch {

	public static void main(String[] args) {
		System.out.println(naiveSearch("avadakedavra".toCharArray(), "aked".toCharArray()));
	}

	// 짚더미 H 의 부분 문자열로 바늘 N이 출현하는 시작 위치들을 모두 반환한다.
	static List<Integer> naiveSearch(final char [] H, final char [] N) {
		List<Integer> ret = new ArrayList<>();

		// 모든 시작 위치를 다 시도해 본다.
		for (int begin=0 ; begin + N.length <= H.length ; begin++) {
			boolean matched = true;

			// 시작위치부터 시작해서 N 의 문자열중 하나라도 틀린 부분이 있다면, fail
			for (int i=0 ; i < N.length ; i++) {
				if (H[begin+i] != N[i]) {
					matched = false;
					break;
				}
			}

			// 시작위치부터 시작해서 N 의 문자열중 틀린 부분이 없다면 success
			if (matched) {
				ret.add(begin);
			}
		}

		return ret;
	}
}
