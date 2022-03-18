package leetcode;

public class NumberOfSetsOfKNonOverlappingLineSegments {
    private static int MOD = (int)1e9+7;

    public int numberOfSets(int n, int k) {

        return 0;
    }

//    public int numberOfSets(int n, int k) {
//        Integer [][] dp = new Integer[n][k+1];
//
//        return solve(dp, n, k, 0);
//    }

    private int solve(Integer [][] dp, int n, int k, int start) {
        if (start >= n && k > 0) {
            return 0;
        } else if (k == 0) {
            return 1;
        }

        if (dp[start][k] != null) {
            return dp[start][k];
        }

        int ways = 0;
        int remainingLines = k - 1;

        // 넣는 경우
        for (int end = start+1; end <= n - remainingLines - 1; end++) {
            ways = ways % MOD + solve(dp, n, remainingLines, end) % MOD;
        }

        // 현재 좌표를 넣지 않는 경우
        ways = ways % MOD + solve(dp, n, k, start+1) % MOD;

        return dp[start][k] = ways % MOD;
    }

    public static void main(String[] args) {
        NumberOfSetsOfKNonOverlappingLineSegments ttt = new NumberOfSetsOfKNonOverlappingLineSegments();
        System.out.println(ttt.numberOfSets(3, 2));

        System.out.println(Integer.MAX_VALUE);
        System.out.println(MOD);
    }
}
