package leetcode.contest.weekly_137;

import java.util.*;

/**
 * @author neo82
 */
public class LastStoneWeight {
	public static void main(String[] args) {
		int [] stones = {2,7,4,1,8,1};

		System.out.println(lastStoneWeight(stones));
	}

	public static int lastStoneWeight(int[] stones) {

		LinkedList<Integer> ll = new LinkedList<>();

		for (int stone : stones) {
			ll.add(stone);
		}

		return solve(ll);
	}

	private static int solve(LinkedList<Integer> ll) {
		if (ll.size() == 1) {
			return ll.get(0);
		} else if (ll.size() == 2 && (ll.get(0) == ll.get(1))) {
			return 0;
		}

		Collections.sort(ll, Comparator.reverseOrder());

		int stone1 = ll.removeFirst();
		int stone2 = ll.removeFirst();

		if (stone1 != stone2) {
			ll.add(Math.max(stone1, stone2) - Math.min(stone1, stone2));
		}

		return solve(ll);
	}
}
