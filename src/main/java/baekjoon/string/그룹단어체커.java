package baekjoon.string;

import java.util.Arrays;
import java.util.Scanner;

public class 그룹단어체커 {
	static int [] arr;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		int cnt = 0;

		while (N-- > 0) {
			String s = scanner.next();

			if (isGroup(s)) {
				cnt++;
			}
		}

		System.out.println(cnt);
	}

	/**
	 * 정렬한다음에 앞에서부터 탐색을 한다면,
	 * 만약 단어가 그룹 단어라면, 현재 값이 cur 값보다 작아서는 안된다.
	 *
	 * @param s
	 * @return
	 */
	static boolean isGroup(String s) {
		arr = new int[26];

		char before = s.charAt(0);
		arr[before-97]++;

		for (int i=1 ; i<s.length() ; i++) {
			char c = s.charAt(i);

			// 이미 알파벳이 세어져있는데, 이전값이 현재값과 같지 않다면 그룹 단어가 아니다.
			if (arr[c-97] > 0 && before != c) {
				return false;
			} else {
				// 그룹단어라면 단어의 출현횟수를 + 시켜주고, before 값을 업데이트 시킨다.
				arr[c-97]++;
				before = c;
			}
		}

		return true;
	}
}
