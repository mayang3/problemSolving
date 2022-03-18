package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class LongestDuplicateString {

	public static void main(String[] args) {
		LongestDuplicateString longestDuplicateString = new LongestDuplicateString();
		System.out.println(longestDuplicateString.longestDupSubstring("aa"));
	}

	public String longestDupSubstring(String s) {
		Integer[] suffixArray = getSuffixArray(s);

		String res = "";

		for (int i = 1; i < suffixArray.length; i++) {
			String prefix = commonPrefix(s, suffixArray[i-1], suffixArray[i]);

			if (prefix.length() > res.length()) {
				res = prefix;
			}
		}

		return res;
	}

	String commonPrefix(String s, int i, int j) {
		int len = 0;
		int start = i;

		// s, i
		// s, j
		while (i < s.length() && j < s.length() && s.charAt(i) == s.charAt(j)) {
			i++;
			j++;
			len++;
		}

		return len == 0 ? "" : s.substring(start, start+len);
	}


	public Integer[] getSuffixArray(String s) {
		int n = s.length();
		int t = 1;
		int[] group = new int[n + 1];

		for (int i = 0; i < n; i++) {
			group[i] = s.charAt(i);
		}
		group[n] = -1; // 문자열의 끝에 다다른 경우

		// 결과적으로 접미사 배열이 될 반환 값. 이 배열을 log(n) 번 정렬한다.
		Integer[] perm = new Integer[n];

		for (int i = 0; i < n; i++) {
			perm[i] = i;
		}

		while (t < n) {
			final int[] rank = group;
			final int finalT = t;

			// a,b 는 각 suffix 의 첫번째 문자의 인덱스
			// 최초, 첫 두글자 기준으로 정렬된다.
			Comparator<Integer> comparator = (a, b) -> {
				if (rank[a] != rank[b]) {
					return rank[a] - rank[b];
				}
				return rank[a + finalT] - rank[b + finalT]; // 비교시 문자열의 끝에 다다른 문자가 있다면 항상 우선이 된다.
			};

			Arrays.sort(perm, comparator);

			t *= 2;
			if (t >= n) {
				break;
			}

			// 2t 글자를 기준으로 한 새로운 그룹 번호를 부여한다.
			// 정렬된 접미사들을 맨 앞에서부터 순회하면서 각 접미사에 그룹 번호를 부여한다.
			// 첫 접미사에는 항상 그룹 번호 0번을 주고, 그 이후부터는 이전 접미사와 첫 글자가 같으면 이전 접미사의 그룹 번호를,
			// 아니면 이전 접미사의 그룹 번호에 1을 더한 번호를 부여한다.
			int[] newGroup = new int[n + 1];
			newGroup[n] = -1;
			newGroup[perm[0]] = 0;

			for (int i = 1; i < n; i++) {
				if (comparator.compare(perm[i - 1], perm[i]) < 0) {
					newGroup[perm[i]] = newGroup[perm[i - 1]] + 1;
				} else {
					newGroup[perm[i]] = newGroup[perm[i - 1]];
				}
			}

			group = newGroup;

		}

		return perm;
	}
}
