package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ExpressiveWords {
	public int expressiveWords(String s, String[] words) {
		List<Pair> dictList = makeDictList(s);

		int tot = 0;

		for (String w : words) {
			List<Pair> compareList = makeDictList(w);

			if (isStretchy(dictList, compareList)) {
				tot++;
			}
		}

		return tot;
	}

	private boolean isStretchy(List<Pair> dictList, List<Pair> compareList) {
		// 길이가 같지 않은 문자열은 확장할 수 없다.
		if (dictList.size() != compareList.size()) {
			return false;
		}

		for (int i = 0; i < dictList.size(); i++) {
			// 길이가 같아도 문자 자체가 다르면 확장할 수 없다.
			if (dictList.get(i).ch != compareList.get(i).ch) {
				return false;
			}

			int dictCnt = dictList.get(i).cnt;

			// 원본 문자열에 같은 문자가 3개 이하인 그룹인 경우는 정확하게 같은 갯수의 문자가 비교그룹에 존재해야 한다.
			if (dictCnt < 3 && dictCnt != compareList.get(i).cnt) {
				return false;
			} else if (dictCnt >= 3 && dictCnt < compareList.get(i).cnt) {
				// 원본 문자열에 문자가 3개 이상인 그룹에는 문자열의 3개 이상이 존재해야 한다.
				return false;
			}
		}

		return true;
	}

	private List<Pair> makeDictList(String s) {
		List<Pair> dictList = new ArrayList<>();

		for (int i = 0; i < s.length(); i++) {
			char here = s.charAt(i);

			if (i == 0 || dictList.get(dictList.size()-1).ch != here) {
				dictList.add(new Pair(here, 1));
			} else {
				dictList.get(dictList.size()-1).cnt++;
			}
		}

		return dictList;
	}

	static class Pair {
		char ch;
		int cnt;

		public Pair(char ch, int cnt) {
			this.ch = ch;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) {
		String [] words = {"aaaa"};

		ExpressiveWords expressiveWords = new ExpressiveWords();
		System.out.println(expressiveWords.expressiveWords("aaa", words));
	}
}
