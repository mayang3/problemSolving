package leetcode.contest.weekly_190;

import java.util.HashMap;
import java.util.Map;

public class Pseudo {

	 public static class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode() {}
	     public TreeNode(int val) { this.val = val; }
	     public TreeNode(int val, TreeNode left, TreeNode right) {
	         this.val = val;
	         this.left = left;
	         this.right = right;
	     }
	 }

	public int pseudoPalindromicPaths(TreeNode root) {
	 	Map<Integer, Integer> countMap = new HashMap<>();
		return solve(root, countMap);
	}

	private int solve(TreeNode root, Map<Integer, Integer> countMap) {
	 	if (root == null) {
	 		return 0;
		}

		countMap.merge(root.val, 1, Integer::sum);

	 	int sum = 0;

	 	if (root.left != null) {
	 		sum += solve(root.left, countMap);
		}

	 	if (root.right != null) {
	 		sum += solve(root.right, countMap);
		}

		if (root.left == null && root.right == null) {
			if (isPalindromicPath(countMap)) {
				sum = 1;
			}
		}

		if (countMap.containsKey(root.val)) {
			countMap.merge(root.val, -1, Integer::sum);

			if (countMap.get(root.val) == 0) {
				countMap.remove(root.val);
			}
		}

		return sum;
	}

	private boolean isPalindromicPath(Map<Integer, Integer> countMap) {
	 	int all = 0;
	 	int one = 0;
	 	int odd = 0;
	 	int even = 0;

	 	for (int cnt : countMap.values()) {
	 		all += cnt;

	 		if (cnt == 1) {
	 			one++;
			} else if (cnt % 2 == 0) {
	 			even++;
			} else if (cnt % 2 != 0) {
	 			odd++;
			}
		}

	 	// 총 숫자가 1개이면 무조건 palindrome, 또는 1번 등장한 숫자가 1개 이상이면 무조건 palindrome 이 아님
	 	if (all == 1) {
	 		return true;
		} else if (one > 1) {
	 		return false;
		}

	 	// 여기까지 왔다는 것은 총 숫자가 1개 초과이고, 1번 등장한 숫자가 1개 이하라는 것
	 	if (all % 2 == 0 && odd == 0) {
	 		return true;
		} else if (one == 1 && odd == 0) {
	 		return true;
		} else if (one == 0 && odd == 1) {
	 		return true;
		}

		return false;
	}

	public static void main(String[] args) {
	 	TreeNode left = new TreeNode(1, new TreeNode(1), new TreeNode(3, null, new TreeNode(3)));
	 	TreeNode right = new TreeNode(1, null, null);

	 	TreeNode root = new TreeNode(2, left, right);

		Pseudo pseudo = new Pseudo();

		int i = pseudo.pseudoPalindromicPaths(root);

		System.out.println(i);
	}
}
