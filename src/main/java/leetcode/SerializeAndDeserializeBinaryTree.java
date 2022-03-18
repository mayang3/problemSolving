package leetcode;

import java.util.HashMap;
import java.util.Map;

public class SerializeAndDeserializeBinaryTree {


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class Codec {
        int max = 0;

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }

            Map<Long, Integer> map = new HashMap<>();
            solve(root, map, 1);

            StringBuilder sb = new StringBuilder();

            for (Map.Entry<Long, Integer> entry : map.entrySet()) {
                sb.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
            }

            sb.deleteCharAt(sb.length()-1);

            return sb.toString();
        }

        void solve(TreeNode root, Map<Long, Integer> map, long i) {
            map.put(i, root.val);

            if (root.left != null) {
                solve(root.left, map, (i*2));
            }

            if (root.right != null) {
                solve(root.right, map, (i*2)+1);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }

            Integer [] nodes = new Integer[(int)(10e4 + 1)];

            String[] split = data.replaceAll("\\[", "").replaceAll("\\]", "").split(",");

            for (int i = 0; i < split.length; i++) {
                if (split[i].equals("null") == false) {
                    nodes[i] = Integer.valueOf(split[i]);
                }
            }

            return recover(nodes, 1);
        }

        private TreeNode recover(Integer[] nodes, int i) {
            if (i > 1000000 || nodes[i] == null) {
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(nodes[i]));

            root.left = recover(nodes, (i*2));
            root.right = recover(nodes, (i*2)+1);

            return root;
        }
    }

    public static void main(String[] args) {

        TreeNode root = makeTestNode(1);
        Codec codec = new Codec();
        String serialize = codec.serialize(root);
        System.out.println(serialize);
    }

    private static TreeNode makeTestNode(int val) {
        if (val > 1000) {
            return null;
        }

        TreeNode root = new TreeNode(val);
        root.right = makeTestNode(val+1);

        return root;
    }

    private static void printTraverse(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        System.out.println(treeNode.val);

        if (treeNode.left != null) {
            printTraverse(treeNode.left);
        }

        if (treeNode.right != null) {
            printTraverse(treeNode.right);
        }
    }
}


