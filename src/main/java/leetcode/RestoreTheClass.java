package leetcode;

import java.math.BigInteger;

public class RestoreTheClass {
    static int MOD = (int)1e9+7;

    public int numberOfArrays(String s, int k) {
        Integer [] dp = new Integer[s.length()];

        return solve(dp, s, k, 0);
    }

    private int solve(Integer[] dp, String s, int k, int i) {
        if (i >= s.length()) {
            return 1;
        }

        if (dp[i] != null) {
            return dp[i];
        }

        int possible = 0;

        for (int j = i; j < s.length(); j++) {
            if (s.charAt(i) == '0') {
                break;
            }

            long num = Long.parseLong(s.substring(i, j+1));

            if (num > k) {
                break;
            }

            possible += (solve(dp, s, k, j+1) % MOD);
        }

        return dp[i] = possible % MOD;
    }

    public static void main(String[] args) {
        RestoreTheClass restoreTheClass = new RestoreTheClass();
        System.out.println(restoreTheClass.numberOfArrays("600342244431311113256628376226052681059918526204", 703));
    }
}
