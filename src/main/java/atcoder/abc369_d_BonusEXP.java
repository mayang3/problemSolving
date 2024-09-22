package atcoder;

import java.util.Scanner;

/**
 * @author neo82
 */
public class abc369_d_BonusEXP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        long[][][] dp = new long[N][2][2];

        dp[0][0][1] = A[0];

        for (int i = 1; i < A.length; i++) {
            dp[i][0][0] = Math.max(dp[i - 1][0][0], dp[i - 1][0][1]);
            dp[i][0][1] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1]) + A[i];
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1]);
            dp[i][1][1] = Math.max(dp[i - 1][0][0], dp[i - 1][0][1]) + (A[i] * 2L);
        }


        System.out.println(Math.max(Math.max(dp[N - 1][0][0], dp[N - 1][0][1]), Math.max(dp[N - 1][1][0], dp[N - 1][1][1])));
    }

}
