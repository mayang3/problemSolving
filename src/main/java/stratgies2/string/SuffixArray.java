package stratgies2.string;

import java.util.*;

public class SuffixArray {

	public static void main(String[] args) {
		System.out.println(getSuffixArrayNaive("mississipi"));
	}

	/**
	 * 일반적인 정렬 알고리즘을 사용해 접미사 배열을 만드는 가장 간단한 방법.
	 *
	 * 시간복잡도는 얼마일까?
	 *
	 * sort() 의 시간복잡도는 O(nlgn) 이지만, 이것은 두 원소의 비교에 상수 시간이 걸릴 때의 이야기이다.
	 * 두 문자열을 비교하는데는 최대 두 문자열의 길이에 비례하는 시간이 걸리므로, 한번 비교에 O(n) 시간이 걸린다고 할 수 있다.
	 * sort() 는 O(nlgn) 번의 비교를 수행하므로, 전체 시간 복잡도는 O(n^2lgn) 이 된다.
	 *
	 * @param S
	 * @return
	 */
	static List<Integer> getSuffixArrayNaive(final String S) {
		// 접미사 시작 위치를 담은 배열을 만든다.
		List<Integer> permutation = new ArrayList<>();

		for (int i=0 ; i<S.length() ; i++) {
			permutation.add(i);
		}

		// 접미사를 비교하는 비교자를 이용해 정렬하면 완성
		// S.subString(o1).compareTo(S.subString(o2)); 의 축약
		Collections.sort(permutation, Comparator.comparing(S::substring));

		return permutation;
	}
}
