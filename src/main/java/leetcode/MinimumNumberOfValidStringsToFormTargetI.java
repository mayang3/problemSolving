package leetcode;

/**
 * @author neo82
 */
public class MinimumNumberOfValidStringsToFormTargetI {
    public static void main(String[] args) {
        String [] words = {"abcdef"};
        String target = "zcas";

        MinimumNumberOfValidStringsToFormTargetI minimumNumberOfValidStringsToFormTargetI = new MinimumNumberOfValidStringsToFormTargetI();
        System.out.println(minimumNumberOfValidStringsToFormTargetI.minValidStrings(words, target));
    }

    static int INF = 987654321;

    public int minValidStrings(String[] words, String target) {
        Trie trie = new Trie();

        for (String word : words) {
            trie.update(word);
        }

        Integer[] dp = new Integer[target.length()];

        int ans = solve(dp, trie, target, 0);

        return ans == INF ? -1 : ans;
    }

    private int solve(Integer[] dp, Trie trie, String target, int i) {
        if (i >= target.length()) {
            return 0;
        }

        if (dp[i] != null) {
            return dp[i];
        }

        int min = INF;
        Node cur = trie.root;

        for (int j = i; j < target.length(); j++) {
            cur = trie.search(cur, target.charAt(j));

            if (cur == null) {
                break;
            }

            min = Math.min(min, solve(dp, trie, target, j + 1) + 1);
        }

        return dp[i] = min;
    }

    static class Trie {
        Node root = new Node();

        public void update(String s) {
            Node cur = root;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new Node();
                }

                cur = cur.children[c - 'a'];
            }
        }

        Node search(Node cur, char c) {
            if (cur.children[c - 'a'] != null) {
                return cur.children[c - 'a'];
            }

            return null;
        }
    }


    static class Node {
        Node[] children = new Node[26];
    }
}
