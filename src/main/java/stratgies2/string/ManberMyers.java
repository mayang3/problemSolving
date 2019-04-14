package stratgies2.string;

import java.util.*;

/**
 * 접미사 배열을 계산하는 manber-myers algorithms
 */
public class ManberMyers {

	public static void main(String[] args) {
		System.out.println(ManberMyers.getSuffixArray("mississipi"));
	}

	// s의 접미사 배열을 계산한다.
	static List<Integer> getSuffixArray(String S) {
		int n = S.length();

		// group[i] = 접미사들을 첫 t글자를 기준으로 정렬했을 때, S[i..] 가 들어가는 그룹 번호,
		// t=1 일 때는 정렬할 것 없이 S[i..] 의 첫글자로 그룹 번호를 정해줘도 같은 효과가 있다.
		int t=1;

		int [] group = new int[n+1];

		for (int i=0 ; i<n ; i++) {
			group[i] = S.charAt(i);
		}

		group[n] = -1;

		// 결과적으로 접미사 배열이 될 반환값. 이 배열을 log(n) 번 정렬한다.
		List<Integer> perm = new ArrayList<>(n);

		for (int i=0 ; i<n ; i++) {
			perm.add(i);
		}

		while (t < n) {
			MyComparator myComparator = new MyComparator(group, t);
			// group[] 은 첫 t글자를 기준으로 계산해 뒀다.
			// 첫 2t 글자를 기준으로 perm 을 다시 정렬한다.
			Collections.sort(perm, myComparator);

			// 2t 글자가 n을 넘는다면 이제 접미사 배열이 완성이다.
			t *= 2;

			if (t >= n) {
				break;
			}

			// 2t 글자를 기준으로 한 그룹 정보를 만든다.
			int [] newGroup = new int[n+1];

			newGroup[n] = -1;
			// 첫번째 접미사에는 항상 그룹번호 0을 준다.
			newGroup[perm.get(0)] = 0;

			for (int i=1 ; i<n ; i++) {
				// 이전 접미사와 첫 글자가 같으면 이전 접미사의 그룹 번호를,
				if (myComparator.compare(perm.get(i-1), perm.get(i)) == 0) {
					newGroup[perm.get(i)] = newGroup[perm.get(i-1)];
					// 아니면 이전 접미사의 그룹 번호에 1을 더한 번호를 부여한다.
				} else {
					newGroup[perm.get(i)] = newGroup[perm.get(i-1)] + 1;
				}
			}

			group = newGroup;
		}

		return perm;
	}

	/**
	 * @author baejunbeom
	 */
	static class MyComparator implements Comparator<Integer> {
		final int [] group;
		final int t;

		MyComparator(int [] group, int t) {
			this.group = group;
			this.t = t;
		}

		@Override
		public int compare(Integer a, Integer b) {
			// 첫 글자가 다르면 이들을 이용해서 비교한다.
			if (group[a] != group[b]) {
				return ((Integer)group[a]).compareTo(group[b]);
			}

			// 첫 글자가 다르지 않다면 S[a+t..] 와[b+t..] 의 첫 t 글자를 비교한다.
			return ((Integer)group[a+t]).compareTo(group[b+t]);
		}
	}
}
