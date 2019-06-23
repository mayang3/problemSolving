package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neo82
 */
public class Permutations {

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> ret = new ArrayList<>();

		if (nums == null || nums.length == 0) {
			return ret;
		}

		boolean [] visited = new boolean[nums.length];

		dfs(ret, new ArrayList<>(), visited, nums);

		return ret;
	}

	public void dfs(List<List<Integer>> ret, List<Integer> perm, boolean [] visited, int [] nums) {
		if (perm.size() == nums.length) {
			ret.add(new ArrayList<>(perm));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (visited[i]) {
				continue;
			}

			visited[i] = true;
			perm.add(nums[i]);

			dfs(ret, perm, visited ,nums);

			perm.remove(perm.size() - 1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) {
		int [] nums = {1,2,3,4,5};

		Permutations permutations = new Permutations();
		List<List<Integer>> lists = permutations.permute(nums);

		for (List<Integer> list : lists) {
			System.out.println(list);
		}
	}
}
