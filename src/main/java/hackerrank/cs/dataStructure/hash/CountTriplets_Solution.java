package hackerrank.cs.dataStructure.hash;

import java.util.*;

public class CountTriplets_Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		long n = scanner.nextLong();
		long r = scanner.nextLong();

		List<Long> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			list.add(scanner.nextLong());
		}

		Map<Long, Long> left = new HashMap<>();
		Map<Long, Long> right = new HashMap<>();

		for (int i = 0; i < n; i++) {
			long key = list.get(i);

			if (right.containsKey(key) == false) {
				right.put(key, 1L);
			} else {
				right.put(key, right.get(key) + 1);
			}
		}

		long ans = 0;

		for (int i = 0; i < n; i++) {
			long key = list.get(i);

			right.put(key, right.get(key)-1);

			if (key % r == 0) {
				ans += left.getOrDefault(key/r, 0L) * right.getOrDefault(key * r, 0L);
			}

			left.put(key, left.getOrDefault(key, 0L)+1);
		}

		System.out.println(ans);
	}
}
