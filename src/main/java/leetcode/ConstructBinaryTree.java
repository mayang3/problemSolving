package leetcode;

import java.util.Arrays;

public class ConstructBinaryTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode();
        root.val = preorder[0];

        if (preorder.length == 1) {
            return root;
        }

        int rootIndex = findIndex(inorder, root.val);

        if (rootIndex > 0) {
            int [] nextInorder = Arrays.copyOfRange(inorder, 0, rootIndex);
            int [] nextPreorder = Arrays.copyOfRange(preorder, 1, nextInorder.length+1);

            root.left = buildTree(nextPreorder, nextInorder);
        }

        if (rootIndex < preorder.length - 1) {
            int [] nextInorder = Arrays.copyOfRange(inorder, rootIndex+1, inorder.length);
            int [] nextPreorder = Arrays.copyOfRange(preorder, rootIndex+1, preorder.length);

            root.right = buildTree(nextPreorder, nextInorder);
        }

        return root;
    }

    private int findIndex(int[] inorder, int val) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int [] preorder = {1,2,3};
        int [] inorder = {3,2,1};

        ConstructBinaryTree constructBinaryTree = new ConstructBinaryTree();

        TreeNode treeNode = constructBinaryTree.buildTree(preorder, inorder);

        printAll(treeNode);
    }

    private static void printAll(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        System.out.println(treeNode.val);

        printAll(treeNode.left);
        printAll(treeNode.right);
    }

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
}


