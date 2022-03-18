package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FrogJump {
	static int [] NEXT_K = {-1, 0, 1};
	public boolean canCross(int[] stones) {
		Set<Integer> stoneSet = new HashSet<>();

		for (int i = 0; i < stones.length; i++) {
			stoneSet.add(stones[i] + 1);
		}

		return solve(new HashMap<>(), stoneSet, 1, 0, stones[stones.length-1]+1);
	}

	boolean solve(Map<String, Boolean> memo, Set<Integer> stoneSet, int unit, int k, int lastStone) {
		if (unit == lastStone) {
			return true;
		} else if (unit > lastStone) {
			return false;
		}

		if (unit == 1) {
			if (stoneSet.contains(2)) {
				return solve(memo, stoneSet, unit + 1, 1, lastStone);
			} else {
				return false;
			}
		}

		String key = getKey(unit, k);

		if (memo.containsKey(key)) {
			return memo.get(key);
		}

		boolean res = false;

		for (int add : NEXT_K) {
			int jump = add + k;

			if (jump > 0 && stoneSet.contains(unit+jump) && solve(memo, stoneSet, unit+jump, jump, lastStone)) {
				res = true;
				break;
			}
		}

		memo.put(key, res);

		return res;
	}

	private String getKey(int unit, int k) {
		return unit + "_" + k;
	}

	public static void main(String[] args) {
		int [] stones = {0,1,3,5,6,8,12,17};

		FrogJump frogJump = new FrogJump();
		System.out.println(frogJump.canCross(stones));
	}
}
