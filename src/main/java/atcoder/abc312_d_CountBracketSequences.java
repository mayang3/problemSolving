package atcoder;

import java.util.Scanner;

/**
 * @author neo82
 */
public class abc312_d_CountBracketSequences {

    static int MOD = 998244353;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String S = scanner.next();

        if (S.length() % 2 != 0) {
            System.out.println(0);
            return;
        }

        Integer[][] dp = new Integer[S.length()][S.length() + 1];

        System.out.println(solve(dp, S, 0, 0));
    }

    private static int solve(Integer[][] dp, String s, int left, int i) {
        int right = i - left;
        int N = s.length();

        if (i >= N) {
            if (left == right) {
                return 1;
            } else {
                return 0;
            }
        }

        if (dp[i][left] != null) {
            return dp[i][left];
        }

        char cur = s.charAt(i);

        long ans = 0;
        if (cur == '?') {

            if (left + 1 <= N / 2) {
                ans = (ans + solve(dp, s, left + 1, i + 1)) % MOD;
            }

            // right
            if (left > right) {
                ans = (ans + solve(dp, s, left, i + 1)) % MOD;
            }

        } else {
            if (cur == '(') {
                if (left + 1 <= N / 2) {
                    ans = (ans + solve(dp, s, left + 1, i + 1)) % MOD;
                }
            } else {
                if (left > right) {
                    ans = (ans + solve(dp, s, left, i + 1)) % MOD;
                }
            }
        }

        return dp[i][left] = (int) (ans % MOD);
    }
}
