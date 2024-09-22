package atcoder;

import java.util.Scanner;

/**
 * @author neo82
 */
public class abc365_d_AtCoderJanken3 {
    static int MINUS_INF = -987654321;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        String s = scanner.next();

        // rock, paper, scissor in that order
        int[][] dp = new int[N][3];

        for (int i = 0; i < 3; i++) {
            dp[0][i] = getWinCount(s.charAt(0), i);
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                int win = getWinCount(s.charAt(i), j);
                int max = Math.max(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);

                if (win == MINUS_INF || max == MINUS_INF) {
                    dp[i][j] = MINUS_INF;
                } else {
                    dp[i][j] = max + win;
                }
            }
        }

        System.out.println(Math.max(Math.max(dp[N-1][0], dp[N-1][1]), dp[N-1][2]));
    }

    static int getWinCount(char aoki, int takahashi) {
        int num = aoki == 'R' ? 0 : aoki == 'P' ? 1 : 2;

        if (num == takahashi) {
            return 0;
        }

        switch (aoki) {
            case 'R':
                return takahashi == 1 ? 1 : MINUS_INF;
            case 'P':
                return takahashi == 2 ? 1 : MINUS_INF;
            case 'S':
                return takahashi == 0 ? 1 : MINUS_INF;
        }

        return 0;
    }

}
