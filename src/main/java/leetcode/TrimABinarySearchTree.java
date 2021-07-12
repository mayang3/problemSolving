package leetcode;

public class TrimABinarySearchTree {


    public TreeNode trimBST(TreeNode root, int low, int high) {
        int val = root.val;

        TreeNode left = null;
        TreeNode right = null;

        if (root.left != null) {
            left = trimBST(root.left, low, high);
            root.left = left;
        }

        if (root.right != null) {
            right = trimBST(root.right, low, high);
            root.right = right;
        }

        if (isValid(val, low, high)) {
            return root;
        }

        // is not valid
        if (left == null && right == null) {
            return null;
        } else if (right == null) {
            return left;
        }

        return right;
    }

    private boolean isValid(int val, int low, int high) {
        return low <= val && val <= high;
    }

    public static void main(String[] args) {

    }


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

}
