package hackerrank.cs.algorithm.string;

import java.util.*;

public class SherlockAndTheValidString {
	static int [] arr = new int[27];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String S = scanner.next();

		if (isValid(S)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	static boolean isValid(String S) {
		for (int i=0 ; i<S.length() ; i++) {
			char c = S.charAt(i);
			arr[c-97]++;
		}

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i=0 ; i<arr.length ; i++) {
			if (arr[i] != 0) {
				if (map.containsKey(arr[i])) {
					map.put(arr[i], map.get(arr[i]) + 1);
				} else {
					map.put(arr[i], 1);
				}
			}
		}

		// 개수가 차이나는 게 2개 이상이면 무조건 실패
		if (map.keySet().size() > 2) {
			return false;
		}

		if (map.keySet().size() == 2) {
			Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();

			Map.Entry<Integer, Integer> first = iterator.next();
			Map.Entry<Integer, Integer> second = iterator.next();

			// 값이 두개인데, 둘중 하나의 중복개수가 1이고 출현빈도도 1이라면, remove 시, 해당 문자가 없어진다.
			if (first.getKey() == 1 && first.getValue() == 1) {
				return true;
			} else if (second.getKey() == 1 && second.getValue() == 1) {
				return true;
			}

			int abs = Math.abs(first.getKey() - second.getKey());

			// 개수가 1차이 이상 나도, 나머지 하나가 값이 1이라면 없애버리면 되므로, 반드시 위의 true 체크 조건 이후에 이 체크가 들어가야 한다.
			if (abs > 1) {
				return false;
			}

			// 개수는 1차이가 나고, 큰 쪽의 개수가 1개가 아니라면 false 이다.
			if (first.getKey() > second.getKey() && first.getValue() != 1) {
				return false;
			} else if (second.getKey() > first.getKey() && second.getValue() != 1) {
				return false;
			}
		}

		// size 가 1이거나,
		// size 가 2인 경우에도,
		// 개수 차이는 1이 나고, 1 많은 문자가 한번만 출현했을 경우에 해당 문자만 빼서 숫자를 맞출 수 있다.
		return true;
	}
}
