package leetcode;

public class DistinctSubsequence {
    public int numDistinct(String s, String t) {
        Integer [][] dp = new Integer[s.length()][t.length()];

        return solve(dp, s, t, 0, 0);
    }

    private int solve(Integer[][] dp, String s, String t, int sIndex, int tIndex) {
        if (tIndex == t.length()) {
            return 1;
        } else if (sIndex == s.length()) {
            return 0;
        }

        if (dp[sIndex][tIndex] != null) {
            return dp[sIndex][tIndex];
        }

        int cnt = 0;

        // 일치한 경우
        if (s.charAt(sIndex) == t.charAt(tIndex)) {
            cnt += solve(dp, s, t, sIndex+1, tIndex+1);
        }

        cnt += solve(dp, s, t, sIndex+1, tIndex);

        return dp[sIndex][tIndex] = cnt;
    }

    public static void main(String[] args) {
        DistinctSubsequence ds = new DistinctSubsequence();
        System.out.println(ds.numDistinct("babgbag", "bag"));
    }
}
