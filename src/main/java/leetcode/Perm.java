package leetcode;

/**
 * @author neo82
 */
public class Perm {
	public String getPermutation(int n, int k) {
		int [] nums = getNum(n);
		boolean [] visited = new boolean[n];

		StringBuilder res = new StringBuilder();
		Dfs dfs = new Dfs(k);

		dfs.solve(res, visited, nums);

		return res.toString();
	}

	static class Dfs {
		int k;

		public Dfs(int k) {
			this.k = k;
		}

		void solve(StringBuilder res, boolean[] visited, int[] nums) {
			if (res.length() == nums.length) {
				k--;
			}

			if (k == 0) {
				return;
			}

			for (int i = 0; i < nums.length; i++) {
				if (visited[i]) {
					continue;
				}

				if (k == 0) {
					return;
				}

				visited[i] = true;
				res.append(nums[i]);

				solve(res, visited, nums);

				if (k == 0) {
					return;
				}

				visited[i] = false;
				res.deleteCharAt(res.length()-1);
			}
		}
	}



	private int[] getNum(int n) {
		int [] nums = new int[n];

		for (int i = 1; i <= n ; i++) {
			nums[i-1] = i;
		}

		return nums;
	}

	public static void main(String[] args) {
		Perm perm = new Perm();
		System.out.println(perm.getPermutation(3, 3));
	}
}
