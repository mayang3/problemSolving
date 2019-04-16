package hackerrank.cs.algorithm.sorting;

import java.util.*;

public class FraudulentActivityNotifications {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int d = scanner.nextInt();

		int [] expenditure = new int[n];

		for (int i = 0; i < n; i++) {
			expenditure[i] = scanner.nextInt();
		}

		int notification = 0;

		Map<Integer, Integer> countMap = new TreeMap<>();

		for (int i = 0; i < d; i++) {
			countMap.merge(expenditure[i], 1, Integer::sum);
		}

		for (int i = d; i < n; i++) {
			if (i > d) {
				countMap.merge(expenditure[i-1], 1, Integer::sum);
				decreaseOrRemove(countMap, expenditure[i-d-1]);
			}

			double median = getMedian(countMap, d);

			if (median * 2 <= expenditure[i]) {
				notification++;
			}
		}

		System.out.println(notification);

	}

	static void decreaseOrRemove(Map<Integer, Integer> map, int key) {
		map.merge(key, -1, Integer::sum );

		if (map.get(key) == 0) {
			map.remove(key);
		}
	}

	/**
	 * countMap 을 순회하면서 median 값을 얻어온다.
	 *
	 * 1. d가 홀수인 경우
	 * - 어떤 경우든지
	 *
	 * @param countMap
	 * @param d
	 * @return
	 */
	static double getMedian(Map<Integer, Integer> countMap, int d) {
		int m = (d / 2)+1;
		boolean odd = (d % 2 != 0);

		int sum = 0;
		int beforeKey = 0;

		for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
			sum += entry.getValue();

			if (sum < m) {
				beforeKey = entry.getKey();
				continue;
			}

			// 홀수인 경우
			if (odd) {
				return entry.getKey();
			} else {
				// 짝수인 경우

				// 절반의 인덱스(m) 까지 정확하게 덮었고, 현재 인덱스의 카운트가 2이상인 경우.
				// 즉, 현재 인덱스의 카운트가 sum 을 만드는데 쓰였으므로 (69라인), 현재 인덱스의 카운트가 2 이상이라는 것은
				// median 의 값이 현재 인덱스의 값으로만 구성된다는 뜻이다.
				if (sum == m && entry.getValue() >= 2) {
					return (entry.getKey() * 2D) / 2D;
				} else if (sum == m && entry.getValue() < 2) {
					// 절반의 인덱스(m) 까지 정확하게 덮었고, 현재 인덱스의 카운트가 1인 경우.
					// 즉, 현재 인덱스와 이전 인덱스의 값을 하나씩 가져와야 하는 경우
					return (double)(beforeKey + entry.getKey()) / 2D;
				} else {
					// 짝수이면서 인덱스(m) 까지 정확하게 덮지는 않은 케이스

					int before = sum - entry.getValue();

					// 이 경우에도 이전 key 값을 가져와야 하는 케이스가 있는데, 바로 beforeSum 과 m 이 1차이만 나는 경우이다.
					// 이때는 현재 sum 이 m 을 많이 초과했다 하더라도, 이전키값 + 현재키값이 된다.
					if (before + 1  == m) {
						return (double)(beforeKey + entry.getKey()) / 2D;
					} else {
						// 그 이외의 경우는 전부 덮었으므로 현재 키값만 가지고 계산하면 된다.
						return (entry.getKey() * 2D) / 2D;
					}
				}
			}
		}

		throw new IllegalArgumentException("impossible");
	}
}
