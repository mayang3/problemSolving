package atcoder;

import java.util.Scanner;

/**
 * @author neo82
 */
public class abc346_d_GomamayoSequence {
    static long INF = (long) 1e10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        String S = scanner.next();

        int[] C = new int[N];

        for (int i = 0; i < N; i++) {
            C[i] = scanner.nextInt();
        }

        // 3가지의 상태가 필요 ,
        // 1. i 의 위치
        // 2. 현재 same 을 포함한 상태냐 포함 안한 상태냐가 필요
        // 3. 현재 위치의 수가 0 or 1 인지의 상태 필요
        // then, Time Complexity O(N * 2 * 2)
        long[][][] dp = new long[N][2][2];
        dp[0][1][0] = dp[0][1][1] = INF;

        if (S.charAt(0) == '0') {
            dp[0][0][1] = C[0];
        } else {
            dp[0][0][0] = C[0];
        }

        for (int i = 1; i < N; i++) {
            char c = S.charAt(i);

            dp[i][0][0] = dp[i - 1][0][1] + (c == '0' ? 0 : C[i]);
            dp[i][0][1] = dp[i - 1][0][0] + (c == '0' ? C[i] : 0);

            long m1 = dp[i - 1][1][1] + (c == '0' ? 0 : C[i]); // 현재를 0으로 만들어야 한다.
            long m2 = dp[i - 1][0][0] + (c == '0' ? 0 : C[i]); // 현재를 0으로 만들어야 한다.

            dp[i][1][0] = Math.min(m1, m2);

            long m3 = dp[i - 1][1][0] + (c == '0' ? C[i] : 0); // 현재를 1로 만들어야 한다.
            long m4 = dp[i - 1][0][1] + (c == '0' ? C[i] : 0); // 현재를 1로 만들어야 한다.

            dp[i][1][1] = Math.min(m3, m4);

        }

        System.out.println(Math.min(dp[N - 1][1][0], dp[N - 1][1][1]));
    }
}
