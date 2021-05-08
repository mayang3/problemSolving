package leetcode;

public class CountGoodNodesInBinaryTree {

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

    public int goodNodes(TreeNode root) {
        return solve(root, -1);
    }

    private int solve(TreeNode root, int max) {

         int res = root.val >= max ? 1 : 0;

         if (root.left != null) {
             res += solve(root.left, Math.max(max, root.val));
         }

         if(root.right != null) {
             res += solve(root.right, Math.max(max, root.val));
         }

        return res;
    }
}
