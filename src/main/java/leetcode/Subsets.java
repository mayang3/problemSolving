package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> ret = new ArrayList<>();

		solve(ret, new ArrayList<>(), nums, 0);

		return ret;
	}

	public void solve(List<List<Integer>> ret, List<Integer> ll, int [] nums, int currentIndex) {
		if (currentIndex >= nums.length) {
			ret.add(new ArrayList<>(ll));
			return;
		}

		// 1. 현재 item 을 선택하는 경우
		ll.add(nums[currentIndex]);
		solve(ret, ll, nums, currentIndex+1);
		ll.remove(ll.size()-1);

		// 2. 현재 item 을 선택하지 않는 경우
		solve(ret,ll,nums, currentIndex+1);
	}

	public static void main(String[] args) {
		int [] nums = {1,2,3};

		Subsets subsets = new Subsets();
		List<List<Integer>> ret = subsets.subsets(nums);

		for (List<Integer> l : ret) {
			System.out.println(l);
		}
	}
}
