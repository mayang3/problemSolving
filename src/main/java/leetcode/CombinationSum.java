package leetcode;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> ret = new ArrayList<>();

		solve(ret, new ArrayList(), candidates, target, 0);

		return ret;
	}

	private void solve(List<List<Integer>> ret, List<Integer> list, int[] candidates, int target, int i) {
		if (target == 0) {
			ret.add(new ArrayList<>(list));
			return;
		}

		if (i >= candidates.length) {
			return;
		}

		if (target - candidates[i] >= 0) {
			list.add(candidates[i]);
			solve(ret, list, candidates, target-candidates[i], i);
			list.remove(list.size()-1);
		}

		solve(ret,list,candidates,target,i+1);
	}

	public static void main(String[] args) {
		int [] candidates = {2,3,6,7};
		int target = 7;

		CombinationSum combinationSum = new CombinationSum();
		List<List<Integer>> ret = combinationSum.combinationSum(candidates, target);

		System.out.println(ret);
	}
}
