package leetcode.random;

import java.util.Scanner;
import java.util.TreeMap;

public class LeetCode_1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int [] prices = new int[n];

		for (int i = 0; i < n; i++) {
			prices[i] = scanner.nextInt();
		}

		System.out.println(maxProfix(prices));
	}

	public static int maxProfix(int [] prices) {
		TreeMap<Integer, Integer> tm = new TreeMap<>();

		for (int p : prices) {
			Integer key = tm.get(p);

			if (key == null) {
				tm.put(p, 1);
			} else {
				tm.merge(p, 1, Integer::sum);
			}
		}

		int max = 0;

		for (int i = prices.length-1; i > 0 ; i--) {
			// 1. 자기 자신 버린다.
			int self = tm.get(prices[i]);

			if (self == 1) {
				tm.remove(prices[i]);
			} else {
				tm.merge(prices[i], -1, Integer::sum);
			}

			// 2. 앞의 날짜의 최소값을 찾는다.
			int min = tm.firstKey();

			// 3. 최대값을 계산한다.
			max = Math.max(max, prices[i] - min);
		}

		return max;
	}
}
