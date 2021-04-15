package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitions {
    public List<List<String>> partition(String s) {

        int n = s.length();
        boolean [][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < n-1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = true;
            }
        }

        for (int k = 3; k <= n; k++) {
            for (int left = 0; left <= n-k ; left++) {
                int right = left + k - 1;

                if (dp[left+1][right-1] && s.charAt(left) == s.charAt(right)) {
                    dp[left][right] = true;
                }
            }
        }

        List<List<String>> res = new ArrayList<>();

        solve(res, dp, n, new ArrayList<>(), 0, s, 0);

        return res;
    }

    private void solve(List<List<String>> res, boolean[][] dp, int n, List<String> subList, int subLen, String s, int currentIndex) {
        if (currentIndex >= n) {
            if (subLen == n) {
                res.add(new ArrayList<>(subList));
            }

            return;
        }

        for (int right = currentIndex; right < n; right++) {

            if (dp[currentIndex][right]) {
                String subString = s.substring(currentIndex, right+1);
                subList.add(subString);
                solve(res, dp, n, subList, subLen + subString.length(), s,  right+1);
                subList.remove(subList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        PalindromePartitions partitions = new PalindromePartitions();
        System.out.println(partitions.partition("aab"));
    }
}
