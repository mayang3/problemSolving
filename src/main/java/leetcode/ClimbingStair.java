package leetcode;

/*

f(1) = 1
f(n) = f(n-1) + f(n-2)


f(1) = 1 -> 1개
f(2) = 2 -> 2개
f(3) = 1 1 1
       1 2
       2 1
       -> 3개
f(4) = 1 1 1 1
	   1 1 2
	   1 2 1
	   2 1 1
	   2 2
	   -> 5개
f(5) = 1 1 1 1 1
	   1 1 1 2
	   1 1 2 1
	   1 2 1 1
	   2 1 1 1
	   1 2 2
	   2 1 2
	   2 2 1
	   => 8개
f(6) = 1 1 1 1 1 1
       1 1 1 1 2
       1 1 1 2 1
       1 1 2 1 1
       1 2 1 1 1
       2 1 1 1 1
       2 2 2

       1 1 2 2
       1 2 1 2
       2 1 1 2
       2 1 2 1
       1 2 2 1
       2 2 1 1

       => 12개..?
       =? 13개일듯??


 */

import java.util.HashMap;
import java.util.Map;

/**
 * @author baejunbeom
 */
public class ClimbingStair {
	private static final Map<Integer, Integer> COUNT_MAP = new HashMap<>();

	public int climbStairs(int n) {
		if (n == 1) {
			return 1;
		}

		if (n == 2) {
			return 2;
		}

		if (COUNT_MAP.containsKey(n)) {
			return COUNT_MAP.get(n);
		}

		int count = climbStairs(n - 1) + climbStairs(n - 2);

		COUNT_MAP.put(n, count);

		return count;
	}

	public static void main(String[] args) {
		ClimbingStair climbingStair = new ClimbingStair();
		int i = climbingStair.climbStairs(30);
		System.out.println(i);
	}
}
