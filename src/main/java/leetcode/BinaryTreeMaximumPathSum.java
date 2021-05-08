package leetcode;

public class BinaryTreeMaximumPathSum {
    static int NEG_INF = -987654321;
    int maxSum = NEG_INF;

    public int maxPathSum(TreeNode root) {
        int res = solve(root);

        return maxSum = Math.max(maxSum, res);
    }

    private int solve(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }

        int leftChild = NEG_INF;
        int rightChild = NEG_INF;

        if (root.left != null) {
            leftChild = solve(root.left);
        }

        if (root.right != null) {
            rightChild = solve(root.right);
        }

        int max = Integer.MIN_VALUE;

        max = Math.max(max, leftChild);
        max = Math.max(max, rightChild);
        max = Math.max(max, leftChild + rightChild + root.val);
        max = Math.max(max, leftChild + root.val);
        max = Math.max(max, rightChild + root.val);
        max = Math.max(max, root.val);

        maxSum = Math.max(maxSum, max);

        return Math.max(Math.max(root.val + leftChild, root.val + rightChild), root.val);
    }

    public static void main(String[] args) {

        TreeNode l = new TreeNode(1, null, null);

        TreeNode root = new TreeNode(-2, l, null);

        BinaryTreeMaximumPathSum binaryTreeMaximumPathSum = new BinaryTreeMaximumPathSum();
        System.out.println(binaryTreeMaximumPathSum.maxPathSum(root));

    }
}

class TreeNode {
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