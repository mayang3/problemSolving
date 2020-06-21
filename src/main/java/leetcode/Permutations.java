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

		// 뒤의 수를 먼저 넣는 경우 그 다음 단계에서 앞의 수까지 검사를 해야 하므로 항상 처음부터 loop 를 돌아야 한다.
		for (int i = 0; i < nums.length; i++) {
			if (visited[i]) {
				continue;
			}

			// 현재 위치를 넣는 경우
			visited[i] = true;
			perm.add(nums[i]);

			dfs(ret, perm, visited ,nums);

			perm.remove(perm.size() - 1);
			// 현재 위치의 수를 넣지 않고 다음 수로 넘어 가는 경우
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
