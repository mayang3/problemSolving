package hackerrank.cs.dataStructure.hash;

import java.util.*;

/**
 * https://www.hackerrank.com/challenges/count-triplets-1/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
 *
 * 최초 시도. 부분 fail.
 *
 * 반례1)
 *
 * 100 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 *
 * 반례2)
 *
 * 5 2
 * 1 2 1 2 4
 *
 * -> 특히 이 반례에서는 순서도 중요함을 알 수 있다 -> 함부로 정렬하면 안됨.
 *
 *
 */
public class CountTriplets {

	static class Pair {
		final int val;
		final int cnt;

		Pair(int val, int cnt) {
			this.val = val;
			this.cnt = cnt;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			Pair pair = (Pair)o;

			return val == pair.val;
		}

		@Override
		public int hashCode() {
			return val;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int r = scanner.nextInt();

		Map<Integer, Integer> linkedHashMap = new LinkedHashMap<>();

		for (int i = 0; i < n; i++) {
			int val = scanner.nextInt();

			Integer existVal = linkedHashMap.get(val);

			if (existVal == null || existVal == 0) {
				linkedHashMap.put(val, 1);
			} else {
				linkedHashMap.put(val, ++existVal);
			}
		}

		List<Pair> pairList = new ArrayList<>();

		for (Map.Entry<Integer, Integer> e : linkedHashMap.entrySet()) {
			pairList.add(new Pair(e.getKey(), e.getValue()));
		}

		int newLen = pairList.size();
		int ans = 0;

		for (int i = 0; i < newLen; i++) {

			int max = -1;

			for (int j = i; j < i+3 && j < newLen; j++) {
				int val = pairList.get(j).val;
				int cnt = pairList.get(j).cnt;

				if (val != 1 && val % r != 0) {
					max = -1;
					break;
				}

				max = Math.max(max, cnt);
			}

			if (max != -1) {
				ans += max;
			}
		}

		System.out.println(ans);

	}


}
