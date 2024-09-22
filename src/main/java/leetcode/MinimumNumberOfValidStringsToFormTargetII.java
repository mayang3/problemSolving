package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author neo82
 */
public class MinimumNumberOfValidStringsToFormTargetII {
    public static void main(String[] args) {

    }

    static int INF = 987654321;

    public int minValidStrings(String[] words, String target) {
        Set<String> dict = new HashSet<>();

        for (String word : words) {
            String s =  "";
            for (int i = 0; i < word.length(); i++) {
                s += word.charAt(i);
                dict.add(s);
            }
        }

        Integer[] dp = new Integer[target.length()];

        int ans = solve(dp, dict, target, 0);

        return ans == INF ? -1 : ans;
    }

    private int solve(Integer[] dp, Set<String> dict, String target, int i) {
        if (i >= target.length()) {
            return 0;
        }

        if (dp[i] != null) {
            return dp[i];
        }

        int min = INF;

        StringBuilder sb = new StringBuilder();

        for (int j = i; j < target.length(); j++) {
            sb.append(target.charAt(j));

            if (!dict.contains(sb.toString())) {
                break;
            }

            min = Math.min(min, solve(dp, dict, target, j + 1) + 1);
        }

        return dp[i] = min;
    }

}
