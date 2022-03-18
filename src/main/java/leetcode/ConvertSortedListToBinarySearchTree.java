package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ConvertSortedListToBinarySearchTree {

	public static class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode() {}
	    TreeNode(int val) { this.val = val; }
	    TreeNode(int val, TreeNode left, TreeNode right) {
	        this.val = val;
	        this.left = left;
	        this.right = right;
	    }
	}

	public static class ListNode {
	    int val;
	    ListNode next;
	    ListNode() {}
	    ListNode(int val) { this.val = val; }
	    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public TreeNode sortedListToBST(ListNode head) {
		List<Integer> list = new ArrayList<>();

		while (head != null) {
			list.add(head.val);
			head = head.next;
		}

		return solve(list);
	}

	private TreeNode solve(List<Integer> list) {
		if (list.size() == 0) {
			return null;
		}

		int mid = list.size() / 2;

		TreeNode root = new TreeNode(list.get(mid));

		root.left = solve(list.subList(0, mid));
		root.right = solve(list.subList(mid + 1, list.size()));

		return root;
	}

	public static void main(String[] args) {
	}
}
