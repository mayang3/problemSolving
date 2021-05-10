package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class DeepestLeavesSum {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int sum = 0;

        while (q.isEmpty() == false) {
            int size = Integer.valueOf(q.size());

            sum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode poll = q.poll();

                sum += poll.val;

                if (poll.left != null) {
                    q.add(poll.left);
                }

                if (poll.right != null) {
                    q.add(poll.right);
                }
            }
        }
        return sum;
    }

}
