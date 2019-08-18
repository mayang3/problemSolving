package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations2 {
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> ret = new ArrayList<>();

		Arrays.sort(nums);

		solve(ret, new ArrayList<>(), new boolean[nums.length], nums);

		return ret;
	}

	void solve(List<List<Integer>> ret, List<Integer> perms, boolean [] visited, int[] nums) {
		if (perms.size() == nums.length) {
			ret.add(new ArrayList<>(perms));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (visited[i]) {
				continue;
			}

			// first of all, sort the array of nums and then we can check below conditional statement
			// if "num[i-1] == num[i] && visited[i-1] == false" is true, we don't have to revisit nums[i-1],
			// because that permutation was already visited when checked the nums[i-1]
			if (i > 0 && nums[i-1] == nums[i] && visited[i-1] == false) {
				continue;
			}

			visited[i] = true;
			perms.add(nums[i]);

			solve(ret, perms, visited, nums);

			visited[i] = false;
			perms.remove(perms.size() - 1);
		}
	}

	public static void main(String[] args) {
		Permutations2 p2 = new Permutations2();

		int [] nums = {1,1,2};

		List<List<Integer>> lists = p2.permuteUnique(nums);

		System.out.println(lists);
	}
}
