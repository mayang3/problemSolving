package leetcode;

public class DiameterOfBinaryTree {
    static class TreeNode {
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

    public int diameterOfBinaryTree(TreeNode root) {
        Ret ret = solve(root);
        return ret.max;
    }

    private Ret solve(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new Ret(0, 0);
        }

        int leftLen = 0;
        int rightLen = 0;
        int max = 0;

        if (root.left != null) {
            Ret leftRet = solve(root.left);
            leftLen = leftRet.len + 1;
            max = Math.max(max, leftRet.max);
        }

        if (root.right != null) {
            Ret rightRet = solve(root.right);
            rightLen = rightRet.len + 1;
            max = Math.max(max, rightRet.max);
        }

        return new Ret(Math.max(leftLen, rightLen), Math.max(max, leftLen+rightLen));
    }

    static class Ret {
        int len;
        int max;

        public Ret(int len, int max) {
            this.len = len;
            this.max = max;
        }
    }

    public static void main(String[] args) {
        DiameterOfBinaryTree diameterOfBinaryTree = new DiameterOfBinaryTree();
        System.out.println(diameterOfBinaryTree.diameterOfBinaryTree(null));
    }
}


