package hackerrank.cs.algorithm.implement;

import java.util.*;

// time over
public class BiggerIsGreater {
	static char [] arr;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			ret = new ArrayList<String>();
			String S = scanner.next();
			arr = S.toCharArray();

			LinkedList<Character> linkedList = new LinkedList<Character>();

			for (char v : arr) {
				linkedList.add(v);
			}

			Collections.sort(linkedList);
			solve(linkedList, new ArrayList<Character>());

			if (ret.size() < 1) {
				System.out.println("no answer");
			} else {
				System.out.println(ret.get(0));
			}
		}
	}

	static List<String> ret;

	static void solve(LinkedList<Character> linkedList, List<Character> ans) {
		if (linkedList == null || linkedList.isEmpty()) {
			StringBuilder sb = new StringBuilder();

			for (int v : ans) {
				sb.append((char)v);
			}

			ret.add(sb.toString());
			return;
		}

		for (int i=0 ; i<linkedList.size() ; i++) {
			LinkedList<Character> newList = new LinkedList<Character>(linkedList);

			List<Character> newRet = new ArrayList<Character>(ans);
			newRet.add(newList.remove(i));

			if (isSmall(newRet) || ret.size() >= 1) {
				continue;
			}

			solve(newList, newRet);
		}
	}

	static boolean isSmall(List<Character> newRet) {
		for (int i=0 ; i<newRet.size() ; i++) {
			if (newRet.get(i) < arr[i]) {
				return true;
			} else if (newRet.get(i) > arr[i]) {
				return false;
			}
		}

		// 조합의 경우가 입력과 모든 자리가 같은 경우는 결과값에 포함하지 않는다.
		if (newRet.size() == arr.length) {
			return true;
		}

		// 입력과 자리수가 다른데 현재까지의 자리수가 같은 경우라면,
		// 다음 자리수를 좀 더 확인해봐야 한다.
		return false;
	}
}
