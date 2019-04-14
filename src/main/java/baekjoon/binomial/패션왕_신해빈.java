package baekjoon.binomial;

import java.util.*;

/**
 * http://5stralia.tistory.com/8
 *
 * 위 풀이 참조..
 *
 * 무식하게 모든 조합에 대해 생성해가면서 시도해보려고 하면 time over 난다..
 *
 * n 이 최대 30이므로.. 30! 까지 가능하다
 *
 * 참고로 30! = 265252859812191058636308480000000 이다.
 *
 */
public class 패션왕_신해빈 {


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int cnt = scanner.nextInt();

			Map<String, Integer> map = new HashMap<>();

			for (int i=0 ; i<cnt ; i++) {
				String dress = scanner.next();
				String category = scanner.next();

				if (map.containsKey(category)) {
					map.put(category, map.get(category) + 1);
				} else {
					map.put(category, 1);
				}
			}

			int sum = 1;

			for (int v : map.values()) {
				sum *= (v+1);
			}

			System.out.println(sum - 1);
		}

	}
}
