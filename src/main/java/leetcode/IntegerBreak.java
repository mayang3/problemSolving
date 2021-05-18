package leetcode;

public class IntegerBreak {
    public int integerBreak(int n) {
        if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }

        Integer [] dp = new Integer[n+1];

        return solve(dp, n);
    }

    private int solve(Integer[] dp, int n) {
        int max = 1;

        if (dp[n] != null) {
            return dp[n];
        }

        for (int i = 1; i <= n; i++) {
            max = Math.max(max, i * solve(dp, n-i));
        }

        return dp[n] = max;
    }

    public static void main(String[] args) {
        IntegerBreak integerBreak = new IntegerBreak();
        System.out.println(integerBreak.integerBreak(10));
    }
}
